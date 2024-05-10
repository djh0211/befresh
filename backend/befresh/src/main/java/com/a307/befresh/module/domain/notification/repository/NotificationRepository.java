package com.a307.befresh.module.domain.notification.repository;

import com.a307.befresh.module.domain.notification.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long>, NotificationRepositoryCustom {
    List<Notification> findAllByRefrigerator_Id(long refrigeratorId);
}
