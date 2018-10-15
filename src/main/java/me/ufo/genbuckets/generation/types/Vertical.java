package me.ufo.genbuckets.generation.types;

import me.ufo.genbuckets.generation.Generation;
import me.ufo.genbuckets.generation.GenerationType;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class Vertical extends Generation {

    public Vertical(Material material, Block block) {
        super(GenerationType.VERTICAL_NO_MECHANICS, material, block);
        setY(1);

        block.setType(material);
    }

    @Override
    public void generate() {
        int x = this.getBlock().getX();
        int y = this.getBlock().getY();
        int z = this.getBlock().getZ();

        Block toGenenerate = this.getBlock().getWorld().getBlockAt(x, y - getY(), z);

        int minusY = getY() + 1;

        setY(minusY);

        if (y - minusY > 0 && this.getBlock().getWorld().getBlockAt(x, y - minusY, z).getType() == Material.AIR) {
            toGenenerate.setType(this.getMaterial());
        } else {
            toGenenerate.setType(this.getMaterial());
            this.setCompleted(true);
        }
    }

}
