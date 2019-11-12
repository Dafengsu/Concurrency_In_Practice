package _08applying_thread_pools;

import java.util.List;
import java.util.concurrent.*;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/5
 */
public class ConcurrentPuzzleSolver<P,M> {
    private final Puzzle<P, M> puzzle;
    private final ExecutorService exec;
    private final ConcurrentMap<P, Boolean> seen;
    protected final ValueLatch<PuzzleNode<P, M>> solution = new ValueLatch<>();

    public ConcurrentPuzzleSolver(Puzzle<P, M> puzzle) {
        this.puzzle = puzzle;
        this.exec = initThreadPool();
        this.seen = new ConcurrentHashMap<>();
        if (exec instanceof ThreadPoolExecutor) {
            ThreadPoolExecutor tpe = (ThreadPoolExecutor) exec;
            tpe.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        }
    }

    private ExecutorService initThreadPool() {
        return Executors.newCachedThreadPool();
    }

    public List<M> solve() throws InterruptedException {
        try {
            P p = puzzle.initialPosition();
            exec.execute(newTask(p, null, null));
            PuzzleNode<P, M> solnPuzzleNode = solution.getValue();
            return (solnPuzzleNode == null) ? null : solnPuzzleNode.asMoveList();
        }finally {
            exec.shutdown();
        }


    }

    protected Runnable newTask(P p,M m,PuzzleNode<P,M> n) {
        return new SolverTask(p, m, n);
    }

    protected class SolverTask extends PuzzleNode<P,M> implements Runnable {
        public SolverTask(P p, M m, PuzzleNode<P, M> n) {
            super(p, m, n);
        }

        @Override
        public void run() {
            if (solution.isSet() || seen.putIfAbsent(pos, true) != null) {
                return;
            }
            if (puzzle.isGoal(pos)) {
                solution.setValue(this);
            } else {
                for (M m : puzzle.legalMoves(pos)) {
                    exec.execute(newTask(puzzle.move(pos, m), m, this));
                }
            }

        }
    }
}
