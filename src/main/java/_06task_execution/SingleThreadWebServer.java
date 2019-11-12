package _06task_execution;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/1
 */
public class SingleThreadWebServer {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            Socket connection = socket.accept();
            handleRequest(connection);
        }
    }
    private static void handleRequest(Socket connection) {
        // request-handling logic here
    }
}
