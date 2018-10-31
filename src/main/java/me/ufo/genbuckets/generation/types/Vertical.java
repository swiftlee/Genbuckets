package me.ufo.genbuckets.generation.types;

import me.ufo.genbuckets.generation.Generation;
import me.ufo.genbuckets.generation.GenerationType;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

public class Vertical extends Generation {

    public Vertical(Material material, Block block) {
        super(GenerationType.VERTICAL, material, block);

        if (material.hasGravity()) {
            block.setType(Material.COBBLESTONE);
        } else {
            block.setType(material);
        }
    }

    @Override
    public void generate() {
        Block toGenerate = this.getBlock().getLocation().add(new Vector(0, -getIndex(), 0)).getBlock();

        if (!this.getMaterial().hasGravity()) {
            Block belowToGenerate = toGenerate.getLocation().add(new Vector(0, -1, 0)).getBlock();

            setIndex(getIndex() + 1);

            if (belowToGenerate.getY() > 0 && belowToGenerate.getType() == Material.AIR) {
                toGenerate.setType(this.getMaterial());
            } else {
                toGenerate.setType(this.getMaterial());
                this.setCompleted(true);
            }
        } else {
            Block cobble = toGenerate.getLocation().add(new Vector(0, -1, 0)).getBlock();
            Block belowCobble = cobble.getLocation().add(new Vector(0, -1, 0)).getBlock();

            setIndex(getIndex() + 1);

            if (belowCobble.getY() > 0 && belowCobble.getType() == Material.AIR) {
                this.getBlock().setType(this.getMaterial());
                toGenerate.setType(Material.COBBLESTONE);
                cobble.setType(Material.COBBLESTONE);
                toGenerate.setType(this.getMaterial());
            } else {
                toGenerate.setType(this.getMaterial());
                cobble.setType(this.getMaterial());
                this.setCompleted(true);
            }
        }
    }

}
