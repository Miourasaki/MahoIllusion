package net.mioruasaki.mahoillusion.occupation.lorelibrary;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.mioruasaki.mahoillusion.MahoIllusion;
import net.mioruasaki.mahoillusion.events.control.ControlManager;
import net.mioruasaki.mahoillusion.occupation.Occupation;
import net.mioruasaki.mahoillusion.occupation.OccupationType;
import net.mioruasaki.mahoillusion.occupation.fireelement.BurningSoul;
import net.mioruasaki.mahoillusion.occupation.fireelement.CalcinedWeapons;
import net.mioruasaki.mahoillusion.occupation.fireelement.PathBurning;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class FireElement extends Occupation {

    static {
        ID = OccupationType.FIRE_ELEMENT;
    }

    @Override
    public int getMaxHealth() {
        return 14;
    }

    MiniMessage mm = MiniMessage.miniMessage();

    @Override
    public Component getSub() {
        return mm.deserialize("<underlined><bold><gradient:#5e4fa2:#f79459:red>燃烧灵魂的骑士");
    }

    @Override
    public ArrayList<Component> getDescription() {
        ArrayList<Component> description = new ArrayList<>();
        description.add(mm.deserialize("燃魂：只要被点燃身上的火焰就不会自然熄灭（能被水灭掉），在燃烧状态下获得2级抗性提升、3级力量、2级生命回复、2级迅捷与1级急迫buff，若在燃烧状态下接触到水，则受到10点伤害（无视防御），燃烧状态下每秒获得2点大招点数"));
        description.add(mm.deserialize("煅烧兵器：使用金属剑（铁、金、下界合金）命中敌方时，能够点燃敌方并对其施加持续2秒的1级凋零buff，若武器上带有任意等级的火焰附加附魔，命中后产生单格小范围爆炸"));
        description.add(mm.deserialize("炼狱之路：点燃自身，并在接下来的5秒内，每移动0.5秒就在自己脚下释放一次伤害为2、半径为0.75格的烈焰冲击并点燃被命中的敌方，每次释放烈焰冲击都能获得3点大招点数，冷却10秒"));
        description.add(mm.deserialize("烬灭之刃：不能主动释放，而是在拥有10点以上大招点数时，每次使用金属剑命中敌方都能产生进行一次伤害为5的协同攻击"));
        return description;
    }


    BurningSoul bs;

    @Override
    public void onLoad(MahoIllusion illusion) {
        illusion.getServer().getPluginManager().registerEvents(new BurningSoul(), illusion);
        illusion.getServer().getPluginManager().registerEvents(new CalcinedWeapons(), illusion);
        bs = new BurningSoul();
        BukkitRunnable secondRun = new BukkitRunnable() {
            @Override
            public void run() {
                // 这里写需要每tick执行的代码
                bs.onTick();
            }
        };
        // 使用 runTaskTimer 方法每tick执行一次任务
        secondRun.runTaskTimer(MahoIllusion.getInstance(), 0, 5);

        ControlManager.registerListener(new PathBurning());
    }

}
