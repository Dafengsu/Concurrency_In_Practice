package _16the_java_memory_model;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/14
 */
public class SafeStates {
    private final Map<String, String> states;
    public SafeStates() {
        states = new HashMap<String, String>();
        states.put("alaska", "AK");
        states.put("alabama", "AL");
        /*...*/
        states.put("wyoming", "WY");
    }
    public String getAbbreviation(String s) {
        return states.get(s);
    }
}
