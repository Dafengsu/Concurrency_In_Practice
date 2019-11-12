package _03sharing_objects;

import net.jcip.annotations.Immutable;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/10/30
 */
@Immutable
public class OneValueCache {
    private final BigInteger lastNumber;
    private final BigInteger[] lastFactors;

    public OneValueCache(BigInteger i, BigInteger[] factors) {
        lastNumber = i;
        if (factors == null) {
            lastFactors = null;
        } else {
            lastFactors = Arrays.copyOf(factors, factors.length);
        }
    }

    public BigInteger[] getFactors(BigInteger i) {
        if (lastNumber == null || !lastNumber.equals(i)) {
            return null;
        } else {
            return Arrays.copyOf(lastFactors, lastFactors.length);
        }
    }
}
