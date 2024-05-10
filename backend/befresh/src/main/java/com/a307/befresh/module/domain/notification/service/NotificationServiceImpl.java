package com.a307.befresh.module.domain.notification.service;

import com.a307.befresh.module.domain.food.Food;
import com.a307.befresh.module.domain.member.Member;
import com.a307.befresh.module.domain.member.repository.MemberRepository;
import com.a307.befresh.module.domain.memberToken.MemberToken;
import com.a307.befresh.module.domain.notification.dto.response.NotificationDetailRes;
import com.a307.befresh.module.domain.notification.repository.NotificationRepository;
import com.a307.befresh.module.domain.refrigerator.Refrigerator;
import com.a307.befresh.module.domain.refrigerator.repository.RefrigeratorRepository;
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
    private final RefrigeratorRepository refrigeratorRepository;

    @Override
    public List<NotificationDetailRes> getNotificationList(long refrigeratorId) {
        return notificationRepository.findNotificationList(refrigeratorId).stream()
                .map((notification -> NotificationDetailRes.builder()
                        .notificationId(notification.getNotificationId())
                        .message(notification.getMessage())
                        .category(notification.getCategory())
                        .dateTime(notification.getRegDttm())
                        .build()))
                .toList();
    }

    @Override
    public void deleteNotidication(Long notificationId) {
        notificationRepository.delete(notificationRepository.findById(notificationId).orElseThrow());
    }

    @Override
    public void sendExpireNotification(List<Food> foodList, String category) {
        for (Food food : foodList) {
            for (Member member : food.getRefrigerator().getMemberSet()) {
                for (MemberToken memberToken : member.getMemberTokenSet()) {
                    String title = food.getName() + "이 " + food.getRefresh().getName() + " 상태가 되었어요!";
                    String body = food.getName() + " 유통 기한 D" + ChronoUnit.DAYS.between(LocalDate.now(), food.getExpirationDate()) +
                            "\n유통 기한을 확인해주세요!";

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
    public void sendTmpNotification(String category, Long refrigeratorId) {
        Refrigerator refrigerator = refrigeratorRepository.findById(refrigeratorId).orElseThrow();
        List<Member> memberList = memberRepository.findByRefrigerator(refrigerator);
        String title = "";
        String body = "";

        for (Member member : memberList) {
            for (MemberToken memberToken : member.getMemberTokenSet()) {
                if (category.equals("register")) {
                    title = "음식 등록 성공!";
                    body = "새로운 음식이 등록되었습니다.";
                    sendMessage(memberToken, title, body, category);
                } else if (category.equals("refresh")) {
                    title = "임시 음식이 주의 상태가 되었어요!";
                    body = "임시 음식의 신선도를 확인해주세요!";
                    sendMessage(memberToken, title, body, category);
                } else if (category.equals("expire")) {
                    title = "임시 음식이 주의 상태가 되었어요!";
                    body = "임시 음식 유통 기한 D-3\n유통 기한을 확인해주세요!";
                    sendMessage(memberToken, title, body, category);
                }
            }
        }

        com.a307.befresh.module.domain.notification.Notification notification = com.a307.befresh.module.domain.notification.Notification.createNotification(category, body, refrigerator);
        notificationRepository.save(notification);
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
}
