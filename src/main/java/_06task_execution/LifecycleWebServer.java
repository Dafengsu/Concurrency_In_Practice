package _06task_execution;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/3
 */
public class LifecycleWebServer {
    private final ExecutorService exec = Executors.newCachedThreadPool();
    public void start() throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (!exec.isShutdown()) {
            try {
                final Socket conn = socket.accept();
                exec.execute(()->{
                    handleRequest(conn);});
            } catch (IOException e) {
                if (!exec.isShutdown()) {
                    log("task submission rejected", e);
                }
            }
        }
    }

    public void stop() {
        exec.shutdown();
    }

    private void log(String msg, Exception e) {
        Logger.getAnonymousLogger().log(Level.WARNING, msg, e);
    }

    void handleRequest(Socket connection) {

    }

    interface Request {

    }

    private Request readRequest(Socket socket) {
        return null;
    }

    private void dispatchRequest(Request request) {

    }

    private boolean isShutdownRequest(Request request) {
        return false;
    }
}
