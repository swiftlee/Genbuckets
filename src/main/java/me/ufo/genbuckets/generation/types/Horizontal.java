package me.ufo.genbuckets.generation.types;

import me.ufo.genbuckets.Genbuckets;
import me.ufo.genbuckets.generation.Generation;
import me.ufo.genbuckets.generation.GenerationType;
import me.ufo.genbuckets.integration.Factions;
import me.ufo.genbuckets.integration.Worldguard;
import me.ufo.genbuckets.util.Border;
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
                toGenerate = addVectorByBlockFace(toGenerateLocation, BlockFace.NORTH, getIndex());
                belowToGenerate = addVectorByBlockFace(toGenerateLocation, BlockFace.NORTH, 1);

                toGenerateLocation.add(new Vector(0, 0, getIndex()));
                break;
            case EAST:
                toGenerate = addVectorByBlockFace(toGenerateLocation, BlockFace.EAST, getIndex());
                belowToGenerate = addVectorByBlockFace(toGenerateLocation, BlockFace.EAST, 1);

                toGenerateLocation.add(new Vector(getIndex(), 0, 0));
                break;
            case SOUTH:
                toGenerate = addVectorByBlockFace(toGenerateLocation, BlockFace.SOUTH, getIndex());
                belowToGenerate = addVectorByBlockFace(toGenerateLocation, BlockFace.SOUTH, 1);

                toGenerateLocation.add(new Vector(0, 0, getIndex()));
                break;
            case WEST:
                toGenerate = addVectorByBlockFace(toGenerateLocation, BlockFace.WEST, getIndex());
                belowToGenerate = addVectorByBlockFace(toGenerateLocation, BlockFace.WEST, 1);

                toGenerateLocation.add(new Vector(getIndex(), 0, 0));
                break;
        }

        setIndex(getIndex() + 1);

        if (toGenerate.getType() != Material.AIR) {
            this.setCompleted(true);
            return;
        }

        if (this.getIndex() <= 80 &&
                belowToGenerate.getType() == Material.AIR &&
                    Factions.playerCanPlaceHere(this.getPlayer(), belowToGenerate) &&
                        Worldguard.playerCanPlaceHere(this.getPlayer(), belowToGenerate) &&
                            !Border.isOutsideOfBorder(belowToGenerate)) {

            Genbuckets.getInstance().getFastBlockUpdate().run(toGenerate.getLocation(), this.getMaterial());
        } else {
            Genbuckets.getInstance().getFastBlockUpdate().run(toGenerate.getLocation(), this.getMaterial());
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
