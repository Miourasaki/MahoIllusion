package net.mioruasaki.mahoillusion.occupation.fireelement;

import net.mioruasaki.mahoillusion.occupation.OccupationCommon;
import net.mioruasaki.mahoillusion.occupation.OccupationType;
import net.mioruasaki.mahoillusion.occupation.fireelement.maho.Emberblade;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.enchantments.Enchantment;
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

public class CalcinedWeapons implements Listener {

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {

        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            ArrayList<Material> activeWeapons = new ArrayList<>();
            activeWeapons.add(Material.IRON_SWORD);
            activeWeapons.add(Material.GOLDEN_SWORD);
            activeWeapons.add(Material.NETHERITE_SWORD);

            ItemStack mainHandItem = player.getInventory().getItemInMainHand();

            if (OccupationType.FIRE_ELEMENT.eqByPlayer(player) &&
                event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK &&
                activeWeapons.contains(mainHandItem.getType())
            ) {
                Entity entity = event.getEntity();

                // 煅烧兵器
                int witherSecond = 20;
                if (player.getFireTicks() > 0) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60, 1));  // 生命回复 I
                    witherSecond = 40;
                    entity.setFireTicks(entity.getFireTicks() + 120);
                    entity.getWorld().spawnParticle(Particle.FLAME, entity.getLocation().add(0, 1.5, 0), 6, 0.1, 0.1, 0.1, 0.05);
                }
                ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, witherSecond, 0));  // 凋零 I
                entity.getWorld().spawnParticle(Particle.SMOKE, entity.getLocation().add(0, 1.5, 0), 6, 0.1, 0.1, 0.1, 0.05);

                if (mainHandItem.getEnchantments().containsKey(Enchantment.FIRE_ASPECT)) {
                    entity.getWorld().spawnParticle(Particle.FLAME, entity.getLocation().add(0, 1.5, 0), witherSecond * 2, 0.1, 0.1, 0.1, 0.5);
                    entity.getWorld().createExplosion( entity.getLocation().add(0,0.8,0), (float) witherSecond / 30, false, false, player);
                }
                // 烬灭之刃
                if (OccupationCommon.getPoint(player) > 25f && player.getFireTicks() > 0) {
                    try {
                        player.sendMessage("释放");
                        Emberblade.create(player);
                    } catch (Exception ignored) {}
                    OccupationCommon.setPoint(player, 0f);
                }

            }
        }
    }
}
