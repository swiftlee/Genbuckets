package me.ufo.genbuckets;

import me.ufo.genbuckets.buckets.Bucket;
import me.ufo.genbuckets.generation.types.Vertical;
import me.ufo.genbuckets.gui.impl.BucketsGUI;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.inventory.ItemStack;

public class GenbucketsListener implements Listener {

    private Genbuckets INSTANCE = Genbuckets.getInstance();

    @EventHandler
    public void onPlayerBucketEmptyEvent(PlayerBucketEmptyEvent event) {
        ItemStack item = event.getPlayer().getItemInHand();
        if (item != null && item.hasItemMeta()) {
            event.setCancelled(true);
            Block block = event.getBlockClicked().getRelative(event.getBlockFace());

            for (Bucket bucket : INSTANCE.getBuckets().getAllBuckets()) {
                if (item.equals(bucket.getItemStack())) {
                    switch (bucket.getGenerationType()) {
                        case VERTICAL:
                            INSTANCE.getGenerationTask().addGeneration(new Vertical(bucket.getMaterial(), block));
                            break;
                    }
                    break;
                }
            }
        }
    }

    @EventHandler
    public void onPlayerInventoryClickEvent(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) return;

        if (event.getClickedInventory().getHolder() instanceof BucketsGUI) {
            event.setCancelled(true);
            INSTANCE.getBucketsGUI().onClick((Player) event.getWhoClicked(), event.getRawSlot());
        }
    }

}
