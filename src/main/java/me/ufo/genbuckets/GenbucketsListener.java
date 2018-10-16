package me.ufo.genbuckets;

import me.ufo.genbuckets.generation.types.Vertical;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.inventory.ItemStack;

public class GenbucketsListener implements Listener {

    @EventHandler
    public void onPlayerBucketEmptyEvent(PlayerBucketEmptyEvent event) {
        ItemStack item = event.getPlayer().getItemInHand();
        if (item != null) {
            event.setCancelled(true);
            Block block = event.getBlockClicked().getRelative(event.getBlockFace());
            Genbuckets.getInstance().getGenerationTask().addGeneration(new Vertical(Material.COBBLESTONE, block));
        }
    }

}
