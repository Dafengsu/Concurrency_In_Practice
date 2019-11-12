package _04composing_objects;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/10/31
 */
@ThreadSafe
public class SafePoint {
    @GuardedBy("this")
    private int x, y;

    private SafePoint(int[] a) {
        this(a[0], a[1]);
    }

    public SafePoint(SafePoint p) {
        this(p.get());
    }

    public synchronized int[] get() {
        return new int[]{x, y};
    }

    public synchronized void set(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public SafePoint(int x, int y) {
        this.set(x, y);
    }
}
