package _15atomic_variables_and_nonblocking_synchronization;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/13
 */
public class PseudoRandom {
    int calculateNext(int prev) {
        prev ^= prev << 6;
        prev ^= prev >>> 21;
        prev ^= (prev << 7);
        return prev;
    }
}
