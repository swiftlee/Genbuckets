package me.ufo.genbuckets.io;

import me.ufo.genbuckets.Genbuckets;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class DataFile {

    private FileConfiguration fileConfiguration;
    private File file;

    public DataFile(String fileName) {
        file = new File(Genbuckets.getInstance().getDataFolder().toString() + "/" + fileName);
    }

    public FileConfiguration getFileConfiguration() {
        if (fileConfiguration == null) {
            fileConfiguration = YamlConfiguration.loadConfiguration(file);
        }
        return fileConfiguration;
    }

    public void saveDefault() {
        if (!file.exists()) {
            Genbuckets.getInstance().saveResource(file.getName(), false);
        }
    }

    public void save() {
        if (fileConfiguration == null) return;

        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
