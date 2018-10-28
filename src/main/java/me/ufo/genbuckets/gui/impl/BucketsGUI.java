package me.ufo.genbuckets.gui.impl;

import me.ufo.genbuckets.Genbuckets;
import me.ufo.genbuckets.buckets.Bucket;
import me.ufo.genbuckets.gui.GUI;
import me.ufo.genbuckets.integration.Econ;
import me.ufo.genbuckets.util.Style;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.text.DecimalFormat;
import java.util.HashMap;

public class BucketsGUI implements GUI, InventoryHolder {

    private Inventory bucketsGUI;
    private HashMap<Integer, ItemStack> bucketSlots = new HashMap<>();

    @Override
    public void build() {
        bucketsGUI = Bukkit.createInventory(this, 54, "Genbuckets");
        bucketSlots.forEach((slot, bucket) -> bucketsGUI.setItem(slot, bucket));
    }

    @Override
    public void onClick(Player player, int slot) {
        if (bucketSlots.containsKey(slot)) {

            for (Bucket bucket : Genbuckets.getInstance().getBuckets().getAllBuckets()) {
                if (bucketSlots.get(slot).equals(bucket.getItemStack())) {
                    if (transact(player, bucket)) {
                        player.getInventory().addItem(bucket.getItemStack().clone());
                        player.sendMessage(Style.translate("&7You have been given a " + bucket.getName() + "&7."));
                        break;
                    }
                }
            }

        }
    }

    private boolean transact(Player player, Bucket bucket) {
        double cost = bucket.getCostOfPurchase();
        if (Econ.withdrawAmountFromPlayer(player, cost)) {
            return true;
        } else {
            DecimalFormat decimalFormat = new DecimalFormat(".##");
            double difference = cost - Econ.econ.getBalance(player);

            player.getPlayer().sendMessage(Style.translate("&7You need &c$" + decimalFormat.format(difference) + " &7more to purchase a " + bucket.getName() + "&7."));
            return false;
        }
    }

    @Override
    public void openGUI(Player player) {
        player.openInventory(bucketsGUI);
    }

    @Override
    public Inventory getInventory() {
        return bucketsGUI;
    }

    public HashMap<Integer, ItemStack> getBucketSlots() {
        return bucketSlots;
    }

}
