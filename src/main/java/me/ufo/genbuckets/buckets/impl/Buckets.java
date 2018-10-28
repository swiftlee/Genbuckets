package me.ufo.genbuckets.buckets.impl;

import me.ufo.genbuckets.Genbuckets;
import me.ufo.genbuckets.buckets.Bucket;
import me.ufo.genbuckets.buckets.Item;
import me.ufo.genbuckets.generation.GenerationType;
import me.ufo.genbuckets.util.Style;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashSet;
import java.util.Set;

public class Buckets implements Item {

    private Genbuckets INSTANCE = Genbuckets.getInstance();
    private Set<Bucket> buckets = new HashSet<>();

    @Override
    public void build() {
        ConfigurationSection vertical = INSTANCE.getConfig().getConfigurationSection("VERTICAL");

        vertical.getKeys(false).forEach(key -> {
            String name = INSTANCE.getConfig().getString("VERTICAL." + key + ".name");
            int slot = INSTANCE.getConfig().getInt("VERTICAL." + key + ".slot");
            double costOfPurchase = INSTANCE.getConfig().getDouble("VERTICAL." + key + ".costOfPurchase");
            double costOfPlacement = INSTANCE.getConfig().getDouble("VERTICAL." + key + ".costOfPlacement");

            ItemStack item = new ItemStack(Material.LAVA_BUCKET);
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.setDisplayName(Style.translate(name));
            itemMeta.setLore(Style.translateLines(INSTANCE.getConfig().getStringList("VERTICAL." + key + ".lore")));
            item.setItemMeta(itemMeta);

            Material material = Material.getMaterial(key);
            INSTANCE.getBucketsGUI().getBucketSlots().put(slot, item);

            buckets.add(new Bucket(name, item, GenerationType.VERTICAL, material, costOfPurchase, costOfPlacement));
        });

    }

    public Set<Bucket> getAllBuckets() {
        return buckets;
    }

}
