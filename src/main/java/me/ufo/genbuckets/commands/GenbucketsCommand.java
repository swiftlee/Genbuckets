package me.ufo.genbuckets.commands;

import me.ufo.genbuckets.Genbuckets;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GenbucketsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            Genbuckets.getInstance().getBucketsGUI().openGUI(player);
        }

        return false;
    }

}
