package me.ufo.genbuckets;

import me.ufo.genbuckets.buckets.Bucket;
import me.ufo.genbuckets.generation.types.Vertical;
import me.ufo.genbuckets.gui.impl.BucketsGUI;
import me.ufo.genbuckets.integration.Econ;
import me.ufo.genbuckets.util.Style;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.inventory.ItemStack;

import java.text.DecimalFormat;

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
                    Player player = event.getPlayer();

                    if (Econ.withdrawAmountFromPlayer(player, bucket.getCostOfPlacement())) {
                        switch (bucket.getGenerationType()) {
                            case VERTICAL:
                                INSTANCE.getGenerationTask().addGeneration(new Vertical(bucket.getMaterial(), block));
                                break;
                        }
                        break;
                    } else {
                        DecimalFormat decimalFormat = new DecimalFormat(".##");
                        double difference = bucket.getCostOfPlacement() - Econ.econ.getBalance(player);

                        player.sendMessage(Style.translate("&7You need &c$" + decimalFormat.format(difference) + " &7more to place this " + bucket.getName() + "&7."));
                    }
                }
            }
        }
    }

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) return;

        if (event.getClickedInventory().getHolder() instanceof BucketsGUI) {
            event.setCancelled(true);
            INSTANCE.getBucketsGUI().onClick((Player) event.getWhoClicked(), event.getRawSlot());
        }
    }

}
