package _07cancellation_and_shutdown;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/3
 */
public class BrokenPrimeProducer extends Thread{
    private final BlockingQueue<BigInteger> queue;
    private volatile boolean cancelled = false;

    public BrokenPrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            while (!cancelled) {
                queue.put(p = p.nextProbablePrime());
            }
        } catch (InterruptedException e) {

        }
    }

    public void cancel() {
        cancelled = true;
    }
}
