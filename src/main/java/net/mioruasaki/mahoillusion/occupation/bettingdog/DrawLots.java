package net.mioruasaki.mahoillusion.occupation.bettingdog;

import net.mioruasaki.mahoillusion.common.utils.maho.TestMahoa;
import net.mioruasaki.mahoillusion.occupation.OccupationType;
import net.mioruasaki.mahoillusion.occupation.bettingdog.maho.FireLots;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class DrawLots implements Listener {


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {

        ItemStack item = event.getItem();
        if (OccupationType.BETTING_DOG.eqByPlayer(event.getPlayer()) &&
                event.getAction().isRightClick() && item != null && item.getType().equals(Material.STICK)) {
            if (item.getAmount() >= 3) {
                item.setAmount(item.getAmount() - 3);

                FireLots.create(event.getPlayer());

            }else {
                event.getPlayer().sendMessage(ChatColor.RED + "木棍不足的说");
            }
        }


    }

}
