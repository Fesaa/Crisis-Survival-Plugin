package org.aameliah.crisisplugin.listeners;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.aameliah.crisisplugin.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

import java.util.HashMap;

public class PlayerListener implements Listener {

    private final HashMap<Player, Long> leaveBedMessages = new HashMap<>();
    private final HashMap<Player, Long> enterBedMessages = new HashMap<>();

    @EventHandler
    public void onPlayerLoginEvent(PlayerLoginEvent e) {
        if (!e.getResult().equals(PlayerLoginEvent.Result.KICK_WHITELIST)) {
            return;
        }
        
        Bukkit.getServer().sendMessage(
                Component.text(e.getPlayer().getName(), NamedTextColor.DARK_AQUA)
                        .append(Component.text(" just tried to join but isn't whitelisted. ", NamedTextColor.AQUA))
                        .append(Component.text("LOL", NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD)));
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Server server = Bukkit.getServer();
        Component joinMessage = Utils.playerComponent(e.getPlayer())
                .append(Component.text(" has joined the ", NamedTextColor.AQUA))
                .append(Utils.getCrisisServerColour())
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
        Component quitMessage = Utils.playerComponent(e.getPlayer())
                .append(Component.text(" has left the ", NamedTextColor.AQUA))
                .append(Utils.getCrisisServerColour())
                .append(Component.text(" ", NamedTextColor.AQUA))
                .append(Component.text(server.getOnlinePlayers().size() - 1, NamedTextColor.AQUA))
                .append(Component.text("/", NamedTextColor.AQUA))
                .append(Component.text(server.getMaxPlayers(), NamedTextColor.AQUA))
                .append(Component.text(" players online.", NamedTextColor.AQUA));
        e.quitMessage(quitMessage);
    }

    @EventHandler
    public void onPlayerBedEnterEvent(PlayerBedEnterEvent e) {
        if (!e.getBedEnterResult().equals(PlayerBedEnterEvent.BedEnterResult.OK)) {
            return;
        }
        Server server = Bukkit.getServer();

        long now = System.currentTimeMillis();
        long lastEnter = this.enterBedMessages.get(e.getPlayer());
        if (now - 1000*10 < lastEnter) {
            return;
        }
        this.enterBedMessages.put(e.getPlayer(), now);

        int onlinePlayers = server.getOnlinePlayers().size();
        int sleepingPlayers = 1;
        for (Player player : server.getOnlinePlayers()) {
            if (player.isSleeping()) {
                sleepingPlayers++;
            }
        }
        server.sendMessage(
                Utils.playerComponent(e.getPlayer())
                        .append(Component.text(" went to sleep! "))
                        .append(Component.text(sleepingPlayers + " of " + onlinePlayers + " sleeping!"))
                        .color(NamedTextColor.BLUE)
        );
    }

    @EventHandler
    public void onPlayerBedLeaveEvent(PlayerBedLeaveEvent e) {
        long now = System.currentTimeMillis();
        long lastEnter = this.leaveBedMessages.get(e.getPlayer());
        if (now - 1000*10 < lastEnter) {
            return;
        }
        this.leaveBedMessages.put(e.getPlayer(), now);


        Server server = Bukkit.getServer();
        int onlinePlayers = server.getOnlinePlayers().size();
        int sleepingPlayers = -1;
        for (Player player : server.getOnlinePlayers()) {
            if (player.isSleeping()) {
                sleepingPlayers++;
            }
        }
        server.sendMessage(
                Utils.playerComponent(e.getPlayer())
                        .append(Component.text(" left their bed! "))
                        .append(Component.text(sleepingPlayers + " of " + onlinePlayers + " sleeping!"))
                        .color(NamedTextColor.BLUE)
        );
    }

    @EventHandler
    public void onPlayerLevelChangeEvent(PlayerLevelChangeEvent e) {
        if (e.getNewLevel() % 5 != 0 || e.getNewLevel() < e.getOldLevel()) {
            return;
        }
        Bukkit.getServer().sendMessage(
                Utils.playerComponent(e.getPlayer())
                        .append(Component.text(" became level ", NamedTextColor.AQUA))
                        .append(Component.text(e.getNewLevel(), NamedTextColor.LIGHT_PURPLE))
                        .append(Component.text("!", NamedTextColor.AQUA))
        );
    }

    @EventHandler
    public void onPlayerChangedWorldEvent(PlayerChangedWorldEvent e) {
        Bukkit.getServer().sendMessage(
                Utils.playerComponent(e.getPlayer())
                        .append(Component.text( " is now in the ", NamedTextColor.AQUA))
                        .append(Component.text(this.worldToReadableString(e.getPlayer().getWorld().getName()), NamedTextColor.LIGHT_PURPLE))
                        .append(Component.text("!", NamedTextColor.AQUA))
        );
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
