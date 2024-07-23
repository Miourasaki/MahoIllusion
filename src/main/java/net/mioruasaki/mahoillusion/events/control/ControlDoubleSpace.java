package net.mioruasaki.mahoillusion.events.control;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import net.mioruasaki.mahoillusion.MahoIllusion;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class ControlDoubleSpace implements Listener {

    @EventHandler
    public void onPlayerPassSpace(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
        if (Control.usePlayers.contains(player)) {
            for (ControlListener li : ControlManager.getListeners()) {
                ControlListener.CallResult callResult = li.onPressDoubleSpace(player);

                BukkitRunnable runnable = new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (callResult.run != null)
                            callResult.run.run();
                    }
                };
                runnable.runTaskLater(MahoIllusion.getInstance(), 1);

                if (callResult.cancelled) event.setCancelled(true);
                if (callResult.load) Control.usePlayers.remove(event.getPlayer());
            }
        }else {
            for (ControlListener li : ControlManager.getListeners()) {
                ControlListener.CallResult callResult = li.onJustPressDoubleSpace(player);

                BukkitRunnable runnable = new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (callResult.run != null)
                            callResult.run.run();
                    }
                };
                runnable.runTaskLater(MahoIllusion.getInstance(), 1);

                if (callResult.load) Control.usePlayers.remove(event.getPlayer());
            }
        }
    }



}
