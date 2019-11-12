package _08applying_thread_pools;

import jdk.nashorn.internal.ir.annotations.Immutable;

import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/5
 */
@Immutable
public class PuzzleNode <P, M>{
    final P pos;
    final M move;
    final PuzzleNode<P, M> pre;

    public PuzzleNode(P pos, M move, PuzzleNode<P, M> pre) {
        this.pos = pos;
        this.move = move;
        this.pre = pre;
    }

    List<M> asMoveList() {
        List<M> solution = new LinkedList<>();
        for (PuzzleNode<P, M> n = this; n.move != null; n = n.pre) {
            solution.add(0, n.move);
        }
        return solution;
    }
}
