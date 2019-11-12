package _03sharing_objects.testthis;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/10/28
 */
public class ThisSafeTest {
    public static void main(String[] args) {
        EventSource<EventListener> source = new EventSource<>();
        ListenerRunnable listRun = new ListenerRunnable(source);
        Thread thread = new Thread(listRun);
        thread.start();
        ThisSafe safe = ThisSafe.getInstance(source);
    }
}
