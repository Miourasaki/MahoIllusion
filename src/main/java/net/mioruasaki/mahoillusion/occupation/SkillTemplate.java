package net.mioruasaki.mahoillusion.occupation;

import net.mioruasaki.mahoillusion.events.control.ControlListener;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.UUID;

public abstract class SkillTemplate extends ControlListener {



    private final HashMap<UUID, Long> activeMap = new HashMap<>();

    protected HashMap<UUID, Long> getActiveMap() {
        return activeMap;
    }

    @Override
    protected boolean onPressQ(Player player) {
        if (getType().eqByPlayer(player)) {
            Long lastActiveTime = activeMap.get(player.getUniqueId());
            if (lastActiveTime == null || lastActiveTime + getTime() < System.currentTimeMillis()) {
                activeMap.put(player.getUniqueId(), System.currentTimeMillis());
                run(player);
            }else {
                DecimalFormat df = new DecimalFormat("#.00"); // 保留两位小数
                Double dou = (lastActiveTime + getTime() - System.currentTimeMillis())/1000.0;
                if (dou <= 0.999)
                    player.sendMessage(ChatColor.RED + "技能冷却中, 剩余 0" + df.format(dou) + " 秒");
                else
                    player.sendMessage(ChatColor.RED + "技能冷却中, 剩余 " + df.format(dou) + " 秒");
            }
        }
        return true;
    }

    protected abstract void run(Player player);
    protected abstract int getTime();
    protected abstract OccupationType getType();

}
