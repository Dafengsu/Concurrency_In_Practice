package _08applying_thread_pools;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/5
 */
public class PuzzleSolver<P, M> extends ConcurrentPuzzleSolver<P, M> {

    public PuzzleSolver(Puzzle<P, M> puzzle) {
        super(puzzle);
    }

    private final AtomicInteger taskCount = new AtomicInteger(0);

    @Override
    protected Runnable newTask(P p, M m, PuzzleNode<P, M> n) {
        return new CountingSolverTask(p, m, n);
    }
    class CountingSolverTask extends SolverTask {
        public CountingSolverTask(P p, M m, PuzzleNode<P, M> n) {
            super(p, m, n);
            taskCount.incrementAndGet();
        }

        @Override
        public void run() {
            try {
                super.run();
            }finally {
                if (taskCount.decrementAndGet() == 0) {
                    solution.setValue(null);
                }
            }

        }
    }

}
