package io.github.dailystruggle.leafqueue.spigotEvents;

import io.github.dailystruggle.leafqueue.API.events.QueuePopEvent;
import io.github.dailystruggle.leafqueue.LeafQueue;
import io.github.dailystruggle.leafqueue.API.types.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

public class PlayerQuit implements Listener {
    @EventHandler(priority = EventPriority.LOW)
    public void onQuit(PlayerQuitEvent event) {
        new PopQueue().runTaskAsynchronously(LeafQueue.getInstance());
    }

    private class PopQueue extends BukkitRunnable {
        @Override
        public void run() {
            ConcurrentLinkedQueue<PlayerData> mainQueue = LeafQueue.getMainQueue();
            PlayerData playerData = mainQueue.poll();
            if(playerData == null) return;
            playerData.setPopTime(System.currentTimeMillis());
            LeafQueue.getAllowedPlayers().put(playerData.getUuid(),playerData);
            Bukkit.getPluginManager().callEvent(new QueuePopEvent(playerData));
            new RemovePlayer(playerData).runTaskLaterAsynchronously(LeafQueue.getInstance(),200);
            for(Map.Entry<UUID,PlayerData> entry : LeafQueue.getQueuedPlayers().entrySet()) {
                entry.getValue().decrementPosition();
            }
        }
    }

    private class RemovePlayer extends BukkitRunnable {
        private final PlayerData playerData;

        private RemovePlayer(PlayerData playerData) {
            this.playerData = playerData;
        }

        @Override
        public void run() {
            if(LeafQueue.getAllowedPlayers().containsKey(playerData.getUuid())) {
                LeafQueue.getAllowedPlayers().remove(playerData.getUuid());
                if(LeafQueue.getMainQueue().size()>0) {
                    new PopQueue().runTaskAsynchronously(LeafQueue.getInstance());
                }
            }
        }
    }
}
