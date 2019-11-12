import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/10
 */
public class ExceptionDemo {
    public static Integer[] ints = new Integer[10];

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(() -> {
            int x = test(2);
        });
    }


    public static Integer test(int i) {

        return ints[i];
    }
}
