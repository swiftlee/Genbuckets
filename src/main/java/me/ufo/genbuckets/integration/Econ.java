package me.ufo.genbuckets.integration;

import me.ufo.genbuckets.Genbuckets;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

public class Econ {

    public static Economy econ = null;

    public static boolean withdrawAmountFromPlayer(Player player, double cost) {
        EconomyResponse er = econ.withdrawPlayer(player, cost);
        return er.transactionSuccess();
    }

    public void setup() {
        if (!setupEconomy()) {
            Genbuckets.getInstance().getLogger().info("VAULT DEPENDENCY NOT FOUND.");
            Bukkit.getPluginManager().disablePlugin(Genbuckets.getInstance());
        } else {
            Genbuckets.getInstance().getLogger().info("VAULT DEPENDENCY FOUND.");
        }
    }

    private boolean setupEconomy() {
        if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

}
