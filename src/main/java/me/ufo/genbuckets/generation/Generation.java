package me.ufo.genbuckets.generation;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public abstract class Generation {

    private Player player;
    private GenerationType generationType;
    private Material material;
    private Block block;
    private BlockFace blockFace;
    private int y = 1;
    private int horizontalIndex = 1;
    private boolean completed;

    public Generation(GenerationType generationType, Material material, Block block) {
        this.generationType = generationType;
        this.material = material;
        this.block = block;
    }

    public Generation(Player player, GenerationType generationType, Material material, Block block, BlockFace blockFace) {
        this.player = player;
        this.generationType = generationType;
        this.material = material;
        this.block = block;
        this.blockFace = blockFace;
    }

    public abstract void generate();

    public Player getPlayer() {
        return player;
    }

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

    public BlockFace getBlockFace() {
        return blockFace;
    }

    public int getHorizontalIndex() {
        return horizontalIndex;
    }

    public void setHorizontalIndex(int horizontalIndex) {
        this.horizontalIndex = horizontalIndex;
    }

}
