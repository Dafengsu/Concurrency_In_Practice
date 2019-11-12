package _14building_custom_synchronizers;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/12
 */
public class MissedNotifyDemo {
    private static Object lock = new Object();
    private static final DateFormat format = new SimpleDateFormat("HH:mm:ss");

    public static void main(String[] args) {
        MissedNotifyDemo missedNotifyDemo = new MissedNotifyDemo();
        new Thread(new WaitThread(missedNotifyDemo),"WaitThread").start();
        new Thread(new NotifyThread(missedNotifyDemo),"NotifyThread").start();
    }
    public void waitMethod() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + ":enter waitMethod at"
                + format.format(new Date()));
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + ": start invoke wait method at "
                    + format.format(new Date()));
            lock.wait();
            System.out.println(Thread.currentThread().getName() + ": end invoke wait method at "
                    + format.format(new Date()));
        }
        System.out.println(Thread.currentThread().getName() + ": exit waitMethod at "
                + format.format(new Date()));
    }

    //通知线程执行的方法
    public void notifyMethod() {
        System.out.println(Thread.currentThread().getName() + ": exit notifyMethod at "
                + format.format(new Date()));
        synchronized (lock) {
            //调用通知的方法
            System.out.println(Thread.currentThread().getName() + ": start invoke notify method at "
                    + format.format(new Date()));
            lock.notifyAll();
            System.out.println(Thread.currentThread().getName() + ": end invoke notify method at "
                    + format.format(new Date()));
        }
        System.out.println(Thread.currentThread().getName() + ": exit notifyMethod at "
                + format.format(new Date()));
    }

    static class WaitThread implements Runnable {
        private MissedNotifyDemo missedNotifyDemo;

        public WaitThread(MissedNotifyDemo missedNotifyDemo) {
            this.missedNotifyDemo = missedNotifyDemo;
        }

        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
                missedNotifyDemo.waitMethod();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class NotifyThread implements Runnable {
        private MissedNotifyDemo missedNotifyDemo;

        public NotifyThread(MissedNotifyDemo missedNotifyDemo) {
            this.missedNotifyDemo = missedNotifyDemo;
        }

        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
                missedNotifyDemo.notifyMethod();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
