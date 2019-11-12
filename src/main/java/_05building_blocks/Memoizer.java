package _05building_blocks;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/1
 */
public class Memoizer<A, V> implements Computable<A, V> {
    public final Map<A, Future<V>> cache =
            new ConcurrentHashMap<>();
    private final Computable<A,V> c;

    public Memoizer(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        while (true) {
            Future<V> f = cache.get(arg);
            if (f == null) {
                Callable<V> eval = () -> c.compute(arg);
                FutureTask<V> ft = new FutureTask<>(eval);
                f = cache.putIfAbsent(arg, ft);
                if (f == null) {
                    f = ft;
                    ft.run();
                }
            }

            try {
                return f.get();
            } catch (CancellationException e) {
                cache.remove(arg, f);
            } catch (ExecutionException e) {
                throw LaunderThrowable.launderThrowable(e.getCause());
            }
        }

    }

}
