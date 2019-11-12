package _04composing_objects;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/10/31
 */
public class NumberRange {
    private final AtomicInteger lower = new AtomicInteger(0);
    private final AtomicInteger upper = new AtomicInteger(0);

    public void setLower(int i) {
        if (i > upper.get()) {
            throw new IllegalArgumentException("can't set lower to " + i + " > upper");
        }
        lower.set(i);
    }
    public void setUpper(int i) {
        // Warning -- unsafe check-then-act
        if (i < lower.get()) {
            throw new IllegalArgumentException("can't set upper to " + i + " < lower");
        }
        upper.set(i);
    }

    public boolean isInRange(int i) {
        return (i >= lower.get() && i <= upper.get());
    }
}
