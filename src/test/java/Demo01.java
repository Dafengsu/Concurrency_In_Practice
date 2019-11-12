import org.junit.Test;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/10/30
 */

public class Demo01 {
    static volatile int a = 0;
    public static void doSomething() {
        while (a < 10) {
            a++;
            System.out.println("a=" + a + " 运行线程...");
        }
        System.out.println("线程运行结束");
    }
    public static void main(String[] args) throws InterruptedException {
        new Thread(Demo01::doSomething).start();
        while (a < 9) {

        }
        System.out.println("主线程运行结束");
    }

    @Test
    public void test() {

    }
}
