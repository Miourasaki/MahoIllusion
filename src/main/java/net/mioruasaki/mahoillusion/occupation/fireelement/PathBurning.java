package net.mioruasaki.mahoillusion.occupation.fireelement;

import net.mioruasaki.mahoillusion.MahoIllusion;
import net.mioruasaki.mahoillusion.events.control.Control;
import net.mioruasaki.mahoillusion.events.control.ControlListener;
import net.mioruasaki.mahoillusion.occupation.OccupationType;
import net.mioruasaki.mahoillusion.occupation.SkillCommon;
import net.mioruasaki.mahoillusion.occupation.SkillTemplate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.UUID;

public class PathBurning extends ControlListener {

    private static final HashMap<UUID, Integer> tickMap = new HashMap<>();
    private static final HashMap<UUID, Long> activeMap = new HashMap<>();

    @Override
    protected void onJustPassDoubleF(Player player) {
        SkillCommon.load(activeMap, 10000L, OccupationType.FIRE_ELEMENT, player, () -> {
            player.setFireTicks(100);
            Control.playSound(player);
            return true;
        });
    }


    public void runTick() {
        for (UUID uuid : activeMap.keySet()) {
            Player player = Bukkit.getPlayer(uuid);
            if (player != null && activeMap.get(uuid) + 5000 >= System.currentTimeMillis()) {
                player.setFireTicks(100);
                Integer tickTime = tickMap.get(uuid);
                if (tickTime == null || tickTime >= 10) {

                    player.getWorld().createExplosion(player.getLocation(), 1, false,false,player);
                    player.getWorld().spawnParticle(Particle.FLAME, player.getLocation(), 50, 0.1, 0.01, 0.1, 0.5);
                    tickMap.put(uuid, 0);
                }else {
                    if (player.isSprinting())
                        tickMap.put(uuid, tickTime+1);

                }

            }
        }
    }
}
