package _14building_custom_synchronizers;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/12
 */
@ThreadSafe
public class ThreadGate {
    @GuardedBy("this")
    private boolean isOpen;
    @GuardedBy("this")
    private int generation;

    public synchronized void close() {
        isOpen = false;
    }

    public synchronized void open() {
        ++generation;
        isOpen = true;
        notifyAll();
    }

    public synchronized void await() throws InterruptedException {
        int arrivalGeneration = this.generation;
        while (!isOpen && arrivalGeneration == generation) {
            wait();
        }
    }
}
