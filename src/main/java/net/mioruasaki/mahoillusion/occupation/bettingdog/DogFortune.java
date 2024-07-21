package net.mioruasaki.mahoillusion.occupation.bettingdog;

import net.mioruasaki.mahoillusion.MahoIllusion;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DogFortune {

    public static void onLoad(MahoIllusion instance) {
        int continueTime = 100;
        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : new BettingDog().getPlayers()) {
                    HashMap<PotionEffect, Integer> potionHashmap = new HashMap<>();
                    potionHashmap.put(new PotionEffect(PotionEffectType.STRENGTH,continueTime,2),5);
                    potionHashmap.put(new PotionEffect(PotionEffectType.WEAKNESS,continueTime,1),1);
                    potionHashmap.put(new PotionEffect(PotionEffectType.SPEED,continueTime,2),3);
                    potionHashmap.put(new PotionEffect(PotionEffectType.SLOWNESS,continueTime,1),-2);
                    potionHashmap.put(new PotionEffect(PotionEffectType.HASTE,continueTime,1),2);
                    potionHashmap.put(new PotionEffect(PotionEffectType.BLINDNESS,continueTime,0),-1);
                    ArrayList<PotionEffect> potionEffects = new ArrayList<>(potionHashmap.keySet());
                    player.addPotionEffect(potionEffects.get(new Random().nextInt(potionEffects.size())));
                }
            }
        };
        // 使用 runTaskTimer 方法每tick执行一次任务
        task.runTaskTimer(instance, 0, continueTime); // 参数分别为：插件实例，延迟执行的tick数，每次执行的tick间隔


    }

}
