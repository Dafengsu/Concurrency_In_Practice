package _04composing_objects;

import java.util.Vector;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/10/31
 */
public class BetterVector<E> extends Vector<E> {
    static final long serialVersionUID = -3963416950630760754L;

    public synchronized boolean putIfAbsent(E x) {
        boolean absent = !contains(x);
        if (absent) {
            add(x);
        }
        return absent;
    }
}
