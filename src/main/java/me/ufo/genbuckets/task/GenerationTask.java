package me.ufo.genbuckets.task;

import me.ufo.genbuckets.Genbuckets;
import me.ufo.genbuckets.generation.Generation;
import org.bukkit.Bukkit;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public class GenerationTask implements Runnable {

    private static ConcurrentHashMap<UUID, Generation> generations = new ConcurrentHashMap<>();
    private int taskID;

    private void runTask() {
        System.out.println("running");
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Genbuckets.getInstance(), this, 0L, 15L);
    }

    private void haltTask() {
        System.out.println("halting " + taskID);
        Bukkit.getServer().getScheduler().cancelTask(taskID);
    }

    @Override
    public void run() {
        System.out.println(isTaskRunning());
        if (generations.size() != 0) {

            generations.forEach((uuid, generation) ->  {
                if (generation.isCompleted()) {
                    generations.remove(uuid, generation);
                    System.out.println("5");
                    return;
                }
                System.out.println("9");
                generation.generate();
            });

        } else {
            this.haltTask();
            generations.clear();
        }
    }

    public void addGeneration(Generation generation) {
        System.out.println("size before: " + generations.size());
        //generations.add(generation);
        generations.put(UUID.randomUUID(), generation);
        System.out.println("size after: " + generations.size());

        if (!isTaskRunning()) {
            this.runTask();
        }
    }

    private boolean isTaskRunning() {
        return Bukkit.getScheduler().isCurrentlyRunning(taskID);
    }

}
