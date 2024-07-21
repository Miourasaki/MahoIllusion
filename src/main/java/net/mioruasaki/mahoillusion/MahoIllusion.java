package net.mioruasaki.mahoillusion;

import net.mioruasaki.mahoillusion.commands.CarryCeremony;
import net.mioruasaki.mahoillusion.events.PlayerUseItem;
import net.mioruasaki.mahoillusion.events.control.Control;
import net.mioruasaki.mahoillusion.occupation.OccupationCommon;
import net.mioruasaki.mahoillusion.occupation.OccupationType;
import net.mioruasaki.mahoillusion.options.Ceremony;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Objects;

public final class MahoIllusion extends JavaPlugin {

    private static MahoIllusion instance;
    public static final String PLUGIN_ID = "mohoillusion";
    public static HashMap<Player, OccupationType> playerOccupation = new HashMap<>();

    public static NamespacedKey getKey(String key) {
        return new NamespacedKey(MahoIllusion.PLUGIN_ID, key);
    }
    public static MahoIllusion getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        getServer().getPluginManager().registerEvents(new PlayerUseItem(), this);


        Control.onLoad(instance);
        getServer().getPluginManager().registerEvents(new Ceremony(), this);
        Objects.requireNonNull(getCommand("occupation")).setExecutor(new CarryCeremony());

        OccupationCommon.onLoad(instance);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        HandlerList.unregisterAll(this);
    }
}
