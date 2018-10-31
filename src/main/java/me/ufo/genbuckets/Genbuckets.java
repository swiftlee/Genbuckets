package me.ufo.genbuckets;

import me.ufo.genbuckets.buckets.impl.Buckets;
import me.ufo.genbuckets.commands.GenbucketsCommand;
import me.ufo.genbuckets.gui.impl.BucketsGUI;
import me.ufo.genbuckets.integration.Econ;
import me.ufo.genbuckets.integration.Factions;
import me.ufo.genbuckets.io.DataFile;
import me.ufo.genbuckets.lang.Messages;
import me.ufo.genbuckets.task.GenerationTask;
import org.bukkit.plugin.java.JavaPlugin;

public class Genbuckets extends JavaPlugin {

    private static Genbuckets instance;

    private Messages messages;
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

        Econ econ = new Econ();
        econ.setup();

        Factions factions = new Factions();
        factions.setup();

        this.saveDefaultConfig();

        messages = new Messages();
        messages.build();

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
        this.getGenerationTask().haltTask();

        instance = null;
    }

    public Messages getMessages() {
        return messages;
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
