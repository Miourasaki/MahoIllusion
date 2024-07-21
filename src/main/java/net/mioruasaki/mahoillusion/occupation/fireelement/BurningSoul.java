package net.mioruasaki.mahoillusion.occupation.fireelement;

import net.mioruasaki.mahoillusion.occupation.OccupationType;
import net.mioruasaki.mahoillusion.occupation.lorelibrary.FireElement;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.UUID;

public class BurningSoul implements Listener {


    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK) || event.getCause().equals(EntityDamageEvent.DamageCause.FIRE)) {
                if (OccupationType.FIRE_ELEMENT.eqByPlayer(player)) {
                    player.setFireTicks(player.getFireTicks() + 30);

                    player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 30, 1));  // 抗性提升 II
                    player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 30, 2));  // 力量 III
                    player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 30, 1));  // 生命回复 I
//                    player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 30, 1));  // 抗火 I
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 30, 1));  // 迅捷 II
                    player.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, 30, 0));  // 急迫 I

                    player.getLocation().getWorld().spawnParticle(Particle.SOUL, player.getLocation().add(0, 1.5, 0), 6, 0.1, 0.1, 0.1, 0.05);
                    player.getLocation().getWorld().spawnParticle(Particle.FLAME, player.getLocation().add(0, 1.5, 0), 6, 0.1, 0.1, 0.1, 0.05);
                }
            } else {
                player.setFireTicks(0);
            }
        }
    }


    private final HashMap<UUID, Integer> playerLastFireTicks = new HashMap<>();

    public void onTick() {


        for (Player player : new FireElement().getPlayers()) {
            Integer lastFireTicks = playerLastFireTicks.get(player.getUniqueId());
            playerLastFireTicks.remove(player.getUniqueId());
            playerLastFireTicks.put(player.getUniqueId(), player.getFireTicks());

            if (lastFireTicks != null && lastFireTicks >= 0 && player.getFireTicks() <= 0 &&
                    player.getLocation().getBlock().isLiquid()) {
                player.clearActivePotionEffects();
                player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 40, 1));  // 凋零 II
                player.damage(10);
            }
        }

    }

}
