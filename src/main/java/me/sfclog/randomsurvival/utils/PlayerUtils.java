package me.sfclog.randomsurvival.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;

public class PlayerUtils {

    public static Location SPAWN1  = new Location(Bukkit.getWorld("world"),-302.509776, 255.000000, 284.496566,-90,0);

    public static Location SPAWN2  = new Location(Bukkit.getWorld("world"),-199.506919, 255.000000, 387.513989,-180,0);

    public static Location SPAWN3  = new Location(Bukkit.getWorld("world"),-96.485680, 255.000000, 284.494053 ,90,0);

    public static Location SPAWN4  = new Location(Bukkit.getWorld("world"),-199.497916, 255.000000, 181.556894,0,0);


    public static void back_to_spawn(Player p) {
        int i = Random.getRandomNumber(1,4);
        Location loc = i == 1 ? SPAWN1 : i == 2 ? SPAWN2 : i == 3 ? SPAWN3 : SPAWN4;
        p.teleport(loc);
        Send.send(p,"&aBạn đã được dịch chuyển về tàu.", Sound.ENTITY_ENDERMAN_TELEPORT);
    }
}
