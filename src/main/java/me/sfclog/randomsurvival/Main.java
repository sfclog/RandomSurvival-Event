package me.sfclog.randomsurvival;

import me.sfclog.randomsurvival.async_core.AsyncCore;
import me.sfclog.randomsurvival.board.UserBoardManage;
import me.sfclog.randomsurvival.commands.RandomSurvivalCommand;
import me.sfclog.randomsurvival.commands.Spawn;
import me.sfclog.randomsurvival.playerevent.PlayerEvent;
import me.sfclog.randomsurvival.random.RandomMap;
import me.sfclog.randomsurvival.tab.PlayerTab;
import me.sfclog.randomsurvival.utils.Color;
import me.sfclog.randomsurvival.utils.WalkRunnable;
import me.sfclog.randomsurvival.worldedit.WorldEditBuilder;
import me.sfclog.randomsurvival.worldevent.WorldEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.io.FileNotFoundException;

public final class Main extends JavaPlugin {

    public static Plugin pl;

    public static WorldEvent worldevent;

    public static Scoreboard board;

    @Override
    public void onEnable() {
        pl = this;
        board = Bukkit.getScoreboardManager().getMainScoreboard();
        getServer().getPluginManager().registerEvents(new PlayerEvent(),this);


        this.getCommand("randomsurvival").setExecutor(new RandomSurvivalCommand());
        this.getCommand("randomsurvival").setTabCompleter(new RandomSurvivalCommand());


        this.getCommand("spawn").setExecutor(new Spawn());

        AsyncCore.update();


        worldevent = new WorldEvent();
        new RandomMap();
        //new WalkRunnable(this);

        for(Player p : Bukkit.getOnlinePlayers()) {
            if(p != null) {
                UserBoardManage.add(p);
                PlayerTab.update_header_and_footer(p);
                set_team(p);
            }
        }



        Bukkit.getScheduler().runTaskAsynchronously(Main.pl, () -> {
            Bukkit.getScheduler().runTask(Main.pl, () -> {
                try {
                    WorldEditBuilder.load_door(false);
                    WorldEditBuilder.draw_ufo();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

            });
        });
    }

    public static void set_team(Player p) {
        Team team = board.getTeam("random_survival") != null ? board.getTeam("random_survival") : board.registerNewTeam("random_survival");
        team.addEntry(p.getName());
        team.setColor(ChatColor.GREEN);
        team.setPrefix(Color.tran("&8(&&#ff0000&lR&#ff9900&la&#99ff00&ln&#006699&ld&#3c009b&lo&#9400d3&lm&8) &f"));
        Objective health = create_obj(board,"health", "dummy");
        health.setDisplayName(Color.tran("&c❤"));
        health.setDisplaySlot(DisplaySlot.BELOW_NAME);
    }

     public static Objective create_obj(Scoreboard board, String t, String tt) {
        return board.getObjective(t) == null ? board.registerNewObjective(t, tt) : board.getObjective(t);
    }

}
