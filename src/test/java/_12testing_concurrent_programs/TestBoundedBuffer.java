package _12testing_concurrent_programs;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.fail;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/9
 */
public class TestBoundedBuffer {
    private static final long LOCKUP_DETECT_TIMEOUT = 1000;
    private static final int CAPACITY = 10000;
    private static final int THRESHOLD = 10000;
    @Test
    public void testIsEmptyWhenConstructed() {
        SemaphoreBoundedBuffer<Integer> bb = new SemaphoreBoundedBuffer<>(10);
        assertTrue(bb.isEmpty());
        assertFalse(bb.isFull());
    }
    @Test
    public void testIsFullAfterPuts() throws InterruptedException {
        SemaphoreBoundedBuffer<Integer> bb = new SemaphoreBoundedBuffer<>(10);
        for (int i = 0; i < 10; i++) {
            bb.put(i);
        }
        assertTrue(bb.isFull());
        assertFalse(bb.isEmpty());
    }
    @Test
    public void testTakeBlocksWhenEmpty() {
        final SemaphoreBoundedBuffer<Integer> bb = new SemaphoreBoundedBuffer<>(10);
        Thread taker = new Thread(() -> {
            try {
                int unused = bb.take();
                fail(); // if we get here, it's an error
            } catch (InterruptedException success) {

            }
        });
        try {
            taker.start();
            Thread.sleep(LOCKUP_DETECT_TIMEOUT);
            assertTrue(taker.isAlive());
            /*taker.interrupt();*/
            bb.put(2);
            taker.join(LOCKUP_DETECT_TIMEOUT);
            assertFalse(taker.isAlive());
        } catch (Exception unexpected) {
            fail();
        }
    }

    class Big {
        double[] data = new double[100000];
    }
    @Test
    public void testLeak() throws InterruptedException {
        SemaphoreBoundedBuffer<Big> bb = new SemaphoreBoundedBuffer<>(CAPACITY);
        int heapSize1 = snapshotHeap();
        for (int i = 0; i < CAPACITY; i++) {
            bb.put(new Big());
            Thread.sleep(100);
        }
        for (int i = 0; i < CAPACITY; i++)
            bb.take();
        int heapSize2 = snapshotHeap();
        assertTrue(Math.abs(heapSize1 - heapSize2) < THRESHOLD);
    }

    private int snapshotHeap() {
        /* Snapshot heap and return heap size */
        return 0;
    }

}
