package net.mioruasaki.mahoillusion.occupation.fightingwildly;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.mioruasaki.mahoillusion.MahoIllusion;
import net.mioruasaki.mahoillusion.events.control.ControlManager;
import net.mioruasaki.mahoillusion.occupation.Occupation;

import java.util.ArrayList;

public class FightingWildly extends Occupation {
    MiniMessage mm = MiniMessage.miniMessage();
    @Override
    public Component getSub() {
        return mm.deserialize("<underlined><bold><gradient:#5e4fa2:#f79459:red>狂战");
    }

    @Override
    public ArrayList<Component> getDescription() {
        return new ArrayList<>();
    }

    @Override
    public int getMaxHealth() {
        return 60;
    }

    @Override
    public void onLoad(MahoIllusion instance) {
        ControlManager.registerListener(new JumpImpact());
        ControlManager.registerListener(new Violent());
        instance.getServer().getPluginManager().registerEvents(new JumpImpact(), instance);

        Violent.onLoad();

        instance.getServer().getPluginManager().registerEvents(new ViolenceProtection(), instance);
        instance.getServer().getPluginManager().registerEvents(new Violent(), instance);
        instance.getServer().getPluginManager().registerEvents(new SpeedBlade(), instance);
    }

    @Override
    public void runSecond() {
        ViolenceProtection.runTask();
    }

    @Override
    public void runTick() {
        ViolenceProtection.runTick();
    }
}
