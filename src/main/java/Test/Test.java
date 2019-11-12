package Test;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/10/28
 */
public class Test {
    private String name;
    public Test() {
        Thread thread = new Thread(() -> {
            System.out.println("name :" + name);
        });
        thread.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        name = "张三";
    }

    public static void main(String[] args) {
        new Test();
    }

    private void run() {
        System.out.println("name :" + name);
    }
}
