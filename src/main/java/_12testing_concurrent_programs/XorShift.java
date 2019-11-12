package _12testing_concurrent_programs;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/9
 */
public class XorShift {
    static final AtomicInteger seq = new AtomicInteger(8862213);
    int x = -1831444054;

    public XorShift(int seed) {
        x = seed;
    }

    public XorShift() {
        this((int) System.nanoTime() + seq.getAndAdd(129));
    }

    public int next() {
        x ^= x << 6;
        x ^= x >>> 21;
        x ^= (x << 7);
        return x;
    }
}
