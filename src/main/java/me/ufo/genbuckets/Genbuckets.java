package me.ufo.genbuckets;

import me.ufo.genbuckets.io.DataFile;
import me.ufo.genbuckets.task.GenerationTask;
import org.bukkit.plugin.java.JavaPlugin;

public class Genbuckets extends JavaPlugin {

    private static Genbuckets instance;
    private GenerationTask generationTask;
    private DataFile dataFile;

    public static Genbuckets getInstance() {
        return instance;
    }

    public GenerationTask getGenerationTask() {
        return generationTask;
    }

    public DataFile getDataFile() {
        return dataFile;
    }

    @Override
    public void onEnable() {
        instance = this;

        dataFile = new DataFile("data.yml");
        dataFile.saveDefault();

        generationTask = new GenerationTask();

        this.getServer().getPluginManager().registerEvents(new GenbucketsListener(), this);
    }

    @Override
    public void onDisable() {

    }

}
