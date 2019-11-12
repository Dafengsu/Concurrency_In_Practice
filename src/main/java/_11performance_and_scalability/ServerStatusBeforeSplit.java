package _11performance_and_scalability;

import net.jcip.annotations.GuardedBy;

import java.util.HashSet;
import java.util.Set;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/9
 */
public class ServerStatusBeforeSplit {
    @GuardedBy("this")
    public final Set<String> users;
    @GuardedBy("this")
    public final Set<String> queries;

    public ServerStatusBeforeSplit() {
        users = new HashSet<>();
        queries = new HashSet<>();
    }

    public synchronized void addUser(String u) {
        users.add(u);
    }

    public synchronized void addQuery(String q) {
        queries.add(q);
    }

    public synchronized void removeUser(String u) {
        users.remove(u);
    }

    public synchronized void removeQuery(String q) {
        queries.remove(q);
    }
}
