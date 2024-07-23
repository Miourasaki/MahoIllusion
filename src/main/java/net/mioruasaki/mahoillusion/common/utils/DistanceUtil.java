package net.mioruasaki.mahoillusion.common.utils;

import org.bukkit.Location;
import org.bukkit.World;

public class DistanceUtil {

    // 计算两个 Location 之间的直线距离
    public static double distance(Location loc1, Location loc2) {
        if (loc1.getWorld() != loc2.getWorld()) {
            throw new IllegalArgumentException("Locations must be in the same world");
        }

        double dx = loc1.getX() - loc2.getX();
        double dy = loc1.getY() - loc2.getY();
        double dz = loc1.getZ() - loc2.getZ();

        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }
}
