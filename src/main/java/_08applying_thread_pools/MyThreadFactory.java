package _08applying_thread_pools;

import java.util.concurrent.ThreadFactory;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/5
 */
public class MyThreadFactory implements ThreadFactory {
    private final String poolName;

    public MyThreadFactory(String poolName) {
        this.poolName = poolName;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new MyAppThread(r, poolName);
    }
}
