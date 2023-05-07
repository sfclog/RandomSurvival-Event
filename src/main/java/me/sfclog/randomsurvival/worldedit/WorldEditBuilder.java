package me.sfclog.randomsurvival.worldedit;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import me.sfclog.randomsurvival.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class WorldEditBuilder {

    public static Location location  = new Location(Bukkit.getWorld("world"),-199.531,254,284.446);


    public static File door_open = new File("plugins/FastAsyncWorldEdit/schematics", "door-open.schem");

    public static File door_close  = new File("plugins/FastAsyncWorldEdit/schematics", "door-close.schem");


    public static void draw_ufo() {
            Location loc = location.clone().add(0,-20,0);
            new BukkitRunnable() {
                double angle = 0;
                public void run() {
                    int max_height = 1;
                    double max_radius = 120;
                    int lines = 3;
                    double height_increasement = 0.1;
                    double radius_increasement = max_radius / max_height;
                    for (int l = 0; l < lines; l++) {
                        for (double y = 0; y < max_height; y+=height_increasement ) {
                            double radius = y * radius_increasement;
                            double x = Math.cos(Math.toRadians(360/lines*l + y*25 - angle)) * radius;
                            double z = Math.sin(Math.toRadians(360/lines*l + y*25 - angle)) * radius;

                            new ParticleBuilder(ParticleEffect.CLOUD, loc.clone().add(x,y,z))
                                    .setSpeed(10f)
                                    .display();
                        }
                    }
                    angle += 2;

                }
            }.runTaskTimer(Main.pl, 1, 1);

    }

    public static void drawline(Location Loc1 , Location Loc2) {


        Vector vector = getDirectionBetweenLocations(Loc1, Loc2);
        for (double i = 1; i <= Loc1.distance(Loc2); i += 0.5) {
            vector.multiply(i);
            Loc1.add(vector);

            new ParticleBuilder(ParticleEffect.FLAME, Loc1)
                    .setSpeed(0.1f)
                    .display();
            new ParticleBuilder(ParticleEffect.CLOUD, Loc1)
                    .setSpeed(0.1f)
                    .display();

            //Loc1.getBlock().setType(Material.FIRE);

            Loc1.subtract(vector);
            vector.normalize();



        }
    }
    static Vector getDirectionBetweenLocations(Location Start, Location End) {
        Vector from = Start.toVector();
        Vector to = End.toVector();
        return to.subtract(from);
    }

    public static void load_door(boolean open) throws FileNotFoundException {
        InputStream schematic = new FileInputStream(open ? door_open : door_close);
        ClipboardFormat format = ClipboardFormats.findByAlias("schem");
        try (ClipboardReader reader = format.getReader(schematic)) {
            final Clipboard clipboard = reader.read();
            final com.sk89q.worldedit.world.World weWorld = BukkitAdapter.adapt(location.getWorld());
            try (EditSession session = WorldEdit.getInstance().newEditSession(weWorld)) {
                Operation operation = new ClipboardHolder(clipboard)
                        .createPaste(session)
                        .to(BlockVector3.at(location.getBlockX(), location.getBlockY(), location.getBlockZ()))
                        .ignoreAirBlocks(false)
                        .build();
                Operations.complete(operation);
            }
        } catch (Exception any) {
            any.printStackTrace();
        }
        drawline(location.clone().add(0,7,0),location);
        particleTutorial(location.clone().add(0,5,0),  1);
        new ParticleBuilder(ParticleEffect.EXPLOSION_LARGE, location)
                .setSpeed(0.1f)
                .display();

    }

    public static void particleTutorial(Location l, double range){
        for(double phi=0; phi<=Math.PI; phi+=Math.PI/15) {
            for (double theta = 0; theta <= 2 * Math.PI; theta += Math.PI / 30) {
                double r = range;
                double x = r * Math.cos(theta) * Math.sin(phi);
                double y = r * Math.cos(phi) + 1.5;
                double z = r * Math.sin(theta) * Math.sin(phi);
                l.add(x, y, z);
                new ParticleBuilder(ParticleEffect.END_ROD, l)
                        .setSpeed(0.1f)
                        .display();
                new ParticleBuilder(ParticleEffect.FLAME, l)
                        .setSpeed(0.1f)
                        .display();
                l.subtract(x, y, z);
            }
        }
    }

}
