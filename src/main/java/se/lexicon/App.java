package se.lexicon;

import se.lexicon.data.NotificationManager;
import se.lexicon.data.NotificationManagerImpl;
import se.lexicon.exception.NotificationExceptionHandler;
import se.lexicon.model.EmailNotification;
import se.lexicon.model.Notification;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        try {
            NotificationManager notificationManager = new NotificationManagerImpl();
            Notification notification1 = new EmailNotification("mehrdad.javan@lexicon.se", "Meeting Reminder", "Don't forget the meeting at 15:00");
            notification1.send();
            notificationManager.createNotification(notification1);

            notificationManager.findAll().forEach(notification -> System.out.println(notification.summary()));

        } catch (Exception e) {
            NotificationExceptionHandler.handleException(e);
        }
    }
}
