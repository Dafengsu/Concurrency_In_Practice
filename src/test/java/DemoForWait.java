import org.junit.Test;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/11
 */
public class DemoForWait {
    public static void main(String[] args) throws InterruptedException {


    }
    @Test
    public void test() throws InterruptedException {
        synchronized (this) {
            this.wait(1000);
        }
    }
}
