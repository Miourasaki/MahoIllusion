package net.mioruasaki.mahoillusion.occupation.paladin;

import net.mioruasaki.mahoillusion.common.utils.DistanceUtil;
import net.mioruasaki.mahoillusion.events.control.ControlListener;
import net.mioruasaki.mahoillusion.occupation.OccupationType;
import net.mioruasaki.mahoillusion.occupation.SkillCommon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;
import java.util.function.Predicate;

public class AnvilHeaven extends ControlListener {

    @Override
    protected boolean onPressF(Player player) {
        if (OccupationType.PALADIN.eqByPlayer(player)) {

            Collection<Entity> nearbyEntities = player.getNearbyEntities(5,1.5,5);
            ArrayList<LivingEntity> entities = new ArrayList<>();
            ArrayList<LivingEntity> players = new ArrayList<>();
            for (Entity entity : nearbyEntities) {
                if (entity instanceof  Player) {
                    if (entity.equals(player)) continue;
                    players.add((Player) entity);
                } else if (entity instanceof LivingEntity) {
                    entities.add((LivingEntity) entity);
                }
            }

            while (players.size() < 3) {
                if (entities.isEmpty()) break;
                players.add(entities.get(0));
                entities.remove(0);
            }

            for (LivingEntity targetEntity : players) {
                targetEntity.setVelocity(new Vector(0,1,0));
            }


        }

        return true;
    }
}
