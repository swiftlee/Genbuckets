package me.ufo.genbuckets.gui.impl;

import me.ufo.genbuckets.Genbuckets;
import me.ufo.genbuckets.buckets.Bucket;
import me.ufo.genbuckets.gui.GUI;
import me.ufo.genbuckets.integration.Econ;
import me.ufo.genbuckets.util.Style;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.DecimalFormat;
import java.util.HashMap;

public class BucketsGUI implements GUI, InventoryHolder {

    private Genbuckets INSTANCE = Genbuckets.getInstance();

    private Inventory bucketsGUI;
    private HashMap<Integer, ItemStack> bucketSlots = new HashMap<>();

    @Override
    public void build() {
        String title = Style.translate(INSTANCE.getConfig().getString("GUI.title"));
        int size = INSTANCE.getConfig().getInt("GUI.size");
        int glassColor = INSTANCE.getConfig().getInt("GUI.stained-glass");
        ItemStack glassFiller = getGlassItem(glassColor);

        bucketsGUI = Bukkit.createInventory(this, size, title);

        for (int i = 0; i < size; i++) {
            bucketsGUI.setItem(i, glassFiller);
        }

        bucketSlots.forEach((slot, bucket) -> bucketsGUI.setItem(slot, bucket));
    }

    @Override
    public void onClick(Player player, int slot) {
        if (bucketSlots.containsKey(slot)) {

            for (Bucket bucket : Genbuckets.getInstance().getBuckets().getAllBuckets()) {
                if (bucketSlots.get(slot).equals(bucket.getItemStack())) {
                    if (transact(player, bucket)) {
                        player.getInventory().addItem(bucket.getItemStack().clone());
                        player.sendMessage(Style.translate(INSTANCE.getMessages().GIVEN_BUCKET.replace("%bucket%", bucket.getName())));
                        break;
                    }
                }
            }

        }
    }

    @Override
    public boolean transact(Player player, Bucket bucket) {
        double cost = bucket.getCostOfPurchase();
        if (Econ.withdrawAmountFromPlayer(player, cost)) {
            return true;
        } else {
            DecimalFormat decimalFormat = new DecimalFormat(".##");
            double difference = cost - Econ.econ.getBalance(player);

            player.sendMessage(Style.translate(INSTANCE.getMessages().NOT_ENOUGH_FOR_PURCHASE.replace("%difference%", decimalFormat.format(difference)).replace("%bucket%", bucket.getName())));
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

    private ItemStack getGlassItem(int color) {
        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) color);
        ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName(" ");
        glass.setItemMeta(glassMeta);
        return glass;
    }

}
