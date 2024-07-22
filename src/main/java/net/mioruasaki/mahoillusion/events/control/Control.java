package net.mioruasaki.mahoillusion.events.control;

import net.mioruasaki.mahoillusion.MahoIllusion;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Boss;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Control implements Listener {

    static ArrayList<Player> usePlayers = new ArrayList<>();
    HashMap<UUID, Long> playerSneakMap = new HashMap<>();

    @EventHandler
    void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
        playerSneakMap.put(event.getPlayer().getUniqueId(), System.currentTimeMillis());
    }


    @EventHandler
    void onPlayerItemHeld(PlayerSwapHandItemsEvent event) {
        long sneakTime = playerSneakMap.get(event.getPlayer().getUniqueId()) + 200;
        if (System.currentTimeMillis() < sneakTime) {
            if (usePlayers.contains(event.getPlayer())) {
                for (ControlListener li : ControlManager.getListeners()) {
                    if (li.onPressShiftAddF(event.getPlayer())) {
                        event.setCancelled(true);
                        usePlayers.remove(event.getPlayer());
                    }
                }
            }else {
                playSound(event.getPlayer());
                usePlayers.add(event.getPlayer());
                event.setCancelled(true);
            }
        }
    }

    public static void playSound(Player player) {
        player.playSound(player.getLocation() ,Sound.ENTITY_EXPERIENCE_ORB_PICKUP,0.2f,1);
    }

    static BossBar controlBar = Bukkit.createBossBar("～+༺༃少女祈祷中(请按下组合键)༃༻+～", BarColor.PINK, BarStyle.SOLID, BarFlag.CREATE_FOG);
    public static void onLoad(MahoIllusion illusion) {
        for (ControlListener cl : ControlManager.getListeners())
            cl.onLoad(illusion);

        controlBar.setProgress(1);
        ControlManager.registerListener(new ControlCancel());

        illusion.getServer().getPluginManager().registerEvents(new Control(), illusion);
        illusion.getServer().getPluginManager().registerEvents(new ControlQ(), illusion);
    }
    public static void runSecond() {
        for (ControlListener cl : ControlManager.getListeners())
            cl.runSecond();
    }
    public static void runTask() {
        for (ControlListener cl : ControlManager.getListeners())
            cl.runTick();
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (usePlayers.contains(player)) {
                controlBar.addPlayer(player);
            }else {
                controlBar.removePlayer(player);
            }
        }
    }

}
