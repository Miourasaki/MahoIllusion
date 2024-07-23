package net.mioruasaki.mahoillusion.occupation;

import net.mioruasaki.mahoillusion.MahoIllusion;
import net.mioruasaki.mahoillusion.events.control.Control;
import net.mioruasaki.mahoillusion.events.control.ControlSpace;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

public class OccupationCommon {

    public static void onLoad(MahoIllusion illusion) {
        for (OccupationType type : OccupationType.values()) {
            type.getInstance().onLoad(illusion);
        }
        BukkitRunnable tickRun = new BukkitRunnable() {
            @Override
            public void run() {
                // 这里写需要每tick执行的代码
                Control.runTask();
//                ControlSpace.onTick();

                for (OccupationType type : OccupationType.values()) {
                    type.getInstance().runTick();
                }
            }
        };
        // 使用 runTaskTimer 方法每tick执行一次任务
        tickRun.runTaskTimer(illusion, 0, 1); // 参数分别为：插件实例，延迟执行的tick数，每次执行的tick间隔
        BukkitRunnable secondRun = new BukkitRunnable() {
            @Override
            public void run() {
                // 这里写需要每tick执行的代码
                Control.runSecond();

                for (OccupationType type : OccupationType.values()) {
                    type.getInstance().runSecond();
                }
            }
        };
        // 使用 runTaskTimer 方法每tick执行一次任务
        secondRun.runTaskTimer(illusion, 0, 20); // 参数分别为：插件实例，延迟执行的tick数，每次执行的tick间隔
    }


    public static void addPoint(Player player, Float point) {
        setPoint(player, getPoint(player) + 1);
    }

    public static void setPoint(Player player, Float point) {
        NamespacedKey key = MahoIllusion.getKey("point");

        PersistentDataContainer persistentDataContainer = player.getPersistentDataContainer();
        persistentDataContainer.set(key, PersistentDataType.FLOAT, point);
    }

    public static Float getPoint(Player player) {
        NamespacedKey key = MahoIllusion.getKey("point");

        PersistentDataContainer persistentDataContainer = player.getPersistentDataContainer();
        Float nowPoint = persistentDataContainer.get(key, PersistentDataType.FLOAT);

        if (nowPoint == null) {
            persistentDataContainer.set(key, PersistentDataType.FLOAT, 0f);
            return 0f;
        }
        else return nowPoint;
    }


}
