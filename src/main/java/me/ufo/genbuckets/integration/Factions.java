package me.ufo.genbuckets.integration;

import com.massivecraft.factions.engine.EnginePermBuild;
import com.massivecraft.massivecore.ps.PS;
import me.ufo.genbuckets.Genbuckets;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class Factions {

    public static boolean playerCanPlaceHere(Player player, Block block) {
        return EnginePermBuild.canPlayerBuildAt(player, PS.valueOf(block), true);
    }

    public void setup() {
        if (!setupFactions()) {
            Genbuckets.getInstance().getLogger().info("FACTIONS DEPENDENCY NOT FOUND.");
            Bukkit.getPluginManager().disablePlugin(Genbuckets.getInstance());
        } else {
            Genbuckets.getInstance().getLogger().info("FACTIONS DEPENDENCY FOUND.");
        }
    }

    private boolean setupFactions() {
        return Bukkit.getServer().getPluginManager().getPlugin("Factions") != null;
    }

}
