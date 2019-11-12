package _07cancellation_and_shutdown;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/4
 */
public class ReaderThread extends Thread{
    private static final int BUFSZ = 512;
    private final Socket socket;
    private final InputStream in;

    public ReaderThread(Socket socket) throws IOException {
        this.socket = socket;
        this.in = socket.getInputStream();
    }

    @Override
    public void interrupt() {
        try {
            socket.close();
        } catch (IOException e) {

        }finally {
            super.interrupt();
        }
    }

    @Override
    public void run() {
        try {
            byte[] buf = new byte[BUFSZ];
            while (true) {
                int count = in.read(buf);
                if (count < 0) {
                    break;
                } else if (count > 0) {
                    processBuffer(buf, count);
                }
            }
        } catch (IOException e) {

        }
    }

    public void processBuffer(byte[] buf, int count) {
    }
}
