package net.mioruasaki.mahoillusion.options;

import net.mioruasaki.mahoillusion.MahoIllusion;
import net.mioruasaki.mahoillusion.occupation.OccupationType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;

public class Ceremonya implements Listener {
    // 创建一个箱子菜单
     static public void openCeremonyMenu(Player player) {
//        Inventory menu = Bukkit.createInventory(player, 9, OccupationType.LORE_LIBRARY.getInstance().getSub());
//
//        // 向菜单中添加一些物品
//        ItemStack diamond = new ItemStack(Material.DIAMOND);
//        ItemStack goldIngot = new ItemStack(Material.GOLD_INGOT);
//        ItemStack emerald = new ItemStack(Material.EMERALD);
//
//        menu.setItem(0, diamond);
//        menu.setItem(4, goldIngot);
//        menu.setItem(8, emerald);
//
//        player.openInventory(menu);
    }


    // 处理点击事件
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        Inventory clickedInventory = event.getClickedInventory();

        if (clickedInventory != null && clickedInventory.getLocation() == null) {
            event.setCancelled(true); // 取消点击事件，防止玩家移动物品

            ItemStack clickedItem = event.getCurrentItem();

            // 处理点击事件
            if (clickedItem != null && clickedItem.getType() != Material.AIR) {
                player.sendMessage("You clicked: " + clickedItem.getType());
            }
        }
    }



}
