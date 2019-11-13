package _15atomic_variables_and_nonblocking_synchronization;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/13
 */
public class LinkedQueue<E> {
    private static class Node<E> {
        final E item;
        final AtomicReference<Node<E>> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = new AtomicReference<>(next);
        }
    }

    /**
     * 创建dummy节点
     */
    private final Node<E> dummy = new Node<E>(null, null);

    /**
     * 创建头节点引用
     */
    private final AtomicReference<Node<E>> head = new AtomicReference<>(dummy);

    /**
     * 创建尾节点引用
     */
    private final AtomicReference<Node<E>> tail = new AtomicReference<>(dummy);

    public boolean put(E item) {
        //创建一个节点
        Node<E> newNode = new Node<>(item, null);
        while (true) {
            //从尾节点引用获取当前尾节点
            Node<E> curTail = tail.get();
            //获取尾节点的下一个节点
            Node<E> tailNext = curTail.next.get();
            //再次检验尾节点引用是否指向当前尾节点
            if (curTail == tail.get()) {
                //如果尾节点的下一个节点不为空
                if (tailNext != null) {
                    // Queue in intermediate state, advance tail
                    //队列在已经处于更新状态(尾节点已经有下一个节点，队里还没有更新尾节点的指针)，更新节点
                    tail.compareAndSet(curTail, tailNext);
                } else {
                    //把新节点更新为尾节点的下一个节点
                    if (curTail.next.compareAndSet(null, newNode)) {
                        //更新队列尾节点指针
                        tail.compareAndSet(curTail, newNode);
                        return true;
                    }
                }
            }
        }
    }

}
