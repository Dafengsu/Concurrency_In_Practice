package _14building_custom_synchronizers;

import net.jcip.annotations.ThreadSafe;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/11
 */
@ThreadSafe
public class BoundedBuffer<V> extends BaseBoundedBuffer<V> {

    public BoundedBuffer() {
        this(100);
    }

    public BoundedBuffer(int capacity) {
        super(capacity);
    }

    public synchronized void put(V v) throws InterruptedException {
        while (isFull()) {
            wait();
        }
        doPut(v);
        notifyAll();
    }

    public synchronized V take() throws InterruptedException {
        while (isEmpty()) {
            wait();
        }
        V v = doTake();
        notifyAll();
        return v;
    }

    public synchronized void alternativePut(V v)throws InterruptedException {
        while (isFull()) {
            wait();
        }
        boolean wasEmpty = isEmpty();
        doPut(v);
        if (wasEmpty) {
            notifyAll();
        }
    }
}
