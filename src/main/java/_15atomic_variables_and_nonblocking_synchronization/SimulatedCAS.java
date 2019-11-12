package _15atomic_variables_and_nonblocking_synchronization;

import net.jcip.annotations.GuardedBy;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/12
 */
public class SimulatedCAS {
    @GuardedBy("this")
    private int value;

    public synchronized int getValue() {
        return value;
    }

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            value = newValue;
        }
        return oldValue;
    }

    public synchronized boolean compareAndSet(int expectedValue, int newValue) {
        return (expectedValue == compareAndSwap(expectedValue, newValue));
    }
}
