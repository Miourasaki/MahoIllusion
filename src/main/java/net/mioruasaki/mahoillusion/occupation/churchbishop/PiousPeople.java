package net.mioruasaki.mahoillusion.occupation.churchbishop;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PiousPeople {

    public static void runTask() {
        for (Player player: new ChurchBishop().getPlayers()) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 25, 2));  // 虚弱 III
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 25, 0));  // 生命回复 I
            player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 25, 0));  // 抗性提升 I
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 25, 0));  // 迅捷 I

            for (Entity entity : player.getNearbyEntities(10,10,10)) {
                if (entity.getUniqueId() == player.getUniqueId()) continue;
                if (entity instanceof LivingEntity)
                    ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 25, 1));  // 虚弱 III
            }

        }
    }

}
