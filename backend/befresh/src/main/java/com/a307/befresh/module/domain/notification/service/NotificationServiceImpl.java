package com.a307.befresh.module.domain.notification.service;

import com.a307.befresh.module.domain.food.Food;
import com.a307.befresh.module.domain.member.Member;
import com.a307.befresh.module.domain.member.repository.MemberRepository;
import com.a307.befresh.module.domain.memberToken.MemberToken;
import com.a307.befresh.module.domain.notification.dto.response.NotificationRegisterRes;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final MemberRepository memberRepository;

    @Override
    public void sendExpireNotification(long refrigeratorId, List<Food> foodList, int daysBefore) {
        List<Member> memberList = memberRepository.findByRefrigerator_Id(refrigeratorId);
        log.info("memberList = " + memberList);

        for (Member member : memberList) {
            Set<MemberToken> memberTokenSet = member.getMemberTokenSet();
            log.info("memberTokenSet = " + memberTokenSet);
            for (MemberToken memberToken : memberTokenSet) {
                for (Food food : foodList) {
                    Message message = Message.builder()
                            .setToken(memberToken.getToken())
                            .setNotification(Notification.builder()
                                    .setTitle("유통기한 알림")
                                    .setBody(food.getName() + "의 유통기한이 " + daysBefore + "일 남았습니다!")
                                    .build()
                            )
                            .build();

                    log.info("message = " + message);

                    try {
                        String response = FirebaseMessaging.getInstance().send(message);
                        log.info("[FCM send] " + response);
                    }
                    catch (FirebaseMessagingException e) {
                        log.info("[FCM except]" + e.getMessage());
                    }
                }
            }
        }
    }
}
