package net.mioruasaki.mahoillusion.occupation.fightingwildly;

import io.papermc.paper.event.player.PlayerInventorySlotChangeEvent;
import net.mioruasaki.mahoillusion.MahoIllusion;
import net.mioruasaki.mahoillusion.occupation.OccupationType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class ViolenceProtection implements Listener {


    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (OccupationType.FIGHTING_WILDLY.eqByPlayer(player)) {
                if (player.getHealth() <= 1) player.setHealth(0);
                else player.setHealth(player.getHealth() - 1);
            }
        }
    }


    public static void runTick() {
        for (Player player: new FightingWildly().getPlayers()) {
        if (player.getInventory().getItemInOffHand().getType() == Material.SHIELD) {
            player.getInventory().addItem(player.getInventory().getItemInOffHand());
            player.getInventory().setItem(40, new ItemStack(Material.AIR));
        }
        }
    }

    public static void runTask() {
        for (Player player: Bukkit.getOnlinePlayers()) {
            if (OccupationType.FIGHTING_WILDLY.eqByPlayer(player)) {
                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ATTACK_SPEED)).setBaseValue(6);


                player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 25, 0));
                player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 25, 0));
                player.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, 25, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 25, 1));
            }else {
                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ATTACK_SPEED)).setBaseValue(4);
            }
        }
    }


}
