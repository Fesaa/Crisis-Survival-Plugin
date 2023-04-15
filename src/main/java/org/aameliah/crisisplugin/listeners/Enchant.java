package org.aameliah.crisisplugin.listeners;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.aameliah.crisisplugin.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class Enchant implements Listener {

    @EventHandler
    public void onEnchantItemEvent(EnchantItemEvent e) {
        int size = e.getEnchantsToAdd().size();
        if (size < 3) {
            return;
        }

        Component hoverComponent = Component.empty();
        int i = 1;
        for (Map.Entry<Enchantment, Integer> entry : e.getEnchantsToAdd().entrySet()) {
            hoverComponent = hoverComponent
                    .append(entry.getKey().displayName(entry.getValue()).color(NamedTextColor.LIGHT_PURPLE));
            if (i != size) {
                hoverComponent = hoverComponent.appendNewline();
            }
            i++;
        }

        ItemStack itemStack = e.getItem().clone();
        itemStack.addEnchantments(e.getEnchantsToAdd());

        Component broadcast = Component.text("W", NamedTextColor.RED)
                .append(Component.text("O", NamedTextColor.GOLD))
                .append(Component.text("W", NamedTextColor.YELLOW))
                .append(Component.text("I", NamedTextColor.GREEN))
                .append(Component.text("E ", NamedTextColor.BLUE))
                .append(Utils.playerComponent(e.getEnchanter()))
                .append(Component.text(" got ", NamedTextColor.AQUA))
                .append(Component.text(e.getEnchantsToAdd().size(), NamedTextColor.DARK_AQUA, TextDecoration.BOLD))
                .append(Component.text(" enchantments on their ", NamedTextColor.AQUA))
                .append(itemStack.displayName().color(NamedTextColor.BLUE)
                        .hoverEvent(HoverEvent.showText(
                                hoverComponent
                        )))
                .append(Component.text("!", NamedTextColor.AQUA))
                ;
        Bukkit.getServer().sendMessage(broadcast);
    }
}
