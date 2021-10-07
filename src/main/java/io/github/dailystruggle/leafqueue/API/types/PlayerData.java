package io.github.dailystruggle.leafqueue.API.types;

import java.util.UUID;

public class PlayerData {
    private final UUID uuid;
    private final String name;
    private Long lastAttempt;
    private Long popTime;
    private int position;

    public PlayerData(UUID uuid, String name, int position) {
        this.uuid = uuid;
        this.name = name;
        this.lastAttempt = System.currentTimeMillis();
        this.position = position;
        this.popTime = 0L;
    }

    public void setLastAttempt(Long lastAttempt) {
        this.lastAttempt = lastAttempt;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public Long getLastAttempt() {
        return lastAttempt;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void decrementPosition() {
        this.position--;
    }

    public Long getPopTime() {
        return popTime;
    }

    public void setPopTime(Long popTime) {
        this.popTime = popTime;
    }
}
