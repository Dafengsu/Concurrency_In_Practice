package _04composing_objects;

import net.jcip.annotations.GuardedBy;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/10/31
 */
public class PrivateLock {
    private final Object myLock = new Object();
    @GuardedBy("myLock") Widget widget;

    void someMethod() {
        synchronized (myLock) {
            // Access or modify the state of widget
        }
    }

}
