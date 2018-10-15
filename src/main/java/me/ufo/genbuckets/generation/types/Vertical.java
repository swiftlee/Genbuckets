package me.ufo.genbuckets.generation.types;

import me.ufo.genbuckets.generation.Generation;
import me.ufo.genbuckets.generation.GenerationType;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

public class Vertical extends Generation {

    public Vertical(Material material, Block block) {
        super(GenerationType.VERTICAL_NO_MECHANICS, material, block);
        setY(1);

        block.setType(material);
    }

    @Override
    public void generate() {
        Block toGenerate = this.getBlock().getLocation().add(new Vector(0, -getY(), 0)).getBlock();
        Block belowToGenerate = toGenerate.getLocation().add(new Vector(0, -1, 0)).getBlock();

        setY(getY() + 1);

        if (belowToGenerate.getY() > 0 && belowToGenerate.getType() == Material.AIR) {
            toGenerate.setType(this.getMaterial());
        } else {
            toGenerate.setType(this.getMaterial());
            this.setCompleted(true);
        }
    }

}
