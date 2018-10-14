package me.ufo.genbuckets;

import org.bukkit.plugin.java.JavaPlugin;

public class Genbuckets extends JavaPlugin {

    private static Genbuckets instance;

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }


    public static Genbuckets getInstance() {
        return instance;
    }

}
