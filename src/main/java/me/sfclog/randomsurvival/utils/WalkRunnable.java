package me.sfclog.randomsurvival.utils;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import me.sfclog.randomsurvival.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class WalkRunnable extends BukkitRunnable {
    Main plugin;

    public WalkRunnable(Main plugin) {
        this.plugin = plugin;
    }

    private static final Set<EntityType> IGNORED_ENTITIES = EnumSet.of(EntityType.VILLAGER, new EntityType[] {
            EntityType.BOAT, EntityType.ARMOR_STAND, EntityType.PRIMED_TNT, EntityType.DROPPED_ITEM, EntityType.ITEM_FRAME, EntityType.EGG, EntityType.ARROW, EntityType.SNOWBALL, EntityType.ENDER_PEARL, EntityType.FIREBALL,
            EntityType.AREA_EFFECT_CLOUD, EntityType.FIREWORK, EntityType.PAINTING, EntityType.MINECART, EntityType.MINECART_CHEST, EntityType.MINECART_COMMAND, EntityType.MINECART_FURNACE, EntityType.MINECART_HOPPER, EntityType.MINECART_TNT, EntityType.MINECART_MOB_SPAWNER,
            EntityType.ENDER_CRYSTAL, EntityType.PLAYER, EntityType.EXPERIENCE_ORB, EntityType.THROWN_EXP_BOTTLE, EntityType.SPLASH_POTION, EntityType.SPECTRAL_ARROW, EntityType.FALLING_BLOCK });

    public void run() {
        for (Player players : Bukkit.getOnlinePlayers()) {
                List<Entity> entities = players.getNearbyEntities(15.0D, 15.0D, 15.0D);
                for (int i = 0; i < entities.size(); i++) {
                    Entity entity = entities.get(i);
                    Vector randomdirection = VectorToPos(entity.getLocation(), players.getLocation());
                    if (entity instanceof org.bukkit.entity.LivingEntity &&
                            !IGNORED_ENTITIES.contains(entity.getType()))
                        if (entity.getType() == EntityType.ENDER_DRAGON) {
                            List<Entity> playerlist = entity.getNearbyEntities(1000.0D, 1000.0D, 1000.0D);
                            for (int d = 0; d < playerlist.size(); d++) {
                                if (((Entity)playerlist.get(d)).getType() == EntityType.PLAYER)
                                    for (int k = 0; k < 10; k++) {
                                        Vector randomdirectionmodified = VectorToPosDragon(entity.getLocation(), players.getLocation());
                                        Entity tnt = entity.getWorld().spawnEntity(entity.getLocation(), EntityType.PRIMED_TNT);
                                        tnt.setVelocity(randomdirectionmodified);
                                    }
                            }
                        } else {
                            Entity tnt = entity.getWorld().spawnEntity(entity.getLocation(), EntityType.PRIMED_TNT);
                            tnt.setVelocity(randomdirection);
                        }
                }
            }
        }


    public static Vector VectorToPos(Location entityloc, Location location) {
        double min = 0.4D;
        Vector vector = new Vector();
        if (entityloc.getX() > location.getX())
            vector.setX(-0.2D);
        if (entityloc.getZ() < location.getZ())
            vector.setZ(0.2D);
        if (entityloc.getX() < location.getX())
            vector.setX(0.2D);
        if (entityloc.getZ() > location.getZ())
            vector.setZ(-0.2D);
        vector.setY(min);
        return vector;
    }

    public static Vector VectorToPosDragon(Location entityloc, Location location) {
        double max = 0.5D;
        double min = 0.2D;
        Vector vector = new Vector();
        if (entityloc.getX() > location.getX())
            vector.setX(-0.2D);
        if (entityloc.getZ() < location.getZ())
            vector.setZ(0.2D);
        if (entityloc.getX() < location.getX())
            vector.setX(0.2D);
        if (entityloc.getZ() > location.getZ())
            vector.setZ(-0.2D);
        vector.setY(Math.random() * (max - min + 1.0D) + min);
        return vector;
    }
}
