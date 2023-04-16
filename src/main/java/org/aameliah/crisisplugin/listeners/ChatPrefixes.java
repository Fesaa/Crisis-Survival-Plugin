package org.aameliah.crisisplugin.listeners;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.aameliah.crisisplugin.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ChatPrefixes implements Listener {


    @EventHandler
    public void onAsyncChatEvent(AsyncChatEvent e) {
        Player player = e.getPlayer();
        e.setCancelled(true);
        Bukkit.getServer().sendMessage(
                this.worldComponent(player.getWorld().getName())
                .append(player.displayName()
                        .hoverEvent(HoverEvent.showText(
                                Component.text("Level: ", NamedTextColor.DARK_AQUA)
                            .append(Component.text(player.getLevel(), NamedTextColor.AQUA))
                            .appendNewline()
                            .append(Component.text("Deaths: ", NamedTextColor.DARK_AQUA))
                            .append(Component.text(player.getStatistic(Statistic.DEATHS), NamedTextColor.AQUA))
                            .appendNewline()
                            .append(Component.text("Mob kills: ", NamedTextColor.DARK_AQUA))
                            .append(Component.text(player.getStatistic(Statistic.MOB_KILLS), NamedTextColor.AQUA))
                )))
                .append(Component.text(": ", NamedTextColor.GRAY))
                .append(e.originalMessage().color(NamedTextColor.WHITE)));
    }

    private Component worldComponent(String name) {
        switch (name) {
            case "world" -> {
                return Utils.overworldPrefix;
            }
            case "world_nether" -> {
                return Utils.netherPrefix;
            }
            case "world_the_end" -> {
                return Utils.endPrefix;
            }
        }
        return Component.text("Unknown", NamedTextColor.WHITE);
    }
}
