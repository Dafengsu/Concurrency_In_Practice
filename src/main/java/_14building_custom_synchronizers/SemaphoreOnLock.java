package _14building_custom_synchronizers;

import net.jcip.annotations.GuardedBy;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/12
 */
public class SemaphoreOnLock {
    private final Lock lock = new ReentrantLock();
    private final Condition permitsAvailable = lock.newCondition();
    @GuardedBy("lock")
    private int permits;

    public SemaphoreOnLock(int initialPermits) {
        lock.lock();
        try {
            permits = initialPermits;
        }finally {
            lock.unlock();
        }
    }

    public void acquire() throws InterruptedException {
        lock.lock();
        try {
            while (permits <= 0) {
                permitsAvailable.await();
            }
            --permits;
        }finally {
            lock.unlock();
        }
    }

    public void release() {
        lock.lock();
        try {
            ++permits;
            permitsAvailable.signal();
        }finally {
            lock.unlock();
        }
    }
}
