package me.sfclog.randomsurvival.playerevent;

import me.sfclog.randomsurvival.Main;
import me.sfclog.randomsurvival.board.UserBoardManage;
import me.sfclog.randomsurvival.random.RandomMap;
import me.sfclog.randomsurvival.rẹoinmap.RejoinMap;
import me.sfclog.randomsurvival.tab.PlayerTab;
import me.sfclog.randomsurvival.utils.Color;
import me.sfclog.randomsurvival.utils.PlayerUtils;
import me.sfclog.randomsurvival.utils.Random;
import me.sfclog.randomsurvival.utils.Send;
import me.sfclog.randomsurvival.worldgruad.WorldGuardAPI;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.event.world.StructureGrowEvent;
import org.bukkit.event.world.WorldSaveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerEvent implements Listener {

    public static List<Material> BLACKLIST_BLOCK;

    static {
        BLACKLIST_BLOCK = new ArrayList<>();
        BLACKLIST_BLOCK.add(Material.CRAFTING_TABLE);
        BLACKLIST_BLOCK.add(Material.FURNACE);
        BLACKLIST_BLOCK.add(Material.ENCHANTING_TABLE);
    }


    @EventHandler
    public void randomSpeedOnEat(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        if(Main.worldevent.is_start()) {
            player.setFoodLevel(Random.getRandomNumber(1,20));
        }
    }


    @EventHandler
    public void onStructureGrow(StructureGrowEvent event) {
        Map<Integer, TreeType> treeTypes = new HashMap<>();
        treeTypes.put(1, TreeType.TREE);
        treeTypes.put(2, TreeType.BIG_TREE);
        treeTypes.put(3, TreeType.ACACIA);
        treeTypes.put(4, TreeType.AZALEA);
        treeTypes.put(5, TreeType.BIRCH);
        treeTypes.put(6, TreeType.JUNGLE);
        treeTypes.put(7, TreeType.JUNGLE_BUSH);
        treeTypes.put(8, TreeType.MANGROVE);
        treeTypes.put(9, TreeType.REDWOOD);
        treeTypes.put(10, TreeType.SMALL_JUNGLE);
        treeTypes.put(11, TreeType.SWAMP);
        treeTypes.put(12, TreeType.TALL_BIRCH);
        treeTypes.put(13, TreeType.BROWN_MUSHROOM);
        treeTypes.put(14, TreeType.RED_MUSHROOM);
        treeTypes.put(15, TreeType.CHORUS_PLANT);
        treeTypes.put(16, TreeType.CRIMSON_FUNGUS);
        treeTypes.put(17, TreeType.WARPED_FUNGUS);

        Location location = event.getLocation();
        World world = event.getWorld();

        java.util.Random random = new java.util.Random();
        random.setSeed(random.nextInt(999999999));

        int randomTreeType = random.nextInt(treeTypes.size());

        event.setCancelled(true);

        switch (randomTreeType) {

            case 13:
            case 14:
                event.getLocation().getBlock().getRelative(BlockFace.DOWN).setType(Material.PODZOL);
                break;

            case 15:
                event.getLocation().getBlock().getRelative(BlockFace.DOWN).setType(Material.END_STONE);
                break;

            case 16:
                event.getLocation().getBlock().getRelative(BlockFace.DOWN).setType(Material.CRIMSON_NYLIUM);
                break;
            case 17:
                event.getLocation().getBlock().getRelative(BlockFace.DOWN).setType(Material.WARPED_NYLIUM);
                break;

            default:
        }

        event.getLocation().getBlock().setType(Material.AIR);
        world.generateTree(location, treeTypes.get(randomTreeType));
    }


    @EventHandler
    public void chickenLayBombs(EntityDropItemEvent event) {
        if(Main.worldevent.is_start()) {
            if (event.getEntityType() == EntityType.CHICKEN
                    && event.getItemDrop().getItemStack().getType() == Material.EGG) {
                java.util.Random random = new java.util.Random();
                random.setSeed(random.nextInt(999999));

                if (random.nextInt(10) >= 9) {

                    event.getItemDrop().getItemStack().setType(Material.AIR);
                    Location location = event.getEntity().getLocation();
                    World world = location.getWorld();
                    world.spawnEntity(location, EntityType.PRIMED_TNT);
                }
            }
        }
    }



    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (Main.worldevent.is_start()) {
            Location location = event.getEntity().getLocation();
            Firework firework = (Firework) location.getWorld().spawn(location, Firework.class);
            FireworkMeta meta = firework.getFireworkMeta();
            meta.addEffect(FireworkEffect.builder()
                    .withColor(org.bukkit.Color.RED)
                    .withColor(org.bukkit.Color.ORANGE)
                    .withColor(org.bukkit.Color.YELLOW)
                    .withColor(org.bukkit.Color.GREEN)
                    .withColor(org.bukkit.Color.BLUE)
                    .withColor(org.bukkit.Color.PURPLE)
                    .withTrail()
                    .withFlicker()
                    .build());
            meta.setPower(0);
            firework.setFireworkMeta(meta);
        }
    }


    @EventHandler
    public void liftedPressurePlates (PlayerInteractEvent event) {

        if (Main.worldevent.is_start()) {
            if (event.getClickedBlock() != null) {
                Player player = event.getPlayer();
                PotionEffect effect = null;

                switch (event.getClickedBlock().getType()) {

                    case OAK_PRESSURE_PLATE:
                        effect = new PotionEffect(PotionEffectType.LEVITATION, 30, 10);
                        break;

                    case SPRUCE_PRESSURE_PLATE:
                        effect = new PotionEffect(PotionEffectType.LEVITATION, 60, 20);
                        break;

                    case ACACIA_PRESSURE_PLATE:
                        effect = new PotionEffect(PotionEffectType.LEVITATION, 80, 12);
                        break;

                    case STONE_PRESSURE_PLATE:
                        effect = new PotionEffect(PotionEffectType.LEVITATION, 20, 10);
                        break;

                    case LIGHT_WEIGHTED_PRESSURE_PLATE:
                        effect = new PotionEffect(PotionEffectType.LEVITATION, 50, 1);
                        break;

                    case HEAVY_WEIGHTED_PRESSURE_PLATE:
                        effect = new PotionEffect(PotionEffectType.LEVITATION, 100, 1);
                        break;

                    default:
                        break;

                }
                if (effect != null) {
                    player.addPotionEffect(effect);
                }
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if(p != null) {
            UserBoardManage.add(p);
            PlayerTab.update_header_and_footer(p);
            Main.set_team(p);
            if(Main.worldevent.is_start()) {
                if(RejoinMap.have(p)) {
                    RejoinMap.remove(p);
                    Send.send(p,"&eBạn đã kết nối lại!", Sound.ENTITY_EXPERIENCE_ORB_PICKUP);
                } else {
                    PlayerUtils.back_to_spawn(p);
                }
            }
            e.setJoinMessage(Color.tran("&8(&a+&8) &f" + p.getName() + " &eđã gia nhập trò chơi"));
        }
    }
    @EventHandler
    public void onDrop(EntityDeathEvent e) {
            if (!(e.getEntity() instanceof Player)) {
                if(Main.worldevent.is_start()) {
                e.getDrops().clear();
                RandomMap.getRandom(e.getEntity().getLocation());
            }
        }
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if(p != null) {
            e.setQuitMessage(Color.tran("&8(&c-&8) &f" + p.getName() + " &eđã từ bỏ trò chơi"));

            if(Main.worldevent.is_start()) {
                RejoinMap.add(p);
            }
        }

    }

    @EventHandler
    public void onPlayewrChangeWorld(PlayerChangedWorldEvent e) {
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(p != null) {
                p.playSound(p.getLocation(),Sound.BLOCK_BELL_USE,100,1);
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {

        Player p = e.getPlayer();
        Block b  = e.getBlock();

        //world guand check
        if(!WorldGuardAPI.check(p,b) && !p.isOp()) {
            return;
        }
        if(!Main.worldevent.is_start()) {
            return;
        }
        if(BLACKLIST_BLOCK.contains(b.getType())) {
            return;
        }

        if(b.getType().name().contains("CHEST")
                || b.getType().name().contains("SHULKER")
                || b.getType().name().contains("BED")
                || b.getType().name().contains("MINECAR")) {
            return;
        }
        e.setDropItems(false);
        if(p.getInventory() != null) {
            ItemStack item = p.getInventory().getItemInMainHand();

                if(!(e.getBlock().getDrops(item).size() <= 0)) {
                    if(!RandomMap.getRandom(b.getLocation())) {
                        for(ItemStack i : e.getBlock().getDrops(item)) {
                            b.getWorld().dropItemNaturally(b.getLocation(),i);
                        }
                    }
               }
        }

    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        if(Main.worldevent.is_start()) {
            if(p.getBedSpawnLocation() == null) {
                e.setRespawnLocation(p.getLocation());
            } else {
                p.teleport(p.getBedSpawnLocation());
            }

        } else {
            PlayerUtils.back_to_spawn(p);
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            if (e.getDamager() instanceof Player)
            {
                if (!Main.worldevent.isPVP()) {
                    e.setCancelled(true);
                }
            }
            if (e.getDamager() instanceof Projectile) {
                Projectile projectile = (Projectile)e.getDamager();
                if (projectile.getShooter() instanceof Player)  {
                    if (!Main.worldevent.isPVP()) {
                        e.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onChat(PlayerChatEvent e) {
        Player p = e.getPlayer();
        if(p.getGameMode() == GameMode.SPECTATOR) {
            Bukkit.getServer().broadcastMessage(Color.tran("&8(&7&lKhán Giả&8) &f" + p.getName() + "&8: &f" + e.getMessage()));
        } else {
            Bukkit.getServer().broadcastMessage(Color.tran("&8(&&#ff0000&lR&#ff9900&la&#99ff00&ln&#006699&ld&#3c009b&lo&#9400d3&lm&8) &f" + p.getName() + "&8: &f" + e.getMessage()));
        }
        e.setCancelled(true);
    }

    @EventHandler
    public void onChat(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if(p.getLocation() != null) {
            Location loc = p.getLocation().clone().add(0,-1,0);
            if(loc!= null && loc.getBlock() != null && loc.getBlock().getType() == Material.IRON_BLOCK) {
                PotionEffect potionEffect = new PotionEffect(PotionEffectType.SPEED, 10, 10);
                potionEffect.apply(p);
                PotionEffect potionEffect1 = new PotionEffect(PotionEffectType.GLOWING, 10, 5);
                potionEffect1.apply(p);
                new ParticleBuilder(ParticleEffect.TOTEM, p.getLocation())
                        .setSpeed(0.1f)
                        .setOffsetY(1)
                        .display();
            }
        }
    }

}
