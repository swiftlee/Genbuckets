package me.ufo.genbuckets;

import me.ufo.genbuckets.io.DataFile;
import me.ufo.genbuckets.io.Serializer;
import me.ufo.genbuckets.task.GenerationTask;
import org.bukkit.plugin.java.JavaPlugin;

public class Genbuckets extends JavaPlugin {

    private static Genbuckets instance;
    private GenerationTask generationTask;

    private DataFile dataFile;

    private Serializer serializer;

    public static Genbuckets getInstance() {
        return instance;
    }

    public GenerationTask getGenerationTask() {
        return generationTask;
    }

    public DataFile getDataFile() {
        return dataFile;
    }

    public Serializer getSerializer() {
        return serializer;
    }

    @Override
    public void onEnable() {
        instance = this;

        dataFile = new DataFile("data.yml");
        dataFile.saveDefault();

        serializer = new Serializer();

        generationTask = new GenerationTask();

        this.getServer().getPluginManager().registerEvents(new GenbucketsListener(), this);
    }

    @Override
    public void onDisable() {

    }

}
