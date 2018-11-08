package me.ufo.genbuckets;

import me.ufo.genbuckets.buckets.Bucket;
import me.ufo.genbuckets.generation.types.Horizontal;
import me.ufo.genbuckets.generation.types.Vertical;
import me.ufo.genbuckets.gui.impl.BucketsGUI;
import me.ufo.genbuckets.integration.Econ;
import me.ufo.genbuckets.integration.Factions;
import me.ufo.genbuckets.integration.Worldguard;
import me.ufo.genbuckets.util.Style;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

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

                    if (!Factions.playerCanPlaceHere(player, block)) return;
                    if (!Worldguard.playerCanPlaceHere(player, block)) return;

                    if (!Econ.withdrawAmountFromPlayer(player, bucket.getCostOfPlacement())) {
                        DecimalFormat decimalFormat = new DecimalFormat(".##");
                        double difference = bucket.getCostOfPlacement() - Econ.econ.getBalance(player);

                        player.sendMessage(Style.translate(INSTANCE.getMessages().NOT_ENOUGH_FOR_PLACEMENT.replace("%difference%", decimalFormat.format(difference)).replace("%bucket%", bucket.getName())));
                        return;
                    }

                    switch (bucket.getGenerationType()) {
                        case HORIZONTAL:
                            if (event.getBlockFace() == BlockFace.UP || event.getBlockFace() == BlockFace.DOWN) {
                                Econ.depositAmountToPlayer(player, bucket.getCostOfPlacement());
                                return;
                            }
                            INSTANCE.getGenerationTask().addGeneration(new Horizontal(player, bucket.getMaterial(), block, event.getBlockFace()));
                            break;
                        case VERTICAL:
                            if (block.getLocation().add(new Vector(0, -1, 0)).getBlock().getType() != Material.AIR) {
                                Econ.depositAmountToPlayer(player, bucket.getCostOfPlacement());
                                return;
                            }
                            INSTANCE.getGenerationTask().addGeneration(new Vertical(bucket.getMaterial(), block));
                            break;
                    }

                    break;
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
