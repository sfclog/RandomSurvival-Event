package me.sfclog.randomsurvival.worldevent;

import me.sfclog.randomsurvival.Main;
import me.sfclog.randomsurvival.utils.Color;
import me.sfclog.randomsurvival.utils.PlayerUtils;
import me.sfclog.randomsurvival.utils.Send;
import me.sfclog.randomsurvival.worldedit.WorldEditBuilder;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.io.FileNotFoundException;

public class WorldEvent {

    public EventStatus status;

    public int eventtime,waittime;

    public boolean pvp;


    public WorldEvent() {
        eventtime = 0;
        waittime = 20;
        status = EventStatus.WAIT;
        pvp = false;
    }

    public void toggle_pvp() {
        pvp = pvp == false ? true : pvp == true ? false : false;
        if(pvp == true) {
            Send.sendglobal("&c&lPVP đã được bật! Bạn có thể PVP với người khác!", Sound.ENTITY_WITHER_SPAWN);
        } else {
            Send.sendglobal("&a&lPVP đã được tắt! Bạn đang được bảo vệ!", Sound.ENTITY_WITCH_DEATH);
        }
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }
    public EventStatus getStatus() {
        return this.status;
    }

    public boolean is_start() {
        return this.status == EventStatus.START;
    }

    public int getWaitTime() {
        return waittime;
    }
    public void up_time() {
        if(status == EventStatus.START) {
            //start
            eventtime++;
            win_scan();
        } else if(status == EventStatus.WAITSTART) {
           waittime--;
           if(waittime <= 5) {
               Send.sendglobal("&fTrò chơi sẽ bắt đầu trong &e" + waittime + " &fgiây!", Sound.BLOCK_NOTE_BLOCK_BIT);
           }
           if(waittime <= 0) {
               waittime = 20;
               status = EventStatus.START;
               for(Player p : Bukkit.getOnlinePlayers()) {
                   if(p != null) {
                       p.getInventory().clear();
                       p.getEnderChest().clear();
                       p.setBedSpawnLocation(null);
                   }
               }
               try {
                   WorldEditBuilder.load_door(true);
               } catch (FileNotFoundException e) {
                   throw new RuntimeException(e);
               }
               Send.sendglobal("&aBắt đầy trò chơi!",Sound.ENTITY_ENDER_DRAGON_GROWL);
               Send.send_center_global(" ");
               Send.send_center_global("&c&lR&6&lA&a&lN&b&lD&9&lO&5&lM");
               Send.send_center_global("&7Random Survival Gamemode");
               Send.send_center_global(" ");
               Send.send_center_global("&fLuật chơi cơ bản");
               Send.send_center_global(" ");
               Send.send_center_global("&7Ai lấy được &5Trứng Rồng &7trước sẽ là");
               Send.send_center_global("&7người chiến thăng trò chơi này.");
               Send.send_center_global(" ");
           }
        }
    }

    private void win_scan() {
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(p != null) {
                if(p.getInventory() != null && p.getInventory().contains(Material.DRAGON_EGG)) {
                    Send.sendglobal("&cKết thúc trò chơi!",Sound.UI_TOAST_CHALLENGE_COMPLETE);
                    Bukkit.getServer().broadcastMessage(Color.tran("&eChúc mừng người chơi &f" + p.getName() + " &elà người chiến thắng!"));
                    Send.send_center_global(" ");
                    Send.send_center_global("&c&lR&6&lA&a&lN&b&lD&9&lO&5&lM");
                    Send.send_center_global("&7Random Survival Gamemode");
                    Send.send_center_global(" ");
                    Send.send_center_global("&fNgười chiến thắng là");
                    Send.send_center_global("&e" + p.getName());
                    Send.send_center_global(" ");
                    Bukkit.getScheduler().runTask(Main.pl, () -> {
                    stop_game();
                    });
                    return;
                }
            }
        }
    }


    public int getTime() {
        return eventtime;
    }

    public void stop_game() {
        eventtime = 0;
        waittime = 20;
        status = EventStatus.WAIT;
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(p != null) {
                PlayerUtils.back_to_spawn(p);
                p.getInventory().clear();
                p.getEnderChest().clear();
                p.setBedSpawnLocation(null);
            }
        }
        try {
            WorldEditBuilder.load_door(false);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isPVP() {
        return pvp;
    }
}
