package me.sfclog.randomsurvival.commands;

import me.sfclog.randomsurvival.utils.PlayerUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Spawn implements  CommandExecutor {

    @Override
    public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
        if (arg0 instanceof Player) {
            Player p = (Player) arg0;
            if(p != null) {
                PlayerUtils.back_to_spawn(p);
            }
        }
        return true;
    }
}
