package org.aameliah.crisisplugin.listeners;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.aameliah.crisisplugin.CrisisPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Server server = Bukkit.getServer();
        Component joinMessage = this.playerComponent(e.getPlayer())
                .append(Component.text(" has joined the ", NamedTextColor.AQUA))
                .append(CrisisPlugin.getCrisisServerColour())
                .append(Component.text(" ", NamedTextColor.AQUA))
                .append(Component.text(server.getOnlinePlayers().size(), NamedTextColor.AQUA))
                .append(Component.text("/", NamedTextColor.AQUA))
                .append(Component.text(server.getMaxPlayers(), NamedTextColor.AQUA))
                .append(Component.text(" players online.", NamedTextColor.AQUA));
        e.joinMessage(joinMessage);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Server server = Bukkit.getServer();
        Component quitMessage = this.playerComponent(e.getPlayer())
                .append(Component.text(" has left the ", NamedTextColor.AQUA))
                .append(CrisisPlugin.getCrisisServerColour())
                .append(Component.text(" ", NamedTextColor.AQUA))
                .append(Component.text(server.getOnlinePlayers().size() - 1, NamedTextColor.AQUA))
                .append(Component.text("/", NamedTextColor.AQUA))
                .append(Component.text(server.getMaxPlayers(), NamedTextColor.AQUA))
                .append(Component.text(" players online.", NamedTextColor.AQUA));
        e.quitMessage(quitMessage);
    }

    @EventHandler
    public void onPlayerBedEnterEvent(PlayerBedEnterEvent e) {
        Server server = Bukkit.getServer();

        int onlinePlayers = server.getOnlinePlayers().size();
        int sleepingPlayers = 1;
        for (Player player : server.getOnlinePlayers()) {
            if (player.isSleeping()) {
                sleepingPlayers++;
            }
        }
        server.sendMessage(
                this.playerComponent(e.getPlayer())
                        .append(Component.text(" went to sleep! "))
                        .append(Component.text(sleepingPlayers + " of " + onlinePlayers + " sleeping!"))
                        .color(NamedTextColor.BLUE)
        );
    }

    @EventHandler
    public void onPlayerBedLeaveEvent(PlayerBedLeaveEvent e) {
        Server server = Bukkit.getServer();
        int onlinePlayers = server.getOnlinePlayers().size();
        int sleepingPlayers = -1;
        for (Player player : server.getOnlinePlayers()) {
            if (player.isSleeping()) {
                sleepingPlayers++;
            }
        }
        server.sendMessage(
                this.playerComponent(e.getPlayer())
                        .append(Component.text(" left their bed! "))
                        .append(Component.text(sleepingPlayers + " of " + onlinePlayers + " sleeping!"))
                        .color(NamedTextColor.BLUE)
        );
    }

    @EventHandler
    public void onPlayerLevelChangeEvent(PlayerLevelChangeEvent e) {
        if (e.getNewLevel() % 10 != 0) {
            return;
        }

        Bukkit.getServer().sendMessage(
                this.playerComponent(e.getPlayer())
                        .append(Component.text(" leveled up to ", NamedTextColor.AQUA))
                        .append(Component.text(e.getNewLevel(), NamedTextColor.LIGHT_PURPLE))
                        .append(Component.text("!", NamedTextColor.AQUA))
        );
    }

    @EventHandler
    public void onPlayerChangedWorldEvent(PlayerChangedWorldEvent e) {
        Bukkit.getServer().sendMessage(
                this.playerComponent(e.getPlayer())
                        .append(Component.text( " is now in the ", NamedTextColor.AQUA))
                        .append(Component.text(this.worldToReadableString(e.getPlayer().getWorld().getName()), NamedTextColor.LIGHT_PURPLE))
                        .append(Component.text("!", NamedTextColor.AQUA))
        );
    }

    private Component playerComponent(Player player) {
        return Component.text(player.getName(), NamedTextColor.DARK_AQUA);
    }

    private String worldToReadableString(String name) {
        switch (name) {
            case "world" -> {
                return "Overworld";
            }
            case "world_nether" -> {
                return "Nether";
            }
            case "world_end" -> {
                return "The End";
            }
        }
        return "Unknown";
    }

}
