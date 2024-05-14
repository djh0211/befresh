package com.a307.befresh.module.domain.food.service;

import com.a307.befresh.global.exception.BaseExceptionHandler;
import com.a307.befresh.global.exception.code.ErrorCode;
import com.a307.befresh.module.domain.Ftype.Ftype;
import com.a307.befresh.module.domain.Ftype.repository.FtypeRepository;
import com.a307.befresh.module.domain.container.Container;
import com.a307.befresh.module.domain.container.repository.ContainerRepository;
import com.a307.befresh.module.domain.elastic.ElasticDocument;
import com.a307.befresh.module.domain.elastic.repository.ElasticRepository;
import com.a307.befresh.module.domain.food.Food;
import com.a307.befresh.module.domain.food.dto.request.FoodRegisterReq;
import com.a307.befresh.module.domain.food.dto.request.FoodRegisterReqList;
import com.a307.befresh.module.domain.food.dto.request.FoodUpdateReq;
import com.a307.befresh.module.domain.food.dto.response.FoodDetailRes;
import com.a307.befresh.module.domain.food.dto.response.FoodFailRes;
import com.a307.befresh.module.domain.food.dto.response.FoodListDetailRes;
import com.a307.befresh.module.domain.food.repository.FoodRepository;
import com.a307.befresh.module.domain.notification.service.NotificationService;
import com.a307.befresh.module.domain.refresh.Refresh;
import com.a307.befresh.module.domain.refresh.repository.RefreshRepository;
import com.a307.befresh.module.domain.refrigerator.Refrigerator;
import com.a307.befresh.module.domain.refrigerator.repository.RefrigeratorRepository;
import jakarta.transaction.Transactional;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    @Value("${X-Naver-Client-Id}")
    private String clientId;

    @Value("${X-Naver-Client-Secret}")
    private String clientSecret;

    private final FoodRepository foodRepository;
    private final RefrigeratorRepository refrigeratorRepository;
    private final RefreshRepository refreshRepository;
    private final FtypeRepository ftypeRepository;
    private final ContainerRepository containerRepository;
    private final ElasticRepository elasticRepository;
    private final NotificationService notificationService;

    @Override
    public void blocking() throws InterruptedException {
        Thread.sleep(5000);
    }

    private void asyncRegisterFood(Refrigerator refrigerator, FoodRegisterReq foodRegisterReq) {

        log.info("[asyncRegisterFood] Food Name: {}, Current Thread: {}, isVirtual: {}",
            foodRegisterReq.name(), Thread.currentThread().toString(),
            Thread.currentThread().isVirtual());

        Ftype ftype = ftypeRepository.findById(foodRegisterReq.ftypeId())
            .orElse(new Ftype(3L, "기타"));

        String name = foodRegisterReq.name();
        LocalDate expirationDate = foodRegisterReq.expirationDate();
        boolean missRegistered = ftype.getId() == 2;

        // elastic search를 통한 음식 검색
        if (ftype.getId() != 2) {
            ElasticDocument elasticDocument = searchElasticSearch(foodRegisterReq.name());

            if (elasticDocument != null) {
                name = elasticDocument.getName();
                expirationDate = LocalDate.now().plusDays(elasticDocument.getExpirationDate());
                missRegistered = true;
            }
        }

        String image = extractImageLink(name);
        log.info("[FoodServiceImpl] image search api: {}", image);

        if (ftype.getId() == 1) {

            Food existFood = containerRepository.findByQrId(foodRegisterReq.qrId());

            if (existFood != null) {
                foodRepository.delete(existFood);
            }

            // 신선도 로직
            Refresh refresh = refreshRepository.findById(4L).orElse(new Refresh(4L, "측정전"));

            Container container = Container.createContainer(name, image, expirationDate, refresh,
                ftype, refrigerator, missRegistered, null, null, null, foodRegisterReq.qrId());

            containerRepository.save(container);

            log.debug("asyncRegisterFood method : container {} success", container.getFoodId());
        } else {

            Refresh refresh = refreshRepository.findById(1L).orElse(new Refresh(1L, "신선"));

            Food food = Food.createFood(name, image, expirationDate, refresh,
                ftype, refrigerator, missRegistered);

            foodRepository.save(food);

            log.debug("asyncRegisterFood method : food {} success", food.getFoodId());
        }
    }

    @Override
    @Async("virtualExecutor")
    @Retryable(retryFor = RuntimeException.class, maxAttempts = 3, backoff = @Backoff(delay = 2000))
    @KafkaListener(topics = "${kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void registerFood(FoodRegisterReqList foodRegisterReqList) {

        Optional<Refrigerator> refrigerator = refrigeratorRepository.findById(
            foodRegisterReqList.refrigeratorId());
        log.debug("registerFood method start : {} ", Thread.currentThread().toString());

        if (refrigerator.isEmpty()) {
            log.error("registerFood method refrigerator: {}이 없습니다.",
                foodRegisterReqList.refrigeratorId());
            throw new BaseExceptionHandler(ErrorCode.NOT_FOUND_REFRIGERATOR_EXCEPTION);
        }

        for (FoodRegisterReq foodRegisterReq : foodRegisterReqList.foodList()) {
            asyncRegisterFood(refrigerator.get(), foodRegisterReq);
        }

        notificationService.sendRegisterNotification(refrigerator.get());   // 등록 알림 전송
        log.debug("registerFood method success : {} ", Thread.currentThread().toString());
    }

    @Recover
    public void recoverFood(RuntimeException exception, FoodRegisterReqList foodRegisterReqList) {
        log.error(
            "[registerFood method] Recover method called after all retry attempt and still getting error");
        log.error("[{}] Error Message: {}", foodRegisterReqList.refrigeratorId(),
            exception.getMessage());

        notificationService.sendRegisterErrorNotification(refrigeratorRepository.findById(
            foodRegisterReqList.refrigeratorId()).orElseThrow());
    }

    public List<FoodListDetailRes> getFoodList(Long refrigeratorId) {

        List<Food> foodList = foodRepository.findByRefrigerator_Id(refrigeratorId);

        log.debug("getFoodList method read : {} ", foodList.size());
        List<FoodListDetailRes> foodListDetailResList = new ArrayList<>();

        for (Food food : foodList) {

            int elapsedTime = calculateElapsedTime(food.getRegDttm());
            double freshState = calculateFreshState(food.getFtype(), food.getRegDttm(),
                food.getExpirationDate());

            foodListDetailResList.add(
                FoodListDetailRes.builder().id(food.getFoodId()).name(food.getName())
                    .image(food.getImage()).regDttm(food.getRegDttm()).elapsedTime(elapsedTime)
                    .freshState(freshState).refresh(food.getRefresh().getName())
                    .ftype(food.getFtype().getName()).build());
        }

        log.debug("getFoodList method success");

        return foodListDetailResList;
    }

    @Override
    public FoodDetailRes getFoodDetail(long foodId) {
        return containerRepository.findById(foodId).map(this::createFoodDetailFromContainer)
            .orElseGet(() -> createFoodDetailFromFood(foodId));
    }

    @Override
    public List<FoodFailRes> getFoodFailList(long refrigeratorId) {
        return foodRepository.findFailFood(refrigeratorId).stream().map(
            food -> FoodFailRes.builder().id(food.getFoodId()).name(food.getName())
                .image(food.getImage()).regDttm(food.getRegDttm()).build()).toList();
    }

    @Override
    @Transactional
    public void removeFood(Long foodId) {
        Food food = foodRepository.findById(foodId).orElseThrow();
        foodRepository.delete(food);
    }

    @Override
    @Transactional
    public void updateFood(FoodUpdateReq foodUpdateReq) {
        Food food = foodRepository.findById(foodUpdateReq.foodId()).orElseThrow();

        String image = extractImageLink(foodUpdateReq.name());

        food.setName(foodUpdateReq.name());
        food.setImage(image);
        food.setExpirationDate(foodUpdateReq.expirationDate());
        foodRepository.save(food);
    }

    @Override
    public long calculateRefresh(Food food) {
        if (food.getExpirationDate() == null) {
            if (food.getFtype().getId() == 1) {
                return 4L;
            } else {
                return 5L;
            }
        }

        long remain = food.getRegDttm().toLocalDate().until(LocalDate.now(), ChronoUnit.DAYS);
        long tot = food.getRegDttm().toLocalDate().until(food.getExpirationDate(), ChronoUnit.DAYS);
        double ratio;

        if (tot <= 0) {
            ratio = 1;
        } else {
            ratio = (double) remain / tot;
        }

        if (ratio < 0.5) {
            return 1L;
        } else if (ratio < 1) {
            return 2L;
        } else {
            return 3L;
        }
    }

    private FoodDetailRes createFoodDetailFromContainer(Container container) {
        int elapsedTime = calculateElapsedTime(container.getRegDttm());

        double freshState = calculateFreshState(container.getFtype(), container.getRegDttm(),
            container.getExpirationDate()); // 추후 계산 로직 추가

        return FoodDetailRes.builder().id(container.getFoodId()).name(container.getName())
            .image(container.getImage()).expirationDate(container.getExpirationDate())
            .regDttm(container.getRegDttm()).elapsedTime(elapsedTime).freshState(freshState)
            .refresh(container.getRefresh().getName()).ftype(container.getFtype().getName())
            .temperature(container.getTemperature()).humidity(container.getHumidity()).build();
    }

    private FoodDetailRes createFoodDetailFromFood(long foodId) {
        Food food = foodRepository.findById(foodId).orElseThrow();
        int elapsedTime = calculateElapsedTime(food.getRegDttm());

        double freshState = calculateFreshState(food.getFtype(), food.getRegDttm(),
            food.getExpirationDate()); // TODO : 추후 계산 로직 추가

        return FoodDetailRes.builder().id(food.getFoodId()).name(food.getName())
            .image(food.getImage()).expirationDate(food.getExpirationDate())
            .regDttm(food.getRegDttm()).elapsedTime(elapsedTime).freshState(freshState)
            .refresh(food.getRefresh().getName()).ftype(food.getFtype().getName()).build();
    }

    private int calculateElapsedTime(LocalDateTime registrationDateTime) {
        return Period.between(registrationDateTime.toLocalDate(), LocalDate.now()).getDays();
    }

    private double calculateFreshState(Ftype ftype, LocalDateTime registrationDateTime,
        LocalDate exprationDate) {

        if (exprationDate == null) {
            return 100;
        }

        // TODO: 용기는 나중에 다르게 설정
        int totalDays = Period.between(registrationDateTime.toLocalDate(), exprationDate).getDays();
        int remindDays = Period.between(LocalDate.now(), exprationDate).getDays();

        double freshState = 0.0;

        if (remindDays > 0) {
            freshState = (double) remindDays / totalDays * 100;
        }

        return freshState;
    }

    // elastic search 조회
    private ElasticDocument searchElasticSearch(String name) {
        List<ElasticDocument> elasticDocuments = elasticRepository.searchBefreshByName(name);

        if (elasticDocuments != null && !elasticDocuments.isEmpty()) {
            ElasticDocument top = elasticDocuments.getFirst();

            log.info("Elastic Search - name: {}, expiration_date: {}, score: {}", top.getName(),
                top.getExpirationDate(), top.getScore());

            for (ElasticDocument elasticDocument : elasticDocuments) {
                if (elasticDocument.getName().replaceAll("\\s+", "")
                    .equals(name.replaceAll("\\s+", ""))) {

                    log.info("Elastic Search - name: {}, expiration_date: {}, score: {}",
                        elasticDocument.getName(), elasticDocument.getExpirationDate(),
                        elasticDocument.getScore());
                    return elasticDocument;
                }
            }
            return top;
        }
        return null;
    }

    private static String get(String apiUrl, Map<String, String> requestHeaders) {
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 오류 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private static HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private static String readBody(InputStream body) {
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
        }
    }

    private String extractImageLink(String name) {
        //이미지 검색 관련
        String text = URLEncoder.encode(name, StandardCharsets.UTF_8);

        String apiURL = "https://openapi.naver.com/v1/search/image?query=" + text
            + "&display=1&start=1&sort=sim";

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL, requestHeaders);

        String image = null;
        int startIndex = responseBody.indexOf("\"thumbnail\":") + "\"thumbnail\":".length();
        int endIndex = responseBody.indexOf("\",", startIndex);

        if (endIndex != -1) {
            image = responseBody.substring(startIndex, endIndex);
        }
        assert image != null;
        image = image.replaceAll("^\"|\"$", "");

        return image;
    }


}
