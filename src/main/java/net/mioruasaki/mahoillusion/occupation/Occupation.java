package net.mioruasaki.mahoillusion.occupation;

import net.kyori.adventure.text.Component;
import net.mioruasaki.mahoillusion.MahoIllusion;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.ArrayList;

public abstract class Occupation {

    protected static OccupationType ID;
    public int getMaxHealth() {
        return 20;
    }
    public void onPlayerJoin(Player player) {}


    public ArrayList<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (OccupationType.getByInterface(this).eqByPlayer(p))
                players.add(p);
        }
        return players;
    }

    public abstract Component getSub();

    public abstract ArrayList<Component> getDescription();


    public void onLoad(MahoIllusion instance) {};
    public void runTick() {};
    public void runSecond() {};

}
