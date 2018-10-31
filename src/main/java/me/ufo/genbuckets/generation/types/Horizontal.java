package me.ufo.genbuckets.generation.types;

import me.ufo.genbuckets.generation.Generation;
import me.ufo.genbuckets.generation.GenerationType;
import me.ufo.genbuckets.integration.Factions;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Horizontal extends Generation {

    public Horizontal(Player player, Material material, Block block, BlockFace blockFace) {
        super(player, GenerationType.HORIZONTAL, material, block, blockFace);

        block.setType(material);
    }

    @Override
    public void generate() {
        Block toGenerate = this.getBlock();
        Block belowToGenerate = null;
        Location toGenerateLocation = toGenerate.getLocation();

        switch (this.getBlockFace()) {
            case NORTH:
                toGenerate = addVectorByBlockFace(toGenerateLocation, BlockFace.NORTH, getHorizontalIndex());
                belowToGenerate = addVectorByBlockFace(toGenerateLocation, BlockFace.NORTH, 1);

                toGenerateLocation.add(new Vector(0, 0, getHorizontalIndex()));
                break;
            case EAST:
                toGenerate = addVectorByBlockFace(toGenerateLocation, BlockFace.EAST, getHorizontalIndex());
                belowToGenerate = addVectorByBlockFace(toGenerateLocation, BlockFace.EAST, 1);

                toGenerateLocation.add(new Vector(getHorizontalIndex(), 0, 0));
                break;
            case SOUTH:
                toGenerate = addVectorByBlockFace(toGenerateLocation, BlockFace.SOUTH, getHorizontalIndex());
                belowToGenerate = addVectorByBlockFace(toGenerateLocation, BlockFace.SOUTH, 1);

                toGenerateLocation.add(new Vector(0, 0, getHorizontalIndex()));
                break;
            case WEST:
                toGenerate = addVectorByBlockFace(toGenerateLocation, BlockFace.WEST, getHorizontalIndex());
                belowToGenerate = addVectorByBlockFace(toGenerateLocation, BlockFace.WEST, 1);

                toGenerateLocation.add(new Vector(getHorizontalIndex(), 0, 0));
                break;
        }

        setHorizontalIndex(getHorizontalIndex() + 1);

        if (this.getHorizontalIndex() <= 80 && belowToGenerate.getType() == Material.AIR &&
                    Factions.playerCanPlaceHere(this.getPlayer(), belowToGenerate)) {
            toGenerate.setType(this.getMaterial());
        } else {
            toGenerate.setType(this.getMaterial());
            this.setCompleted(true);
        }
    }

    private Block addVectorByBlockFace(Location location, BlockFace blockFace, int index) {
        switch (blockFace) {
            case NORTH:
                return location.add(new Vector(0, 0, -index)).getBlock();
            case EAST:
                return location.add(new Vector(index, 0, 0)).getBlock();
            case SOUTH:
                return location.add(new Vector(0, 0, index)).getBlock();
            case WEST:
                return location.add(new Vector(-index, 0, 0)).getBlock();
        }
        return null;
    }

}
