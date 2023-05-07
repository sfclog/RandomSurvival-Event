package me.sfclog.randomsurvival.itemdespawn;

import me.sfclog.randomsurvival.Main;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;

public class ItemDespawnTask {

    public static void despawnentity() {
        for(World w : Bukkit.getWorlds()) {
            if(w != null) {
                Bukkit.getScheduler().runTask(Main.pl, () -> {
                for(Item item : w.getEntitiesByClass(Item.class)) {
                    if(item != null && item .getTicksLived() > 500) {
                        item.remove();
                    }
                }
                });
                for(LivingEntity livi : w.getEntitiesByClass(LivingEntity.class)) {
                    if(livi != null && livi.getType() != EntityType.PLAYER && livi .getTicksLived() > 1000) {
                        livi.remove();
                    }
                }
            }
        }

    }
}
