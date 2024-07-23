package net.mioruasaki.mahoillusion.occupation.paladin.maho;

import net.mioruasaki.mahoillusion.MahoIllusion;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Collection;

public class DivineImpact {
    public static void create(Player player) {
        BukkitScheduler scheduler = MahoIllusion.getInstance().getServer().getScheduler();
        scheduler.runTask(MahoIllusion.getInstance(), () -> {

            Location spawnLocation = player.getLocation().add(0,0.5,0); //

            playAnimation(spawnLocation, 5, player);

        });
    }

    private static void playAnimation(Location location, int maxDistance, Player player) {
        final int[] distanceMoved = {0};

        BukkitScheduler scheduler = MahoIllusion.getInstance().getServer().getScheduler();

        final int[] taskId = new int[1];
        taskId[0] = scheduler.runTaskTimer(MahoIllusion.getInstance(), () -> {
            if (distanceMoved[0] > maxDistance) {
                beHitEntity.clear();
                scheduler.cancelTask(taskId[0]); // 达到最大距离后取消任务
                return;
            }


            location.add(0,0.05 + (double) distanceMoved[0] /10,0).setPitch(0);


            for (float i = 0 ; i < 360; i ++) {
                if (distanceMoved[0] == 0) continue;
                float yaw = i;

                location.setYaw(yaw);
                Vector picVector = location.clone().getDirection().multiply(distanceMoved[0]*2);
                Location picLocation = location.clone().add(picVector);
                if (distanceMoved[0] < 4) {
                    picLocation.getWorld().spawnParticle(Particle.END_ROD, picLocation, 1, 0, 0, 0, 0);
                    picLocation.getWorld().spawnParticle(Particle.TOTEM_OF_UNDYING, picLocation, 1, 0.01, 0, 0.01, 0.1);
                }

                for (int j = 1 ; j<=2 ; j++) hitEntity(location.clone().add(location.getDirection().multiply(j)), player, picVector, (distanceMoved[0] + 1));
            }

            distanceMoved[0] += 1;
        }, 0L, 2L).getTaskId(); // 10L表示每10 ticks（1 tick = 50毫秒）执行一次，这里可以根据需要调整


    }

    private static Collection<Entity> beHitEntity = new ArrayList<>();

    private static void hitEntity(Location location, Player player, Vector vector, int power) {
        Collection<Entity> nearbyEntities = location.getWorld().getNearbyEntities(location,2.5 , 2, 2.5); // 在2个方块半径内寻找附近的实体

        for (Entity nearbyEntity : nearbyEntities) {
            if (nearbyEntity.equals(player)) {
                continue; // 跳过自身
            }
            if (nearbyEntity instanceof LivingEntity && !beHitEntity.contains(nearbyEntity)) {
                LivingEntity entity = (LivingEntity) nearbyEntity;
                entity.damage(1, player);
//                if (entity.getHealth() <= 5) entity.setHealth(0);
//                else entity.setHealth(entity.getHealth() - 5);

                nearbyEntity.setVelocity(vector.multiply(0.5).add(new Vector(0,0.6,0)));
                beHitEntity.add(nearbyEntity);
            }

        }

    }

}
