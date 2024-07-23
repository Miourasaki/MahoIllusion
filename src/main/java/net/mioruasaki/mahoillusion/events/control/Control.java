package net.mioruasaki.mahoillusion.events.control;

import net.mioruasaki.mahoillusion.MahoIllusion;
import net.mioruasaki.mahoillusion.events.control.template.ControlAll;
import net.mioruasaki.mahoillusion.events.control.template.ControlCancel;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Control implements Listener {

    static ArrayList<Player> usePlayers = new ArrayList<>();
    HashMap<UUID, Long> playerSneakMap = new HashMap<>();
    HashMap<UUID, Long> playerFMap = new HashMap<>();

    @EventHandler
    void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
        if (event.isSneaking())
            playerSneakMap.put(event.getPlayer().getUniqueId(), System.currentTimeMillis());
    }


    @EventHandler
    void onPlayerItemHeld(PlayerSwapHandItemsEvent event) {
        Long sneakTime = playerSneakMap.get(event.getPlayer().getUniqueId());
        if (sneakTime !=null && System.currentTimeMillis() < sneakTime  + 150) {
            playerSneakMap.put(event.getPlayer().getUniqueId(), 0L);
            if (usePlayers.contains(event.getPlayer())) {
                for (ControlListener li : ControlManager.getListeners()) {
                    if (li.onPressShiftAddF(event.getPlayer())) {
                        event.setCancelled(true);
                        usePlayers.remove(event.getPlayer());
                    }
                }
            }else {
                playSound(event.getPlayer());
                for (ControlListener li : ControlManager.getListeners()) {
                    li.onJustPressShiftAddF(event.getPlayer());
                }
//                event.getPlayer().setAllowFlight(true);
                usePlayers.add(event.getPlayer());
                event.setCancelled(true);
            }
        } else {
            if (Control.usePlayers.contains(event.getPlayer())) {
                for (ControlListener li : ControlManager.getListeners()) {
                    if (li.onPressF(event.getPlayer())) {
                        event.setCancelled(true);
                        Control.usePlayers.remove(event.getPlayer());
                    }
                }
            } else {
                Long fTime = playerFMap.get(event.getPlayer().getUniqueId());
                if (fTime !=null && System.currentTimeMillis() < fTime  + 200) {
                    for (ControlListener li : ControlManager.getListeners()) {
                        li.onJustPassDoubleF(event.getPlayer());
                    }
                }else {
                    playerFMap.put(event.getPlayer().getUniqueId(), System.currentTimeMillis());
                }

            }
        }
    }

    public static void playSound(Player player) {
        player.playSound(player.getLocation() ,Sound.ENTITY_EXPERIENCE_ORB_PICKUP,0.2f,1);
    }

    static BossBar controlBar = Bukkit.createBossBar("～+༺༃少女祈祷中(请按下组合键)༃༻+～", BarColor.PINK, BarStyle.SOLID, BarFlag.CREATE_FOG);
    public static void onLoad(MahoIllusion illusion) {

        controlBar.setProgress(1);
        ControlManager.registerListener(new ControlCancel());
//        ControlManager.registerListener(new ControlAll());

        illusion.getServer().getPluginManager().registerEvents(new ControlDoubleSpace(), illusion);
        illusion.getServer().getPluginManager().registerEvents(new ControlSpace(), illusion);
        illusion.getServer().getPluginManager().registerEvents(new Control(), illusion);
        illusion.getServer().getPluginManager().registerEvents(new ControlQ(), illusion);
    }
    public static void runSecond() {
    }
    public static void runTask() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (usePlayers.contains(player)) {
                controlBar.addPlayer(player);
            }else {
                if (!player.getGameMode().equals(GameMode.CREATIVE))
                    player.setAllowFlight(false);
                controlBar.removePlayer(player);
            }
        }
    }

}
