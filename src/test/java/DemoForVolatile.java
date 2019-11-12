/**
 * @description: 验证Synchronize机制可不可以解决并发条件下变量的可见性问题
 * @author: Dafengsu
 * @date: 2019/11/12
 */
public class DemoForVolatile {
   static int a = 0;

    static void fun() {
        while (true) {
            synchronized (DemoForVolatile.class) {
                a++;
                if (a > 10) {
                    break;
                }
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("a = %d, 线程运行中...\n", a);
        }
        System.out.println("新建的后台线程结束");
    }

    public static void main(String[] args) {
        new Thread(DemoForVolatile::fun).start();
        while (true) {
            int b;
            synchronized (DemoForVolatile.class) {
                b = a;
            }

            if (b >= 10) {
                System.out.println("主线程执行完毕");
                break;
            }
        }
    }

    public synchronized static int getA() {
        return a;
    }

    public synchronized static void setA(int a) {
        DemoForVolatile.a = a;
    }
}
