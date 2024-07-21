package net.mioruasaki.mahoillusion.occupation.fightingwildly;

import net.mioruasaki.mahoillusion.occupation.OccupationType;
import net.mioruasaki.mahoillusion.occupation.season.SeaSon;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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

    @EventHandler
    public void onPlayerItemHeld(PlayerSwapHandItemsEvent event) {

        // 获取玩家在新槽位上的物品
        ItemStack newItem = event.getOffHandItem();

        // 检查新物品是否是盾牌
        if (newItem.getType() == Material.SHIELD) {
            event.setCancelled(true);
        }
    }


    public static void runTask() {
        for (Player player: new FightingWildly().getPlayers()) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 25, 0));
            player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 25, 0));
            player.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, 25, 1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 25, 1));
        }
    }


}
