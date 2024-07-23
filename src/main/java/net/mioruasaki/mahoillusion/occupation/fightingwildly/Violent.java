package net.mioruasaki.mahoillusion.occupation.fightingwildly;

import net.mioruasaki.mahoillusion.MahoIllusion;
import net.mioruasaki.mahoillusion.events.control.ControlListener;
import net.mioruasaki.mahoillusion.occupation.OccupationType;
import net.mioruasaki.mahoillusion.occupation.SkillCommon;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class Violent extends ControlListener implements Listener {
    private static final HashMap<UUID, Long> activeMap = new HashMap<>();
    private static final Collection<Player> activePlayer = new ArrayList<>();

    @Override
    protected void onJustPassDoubleF(Player player) {
        SkillCommon.load(activeMap, 20000L, OccupationType.FIGHTING_WILDLY, player ,() -> {

            activePlayer.add(player);
            Bukkit.getServer().getScheduler().runTaskLater(MahoIllusion.getInstance(), () -> {
                activePlayer.remove(player);
            }, 120L);
            return true;
        });
    }

    static void onLoad() {
        Bukkit.getServer().getScheduler().runTaskTimer(MahoIllusion.getInstance(), Violent::runTask, 0 , 6);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        activePlayer.remove(event.getPlayer());
    }



    private static void runTask() {
        for (Player player: new FightingWildly().getPlayers()) {
            if (activePlayer.contains(player)) {

                player.setWalkSpeed(0.4F);
                Collection<Entity> nearbyEntities = player.getWorld().getNearbyEntities(player.getLocation(),6 , 4, 6); // 在2个方块半径内寻找附近的实体
                for (Entity entity: nearbyEntities) {
                    if (entity.equals(player)) continue;

                    if (entity instanceof LivingEntity) {
                        entity.getWorld().spawnParticle(Particle.SWEEP_ATTACK ,entity.getLocation() , 1 ,0 ,0 ,0 ,0);
                        ((LivingEntity) entity).damage(2, player);
                        ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 20, 0));
                }
                }
                if (new Random().nextInt(10) < 4) {
                    if (player.getHealth() <= player.getMaxHealth() - 1)
                        player.setHealth(player.getHealth() + 1);
                }

            }else {
                player.setWalkSpeed(0.2F);
            }
        }
    }

}
