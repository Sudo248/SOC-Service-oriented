package com.sudo248.notification.service;

import com.sudo248.notification.repository.entity.Notification;
import com.sudo248.notification.repository.entity.User;

public interface NotificationService {

    static final String promotionTopic = "public.promotion";

    String sendNotificationToTopic(String userId, String topic, Notification notification);

    String sendNotification(String userId, String otherId, Notification notification);

    boolean saveUser(User user);

    boolean saveToken(String userId, String token);

}
