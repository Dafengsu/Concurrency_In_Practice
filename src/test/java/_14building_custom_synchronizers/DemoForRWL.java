package _14building_custom_synchronizers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import static java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/12
 */
public class DemoForRWL {
    private static Integer count = 0;
    private final static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final static ReadLock readLock = readWriteLock.readLock();
    private final static WriteLock writeLock = readWriteLock.writeLock();
    public static void main(String[] args) {
        ExecutorService exec = new ThreadPoolExecutor(10, 10, 0L, MILLISECONDS, new LinkedBlockingQueue<>());

        for (int i = 0; i < 100; i++) {
            exec.submit(DemoForRWL::getCount);
            int finalI = i;
            exec.submit(() -> setCount(finalI));
        }
        exec.shutdown();
    }

    public static Integer getCount() {
        readLock.lock();
        int x;
        try {
            x = count;
            System.out.println(Thread.currentThread().getName() + " getCount: " + count);
        }finally {
            readLock.unlock();
        }
        return x;
    }

    public static void setCount(Integer count) {
        writeLock.lock();
        try {
            DemoForRWL.count = count;
            System.out.println(Thread.currentThread().getName() + " setCount: " + count);
        }finally {
            writeLock.unlock();
        }

    }
}
