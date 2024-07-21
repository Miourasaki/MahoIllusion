package net.mioruasaki.mahoillusion.occupation.season;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.mioruasaki.mahoillusion.MahoIllusion;
import net.mioruasaki.mahoillusion.occupation.Occupation;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class SeaSon extends Occupation {
    public static ItemStack itemStack = new ItemStack(Material.TRIDENT);

    @Override
    public void onPlayerJoin(Player player) {
        player.getInventory().addItem(itemStack);
    }

    @Override
    public void onLoad(MahoIllusion instance) {
        itemStack.addUnsafeEnchantment(Enchantment.LOYALTY, 3);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE); // Hide the unbreakable tag from lore
        meta.setDisplayName(ChatColor.YELLOW + "Maris III Trident");

        itemStack.setItemMeta(meta);

    }

    MiniMessage mm = MiniMessage.miniMessage();
    @Override
    public Component getSub() {
        return mm.deserialize("<underlined><bold><gradient:#5e4fa2:#f79459:red>海之子");
    }

    @Override
    public ArrayList<Component> getDescription() {
        return new ArrayList<>();
    }

    @Override
    public void runSecond() {
        OceanResonance.runTask();
    }
}
