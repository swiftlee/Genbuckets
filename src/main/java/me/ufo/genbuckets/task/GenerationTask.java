package me.ufo.genbuckets.task;

import me.ufo.genbuckets.Genbuckets;
import me.ufo.genbuckets.generation.Generation;
import org.bukkit.Bukkit;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class GenerationTask implements Runnable {

    private static ConcurrentHashMap<UUID, Generation> generations = new ConcurrentHashMap<>();
    private Genbuckets INSTANCE = Genbuckets.getInstance();
    private int taskID;

    private void runTask() {
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(INSTANCE, this, 15L, 15L);
    }

    public void haltTask() {
        Bukkit.getServer().getScheduler().cancelTask(taskID);
    }

    @Override
    public void run() {
        if (!generations.isEmpty()) {

            generations.forEach((uuid, generation) -> {
                if (generation.isCompleted()) {
                    generations.remove(uuid, generation);
                    return;
                }
                generation.generate();
            });

        } else {
            this.haltTask();
            generations.clear();
        }
    }

    public void addGeneration(Generation generation) {
        if (generations.isEmpty()) this.runTask();

        generations.put(UUID.randomUUID(), generation);
    }

}
