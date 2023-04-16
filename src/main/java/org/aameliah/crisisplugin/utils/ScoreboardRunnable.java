package org.aameliah.crisisplugin.utils;

import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ScoreboardRunnable implements Runnable {

    @Override
    public void run() {
        Scoreboard scoreboard = Bukkit.getServer().getScoreboardManager().getMainScoreboard();

        Team overworld = scoreboard.getTeam("overworld");
        if (overworld == null) {
            overworld = scoreboard.registerNewTeam("overworld");
        }
        Team nether = scoreboard.getTeam("nether");
        if (nether == null) {
            nether = scoreboard.registerNewTeam("nether");
        }
        Team end = scoreboard.getTeam("end");
        if (end == null) {
            end = scoreboard.registerNewTeam("end");
        }

        overworld.prefix(Utils.overworldPrefix);
        overworld.color(NamedTextColor.BLUE);
        nether.prefix(Utils.netherPrefix);
        nether.color(NamedTextColor.RED);
        end.prefix(Utils.endPrefix);
        end.color(NamedTextColor.DARK_PURPLE);

        World overworldWorld = Bukkit.getServer().getWorld("world");
        if (overworldWorld != null) {
            for (Player player: overworldWorld.getPlayers()) {
                overworld.addEntry(player.getName());
            }
        }
        World netherWorld = Bukkit.getServer().getWorld("world_nether");
        if (netherWorld != null) {
            for (Player player: netherWorld.getPlayers()) {
                nether.addEntry(player.getName());
            }
        }
        World endWorld = Bukkit.getServer().getWorld("world_the_end");
        if (endWorld != null) {
            for (Player player: endWorld.getPlayers()) {
                end.addEntry(player.getName());
            }
        }





    }
}
