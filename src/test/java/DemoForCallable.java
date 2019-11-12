import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/3
 */
public class DemoForCallable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService exec = Executors.newSingleThreadExecutor();
        List<String> list = new ArrayList<>();
        Future<List<String>> future = exec.submit(() -> list.add("测试"), list);
        exec.submit(() -> list.add("测试"));
        exec.submit(() -> {
            List<String> list2 = new ArrayList<>();
            list2.add("测试");
            return list2;
        });


        List<String> strings = future.get();
        System.out.println(strings.get(0));
        exec.shutdown();
    }
}
