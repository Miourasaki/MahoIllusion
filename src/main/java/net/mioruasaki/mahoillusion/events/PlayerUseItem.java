package net.mioruasaki.mahoillusion.events;

import net.mioruasaki.mahoillusion.MahoIllusion;
import net.mioruasaki.mahoillusion.maho.TestMaho;
import net.mioruasaki.mahoillusion.maho.TestMahoa;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerUseItem implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {

                if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_AIR) {
                    // 玩家右键点击了方块、空气或实体

                    Player player = event.getPlayer();

                    if (event.getItem() != null) {

                        switch (event.getItem().getType()) {
                            case ENCHANTED_BOOK: {
                                TestMahoa.testMaho(player);
                                event.setCancelled(false);
                                break;
                            }
                            case STONE_HOE: {
                                TestMaho.testMaho(player);
                                event.setCancelled(false);
                                break;
                            }
                            default:
                                break;
                        }
                    }
                }
    }

}
