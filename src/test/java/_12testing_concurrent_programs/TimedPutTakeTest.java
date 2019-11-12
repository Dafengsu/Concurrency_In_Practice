package _12testing_concurrent_programs;

import java.util.concurrent.CyclicBarrier;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/10
 */
public class TimedPutTakeTest extends PutTakeTest{
    private BarrierTimer timer = new BarrierTimer();


    public TimedPutTakeTest(int capacity, int nPairs, int nTrials) {
        super(capacity, nPairs, nTrials);
        barrier = new CyclicBarrier(nPairs * 2 + 1, timer);
    }

    @Override
    public void test() throws InterruptedException {
        try {
            timer.clear();
            for (int i = 0; i < nPairs; i++) {
                PutTakeTest.pool.execute(new PutTakeTest.Producer());
                PutTakeTest.pool.execute(new PutTakeTest.Consumer());
            }
            barrier.await();
            barrier.await();
            long nsPerItem = timer.getTime();
            System.out.print("Throughput: " + nsPerItem + " ns/item");
            assertEquals(putSum.get(), takeSum.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int tpt = 10000;
        for (int cap = 1; cap < 1000; cap *= 10) {
            System.out.println("Capacity:" + cap);
            for (int pairs = 1; pairs < 128; pairs *= 2) {
                TimedPutTakeTest t = new TimedPutTakeTest(cap, pairs, tpt);
                System.out.print("Pairs: " + pairs + "\t");
                t.test();
                System.out.print("\t");
             /*   Thread.sleep(10);*/
                t.test();
                System.out.println();
               /* Thread.sleep(10);*/
            }
        }
        PutTakeTest.pool.shutdown();
    }
}
