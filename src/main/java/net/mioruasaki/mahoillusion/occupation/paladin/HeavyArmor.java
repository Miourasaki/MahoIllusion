package net.mioruasaki.mahoillusion.occupation.paladin;

import net.mioruasaki.mahoillusion.occupation.churchbishop.ChurchBishop;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HeavyArmor {

    public static void runTask() {
        for (Player player: new Paladin().getPlayers()) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 25, 0));
            player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 25, 2));  // 抗性提升 III
            player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 25, 1));

        }
    }

}
