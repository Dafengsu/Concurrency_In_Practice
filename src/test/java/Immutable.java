/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/10/31
 */
public class Immutable {
    private final String name;
    public static Immutable test;

    public Immutable(String name) {
        test = this;
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.name = name;
    }

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> new Immutable("张三")).start();
        Thread.sleep(100);
        System.out.println(Immutable.test.name);
    }
}
