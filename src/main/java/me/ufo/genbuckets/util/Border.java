package me.ufo.genbuckets.util;

import org.bukkit.Location;
import org.bukkit.WorldBorder;
import org.bukkit.block.Block;

public final class Border {

    private Border() {
        throw new RuntimeException("Cannot instantiate utility class.");
    }

    public static boolean isOutsideOfBorder(Block block) {
        Location loc = block.getLocation();
        WorldBorder border = block.getWorld().getWorldBorder();
        double size = border.getSize() / 2;
        Location center = border.getCenter();
        double x = loc.getX() - center.getX(), z = loc.getZ() - center.getZ();
        return ((x >= size || (-x) > size) || (z >= size || (-z) > size));
    }

    public static boolean isOutsideOfBorder(Location loc) {
        WorldBorder border = loc.getWorld().getWorldBorder();
        double size = border.getSize() / 2;
        Location center = border.getCenter();
        double x = loc.getX() - center.getX(), z = loc.getZ() - center.getZ();
        return ((x >= size || (-x) > size) || (z >= size || (-z) > size));
    }

}
