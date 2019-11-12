package _08applying_thread_pools;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/5
 */
public abstract class TransformingSequential {
    void processSequentially(List<Element> elements) {
        for (Element e : elements) {
            process(e);
        }
    }

    void processInParallel(Executor exec, List<Element> elements) {
        for (final Element e : elements) {
            exec.execute(() -> process(e));
        }
    }



    public <T> void sequentialRecursive(List<Node<T>> nodes, Collection<T> results) {
        for (Node<T> node : nodes) {
            results.add(node.compute());
            sequentialRecursive(node.getChildren(), results);
        }
    }

    public <T> void parallelRecursive(final Executor exec, List<Node<T>> nodes, final Collection<T> results) {
        for (final Node<T> node : nodes) {
            exec.execute(() -> results.add(node.compute()));
            parallelRecursive(exec, node.getChildren(), results);
        }
    }
    public <T> Collection<T> getParallelResults(List<Node<T>> nodes) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        Queue<T> resultQueue = new ConcurrentLinkedQueue<>();
        parallelRecursive(exec, nodes, resultQueue);
        exec.shutdown();
        exec.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        return resultQueue;
    }
    public abstract void process(Element element);
    interface Element {
    }

    interface Node<T> {
        T compute();

        List<Node<T>> getChildren();
    }

}

