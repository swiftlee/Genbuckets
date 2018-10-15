package me.ufo.genbuckets.task;

import me.ufo.genbuckets.Genbuckets;
import me.ufo.genbuckets.generation.Generation;
import org.bukkit.Bukkit;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class GenerationTask implements Runnable {

    private static ConcurrentHashMap<UUID, Generation> generations = new ConcurrentHashMap<>();
    private int taskID;

    private void runTask() {
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Genbuckets.getInstance(), this, 0L, 15L);
    }

    private void haltTask() {
        Bukkit.getServer().getScheduler().cancelTask(taskID);
    }

    @Override
    public void run() {
        if (generations.size() > 0) {

            generations.forEach((uuid, generation) ->  {
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
        if (generations.size() == 0) {
            this.runTask();
        }

        generations.put(UUID.randomUUID(), generation);
    }

}
