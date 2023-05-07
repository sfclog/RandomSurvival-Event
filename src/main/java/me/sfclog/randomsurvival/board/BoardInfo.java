package me.sfclog.randomsurvival.board;

import me.sfclog.randomsurvival.Main;
import me.sfclog.randomsurvival.utils.Color;
import me.sfclog.randomsurvival.utils.TimeUtil;
import me.sfclog.randomsurvival.worldevent.EventStatus;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class BoardInfo {



    public static Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Saigon"));
    public static Date currentDate = calendar.getTime();
    public static DateFormat df = new SimpleDateFormat("dd/MM/yy");






    public static String getTitle() {
        return Color.tran("&#ff0000&lＲ&#ff9900&lＡ&#99ff00&lＮ&#006699&lＤ&#3c009b&lＯ&#9400d3&lＭ");
    }

    public static List<String> getInfo(Player player) {
        ArrayList<String> list = new ArrayList<>();

        EventStatus status = Main.worldevent.getStatus();

         if(status == EventStatus.START) {
            list.add("&7<date>");
            list.add(" ");
            list.add("&fNgười chơi:");
            list.add(" &a<name>");
            list.add(" ");
            list.add("&fThời gian:");
            list.add(" &b<world_time>");
            list.add(" ");
            list.add("&fTổng: &b<online>");
            list.add(" ");
            list.add("&f► &aᴘʟᴀʏ.sιмᴘмc.ɴᴇт");
            list.replaceAll(a ->
                    a.replace("<date>", df.format(currentDate))
                            .replace("<name>", player.getName())
                            .replace("<online>", String.valueOf(Bukkit.getOnlinePlayers().size()))
                            .replace("<world_time>", TimeUtil.gettime_h(Main.worldevent.getTime()))
            );
            }
            if(status == EventStatus.WAIT) {
            list.add("&7<date>");
            list.add(" ");
            list.add("&fNgười chơi:");
            list.add(" &a<name>");
            list.add(" ");
            list.add("Đang có:");
            list.add("  &8(&b<online>&8/&e100&8)");
            list.add(" ");
            list.add("&fTrạng thái:");
            list.add(" &2Đang chờ...");
            list.add(" ");
            list.add("&f► &aᴘʟᴀʏ.sιмᴘмc.ɴᴇт");
            list.replaceAll(a ->
                    a.replace("<date>", df.format(currentDate))
                            .replace("<name>", player.getName())
                            .replace("<online>", String.valueOf(Bukkit.getOnlinePlayers().size()))
            );
            }
            if(status == EventStatus.WAITSTART) {
            list.add("&7<date>");
            list.add(" ");
            list.add("&fNgười chơi:");
            list.add(" &a<name>");
            list.add(" ");
            list.add("Đang có:");
            list.add("  &8(&b<online>&8/&e100&8)");
            list.add(" ");
            list.add("&fTrạng thái:");
            list.add(" &eBắt đầu sau &f<waittime>");
            list.add(" ");
            list.add("&f► &aᴘʟᴀʏ.sιмᴘмc.ɴᴇт");
            list.replaceAll(a ->
                    a.replace("<date>", df.format(currentDate))
                            .replace("<name>", player.getName())
                            .replace("<online>", String.valueOf(Bukkit.getOnlinePlayers().size()))
                            .replace("<waittime>", String.valueOf(Main.worldevent.getWaitTime()))
            );
            }


        return Color.tran(list);
    }

    public static String getHeader() {
        String build = null;
        List<String> ok = new ArrayList<>();
        ok.add(" ");
        ok.add(Color.tran("&#ff0000&lＲ&#ff9900&lＡ&#99ff00&lＮ&#006699&lＤ&#3c009b&lＯ&#9400d3&lＭ"));
        ok.add(Color.tran("&7Random Survival GameMode"));
        ok.add(" ");
        for(String done : ok) {
            if(done != null) {
                build = build == null ? done : build + "\n" + done;
            }
        }
        return build;
    }
    public static String getFooter() {
        String build = null;
        List<String> ok = new ArrayList<>();
        ok.add(" ");
        ok.add(Color.tran("&fPhát triển bởi &d@Candy Staff"));
        ok.add(Color.tran("&fWebsite: &ewww.simpmc.net"));
        ok.add(" ");
        for(String done : ok) {
            if(done != null) {
                build = build == null ? done : build + "\n" + done;
            }
        }
        return build;
    }
}
