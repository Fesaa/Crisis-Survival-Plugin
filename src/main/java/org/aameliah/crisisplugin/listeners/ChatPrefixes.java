package org.aameliah.crisisplugin.listeners;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.event.HoverEventSource;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public class ChatPrefixes implements Listener {


    @EventHandler
    public void onAsyncChatEvent(AsyncChatEvent e) {
        e.setCancelled(true);
        Bukkit.getServer().sendMessage(
                Component.text("[", NamedTextColor.WHITE)
                .append(this.worldComponent(e.getPlayer().getWorld().getName()))
                .append(Component.text("] ", NamedTextColor.WHITE))
                .append(this.playerComponent(e.getPlayer()))
                .append(e.originalMessage()));
    }

    private Component playerComponent(Player player) {
        return Component.text(player.getName(), NamedTextColor.DARK_AQUA)
                .hoverEvent(HoverEvent.showText(
                        Component.text("Level: ", NamedTextColor.DARK_AQUA)
                                .append(Component.text(player.getLevel(), NamedTextColor.AQUA))
                                .append(Component.newline())
                                .append(Component.text("Deaths: ", NamedTextColor.DARK_AQUA))
                                .append(Component.text(player.getStatistic(Statistic.DEATHS), NamedTextColor.AQUA))
                                .append(Component.newline())
                                .append(Component.text("Mob kills: ", NamedTextColor.DARK_AQUA))
                                .append(Component.text(player.getStatistic(Statistic.MOB_KILLS), NamedTextColor.AQUA))
                ))
                .append(Component.text(": ", NamedTextColor.GRAY));
    }


    private Component worldComponent(String name) {
        switch (name) {
            case "world" -> {
                return Component.text("Overworld", NamedTextColor.GREEN);
            }
            case "world_nether" -> {
                return Component.text("Nether", NamedTextColor.DARK_RED);
            }
            case "world_end" -> {
                return Component.text("The End", NamedTextColor.DARK_PURPLE);
            }
        }
        return Component.text("Unknown", NamedTextColor.WHITE);
    }
}
