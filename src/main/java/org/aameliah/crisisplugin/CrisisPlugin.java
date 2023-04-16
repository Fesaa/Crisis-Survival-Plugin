package org.aameliah.crisisplugin;

import org.aameliah.crisisplugin.listeners.ChatPrefixes;
import org.aameliah.crisisplugin.listeners.Enchant;
import org.aameliah.crisisplugin.listeners.PlayerListener;
import org.aameliah.crisisplugin.utils.ScoreboardRunnable;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class CrisisPlugin extends JavaPlugin {

    public ScoreboardRunnable scoreboardRunnable;

    @Override
    public void onEnable() {

        this.scoreboardRunnable = new ScoreboardRunnable();

        Bukkit.getPluginManager().registerEvents(new PlayerListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ChatPrefixes(), this);
        Bukkit.getPluginManager().registerEvents(new Enchant(), this);


        Bukkit.getLogger().info("CrisisPlugin has been enabled!");
    }
}
