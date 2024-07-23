package net.mioruasaki.mahoillusion.occupation.bettingdog;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.mioruasaki.mahoillusion.MahoIllusion;
import net.mioruasaki.mahoillusion.occupation.Occupation;
import net.mioruasaki.mahoillusion.occupation.OccupationType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class BettingDog extends Occupation {

    static {
        ID = OccupationType.BETTING_DOG;
    }

    MiniMessage mm = MiniMessage.miniMessage();

    @Override
    public Component getSub() {
        return mm.deserialize("<underlined><bold><gradient:#5e4fa2:#f79459:red>星条旗的赌徒");
    }

    @Override
    public ArrayList<Component> getDescription() {
        return new ArrayList<>();
    }

    @Override
    public void onLoad(MahoIllusion instance) {
        DogFortune.onLoad(instance);
        instance.getServer().getPluginManager().registerEvents(new DoubleEdged(), instance);
        instance.getServer().getPluginManager().registerEvents(new DrawLots(), instance);
    }
}
