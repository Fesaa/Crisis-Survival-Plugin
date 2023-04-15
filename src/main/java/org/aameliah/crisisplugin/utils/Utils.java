package org.aameliah.crisisplugin.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

public class Utils {

    public static Component getCrisisServerColour() {
        return Component.text("Crisis", NamedTextColor.LIGHT_PURPLE)
                .append(Component.text(" server", NamedTextColor.DARK_PURPLE))
                .append(Component.text("!!", NamedTextColor.DARK_AQUA));
    }

    public static Component playerComponent(Player player) {
        return Component.text(player.getName(), NamedTextColor.DARK_AQUA);
    }
}
