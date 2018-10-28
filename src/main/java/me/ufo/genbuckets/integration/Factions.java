package me.ufo.genbuckets.integration;

import com.massivecraft.factions.listeners.FactionsBlockListener;
import me.ufo.genbuckets.Genbuckets;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class Factions {

    public static boolean playerCanPlaceHere(Player player, Block block) {
        return FactionsBlockListener.playerCanBuildDestroyBlock(player, block.getLocation(), "lava bucket", true);
    }

    public void setup() {
        if (!setupFactions()) {
            Genbuckets.getInstance().getLogger().info("FACTIONSUUID DEPENDENCY NOT FOUND.");
            Bukkit.getPluginManager().disablePlugin(Genbuckets.getInstance());
        } else {
            Genbuckets.getInstance().getLogger().info("FACTIONSUUID DEPENDENCY FOUND.");
        }
    }

    private boolean setupFactions() {
        return Bukkit.getServer().getPluginManager().getPlugin("Factions") != null;
    }

}
