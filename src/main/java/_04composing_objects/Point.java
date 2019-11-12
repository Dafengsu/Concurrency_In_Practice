package _04composing_objects;


import net.jcip.annotations.Immutable;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/10/31
 */
@Immutable
public class Point {
    public final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
