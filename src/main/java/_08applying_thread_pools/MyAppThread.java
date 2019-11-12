package _08applying_thread_pools;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/5
 */
public class MyAppThread extends Thread {
    public static final String DEFAULT_NAME = "MyAppThread";
    private static volatile boolean debugLifecycle = false;
    private static final AtomicInteger created = new AtomicInteger();
    private static final AtomicInteger alive = new AtomicInteger();
    private static final Logger log = Logger.getAnonymousLogger();

    public MyAppThread(Runnable target) {
        super(target, DEFAULT_NAME);
    }

    public MyAppThread(Runnable r, String poolName) {
        super(r, poolName + "-" + created.incrementAndGet());
        setUncaughtExceptionHandler((t,e)-> log.log(Level.SEVERE, "UNVAUHT in thread" + t.getName() + e));
    }

    @Override
    public void run() {
        boolean debug = debugLifecycle;
        if (debug) {
            log.log(Level.FINE, "Created" + getName());
        }
        try {
            alive.incrementAndGet();
            super.run();
        }finally {
            alive.decrementAndGet();
            if (debug) {
                log.log(Level.FINE, "Exiting" + getName());
            }
        }
    }

    public static int getThreadsCreated() {
        return created.get();
    }

    public static int getThreadsAlive() {
        return alive.get();
    }

    public static boolean isDebugLifecycle() {
        return debugLifecycle;
    }

    public static void setDebugLifecycle(boolean debugLifecycle) {
        MyAppThread.debugLifecycle = debugLifecycle;
    }
}
