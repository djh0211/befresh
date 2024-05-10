package com.a307.befresh.module.domain.notification.service;

import com.a307.befresh.module.domain.food.Food;
import com.a307.befresh.module.domain.member.Member;
import com.a307.befresh.module.domain.member.repository.MemberRepository;
import com.a307.befresh.module.domain.memberToken.MemberToken;
import com.a307.befresh.module.domain.notification.dto.response.NotificationDetailRes;
import com.a307.befresh.module.domain.notification.repository.NotificationRepository;
import com.a307.befresh.module.domain.refrigerator.Refrigerator;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final MemberRepository memberRepository;
    private final NotificationRepository notificationRepository;

    @Override
    public void sendExpireNotification(List<Food> foodList) {
        for (Food food : foodList) {
            for (Member member : food.getRefrigerator().getMemberSet()) {
                for (MemberToken memberToken : member.getMemberTokenSet()) {
                    String title = food.getName() + "이 " + food.getRefresh().getName() + " 상태가 되었어요!";
                    String body = food.getName() + " 유통 기한 D" + ChronoUnit.DAYS.between(LocalDate.now(), food.getExpirationDate()) +
                            "\n유통 기한을 확인해주세요!";
                    String category = "expire";

                    sendMessage(memberToken, title, body, category);
                }
            }
        }
    }

    @Override
    public void sendRegisterNotification(Refrigerator refrigerator) {
        String title = "음식 등록 성공!";
        String body = "새로운 음식이 등록되었습니다.";
        String category = "register";

        com.a307.befresh.module.domain.notification.Notification notification = com.a307.befresh.module.domain.notification.Notification.createNotification(category, body, refrigerator);
        notificationRepository.save(notification);

        List<Member> memberList = memberRepository.findByRefrigerator(refrigerator);

        for (Member member : memberList) {
            Set<MemberToken> memberTokenSet = member.getMemberTokenSet();
            for (MemberToken memberToken : memberTokenSet) {
                sendMessage(memberToken, title, body, category);
            }
        }
    }

    @Override
    public void sentTempNotification1(String fcmToken) {
        Message message = Message.builder()
                .setToken(fcmToken)
                .setNotification(Notification.builder()
                        .setTitle("유통기한 알림")
                        .setBody("샘플 유통기한입니다.")
                        .build()
                )
                .build();

        log.info("message = " + message);

        try {
            String response = FirebaseMessaging.getInstance().send(message);
            log.info("[FCM send] " + response);
        } catch (FirebaseMessagingException e) {
            log.info("[FCM except]" + e.getMessage());
        }
    }

    @Override
    public void sentTempNotification2(String fcmToken) {
        // 메시지에 Notification 객체와 Data 객체를 함께 포함
        Message message = Message.builder()
                .setToken(fcmToken)
                .setNotification(Notification.builder()
                        .setTitle("유통기한 알림")
                        .setBody("샘플 유통기한입니다.\n 이 알림은 expire 카테고리에 해당하는 알림입니다.")
                        .build()
                )
                .putData("category", "expire")  // 카테고리 정보를 추가
                .build();

        log.info("message = " + message);

        try {
            // 메시지 전송
            String response = FirebaseMessaging.getInstance().send(message);
            log.info("[FCM send] " + response);
        } catch (FirebaseMessagingException e) {
            log.info("[FCM exception] " + e.getMessage());
        }
    }

    private static void sendMessage(MemberToken memberToken, String title, String body, String category) {
        Message message = Message.builder()
                .setToken(memberToken.getToken())
                .setNotification(Notification.builder()
                        .setTitle(title)
                        .setBody(body)
                        .build()
                )
                .putData("category", category)
                .build();

        try {
            String response = FirebaseMessaging.getInstance().send(message);
            log.info("[FCM send] " + response);
        } catch (FirebaseMessagingException e) {
            log.info("[FCM except]" + e.getMessage());
        }
    }
    @Override
    public List<NotificationDetailRes> getNotificationList(long refrigeratorId) {
        return notificationRepository.findNotificationList(refrigeratorId).stream()
                .map((notification -> NotificationDetailRes.builder()
                        .message(notification.getMessage())
                        .category(notification.getCategory())
                        .dateTime(notification.getRegDttm())
                        .build()))
                .toList();
    }

}
