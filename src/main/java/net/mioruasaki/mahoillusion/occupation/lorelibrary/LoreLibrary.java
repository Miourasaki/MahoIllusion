package net.mioruasaki.mahoillusion.occupation.lorelibrary;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.mioruasaki.mahoillusion.MahoIllusion;
import net.mioruasaki.mahoillusion.occupation.Occupation;
import net.mioruasaki.mahoillusion.occupation.OccupationType;

import java.util.ArrayList;

public class LoreLibrary extends Occupation {


    static {
        ID = OccupationType.LORE_LIBRARY;
    }

    MiniMessage mm = MiniMessage.miniMessage();
    @Override
    public Component getSub() {
//        return mm.deserialize("<underlined><bold><gradient:#5e4fa2:#f79459:red>不动的大图书馆");
        return Component.text("\uEFE0")
                .font(Key.key("miourasaki:ceremony"))
                .color(TextColor.color(255,255,255));
//        return mm.deserialize("<font:miourasaki:ceremony>\uF8EF");
    }


    @Override
    public ArrayList<Component> getDescription() {
        ArrayList<Component> description = new ArrayList<>();
        description.add(mm.deserialize("帕奇帕奇帕"));
        return description;
    }


}
