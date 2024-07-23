package net.mioruasaki.mahoillusion.occupation.paladin;

import net.mioruasaki.mahoillusion.events.control.ControlListener;
import net.mioruasaki.mahoillusion.occupation.OccupationCommon;
import net.mioruasaki.mahoillusion.occupation.OccupationType;
import net.mioruasaki.mahoillusion.occupation.SkillCommon;
import net.mioruasaki.mahoillusion.occupation.fightingwildly.maho.JumpshinImpact;
import net.mioruasaki.mahoillusion.occupation.fireelement.maho.Emberblade;
import net.mioruasaki.mahoillusion.occupation.paladin.maho.DivineImpact;
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
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

public class DivineJudgment implements Listener {

    private static final HashMap<UUID, Long> activeMap = new HashMap<>();
    private static final Collection<Player> activeList = new ArrayList<>();

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {

        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            ArrayList<Material> activeWeapons = new ArrayList<>();
            activeWeapons.add(Material.WOODEN_AXE);
            activeWeapons.add(Material.STONE_AXE);
            activeWeapons.add(Material.IRON_AXE);
            activeWeapons.add(Material.GOLDEN_AXE);
            activeWeapons.add(Material.DIAMOND_AXE);
            activeWeapons.add(Material.NETHERITE_AXE);

            ItemStack mainHandItem = player.getInventory().getItemInMainHand();

            if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK &&
                    activeWeapons.contains(mainHandItem.getType()) &&
                    event.isCritical()
            ) {

                SkillCommon.load(activeMap, 1500L, OccupationType.PALADIN, player, () -> {
                        DivineImpact.create(player);
                    OccupationCommon.addPoint(player, 5f);
                    return true;
                });


            }
        }
    }


}
