package me.sfclog.randomsurvival.async_core;

import me.sfclog.randomsurvival.Main;
import me.sfclog.randomsurvival.board.UserBoardManage;
import me.sfclog.randomsurvival.itemdespawn.ItemDespawnTask;
import org.bukkit.Bukkit;

public class AsyncCore {

    public static void update() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(Main.pl, new Runnable() {
            public void run() {
                Main.worldevent.up_time();
                UserBoardManage.update();
                ItemDespawnTask.despawnentity();
            }
        }, 20, 20);
    }
}
