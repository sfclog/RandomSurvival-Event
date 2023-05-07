package me.sfclog.randomsurvival.commands;

import me.sfclog.randomsurvival.Main;
import me.sfclog.randomsurvival.utils.Color;
import me.sfclog.randomsurvival.utils.Send;
import me.sfclog.randomsurvival.worldedit.WorldEditBuilder;
import me.sfclog.randomsurvival.worldevent.EventStatus;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class RandomSurvivalCommand implements TabExecutor, CommandExecutor {


    @Override
    public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
        if (arg0 instanceof Player) {
            Player p = (Player) arg0;
            if (p.hasPermission("&") || p.isOp() || p.hasPermission("mythatmobs.commands")) {
                if (arg1.getName().equalsIgnoreCase("randomsurvival")) {
                    if (arg3.length < 1) {

                    } else if (arg3[0].equalsIgnoreCase("start")) {
                        if(Main.worldevent.status == EventStatus.WAIT) {
                            Main.worldevent.setStatus(EventStatus.WAITSTART);
                            send(arg0,"&aĐã bắt đàu trò chơi !");
                            Send.sendglobal("&aQuản trị viên đã bắt đầu trò chơi!", Sound.ENTITY_ENDER_DRAGON_AMBIENT);
                        } else {
                            send(arg0,"&cTrò chơi đang bắt đầu rồi !");
                        }
                    } else if (arg3[0].equalsIgnoreCase("stop")) {
                        if (Main.worldevent.status == EventStatus.START) {
                            Main.worldevent.stop_game();
                            send(arg0, "&aĐã kết thúc trò chơi !");
                        } else {
                            send(arg0, "&cTrò chơi chưa bắt đầu !");
                        }
                    } else if (arg3[0].equalsIgnoreCase("pvptoggle")) {
                        Main.worldevent.toggle_pvp();
                    } else if (arg3[0].equalsIgnoreCase("glowing")) {
                        for(Player pl : Bukkit.getOnlinePlayers()) {
                            PotionEffect potionEffect1 = new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 5);
                            potionEffect1.apply(pl);
                        }
                    }
                }
            }
        }
        return true;
    }

    public static void send(CommandSender send, String msg) {
        send.sendMessage(Color.tran(msg));
    }

    @Override
    public List<String> onTabComplete(CommandSender arg0, org.bukkit.command.Command arg1, String arg2, String[] arg3) {
        ArrayList<String> s = new ArrayList<String>();
        s.add("start");
        s.add("stop");
        s.add("pvptoggle");
        s.add("glowing");
        return s;
    }
}
