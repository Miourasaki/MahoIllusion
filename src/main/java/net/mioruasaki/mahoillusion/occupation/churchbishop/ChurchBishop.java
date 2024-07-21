package net.mioruasaki.mahoillusion.occupation.churchbishop;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.mioruasaki.mahoillusion.occupation.Occupation;

import java.util.ArrayList;

public class ChurchBishop extends Occupation {
    MiniMessage mm = MiniMessage.miniMessage();
    @Override
    public Component getSub() {
        return mm.deserialize("<underlined><bold><gradient:#5e4fa2:#f79459:red>残余的主教");
    }

    @Override
    public ArrayList<Component> getDescription() {
        return new ArrayList<>();
    }

    @Override
    public void runSecond() {
     PiousPeople.runTask();
    }
}
