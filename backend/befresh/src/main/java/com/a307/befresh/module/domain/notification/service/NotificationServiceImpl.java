package com.a307.befresh.module.domain.notification.service;

import com.a307.befresh.module.domain.notification.dto.response.NotificationRegisterRes;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {

    @Override
    @PostConstruct
    public void sendFreshWarnMessage() {
        NotificationRegisterRes test = NotificationRegisterRes.builder()
                .token("fLW0wNSODS-Sw4B0_Ufj3f:APA91bENH5knCjgl_aXqljQpaq6glOI_a5YxwdFKdm4v5AygUwe9tvHaEgiZdUYTPa3jf9XidVcL0MupNie9AUYYI2aExTYiN6iyzD05DTvvrrnn3qSdZ-3dQhLZqwYTNbL3eqcnsLKU")
                .title("title")
                .body("body")
                .build();

        Message message = Message.builder()
                .setToken(test.token())
                .setNotification(Notification.builder()
                        .setTitle(test.title())
                        .setBody(test.body())
                        .build()
                )
                .build();

        try {
            String response = FirebaseMessaging.getInstance().send(message);
            log.info("[FCM send] " + response);
        } catch (FirebaseMessagingException e) {
            log.info("[FCM except]" + e.getMessage());
        }
    }
}
