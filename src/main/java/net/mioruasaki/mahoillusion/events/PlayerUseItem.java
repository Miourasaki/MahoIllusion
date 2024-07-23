package net.mioruasaki.mahoillusion.events;

import net.mioruasaki.mahoillusion.occupation.fireelement.maho.Emberblade;
import net.mioruasaki.mahoillusion.common.utils.maho.TestMaho;
import net.mioruasaki.mahoillusion.common.utils.maho.TestMahoa;
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
                            case GOLDEN_HOE: {
                                Emberblade.create(player);
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
