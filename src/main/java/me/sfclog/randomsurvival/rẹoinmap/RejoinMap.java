package me.sfclog.randomsurvival.rẹoinmap;

import org.bukkit.entity.Player;

import java.util.HashSet;

public class RejoinMap {

    public static HashSet<String> player_rejoin = new HashSet<>();

    public static void add(Player p) {
        if(!player_rejoin.contains(p.getName())) {
            player_rejoin.add(p.getName());
        }
    }
    public static boolean have(Player p) {
        if(player_rejoin.contains(p.getName())) {
            return true;
        }
        return false;
    }
    public static void remove(Player p) {
        if(player_rejoin.contains(p.getName())) {
            player_rejoin.remove(p.getName());
        }
    }

}
