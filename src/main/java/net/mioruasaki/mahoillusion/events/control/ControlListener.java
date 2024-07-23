package net.mioruasaki.mahoillusion.events.control;

import net.mioruasaki.mahoillusion.MahoIllusion;
import org.bukkit.entity.Player;

public abstract class ControlListener {

    protected boolean onPressShiftAddF(Player player) {
        return false;
    }
    protected void onJustPressShiftAddF(Player player) {}
    protected boolean onPressQ(Player player) {
        return false;
    }
    protected boolean onPressF(Player player) {
        return false;
    }
    protected void onJustPassDoubleF(Player player) { }
    protected CallResult onPressSpace(Player player) {
        return new CallResult(false,false);
    }
    protected CallResult onPressDoubleSpace(Player player) {
        return new CallResult(false,false);
    }
    protected CallResult onJustPressDoubleSpace(Player player) {
        return new CallResult(false,false);
    }

    public static class CallResult {
        public boolean cancelled = false;
        public boolean load = false;
        public Run run = null;

        public CallResult(boolean cancelled, boolean load) {
            this.cancelled = cancelled;
            this.load = load;
        }

        public void setRun(Run run) {
            this.run = run;
        }

        public interface Run {
            void run();
        }
    }
}
