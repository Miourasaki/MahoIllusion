package net.mioruasaki.mahoillusion.options;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.mioruasaki.mahoillusion.MahoIllusion;
import net.mioruasaki.mahoillusion.occupation.Occupation;
import net.mioruasaki.mahoillusion.occupation.OccupationType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.ArrayList;


public class Ceremony implements Listener {
    // 打开仪式书
     static public void openCeremonyMenu(Player player) {

         // Create a book menu
         ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
         BookMeta meta = (BookMeta) book.getItemMeta();

         ArrayList<Component> components = new ArrayList<>();
         for (OccupationType type : OccupationType.values()) {

             Occupation occupation = type.getInstance();

             Component button = Component.text("[选择]")
                     .clickEvent(ClickEvent.runCommand(String.format("/occupation set %s", type)));
             TextComponent component = Component.text()
                     .append(occupation.getSub())
                     .appendNewline()
                     .appendNewline()
                     .appendNewline()
                     .appendNewline()
                     .appendNewline()
                     .appendNewline()
                     .appendNewline()
                     .appendNewline()
                     .appendNewline()
                     .appendNewline()
                     .appendNewline()
                     .appendNewline()
                     .appendNewline()
                     .append(Component.text("  "))
                     .append(button)
                     .build();
             components.add(component);
             components.addAll(occupation.getDescription());
         }
         BookMeta finishMeta = meta.toBuilder()
                 .pages(components)
                 .title(Component.text("元素仪式指南"))
                 .author(Component.text("白澪 · 卡丝蕾特"))
                 .build();
         book.setItemMeta(finishMeta);

        player.openBook(book);
    }

    @EventHandler
    public void onEvent(PlayerAnimationEvent event) {
        PersistentDataContainer container = event.getPlayer().getPersistentDataContainer();

        OccupationType occupation = container.get(MahoIllusion.getKey("occupation"), OccupationType.INSTANCE);
        if (occupation == null) {
            Ceremony.openCeremonyMenu(event.getPlayer());
        }
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        PersistentDataContainer container = player.getPersistentDataContainer();

        OccupationType occupation = container.get(MahoIllusion.getKey("occupation"), OccupationType.INSTANCE);
        System.out.println(occupation);
        if (occupation == null) {
            Ceremony.openCeremonyMenu(player);
        }
    }

}
