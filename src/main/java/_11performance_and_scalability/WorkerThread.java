package _11performance_and_scalability;

import java.util.concurrent.BlockingQueue;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/9
 */
public class WorkerThread extends Thread {
    private final BlockingQueue<Runnable> queue;

    public WorkerThread(BlockingQueue<Runnable> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Runnable task = queue.take();
                task.run();
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
