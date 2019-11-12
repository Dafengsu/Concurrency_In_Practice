package _03sharing_objects.testthis;

/**
 * @author Dafengsu
 */
public class ThisEscape {

    private final int id;
    private final String name;
    public ThisEscape(EventSource<EventListener> source) {
        id = 1;
        source.registerListener(new EventListener() {
            @Override
            public void onEvent(Object obj) {
                System.out.println("id: "+ThisEscape.this.id);
                System.out.println("name: " + ThisEscape.this.name);
            }
        });

        try {
            // 调用sleep模拟其他耗时的初始化操作
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        name = "flysqrlboy";
    }
}