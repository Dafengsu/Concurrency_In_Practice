package _03sharing_objects.testthis;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/10/28
 */
public class ThisSafe {
    public final int id;
    public final String name;
    private final EventListener listener;

    private ThisSafe() {
        id = 1;
        listener = new EventListener() {
            @Override
            public void onEvent(Object e) {
                System.out.println("id: " + ThisSafe.this.id);
                System.out.println("name: " + ThisSafe.this.name);
            }
        };
        try {
            // 调用sleep模拟其他耗时的初始化操作
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        name = "张三";
    }

    public static ThisSafe getInstance(EventSource<EventListener> source) {
        ThisSafe safe = new ThisSafe();
        source.registerListener(safe.listener);
        return safe;
    }
}
