package _03sharing_objects.testthis;

import java.util.ArrayList;
import java.util.List;

public class EventSource<T> {

    private final List<T> eventListeners ;

    public EventSource() {
        eventListeners = new ArrayList<T>() ;
    }

    public synchronized void registerListener(T eventListener) {
        eventListeners.add(eventListener);
        notifyAll();
    }

    public synchronized List<T> retrieveListeners() throws InterruptedException {
        if(eventListeners.size() <= 0 ) {
            wait();
        }
        return new ArrayList<>(eventListeners);
    }
}