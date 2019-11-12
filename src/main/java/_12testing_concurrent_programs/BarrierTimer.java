package _12testing_concurrent_programs;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/10
 */
public class BarrierTimer implements Runnable {
    private boolean started;
    private long startTime, endTime;


    @Override
    public synchronized void run() {
        long t = System.nanoTime();
        if (!started) {
            started = true;
            startTime = t;
        } else {
            endTime = t;
        }
    }

    public synchronized void clear() {
        started = false;
    }

    public synchronized long getTime() {
        return endTime - startTime;
    }
}
