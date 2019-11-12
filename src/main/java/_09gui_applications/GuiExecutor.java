package _09gui_applications;

import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/6
 */
public class GuiExecutor extends AbstractExecutorService {
    private static final GuiExecutor instance = new GuiExecutor();

    private GuiExecutor() {

    }

    public static GuiExecutor instance() {
        return instance;
    }

    @Override
    public void execute(Runnable command) {
        if (SwingUtilities.isEventDispatchThread()) {
            command.run();
        } else {
            SwingUtilities.invokeLater(command);
        }
    }

    @Override
    public void shutdown() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Runnable> shutdownNow() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isShutdown() {
        return false;
    }

    @Override
    public boolean isTerminated() {
        return false;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        throw new UnsupportedOperationException();
    }
}
