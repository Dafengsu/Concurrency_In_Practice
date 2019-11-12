package _11performance_and_scalability;

import net.jcip.annotations.GuardedBy;

import java.util.HashSet;
import java.util.Set;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/9
 */
public class ServerStatusAfterSplit {
    @GuardedBy("users")
    public final Set<String> users;
    @GuardedBy("queries")
    public final Set<String> queries;

    public ServerStatusAfterSplit() {
        users = new HashSet<String>();
        queries = new HashSet<String>();
    }

    public void addUser(String u) {
        synchronized (users) {
            users.add(u);
        }
    }

    public void addQuery(String q) {
        synchronized (queries) {
            queries.add(q);
        }
    }

    public void removeUser(String u) {
        synchronized (users) {
            users.remove(u);
        }
    }

    public void removeQuery(String q) {
        synchronized (users) {
            queries.remove(q);
        }
    }

}
