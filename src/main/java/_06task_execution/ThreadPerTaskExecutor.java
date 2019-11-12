package _06task_execution;

import java.util.concurrent.Executor;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/2
 */
public class ThreadPerTaskExecutor implements Executor {

    @Override
    public void execute(Runnable command) {
        new Thread(command).start();
    }
}
