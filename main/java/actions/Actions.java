package actions;

import actions.utils.*;

import java.util.Collections;
import java.util.TreeMap;
import java.util.Map;


public class Actions {

    private static Map<String, Action> map;

    static {
        Map<String, Action> temp = new TreeMap<>();
        temp.put("help",   new Help());
        temp.put("uptime", new Uptime());

        map = Collections.unmodifiableMap(temp);
    }

    public static Map<String, Action> getMap() { return map; }
}
