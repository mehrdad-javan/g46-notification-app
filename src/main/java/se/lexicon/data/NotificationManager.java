package se.lexicon.data;

import se.lexicon.model.Notification;

import java.util.Collection;

public interface NotificationManager {

    void createNotification(Notification notification);

    Notification findNotification(String requestId);

    Collection<Notification> findAll();

    //...
}
