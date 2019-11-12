package _07cancellation_and_shutdown;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/3
 */
public class PrimeProducer extends Thread{
    private final BlockingQueue<BigInteger> queue;

    public PrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }
}
