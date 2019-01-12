package me.ufo.genbuckets.fastblockupdate.impl;

//import me.ufo.genbuckets.Genbuckets;
import me.ufo.genbuckets.fastblockupdate.FastBlockUpdate;
import net.minecraft.server.v1_8_R3.*;
//import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;

/**
 * @author LilProteinShake
 * @github https://github.com/ProteinDev/
 * @mcmarket https://www.mc-market.org/members/130554/
 */
public class FastBlockUpdate_1_8_R3 implements FastBlockUpdate {

    @Override
    public void run(Location location, Material material) {
        World w = ((CraftWorld) location.getWorld()).getHandle();
        Chunk chunk = w.getChunkAt(location.getBlockX() >> 4, location.getBlockZ() >> 4);
        BlockPosition bp = new BlockPosition(location.getX(), location.getY(), location.getZ());
        IBlockData ibd = Block.getByCombinedId(material.getId());
        //Bukkit.getScheduler().runTask(Genbuckets.getInstance(), () -> w.setTypeAndData(bp, ibd, 2));
        chunk.a(bp, ibd);
    }

    @Override
    public void run(Location location, Material material, byte data) {
        World w = ((CraftWorld) location.getWorld()).getHandle();
        Chunk chunk = w.getChunkAt(location.getBlockX() >> 4, location.getBlockZ() >> 4);
        BlockPosition bp = new BlockPosition(location.getX(), location.getY(), location.getZ());
        IBlockData ibd = Block.getByCombinedId(material.getId() + (data << 12));
        //Bukkit.getScheduler().runTask(Genbuckets.getInstance(), () -> w.setTypeAndData(bp, ibd, 2));
        chunk.a(bp, ibd);
    }

}