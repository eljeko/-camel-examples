package org.apache.camel.example.service;

import org.apache.camel.example.model.Notification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("notificationService")
public class NotificationService {

    public List<Notification> getNotifications(String id) {
        ArrayList<Notification> notifications = new ArrayList<Notification>();
        notifications.add(new Notification("Watch out!"));
        notifications.add(new Notification("Not so good"));
        notifications.add(new Notification("Done"));
        return notifications;
    }
}
