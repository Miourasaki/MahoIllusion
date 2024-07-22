package net.mioruasaki.mahoillusion.commands;

import net.mioruasaki.mahoillusion.MahoIllusion;
import net.mioruasaki.mahoillusion.occupation.Occupation;
import net.mioruasaki.mahoillusion.occupation.OccupationCommon;
import net.mioruasaki.mahoillusion.occupation.OccupationType;
import net.mioruasaki.mahoillusion.options.Ceremony;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.Arrays;

public class CarryCeremony implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {


        if (args.length >= 1) {
            if (Arrays.asList("get", "choice").contains(args[0])) Ceremony.openCeremonyMenu((Player) commandSender);
            if (commandSender instanceof Player) {
                if (args[0].equals("clear")) {
                    ((Player) commandSender).getPersistentDataContainer().remove(MahoIllusion.getKey("occupation"));
                }
                if (args[0].equals("set") || args.length >= 2) {
                    try {
                        OccupationType occupationType = OccupationType.valueOf(args[1]);
                        Occupation occupation = occupationType.getInstance();
                        Player player = ((Player) commandSender);
                        player.getPersistentDataContainer().set(MahoIllusion.getKey("occupation"), OccupationType.INSTANCE, occupationType);
                        player.setMaxHealth(occupation.getMaxHealth());
                        occupation.onPlayerJoin(player);
                    }catch (Exception e) {
                        commandSender.sendMessage( "§c未知的元素选择");
                    }
                }
                if (args[0].equals("point") || args.length >= 3) {
                    if (args[1].equals("get")) {
                        commandSender.sendMessage(OccupationCommon.getPoint((Player) commandSender).toString());
                    }else if (args[1].equals("set")) {
                        OccupationCommon.setPoint((Player) commandSender, Float.valueOf(args[2]));
                    }
                }
            }
        }

        return true;
    }
}
