package _03sharing_objects;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/10/30
 */
public class StuffIntoPublic {
    public static Holder holder;

    public void initialize() {
        holder = new Holder(42);
    }

    public static void main(String[] args) {
        StuffIntoPublic sf = new StuffIntoPublic();
        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            StuffIntoPublic.holder.assertSanity();
        }).start();
        sf.initialize();



    }
}
