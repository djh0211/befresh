package com.a307.befresh.module.domain.food.service;

import com.a307.befresh.module.domain.Ftype.Ftype;
import com.a307.befresh.module.domain.Ftype.repository.FtypeRepository;
import com.a307.befresh.module.domain.food.Food;
import com.a307.befresh.module.domain.food.dto.request.FoodRegisterReq;
import com.a307.befresh.module.domain.food.repository.FoodRepository;
import com.a307.befresh.module.domain.refresh.repository.RefreshRepository;
import com.a307.befresh.module.domain.refrigerator.Refrigerator;
import com.a307.befresh.module.domain.refrigerator.repository.RefrigeratorRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;
    private final RefrigeratorRepository refrigeratorRepository;
    private final RefreshRepository refreshRepository;
    private final FtypeRepository ftypeRepository;

    @Override
    public void blocking() throws InterruptedException {
        Thread.sleep(5000);
    }

    @Override
    public String registerFood(FoodRegisterReq foodRegisterReq, long member) {
        Optional<Refrigerator> refrigerator = refrigeratorRepository.findById(
            foodRegisterReq.refrigeratorId());

        if (refrigerator.isEmpty()) {
            return null;
        }

        Optional<Ftype> ftype = ftypeRepository.findById(foodRegisterReq.ftypeId());

        if (ftype.isEmpty()) {
            return null;
        }

        Food food = Food.createFood(foodRegisterReq.name(), foodRegisterReq.expirationDate(), null,
            ftype.get(), refrigerator.get());

        foodRepository.save(food);

        return food.getName();
    }
}
