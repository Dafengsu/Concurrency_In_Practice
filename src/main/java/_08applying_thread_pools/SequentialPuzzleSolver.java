package _08applying_thread_pools;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/5
 */
public class SequentialPuzzleSolver<P, M> {
    private final Puzzle<P, M> puzzle;
    private final Set<P> seen = new HashSet<>();

    public SequentialPuzzleSolver(Puzzle<P, M> puzzle) {
        this.puzzle = puzzle;
    }

    public List<M> solve() {
        P p = puzzle.initialPosition();
        return search(new PuzzleNode<P, M>(p, null, null));
    }

    private List<M> search(PuzzleNode<P, M> node) {
        if (!seen.contains(node.pos)) {
            seen.add(node.pos);
            if (puzzle.isGoal(node.pos)) {
                return node.asMoveList();
            }
            for (M move : puzzle.legalMoves(node.pos)) {
                P p = puzzle.move(node.pos, move);
                PuzzleNode<P, M> child = new PuzzleNode<>(p, move, node);
                List<M> result = search(child);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

}
