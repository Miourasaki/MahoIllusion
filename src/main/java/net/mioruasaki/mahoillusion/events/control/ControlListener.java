package net.mioruasaki.mahoillusion.events.control;

import net.mioruasaki.mahoillusion.MahoIllusion;
import org.bukkit.entity.Player;

public abstract class ControlListener {

    protected boolean onPressShiftAddF(Player player) {
        return false;
    }
    protected boolean onPressQ(Player player) {
        return false;
    }
    protected boolean onPressF(Player player) {
        return false;
    }

}
