package me.ufo.genbuckets.util;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public final class Style {

    private Style() {
        throw new RuntimeException("Cannot instantiate utility class.");
    }

    public static String translate(String in) {
        return ChatColor.translateAlternateColorCodes('&', in);
    }

    public static List<String> translateLines(List<String> lines) {
        List<String> toReturn = new ArrayList<>();

        for (String line : lines) {
            toReturn.add(ChatColor.translateAlternateColorCodes('&', line));
        }

        return toReturn;
    }

}
