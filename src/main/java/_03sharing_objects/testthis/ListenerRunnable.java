package _03sharing_objects.testthis;



import java.util.List;

public class ListenerRunnable implements Runnable {

    private EventSource<EventListener> source;
    public ListenerRunnable(EventSource<EventListener> source) {
        this.source = source;
    }
    @Override
    public void run() {
        List<EventListener> listeners = null;

        try {
            listeners = source.retrieveListeners();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assert listeners != null;
        for(EventListener listener : listeners) {
            listener.onEvent(new Object());
        }
    }

}