package _05building_blocks;

import java.util.concurrent.BlockingQueue;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/1
 */
public class TaskRunnable implements Runnable {
    BlockingQueue<Task> queue;

    @Override
    public void run() {
        try {
            processTask(queue.take());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    void processTask(Task task) {

    }

    interface Task {
    }

}
