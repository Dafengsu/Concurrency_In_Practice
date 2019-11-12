package _09gui_applications;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.*;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/6
 */
public class SwingUtilities {
    private static final ExecutorService exec = Executors.newSingleThreadExecutor(new SwingFactory());
    private static volatile Thread swingThread;
    private static class SwingFactory implements ThreadFactory {

        @Override
        public Thread newThread(Runnable r) {
            swingThread = new Thread(r);
            return swingThread;
        }
    }

    public static boolean isEventDispatchThread() {
        return Thread.currentThread() == swingThread;
    }

    public static void invokeLater(Runnable task) {
        exec.execute(task);
    }

    public static void invokeAndWait(Runnable task) throws InterruptedException, InvocationTargetException {
        Future<?> f = exec.submit(task);
        try {
            f.get();
        } catch (ExecutionException e) {
            throw new InvocationTargetException(e);
        }
    }
}
