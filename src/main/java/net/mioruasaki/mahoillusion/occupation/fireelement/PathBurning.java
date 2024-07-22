package net.mioruasaki.mahoillusion.occupation.fireelement;

import net.mioruasaki.mahoillusion.MahoIllusion;
import net.mioruasaki.mahoillusion.events.control.Control;
import net.mioruasaki.mahoillusion.events.control.ControlListener;
import net.mioruasaki.mahoillusion.occupation.OccupationType;
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

    private static final HashMap<UUID, Long> activeMap = new HashMap<>();
    private static final HashMap<UUID, Integer> tickMap = new HashMap<>();

    @Override
    protected boolean onPressQ(Player player) {
        if (OccupationType.FIRE_ELEMENT.eqByPlayer(player)) {
            Long lastActiveTime = activeMap.get(player.getUniqueId());
            if (lastActiveTime == null || lastActiveTime + 10000 < System.currentTimeMillis()) {
                player.setFireTicks(100);
                activeMap.put(player.getUniqueId(), System.currentTimeMillis());
                Control.playSound(player);
            }else {
                DecimalFormat df = new DecimalFormat("#.00"); // 保留两位小数
                Double dou = (lastActiveTime + 10000 - System.currentTimeMillis())/1000.0;

                player.sendMessage(ChatColor.RED + "技能冷却中, 剩余 " + df.format(dou) + " 秒");
            }
        }
        return true;
    }

    @Override
    public void onLoad(MahoIllusion illusion) {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
    }

    @Override
    protected void runTick() {
        for (UUID uuid : activeMap.keySet()) {
            Player player = Bukkit.getPlayer(uuid);
            if (player != null && activeMap.get(uuid) + 5000 >= System.currentTimeMillis()) {
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
