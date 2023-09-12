package se.lexicon.model;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Notification {

    private final String requestId;
    private final LocalDateTime createDateTime;

    public Notification() {
        this.requestId = UUID.randomUUID().toString();
        this.createDateTime = LocalDateTime.now();
    }

    public abstract void send();

    public abstract String summary();

    public String getRequestId() {
        return requestId;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }
}
