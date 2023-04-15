package org.aameliah.crisisplugin;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.aameliah.crisisplugin.listeners.ChatPrefixes;
import org.aameliah.crisisplugin.listeners.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class CrisisPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        Bukkit.getPluginManager().registerEvents(new ChatPrefixes(), this);


        Bukkit.getLogger().info("CrisisPlugin has been enabled!");
    }


    public static Component getCrisisServerColour() {
        return Component.text("Crisis", NamedTextColor.LIGHT_PURPLE)
                .append(Component.text(" server", NamedTextColor.DARK_PURPLE))
                .append(Component.text("!!", NamedTextColor.DARK_AQUA));
    }


}
