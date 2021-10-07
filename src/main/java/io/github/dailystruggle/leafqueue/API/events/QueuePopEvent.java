package io.github.dailystruggle.leafqueue.API.events;

import io.github.dailystruggle.leafqueue.API.types.PlayerData;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class QueuePopEvent extends Event {
    private static final HandlerList HANDLERS_LIST = new HandlerList();

    private final PlayerData playerData;

    public QueuePopEvent(PlayerData playerData) {
        this.playerData = playerData;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }

    public PlayerData getPlayerData() {
        return playerData;
    }
}
