package net.mioruasaki.mahoillusion.occupation.season;

import net.mioruasaki.mahoillusion.occupation.churchbishop.ChurchBishop;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class OceanResonance {

    public static void runTask() {
        for (Player player: new SeaSon().getPlayers()) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 25, 0));  // 水下呼吸 I
            player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 25, 0));  // 海豚的恩惠 I

            if (player.getLocation().getBlock().isLiquid() || isInrain(player)) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 25, 1));  // 抗性提升 II
                player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 25, 1));  // 抗性提升 II
            }


        }
    }

    static boolean isInrain(Player player) {
        if (player.getWorld().isClearWeather()) return false;
        Location playerLocation = player.getLocation();

        // Define a range to check above the player's head
        int checkRadius = 1; // Check 1 block above the player's head

        // Iterate over the range of coordinates above the player's head
        for (int x = -checkRadius; x <= checkRadius; x++) {
            for (int y = 0; y <= checkRadius; y++) {
                for (int z = -checkRadius; z <= checkRadius; z++) {
                    Location blockLocation = playerLocation.clone().add(x, y, z);
                    Block block = blockLocation.getBlock();

                    // Check if the block is not air or a transparent block
                    if (!block.isEmpty() && !block.isPassable()) {
                        return false;
                        // You can perform additional actions here if needed
                    }
                }
            }
        }

        return true;
    }

}
