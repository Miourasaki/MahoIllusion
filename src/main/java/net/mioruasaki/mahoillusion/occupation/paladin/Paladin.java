package net.mioruasaki.mahoillusion.occupation.paladin;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.mioruasaki.mahoillusion.MahoIllusion;
import net.mioruasaki.mahoillusion.events.control.ControlManager;
import net.mioruasaki.mahoillusion.occupation.Occupation;
import net.mioruasaki.mahoillusion.occupation.churchbishop.PiousPeople;

import java.util.ArrayList;

public class Paladin extends Occupation {
    MiniMessage mm = MiniMessage.miniMessage();
    @Override
    public Component getSub() {
        return mm.deserialize("<underlined><bold><gradient:#5e4fa2:#f79459:red>圣骑");
    }

    @Override
    public ArrayList<Component> getDescription() {
        return new ArrayList<>();
    }

    @Override
    public int getMaxHealth() {
        return 12;
    }


    @Override
    public void onLoad(MahoIllusion instance) {
        ControlManager.registerListener(new AnvilHeaven());
     instance.getServer().getPluginManager().registerEvents(new DivineJudgment(), instance);
    }

    @Override
    public void runSecond() {
        HeavyArmor.runTask();
    }
}
