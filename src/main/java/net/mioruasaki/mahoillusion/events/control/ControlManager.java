package net.mioruasaki.mahoillusion.events.control;

import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

public class ControlManager {

    static HashMap<Integer,ControlListener> controlListeners = new HashMap<>();

    static Collection<ControlListener> getListeners() {
        return controlListeners.values();
    }

    public static int registerListener (ControlListener controlListener) {
        int pid = new Random().nextInt(65535);
        controlListeners.put(pid, controlListener);

        return pid;
    }

    public static void unregisterListener (Integer pid) {
        controlListeners.remove(pid);
    }



}
