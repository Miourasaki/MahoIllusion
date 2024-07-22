package net.mioruasaki.mahoillusion.occupation.fightingwildly;

import net.mioruasaki.mahoillusion.occupation.OccupationCommon;
import net.mioruasaki.mahoillusion.occupation.OccupationType;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class SpeedBlade implements Listener {


    static HashMap<UUID, Long> damageTimeMap = new HashMap<>();

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {

        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            ArrayList<Material> activeWeapons = new ArrayList<>();
            activeWeapons.add(Material.WOODEN_SWORD);
            activeWeapons.add(Material.STONE_SWORD);
            activeWeapons.add(Material.IRON_SWORD);
            activeWeapons.add(Material.GOLDEN_SWORD);
            activeWeapons.add(Material.DIAMOND_SWORD);
            activeWeapons.add(Material.NETHERITE_SWORD);

            ItemStack mainHandItem = player.getInventory().getItemInMainHand();

            if (OccupationType.FIGHTING_WILDLY.eqByPlayer(player) &&
                    event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK &&
                    activeWeapons.contains(mainHandItem.getType())
            ) {
                Entity entity = event.getEntity();

                if (new Random().nextInt(4) == 1) {
                    ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 20, 0));
                    ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 20, 9));
                    OccupationCommon.addPoint(player, 1f);
                }

                Long damageTime = damageTimeMap.get(player.getUniqueId());
                if (damageTime != null && damageTime + 3000 > System.currentTimeMillis()) {

                    int rand = new Random().nextInt(100);
                    if (rand < 15) {
                        player.setHealth(player.getHealth() + 2);

                        OccupationCommon.addPoint(player, 1f);
                    }else if (rand < 45) {
                        player.setHealth(player.getHealth() + 1);
                        OccupationCommon.addPoint(player, 1f);
                    }

                }


            }
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (OccupationType.FIGHTING_WILDLY.eqByPlayer(player)) {
                damageTimeMap.put(player.getUniqueId(), System.currentTimeMillis());
            }
        }
    }

}
