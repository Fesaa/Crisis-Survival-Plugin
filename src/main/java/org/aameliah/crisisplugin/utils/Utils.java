package org.aameliah.crisisplugin.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;

public class Utils {
    public static final Component overworldPrefix = Component.text("[", NamedTextColor.GRAY)
            .append(Component.text("Over", TextColor.color(46,139,87)))
            .append(Component.text("world", TextColor.color(0,191,255)))
            .append(Component.text("] ", NamedTextColor.GRAY));

    public static final Component netherPrefix = Component.text("[", NamedTextColor.GRAY)
            .append(Component.text("Nether", TextColor.color(178,34,349)))
            .append(Component.text("] ", NamedTextColor.GRAY));

    public static final Component endPrefix = Component.text("[", NamedTextColor.GRAY)
            .append(Component.text("The", TextColor.color(105,105,105)))
            .append(Component.text(" End", TextColor.color(128,0,128)))
            .append(Component.text("] ", NamedTextColor.GRAY));

    public static Component getCrisisServerColour() {
        return Component.text("Crisis", NamedTextColor.LIGHT_PURPLE)
                .append(Component.text(" server", NamedTextColor.DARK_PURPLE))
                .append(Component.text("!!", NamedTextColor.DARK_AQUA));
    }

    public static Component playerComponent(Player player) {
        return Component.text(player.getName(), NamedTextColor.DARK_AQUA);
    }
}
