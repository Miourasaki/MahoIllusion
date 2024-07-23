package net.mioruasaki.mahoillusion.occupation.fightingwildly;

import net.mioruasaki.mahoillusion.events.control.ControlListener;
import net.mioruasaki.mahoillusion.occupation.OccupationCommon;
import net.mioruasaki.mahoillusion.occupation.OccupationType;
import net.mioruasaki.mahoillusion.occupation.SkillCommon;
import net.mioruasaki.mahoillusion.occupation.fightingwildly.maho.JumpshinImpact;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

public class JumpImpact extends ControlListener implements Listener {

    private static final HashMap<UUID, Long> activeMap = new HashMap<>();
    private static final Collection<Player> activeList = new ArrayList<>();

    @Override
    protected CallResult onPressSpace(Player player) {
        CallResult callResult = new CallResult(false, true);
        if (SkillCommon.load(activeMap, 4000L, OccupationType.FIGHTING_WILDLY, player, () -> {
            callResult.setRun(() -> {
                player.setVelocity(new Vector(0,1,0));
                activeList.add(player);
            });

            return true;
        })) {
            return callResult;
        };
        return new ControlListener.CallResult(false,true);
    }


    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
            Player player = event.getPlayer();
            if ((player.isInWater() || player.isInLava() || player.isOnGround()) &&
            activeList.contains(player)) {
                JumpshinImpact.create(player);
                OccupationCommon.addPoint(player, 15f);
                activeList.remove(player);
            }
    }

}
