package io.github.dailystruggle.leafqueue.spigotEvents;

import io.github.dailystruggle.leafqueue.LeafQueue;
import io.github.dailystruggle.leafqueue.API.types.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.UUID;

public class PlayerLogin implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void onLogin(PlayerLoginEvent event) {
        if(Bukkit.getOnlinePlayers().size() + LeafQueue.getAllowedPlayers().size() < 1) {
            return;
        }

        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        if(player.hasPermission("leafqueue.bypass")) return;

        if(LeafQueue.getAllowedPlayers().containsKey(uuid)) {
            LeafQueue.getAllowedPlayers().remove(uuid);
            return;
        }

        if(!LeafQueue.getQueuedPlayers().containsKey(uuid)) {
            PlayerData playerData = new PlayerData(
                    player.getUniqueId(),
                    player.getName(),
                    LeafQueue.getMainQueue().size()+1);
            LeafQueue.getMainQueue().add(playerData);
            LeafQueue.getQueuedPlayers().put(uuid,playerData);
        }
        event.disallow(PlayerLoginEvent.Result.KICK_OTHER,"too many players! your spot: "
                + LeafQueue.getQueuedPlayers().get(uuid).getPosition());
    }
}
