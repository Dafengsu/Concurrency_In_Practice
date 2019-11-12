package _03sharing_objects;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/10/26
 */
public class NoVisibility {
    private static boolean ready;
    private static int number;

    private static class ReadThread extends Thread {
        @Override
        public void run() {

            while (!ready) {
                Thread.yield();
            }
            synchronized (NoVisibility.class) {
                if (ready) {
                    System.out.println(number);
                }
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            synchronized (NoVisibility.class) {
                number = 0;
                ready = false;
            }

            new ReadThread().start();
            synchronized (NoVisibility.class) {
                number = 42;
                ready = true;
            }


        }

    }
}
