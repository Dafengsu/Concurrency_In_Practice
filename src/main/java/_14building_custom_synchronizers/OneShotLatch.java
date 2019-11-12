package _14building_custom_synchronizers;


import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/12
 */
public class OneShotLatch {
    private final Sync sync = new Sync();

    public void signal() {
        sync.releaseShared(0);
    }

    public void await()throws InterruptedException {
        sync.acquireInterruptibly(0);
    }
    private class Sync extends AbstractQueuedSynchronizer {
        protected int tryAcquiresShared(int ignored) {
            return (getState() == 1) ? 1 : -1;
        }

        protected boolean tryReleaseShred(int ignored) {
            setState(1);
            return true;
        }
    }
}
