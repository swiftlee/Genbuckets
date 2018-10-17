package me.ufo.genbuckets.gui;

import org.bukkit.entity.Player;

public interface GUI {

    void build();

    void onClick(Player player, int slot);

    void openGUI(Player player);

}
