package _15atomic_variables_and_nonblocking_synchronization;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/13
 */
public class AtomicPseudoRandom extends PseudoRandom {
    private AtomicInteger seed;

    public AtomicPseudoRandom(AtomicInteger seed) {
        this.seed = seed;
    }

    public int nexInt(int n) {
        while (true) {
            int s = seed.get();
            int nexSeed = calculateNext(s);
            if (seed.compareAndSet(s, nexSeed)) {
                int remainder = s % n;
                return remainder > 0 ? remainder : remainder + n;
            }
        }
    }
}
