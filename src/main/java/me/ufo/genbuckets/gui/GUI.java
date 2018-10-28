package me.ufo.genbuckets.gui;

import me.ufo.genbuckets.buckets.Bucket;
import org.bukkit.entity.Player;

public interface GUI {

    void build();

    void onClick(Player player, int slot);

    void openGUI(Player player);

    boolean transact(Player player, Bucket bucket);

}
