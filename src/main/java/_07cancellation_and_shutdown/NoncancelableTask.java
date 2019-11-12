package _07cancellation_and_shutdown;

import java.util.concurrent.BlockingQueue;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/3
 */
public class NoncancelableTask {
    public Task getNextTask(BlockingQueue<Task> queue) {
        boolean interrupted = false;
        try {
            while (true) {
                try {
                    return queue.take();
                } catch (InterruptedException e) {
                    interrupted = true;
                }
            }
        }finally {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
    }
    interface Task {

    }
}
