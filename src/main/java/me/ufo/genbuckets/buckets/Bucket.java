package me.ufo.genbuckets.buckets;

import me.ufo.genbuckets.generation.GenerationType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Bucket {

    private String name;
    private ItemStack itemStack;
    private GenerationType generationType;
    private Material material;
    private double costOfPurchase;
    private double costOfPlacement;

    public Bucket(String name, ItemStack itemStack, GenerationType generationType, Material material, double costOfPurchase, double costOfPlacement) {
        this.name = name;
        this.itemStack = itemStack;
        this.generationType = generationType;
        this.material = material;
        this.costOfPurchase = costOfPurchase;
        this.costOfPlacement = costOfPlacement;
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

    public double getCostOfPurchase() {
        return costOfPurchase;
    }

    public double getCostOfPlacement() {
        return costOfPlacement;
    }

}
