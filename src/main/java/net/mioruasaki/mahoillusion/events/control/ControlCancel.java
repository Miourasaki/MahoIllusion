package net.mioruasaki.mahoillusion.events.control;

import net.mioruasaki.mahoillusion.MahoIllusion;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ControlCancel extends ControlListener{

    @Override
    protected boolean onPressShiftAddF(Player player) {
        Control.playSound(player);
        player.sendMessage(ChatColor.RED + "组合键已取消");

        return true;
    }

}
