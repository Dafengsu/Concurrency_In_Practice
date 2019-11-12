package _08applying_thread_pools;

import java.util.Set;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/5
 */
public interface Puzzle<P, M> {
    P initialPosition();

    boolean isGoal(P position);

    Set<M> legalMoves(P position);

    P move(P position, M move);
}
