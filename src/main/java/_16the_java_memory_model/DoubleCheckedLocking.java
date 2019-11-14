package _16the_java_memory_model;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/14
 */
public class DoubleCheckedLocking {

    public static void main(String[] args) throws InterruptedException {

        Thread one = new Thread(() -> {
            DoubleCheckedLocking.getInstance();
        });
        Thread two = new Thread(() -> {
            System.out.println(resource.n);
        });
        one.start();
        Thread.sleep(100);
        two.start();
    }
    private volatile static Resource resource;

    public static Resource getInstance() {
        if (resource == null) {
            synchronized (DoubleCheckedLocking.class) {
                if (resource == null) {
                    resource = new Resource(2);
                }
            }
        }
        return resource;
    }

    static class Resource {
        int n;

        public Resource(int n) {
            resource = this;
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.n = n;
        }
    }
}
