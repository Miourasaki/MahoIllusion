package net.mioruasaki.mahoillusion.events.control.template;

import net.mioruasaki.mahoillusion.events.control.ControlListener;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ControlAll extends ControlListener {

    @Override
    protected boolean onPressF(Player player) {
        player.sendMessage(ChatColor.YELLOW + "你按下了 组合键F");
        return true;
    }

    @Override
    protected boolean onPressQ(Player player) {
        player.sendMessage(ChatColor.YELLOW + "你按下了 组合键Q");
        return true;
    }

    @Override
    protected boolean onPressShiftAddF(Player player) {
        player.sendMessage(ChatColor.YELLOW + "你按下了 组合键Shift+F");
        return true;
    }

    @Override
    protected void onJustPassDoubleF(Player player) {
        player.sendMessage(ChatColor.YELLOW + "你按下了 双F");
    }
}
