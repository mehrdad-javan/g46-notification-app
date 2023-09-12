package se.lexicon.data;

import se.lexicon.exception.DataNotFoundException;
import se.lexicon.model.Notification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NotificationManagerImpl implements NotificationManager {

    private List<Notification> notificationList = new ArrayList<>();

    @Override
    public void createNotification(Notification notification) {
        if (notification == null) throw new IllegalArgumentException("Notification object was null.");
        notificationList.add(notification);
    }

    @Override
    public Notification findNotification(String requestId) {
        return notificationList.stream()
                .filter(notification -> notification.getRequestId().equals(requestId))
                .findFirst()
                .orElseThrow(() -> new DataNotFoundException("Notification data not found.", requestId));
    }

    @Override
    public Collection<Notification> findAll() {
        return new ArrayList<>(notificationList);
    }
}
