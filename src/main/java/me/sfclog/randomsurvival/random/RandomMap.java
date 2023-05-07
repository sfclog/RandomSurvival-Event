package me.sfclog.randomsurvival.random;

import me.sfclog.randomsurvival.utils.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class RandomMap {

    public static List<Material> typelist;

    public static List<EntityType> entitylist;

    public RandomMap() {
        typelist = new ArrayList<>();
        entitylist = new ArrayList<>();

        for(Material m : Material.values()) {
            if(m != null
                    && m != Material.COMMAND_BLOCK
                    && m != Material.BEDROCK
                    && m != Material.BARRIER
                    && m != Material.LIGHT
                    && m != Material.DRAGON_EGG
                    && !m.name().contains("COMMAND")
                    && !m.name().contains("END")
                    && m != Material.DEBUG_STICK) {
                typelist.add(m);
            }
        }
        for(EntityType type : EntityType.values()) {
            if(type != null  && type != EntityType.ENDER_DRAGON && type != EntityType.PLAYER) {
                entitylist.add(type);
            }
        }
    }

    public static boolean getRandom(Location loc) {
       int i = Random.getRandomNumber(0,8);
       if(i == 0 || i == 2 || i == 4) {
           Material m = typelist.get(Random.getRandomNumber(0,typelist.size() -1));
           if(m != null) {
               loc.getWorld().dropItemNaturally(loc,new ItemStack(m));
               return true;
           }
       }
       if(i == 1 || i == 3 || i == 5) {
           EntityType type =  entitylist.get(Random.getRandomNumber(0,entitylist.size() -1));
           if(type != null) {
               loc.getWorld().spawnEntity(loc, type);
               return true;
           }
       }
      return false;
    }
}
