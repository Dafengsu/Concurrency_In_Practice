package _04composing_objects;

import net.jcip.annotations.NotThreadSafe;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/10/31
 */
@NotThreadSafe
public class MutablePoint {
    public int x, y;

    public MutablePoint() {
        x = 0;
        y = 0;
    }

    public MutablePoint(MutablePoint point) {
        this.x = point.x;
        this.y = point.y;
    }
}
