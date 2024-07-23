package net.mioruasaki.mahoillusion.occupation.bettingdog;

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

import static org.apache.commons.lang3.RandomUtils.nextInt;

public class DoubleEdged implements Listener {



    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {

        if (event.getDamager() instanceof Player && event.getEntity() instanceof LivingEntity) {
            Player player = (Player) event.getDamager();
            Entity entity = event.getEntity();
            ArrayList<Material> activeWeapons = new ArrayList<>();
            activeWeapons.add(Material.WOODEN_SWORD);
            activeWeapons.add(Material.STONE_SWORD);
            activeWeapons.add(Material.IRON_SWORD);
            activeWeapons.add(Material.GOLDEN_SWORD);
            activeWeapons.add(Material.DIAMOND_SWORD);
            activeWeapons.add(Material.NETHERITE_SWORD);

            ItemStack mainHandItem = player.getInventory().getItemInMainHand();

            if (OccupationType.BETTING_DOG.eqByPlayer(player) &&
                    activeWeapons.contains(mainHandItem.getType())
            ) {
                Random random = new Random();
                int clearHealth = random.nextInt(4);

                LivingEntity target;
                if (random.nextInt(2) < 1) {
                    target = (LivingEntity) entity;
                    if (target.getHealth() <= clearHealth && target.getHealth() > 0) target.setHealth(0);
                    else target.setHealth(target.getHealth() - clearHealth);
                    OccupationCommon.addPoint(player, 1F);
                }else {
                    if (player.getHealth() <= 0 || player.isDead()) {
                        event.setCancelled(true);
                    }
                    player.damage(clearHealth, player);
                }




            }
        }
    }

}
