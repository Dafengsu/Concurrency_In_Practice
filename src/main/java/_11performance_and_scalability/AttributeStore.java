package _11performance_and_scalability;

import net.jcip.annotations.GuardedBy;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/9
 */
public class AttributeStore {
    @GuardedBy("this")
    private final Map<String, String> attributes = new HashMap<>();

    public synchronized boolean userLocationMatches(String name, String regexp) {
        String key = "users." + name + ".location";
        String location = attributes.get(key);
        if (location == null) {
            return false;
        } else {
            return Pattern.matches(regexp, location);
        }
    }
}
