import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/4
 */
public class DemoForLog {
    public static void main(String[] args) throws IOException {
        Logger logger = Logger.getAnonymousLogger();
        FileHandler handler = new FileHandler("E:/MYJAVA/Concurrency/Concurrency_In_Practice/log.txt",true);
        logger.addHandler(handler);
        logger.warning("测试");
    }
}
