package me.ufo.genbuckets.generation;

import org.bukkit.Material;
import org.bukkit.block.Block;

public abstract class Generation {

    private GenerationType generationType;
    private Material material;
    private Block block;
    private int y = 1;
    private boolean completed;

    public Generation(GenerationType generationType, Material material, Block block) {
        this.generationType = generationType;
        this.material = material;
        this.block = block;
    }

    public abstract void generate();

    public GenerationType getGenerationType() {
        return generationType;
    }

    public Material getMaterial() {
        return material;
    }

    public Block getBlock() {
        return block;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}
