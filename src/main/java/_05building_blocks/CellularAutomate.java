package _05building_blocks;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/1
 */
public class CellularAutomate {
    private final Board mainBoard;
    private final CyclicBarrier barrier;
    private final Worker[] workers;

    public CellularAutomate(Board board) {
        this.mainBoard = board;
        int count = Runtime.getRuntime().availableProcessors();
        this.barrier = new CyclicBarrier(count, mainBoard::commitNewValues);
        this.workers = new Worker[count];
        for (int i = 0; i < count; i++) {
            workers[i] = new Worker(mainBoard.getSubBoard(count, i));
        }
    }

    public void start() {
        for (Worker worker : workers) {
            new Thread(worker).start();
        }
        mainBoard.waitForConvergence();

    }
    interface Board {
        int getMaxX();

        int getMaxY();

        int getValue(int x, int y);

        int setNewValue(int x, int y, int value);

        void commitNewValues();

        boolean hasConverged();

        void waitForConvergence();

        Board getSubBoard(int numPartitions, int index);
    }

    private class Worker implements Runnable {
        private final Board board;

        public Worker(Board board) {
            this.board = board;
        }

        @Override
        public void run() {
            while (!board.hasConverged()) {
                for (int x = 0; x < board.getMaxX(); x++) {
                    for (int y = 0; y < board.getMaxY(); y++) {
                        board.setNewValue(x, y, computeValue(x, y));
                    }
                    try {
                        barrier.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                       return;
                    }
                }
            }
        }

        private int computeValue(int x, int y) {
            return 0;
        }
    }
}
