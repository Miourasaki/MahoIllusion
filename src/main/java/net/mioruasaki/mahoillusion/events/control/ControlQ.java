package net.mioruasaki.mahoillusion.events.control;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class ControlQ implements Listener {

    @EventHandler
    public void onPlayerPassQ(PlayerDropItemEvent event) {
        if (Control.usePlayers.contains(event.getPlayer())) {
            for (ControlListener li : ControlManager.getListeners()) {
                if (li.onPressQ(event.getPlayer())) {
                    event.setCancelled(true);
                    Control.usePlayers.remove(event.getPlayer());
                }
            }
        }
    }

}
