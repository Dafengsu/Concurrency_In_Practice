package _05building_blocks;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.math.BigInteger;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/1
 */
public class Factotizer extends GenericServlet {
    private final Computable<BigInteger, BigInteger[]> c = this::factor;
    private final Computable<BigInteger, BigInteger[]> cache = new Memoizer<>(c);

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        try {
            BigInteger i = extractFromRequest(req);
            encodeIntoResponse(res, cache.compute(i));
        } catch (InterruptedException e) {
            encodeError(res, "factorization interrupted");
        }
    }
    void encodeIntoResponse(ServletResponse resp, BigInteger[] factors) {
    }

    void encodeError(ServletResponse resp, String errorString) {
    }

    BigInteger extractFromRequest(ServletRequest req) {
        return new BigInteger("7");
    }
    BigInteger[] factor(BigInteger i) {
        // Doesn't really factor
        return new BigInteger[]{i};
    }
}
