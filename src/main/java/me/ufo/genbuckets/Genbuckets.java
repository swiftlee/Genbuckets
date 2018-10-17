package me.ufo.genbuckets;

import me.ufo.genbuckets.buckets.impl.Buckets;
import me.ufo.genbuckets.commands.GenbucketsCommand;
import me.ufo.genbuckets.gui.impl.BucketsGUI;
import me.ufo.genbuckets.io.DataFile;
import me.ufo.genbuckets.task.GenerationTask;
import org.bukkit.plugin.java.JavaPlugin;

public class Genbuckets extends JavaPlugin {

    private static Genbuckets instance;
    private DataFile dataFile;

    private GenerationTask generationTask;

    private Buckets buckets;
    private BucketsGUI bucketsGUI;

    public static Genbuckets getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        long startTime = System.currentTimeMillis();

        instance = this;

        this.saveDefaultConfig();
        dataFile = new DataFile("data.yml");
        dataFile.saveDefault();

        generationTask = new GenerationTask();
        buckets = new Buckets();
        bucketsGUI = new BucketsGUI();

        buckets.build();
        bucketsGUI.build();

        this.getCommand("genbuckets").setExecutor(new GenbucketsCommand());
        this.getServer().getPluginManager().registerEvents(new GenbucketsListener(), this);

        this.getLogger().info("Successfully loaded. Took (" + (System.currentTimeMillis() - startTime) + "ms).");
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public DataFile getDataFile() {
        return dataFile;
    }

    public GenerationTask getGenerationTask() {
        return generationTask;
    }

    public Buckets getBuckets() {
        return buckets;
    }

    public BucketsGUI getBucketsGUI() {
        return bucketsGUI;
    }

}
