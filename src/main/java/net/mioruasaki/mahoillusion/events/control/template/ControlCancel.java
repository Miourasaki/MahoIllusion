package net.mioruasaki.mahoillusion.events.control.template;

import net.mioruasaki.mahoillusion.events.control.Control;
import net.mioruasaki.mahoillusion.events.control.ControlListener;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ControlCancel extends ControlListener {

    @Override
    protected boolean onPressShiftAddF(Player player) {
        Control.playSound(player);
        player.sendMessage(ChatColor.RED + "组合键已取消");

        return true;
    }

}
