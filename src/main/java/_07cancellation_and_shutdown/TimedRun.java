package _07cancellation_and_shutdown;

import java.util.concurrent.*;

import static _05building_blocks.LaunderThrowable.launderThrowable;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/4
 */
public class TimedRun {
    private static final ExecutorService taskExec = Executors.newCachedThreadPool();

    public static void timedRun(Runnable r, long timeout, TimeUnit unit) throws InterruptedException {
        Future<?> task = taskExec.submit(r);
        try {
            task.get(timeout, unit);
        } catch (ExecutionException e) {

        } catch (TimeoutException e) {
            throw launderThrowable(e.getCause());
        }finally {
            task.cancel(true);
        }
    }
}
