import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/5
 */
public class DemoForThreadFactory {
    public static void main(String[] args) {
        ThreadFactory threadFactory = Executors.privilegedThreadFactory();
    }
}
