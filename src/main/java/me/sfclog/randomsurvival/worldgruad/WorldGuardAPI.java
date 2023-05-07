package me.sfclog.randomsurvival.worldgruad;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class WorldGuardAPI {


    public static boolean check(Player p , Block b) {

        RegionQuery query = WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery();
        com.sk89q.worldedit.util.Location loc = BukkitAdapter.adapt(b.getLocation());
        com.sk89q.worldedit.world.World world = BukkitAdapter.adapt(b.getWorld());
        if (!WorldGuard.getInstance().getPlatform().getSessionManager().hasBypass(WorldGuardPlugin.inst().wrapPlayer(p), world)) {
           if(query.testState(loc, WorldGuardPlugin.inst().wrapPlayer(p), Flags.BUILD)) {
               return true;
           }
        }
        return false;
    }
}
