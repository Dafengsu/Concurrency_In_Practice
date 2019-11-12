package _10avoiding_liveness_hazards;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/8
 */
public class LeftRightDeadlock {
    private final Object left = new Object();
    private final Object right = new Object();

    public void leftRight() {
        synchronized (left) {
            synchronized (right) {
                doSomething();
            }
        }
    }

    public void rightLeft() {
        synchronized (right) {
            synchronized (left) {
                doSomethingElse();
            }
        }
    }

    void doSomethingElse() {

    }

    void doSomething() {

    }
}
