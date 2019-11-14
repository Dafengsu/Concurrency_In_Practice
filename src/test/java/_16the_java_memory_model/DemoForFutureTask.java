package _16the_java_memory_model;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @description:    查看FutureTask的多线程实现
 * @author: Dafengsu
 * @date: 2019/11/14
 */
public class DemoForFutureTask {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        Future<String> task = exec.submit(() -> {
            System.out.println("测试");
            return "测试";
        });
        task.get();
        System.out.println("主线程结束");
    }
}
