package org.example.building;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class BuildingListener implements Listener {

    private Block lastTargetBlock = null;
    private HashMap<UUID, Long> cooldowns = new HashMap<>();
    private HashMap<UUID, BukkitRunnable> particleTasks = new HashMap<>();
    private HashMap<UUID, Boolean> hasSentMessage = new HashMap<>();

    private static final int COOLDOWN_TIME = 2000;

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        long currentTime = System.currentTimeMillis();

        if (cooldowns.containsKey(playerUUID) && currentTime - cooldowns.get(playerUUID) < COOLDOWN_TIME) {
            return;
        }

        if (player.getInventory().getItemInMainHand().getType() == Material.STICK &&
                "Builder Stick".equals(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName()) &&
                event.getAction() == Action.RIGHT_CLICK_BLOCK) {

            Block targetBlock = event.getClickedBlock();
            if (targetBlock == null || targetBlock.getType() == Material.AIR) {
                player.sendMessage(ChatColor.RED + "Нет целевого блока для постройки.");
                return;
            }

            // Получаем направление, в которое смотрит игрок
            org.bukkit.block.BlockFace playerFacing = player.getFacing();

            boolean blockExistsNearby = false;

            // Проверка наличия блока в радиусе 1 блока от целевых блоков
            for (int y = 1; y < 3; y++) {
                for (int x = 0; x < 2; x++) {
                    for (int z = 0; z < 2; z++) {
                        Block blockToPlace;
                        if (playerFacing == org.bukkit.block.BlockFace.NORTH) {
                            blockToPlace = targetBlock.getRelative(x, y, -z);
                        } else if (playerFacing == BlockFace.WEST) {
                            blockToPlace = targetBlock.getRelative(-x, y, z);
                        } else {
                            blockToPlace = targetBlock.getRelative(x * playerFacing.getModX(), y, z * playerFacing.getModZ());
                        }
                        for (int dx = -1; dx <= 1; dx++) {
                            for (int dz = -1; dz <= 1; dz++) {
                                Block adjacentBlock = blockToPlace.getRelative(dx, 0, dz);
                                if ((dx != 0 || dz != 0) && adjacentBlock.getType() != Material.AIR) {
                                    blockExistsNearby = true;
                                    break;
                                }
                            }
                            if (blockExistsNearby) break;
                        }
                        if (blockExistsNearby) break;
                    }
                    if (blockExistsNearby) break;
                }
                if (blockExistsNearby) break;
            }
            if (blockExistsNearby) {
                if (!hasSentMessage.getOrDefault(playerUUID, false)) {
                    player.sendMessage(ChatColor.RED + "Постройка невозможна: блок уже существует рядом.");
                    hasSentMessage.put(playerUUID, true);
                }
                return;
            } else {
                hasSentMessage.put(playerUUID, false);
            }

            new BukkitRunnable() {
                int y = 1;
                int x = 0;
                int z = 0;

                @Override
                public void run() {
                    if (y >= 3) {
                        cancel();
                        return;
                    }
                    Block block;
                    if (playerFacing == org.bukkit.block.BlockFace.NORTH) {
                        block = targetBlock.getRelative(x, y, -z);
                    } else if (playerFacing == org.bukkit.block.BlockFace.WEST) {
                        block = targetBlock.getRelative(-x, y, z);
                    } else {
                        block = targetBlock.getRelative(x, y, z);
                    }
                    block.setType(Material.DIAMOND_BLOCK);
                    z++;
                    if (z >= 2) {
                        z = 0;
                        x++;
                        if (x >= 2) {
                            x = 0;
                            y++;
                        }
                    }
                }
            }.runTaskTimer(JavaPlugin.getProvidingPlugin(getClass()), 0L, 10L);
            cooldowns.put(playerUUID, currentTime);
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();

        if (player.getInventory().getItemInMainHand().getType() == Material.STICK &&
                "Builder Stick".equals(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName())) {
            Block targetBlock = player.getTargetBlockExact(5);
            if (targetBlock == null || targetBlock.getType() == Material.AIR || targetBlock.equals(lastTargetBlock)) {
                return;
            }
            lastTargetBlock = targetBlock;

            if (particleTasks.containsKey(playerUUID)) {
                particleTasks.get(playerUUID).cancel();
                particleTasks.remove(playerUUID);
            }

            Particle particleType = Particle.DOLPHIN;
            BukkitRunnable task = new BukkitRunnable() {
                @Override
                public void run() {
                    generateParticleOutline(targetBlock, player);
                }
            };
            task.runTaskTimer(JavaPlugin.getProvidingPlugin(getClass()), 0L, 2L);
            particleTasks.put(playerUUID, task);
        } else {
            if (particleTasks.containsKey(playerUUID)) {
                particleTasks.get(playerUUID).cancel();
                particleTasks.remove(playerUUID);
            }
        }
    }

    @EventHandler
    public void onPlayerItemHeld(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();

        if (particleTasks.containsKey(playerUUID)) {
            particleTasks.get(playerUUID).cancel();
            particleTasks.remove(playerUUID);
        }
    }

    private void generateParticleOutline(Block block, Player player) {
        World world = block.getWorld();
        double x = block.getX();
        double y = block.getY();
        double z = block.getZ();
        Location corner1 = new Location(world, x, y + 1, z);
        Location corner2;

        org.bukkit.block.BlockFace playerFacing = player.getFacing();
        int length = 2;
        int width = 2;
        int height = 3;

        if (playerFacing == org.bukkit.block.BlockFace.NORTH) {
            corner1 = new Location(world, x, y + 1, z + 1);
            corner2 = new Location(world, x + width, y + height, z - length + 1);
        } else if (playerFacing == org.bukkit.block.BlockFace.SOUTH) {
            corner2 = new Location(world, x + width, y + height, z + length);
        } else if (playerFacing == org.bukkit.block.BlockFace.WEST) {
            corner1 = new Location(world, x + 1, y + 1, z);
            corner2 = new Location(world, x - length + 1, y + height, z + width);
        } else { // EAST
            corner2 = new Location(world, x + length, y + height, z + width);
        }

        List<Location> outlineLocations = getCubeOutline(corner1, corner2, 0.1); // Уменьшенное расстояние между партиклами для лучшей видимости

        for (Location location : outlineLocations) {
            world.spawnParticle(Particle.DOLPHIN, location.getX(), location.getY(), location.getZ(), 1);
        }
    }

    private List<Location> getCubeOutline(Location corner1, Location corner2, double particleDistance) {
        List<Location> result = new ArrayList<>();
        World world = corner1.getWorld();
        double minX = Math.min(corner1.getX(), corner2.getX());
        double minY = Math.min(corner1.getY(), corner2.getY());
        double minZ = Math.min(corner1.getZ(), corner2.getZ());
        double maxX = Math.max(corner1.getX(), corner2.getX());
        double maxY = Math.max(corner1.getY(), corner2.getY());
        double maxZ = Math.max(corner1.getZ(), corner2.getZ());

        for (double x = minX; x <= maxX; x += particleDistance) {
            result.add(new Location(world, x, minY, minZ));
            result.add(new Location(world, x, minY, maxZ));
            result.add(new Location(world, x, maxY, minZ));
            result.add(new Location(world, x, maxY, maxZ));
        }

        for (double y = minY; y <= maxY; y += particleDistance) {
            result.add(new Location(world, minX, y, minZ));
            result.add(new Location(world, minX, y, maxZ));
            result.add(new Location(world, maxX, y, minZ));
            result.add(new Location(world, maxX, y, maxZ));
        }

        for (double z = minZ; z <= maxZ; z += particleDistance) {
            result.add(new Location(world, minX, minY, z));
            result.add(new Location(world, minX, maxY, z));
            result.add(new Location(world, maxX, minY, z));
            result.add(new Location(world, maxX, maxY, z));
        }

        result.add(new Location(world, minX, minY, minZ));
        result.add(new Location(world, minX, minY, maxZ));
        result.add(new Location(world, maxX, minY, minZ));
        result.add(new Location(world, maxX, minY, maxZ));

        return result;
    }
}
