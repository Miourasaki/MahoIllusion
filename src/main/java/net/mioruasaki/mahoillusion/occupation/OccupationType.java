package net.mioruasaki.mahoillusion.occupation;

import net.mioruasaki.mahoillusion.MahoIllusion;
import net.mioruasaki.mahoillusion.occupation.bettingdog.BettingDog;
import net.mioruasaki.mahoillusion.occupation.churchbishop.ChurchBishop;
import net.mioruasaki.mahoillusion.occupation.fightingwildly.FightingWildly;
import net.mioruasaki.mahoillusion.occupation.lorelibrary.FireElement;
import net.mioruasaki.mahoillusion.occupation.lorelibrary.LoreLibrary;
import net.mioruasaki.mahoillusion.occupation.paladin.Paladin;
import net.mioruasaki.mahoillusion.occupation.season.SeaSon;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public enum OccupationType {
    CHURCH_BISHOP(new ChurchBishop()),
    BETTING_DOG(new BettingDog()),
    SEA_SON(new SeaSon()),
    PALADIN(new Paladin()),
    FIRE_ELEMENT(new FireElement()),
    LORE_LIBRARY(new LoreLibrary()),
    FIGHTING_WILDLY(new FightingWildly()),
    ;


    private final Occupation implementation;

    // 构造方法
    OccupationType(Occupation implementation) {
        this.implementation = implementation;
    }

    // 获取接口实例的方法
    public Occupation getInstance() {
        return this.implementation;
    }

    public static OccupationType getByInterface(Occupation occ) {
        for (OccupationType value : OccupationType.values()) {
            if (value.getInstance().getClass().equals(occ.getClass())) {
                return value;
            }
        }
        throw new IllegalArgumentException("No enum constant with this interface");
    }


    public boolean eqByPlayer(Player player) {
        PersistentDataContainer container = player.getPersistentDataContainer();
        OccupationType occupation = container.get(MahoIllusion.getKey("occupation"), OccupationType.INSTANCE);
        return occupation == this;
    }



    public static final OccupationDataType INSTANCE = new OccupationDataType();
    static class OccupationDataType implements PersistentDataType<String, OccupationType> {
        @Override
        public @NotNull Class<String> getPrimitiveType() {
            return String.class;
        }

        @Override
        public @NotNull Class<OccupationType> getComplexType() {
            return OccupationType.class;
        }

        @Override
        public @NotNull String toPrimitive(@NotNull OccupationType occupation, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
            return occupation.toString();
        }

        @Override
        public @NotNull OccupationType fromPrimitive(@NotNull String s, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
            try {
                return OccupationType.valueOf(s);
            } catch (IllegalArgumentException e) {
                // Handle invalid occupation name
                return null;
            }
        }
    }

}
