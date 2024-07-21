package net.mioruasaki.mahoillusion.occupation;

import net.mioruasaki.mahoillusion.MahoIllusion;
import net.mioruasaki.mahoillusion.events.control.Control;
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
                for (OccupationType type : OccupationType.values()) {
                    type.getInstance().runSecond();
                }
            }
        };
        // 使用 runTaskTimer 方法每tick执行一次任务
        secondRun.runTaskTimer(illusion, 0, 20); // 参数分别为：插件实例，延迟执行的tick数，每次执行的tick间隔
    }

}
