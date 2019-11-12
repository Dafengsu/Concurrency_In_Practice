package _07cancellation_and_shutdown;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/4
 */
public class CheckForMail {
    public boolean checkMail(Set<String> hosts, long timeout, TimeUnit unit) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        final AtomicBoolean hasNewMail = new AtomicBoolean(false);
        try {
            for (final String host : hosts) {
                exec.execute(()->{
                    if (checkMail(host)) {
                        hasNewMail.set(true);
                    }});
            }
        }finally {
            exec.shutdown();
            exec.awaitTermination(timeout, unit);
        }
        return hasNewMail.get();
    }
    private boolean checkMail(String host) {
        return false;
    }

}
