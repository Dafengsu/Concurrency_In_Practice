package _15atomic_variables_and_nonblocking_synchronization;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/12
 */
public class CasCounter {
    private SimulatedCAS value;

    public int getValue() {
        return value.getValue();
    }

    public int increment() {
        int v;
        do {
            v = value.getValue();
        } while (v != value.compareAndSwap(v, v + 1));
        return v + 1;
    }

}
