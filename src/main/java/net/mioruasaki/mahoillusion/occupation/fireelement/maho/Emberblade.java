package net.mioruasaki.mahoillusion.occupation.fireelement.maho;

import net.mioruasaki.mahoillusion.MahoIllusion;
import net.mioruasaki.mahoillusion.common.utils.VectorUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;

import java.util.List;

public class Emberblade {
    public static void create(Player player) {
        BukkitScheduler scheduler = MahoIllusion.getInstance().getServer().getScheduler();
        Vector direction = player.getLocation().getDirection();
        scheduler.runTask(MahoIllusion.getInstance(), () -> {

            int radius = 1; // 圆的半径，可以根据需要调整大小
            int center = (radius * 2 + 1) / 2; // 圆心的 x 坐标
            for (int i = -radius; i <= radius; i++) {
                for (int j = -radius; j <= radius; j++) {

                    double distance = Math.sqrt((i - center + radius) * (i - center + radius) + (j - center + radius) * (j - center + radius));


                    if (Math.abs(distance - radius) < 0.5 || distance < radius) {
                        Vector vector = VectorUtils.rotateVectorAroundY(direction.multiply(1), 90);
                        Location spawnLocation = player.getLocation().clone().add(0, 1.7 + i/2.0, 0).add(vector.multiply(j/2)); // 在玩家上方1格生成

                        // 创建并设置盔甲架属性
                        ArmorStand armorStand = (ArmorStand) spawnLocation.getWorld().spawnEntity(spawnLocation, EntityType.ARMOR_STAND);
                        armorStand.setVisible(false); // 设置为不可见
                        armorStand.setSmall(true);
                        armorStand.setBasePlate(false);
                        armorStand.setAI(true);
                        armorStand.setGravity(false);

                        // 设置盔甲架的移动逻辑
                        moveArmorStand(armorStand, 50, direction, player);
                    }
                }
            }
        });
    }

    private static void moveArmorStand(ArmorStand armorStand, int maxDistance, Vector direction, Player player) {
        final double[] distanceMoved = {0};

        BukkitScheduler scheduler = MahoIllusion.getInstance().getServer().getScheduler();

        final int[] taskId = new int[1];
        taskId[0] = scheduler.runTaskTimer(MahoIllusion.getInstance(), () -> {
            if (distanceMoved[0] >= maxDistance) {
                armorStand.remove(); // 如果前方有阻挡，则移除盔甲架
                scheduler.cancelTask(taskId[0]); // 达到最大距离后取消任务
                return;
            }


            for (int i = 0; i <= 2; i++) {
                Location currentLocation = armorStand.getLocation();
                Location nextLocation = currentLocation.add(direction.multiply(1));
                // 生成粒子
                    nextLocation.getWorld().spawnParticle(Particle.SMOKE, nextLocation, 10, 0.01, 0.01, 0.01, 0.02);
                // 碰撞检测
                armorStand.teleport(nextLocation); // 移动盔甲架
                if (isBlocked(nextLocation) || isPlayer(armorStand, player)) {
                    armorStand.remove(); // 如果前方有阻挡，则移除盔甲架
                    scheduler.cancelTask(taskId[0]); // 碰撞后取消任务
                    return;
                }
            }


            distanceMoved[0] += 1;
        }, 0L, 1L).getTaskId(); // 10L表示每10 ticks（1 tick = 50毫秒）执行一次，这里可以根据需要调整


    }

    private static boolean isBlocked(Location location) {
        Block block = location.getBlock();
        // 检查是否为空气或可穿过方块（这里简单判断为非空气方块）
        if (block.getType() != Material.AIR) {
            return true;
        }
        return false;

        // 更复杂的碰撞检测可以包括检查其他实体等
    }

    private static boolean isPlayer(ArmorStand armorStand, Player player) {
        List<Entity> nearbyEntities = armorStand.getNearbyEntities(0.5, 0.5, 0.5); // 在2个方块半径内寻找附近的实体


        for (Entity nearbyEntity : nearbyEntities) {
            if (nearbyEntity.equals(armorStand) || nearbyEntity.equals(player)) {
                continue; // 跳过自身
            }

            // 获取附近实体的包围盒
            BoundingBox nearbyBoundingBox = nearbyEntity.getBoundingBox();

            // 检查包围盒是否重叠
            if (armorStand.getBoundingBox().overlaps(nearbyBoundingBox)) {

                try {
                    Damageable damageable = (Damageable) nearbyEntity;
                    damageable.damage(1, player);
                    final double[] i = {0};
                    final int[] taskId = new int[1];
                    BukkitScheduler scheduler = MahoIllusion.getInstance().getServer().getScheduler();
                    taskId[0] = scheduler.runTaskTimer(MahoIllusion.getInstance(), () -> {
                        if (i[0] > 4) {
                            scheduler.cancelTask(taskId[0]); // 达到最大距离后取消任务
                            return;
                        }


                        if (damageable.getHealth() > 1) {
                            damageable.setHealth(damageable.getHealth() - 1);
                        } else {
                            scheduler.cancelTask(taskId[0]); // 达到最大距离后取消任务
                            damageable.setHealth(0);
                            return;
                        }
                        damageable.getLocation().getWorld().playSound(damageable.getLocation(), Sound.ENTITY_PLAYER_HURT, 1, 0);
                        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 0);
                        i[0] += 1;
                    }, 0L, 1L).getTaskId(); // 10L表示每10 ticks（1 tick = 50毫秒）执行一次，这里可以根据需要调整

                    return true;

                } catch (Exception e) {
                    return false;

                }
            }
        }

        return false;

        // 更复杂的碰撞检测可以包括检查其他实体等
    }
}
