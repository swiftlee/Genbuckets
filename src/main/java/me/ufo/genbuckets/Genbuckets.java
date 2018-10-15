package me.ufo.genbuckets;

import me.ufo.genbuckets.task.GenerationTask;
import org.bukkit.plugin.java.JavaPlugin;

public class Genbuckets extends JavaPlugin {

    private static Genbuckets instance;

    private GenerationTask generationTask;

    @Override
    public void onEnable() {
        instance = this;

        generationTask = new GenerationTask();

        this.getServer().getPluginManager().registerEvents(new GenbucketsListener(), this);
    }

    @Override
    public void onDisable() {

    }

    public static Genbuckets getInstance() {
        return instance;
    }

    public GenerationTask getGenerationTask() {
        return generationTask;
    }

}
