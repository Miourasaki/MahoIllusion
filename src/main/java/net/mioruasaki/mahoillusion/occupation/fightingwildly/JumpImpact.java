package net.mioruasaki.mahoillusion.occupation.fightingwildly;

import net.mioruasaki.mahoillusion.occupation.OccupationType;
import net.mioruasaki.mahoillusion.occupation.SkillTemplate;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class JumpImpact extends SkillTemplate {

    @Override
    protected int getTime() {
        return 4000;
    }

    @Override
    protected OccupationType getType() {
        return OccupationType.FIGHTING_WILDLY;
    }

    @Override
    protected void run(Player player) {

        player.setVelocity(new Vector(0,2,0));
    }
}
