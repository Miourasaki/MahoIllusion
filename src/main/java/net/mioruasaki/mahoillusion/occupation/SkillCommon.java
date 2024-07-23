package net.mioruasaki.mahoillusion.occupation;

import net.mioruasaki.mahoillusion.events.control.ControlListener;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.UUID;

public class SkillCommon {

     public static boolean load(HashMap<UUID, Long> activeMap, Long time, OccupationType type, Player player, SkillCommon.Run common) {
        if (type.eqByPlayer(player)) {
            Long lastActiveTime = activeMap.get(player.getUniqueId());
            if (lastActiveTime == null || lastActiveTime + time < System.currentTimeMillis()) {
                activeMap.put(player.getUniqueId(), System.currentTimeMillis());
                return common.run();
            }else {
                DecimalFormat df = new DecimalFormat("#.00"); // 保留两位小数
                Double dou = (lastActiveTime + time - System.currentTimeMillis())/1000.0;
                if (dou <= 0.999)
                    player.sendMessage(ChatColor.RED + "技能冷却中, 剩余 0" + df.format(dou) + " 秒");
                else
                    player.sendMessage(ChatColor.RED + "技能冷却中, 剩余 " + df.format(dou) + " 秒");
            }
        }
        return false;
    }


    public interface Run {
        boolean run();
    }

}
