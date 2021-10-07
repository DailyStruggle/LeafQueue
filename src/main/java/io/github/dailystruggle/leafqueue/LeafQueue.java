package io.github.dailystruggle.leafqueue;

import io.github.dailystruggle.leafqueue.API.types.PlayerData;
import io.github.dailystruggle.leafqueue.spigotEvents.PlayerLogin;
import io.github.dailystruggle.leafqueue.spigotEvents.PlayerQuit;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public final class LeafQueue extends JavaPlugin {
    private static LeafQueue instance;
    private static final ConcurrentLinkedQueue<PlayerData> mainQueue = new ConcurrentLinkedQueue<>();
    private static final Map<UUID,PlayerData> queuedPlayers = new HashMap<>();
    private static final Map<UUID,PlayerData> allowedPlayers = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        Bukkit.getPluginManager().registerEvents(new PlayerLogin(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuit(),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static LeafQueue getInstance() {
        return instance;
    }

    public static ConcurrentLinkedQueue<PlayerData> getMainQueue() {
        return mainQueue;
    }

    public static Map<UUID, PlayerData> getAllowedPlayers() {
        return allowedPlayers;
    }

    public static Map<UUID, PlayerData> getQueuedPlayers() {
        return queuedPlayers;
    }
}
