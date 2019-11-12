package _06task_execution;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/1
 */
public class TaskExecutionWebServer {
    private static final int NTHREADS = 100;
    private static final Executor exec = Executors.newFixedThreadPool(NTHREADS);

    public static void main(String[] args) throws IOException {
      /*  final ServerSocket socket = new ServerSocket(80);
        while (true) {
            final Socket connection = socket.accept();
            exec.execute(() -> handleRequest(connection));
        }*/
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();

        //Common Thread Pool
        ExecutorService pool = new ThreadPoolExecutor(5, 200,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        pool.execute(()-> System.out.println(Thread.currentThread().getName()));
        pool.shutdown();//gracefully shutdown
    }
    private static void handleRequest(Socket connection) {
        // request-handling logic here
    }
}
