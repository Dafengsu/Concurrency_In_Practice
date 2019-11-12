package _14building_custom_synchronizers;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/12
 */
public class ThreadGateTest {
    @Test
    public void test() throws InterruptedException {
        final AtomicInteger count = new AtomicInteger(0);
        ThreadGate gate = new ThreadGate();
        Executors.newFixedThreadPool(10);
        DateFormat format = new SimpleDateFormat("HH:mm:ss");
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
        ExecutorService exec = new ThreadPoolExecutor(10, 10, 0L, MILLISECONDS, queue, new ThreadFactoryBuilder().setNameFormat("test-%d").build());
        for (int i = 0; i < 10; i++) {
            exec.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " 进入run方法体开始第一次等待 " + format.format(new Date()));
                count.incrementAndGet();
                try {
                    gate.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "执行完第一次任务，结束第一次等待 " + format.format(new Date()));
            });
        }
        while (count.get() < 10) {
            Thread.yield();
        }
        count.set(0);
        gate.open();

        gate.close();
        for (int i = 0; i < 10; i++) {
            exec.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " 进入run方法体开始第二次等待 " + format.format(new Date()));
                count.incrementAndGet();
                try {
                    gate.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "执行完第一次任务，结束第二次等待 " + format.format(new Date()));
            });
        }
        while (count.get() < 10) {
            Thread.yield();
        }
        gate.open();
        Thread.sleep(1000);

    }

}
