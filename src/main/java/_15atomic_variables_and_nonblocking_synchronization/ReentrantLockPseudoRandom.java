package _15atomic_variables_and_nonblocking_synchronization;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/13
 */
public class ReentrantLockPseudoRandom extends PseudoRandom {
    private final Lock lock = new ReentrantLock(false);
    private int seed;

    public ReentrantLockPseudoRandom(int seed) {
        this.seed = seed;
    }

    public int nextInt(int n) {
        lock.lock();
        try {
            int s = seed;
            s = calculateNext(s);
            int remainder = s % n;
            return remainder > 0 ? remainder : remainder + n;
        }finally {
            lock.unlock();
        }
    }
}
