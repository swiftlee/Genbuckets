package me.ufo.genbuckets.buckets;

import me.ufo.genbuckets.generation.GenerationType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Bucket {

    private String name;
    private ItemStack itemStack;
    private GenerationType generationType;
    private Material material;

    public Bucket(String name, ItemStack itemStack, GenerationType generationType, Material material) {
        this.name = name;
        this.itemStack = itemStack;
        this.generationType = generationType;
        this.material = material;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public GenerationType getGenerationType() {
        return generationType;
    }

    public Material getMaterial() {
        return material;
    }

    public String getName() {
        return name;
    }

}
