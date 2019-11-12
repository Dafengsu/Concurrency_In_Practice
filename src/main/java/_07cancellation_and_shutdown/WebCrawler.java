package _07cancellation_and_shutdown;

import net.jcip.annotations.GuardedBy;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/4
 */
public abstract class WebCrawler {
    private volatile TrackingExecutor exec;
    @GuardedBy("this")
    private final Set<URL> urlsToCrawl = new HashSet<>();

    private final ConcurrentMap<URL, Boolean> seen = new ConcurrentHashMap<>();
    private static final long TIMEOUT = 500;
    private static final TimeUnit UNIT = MILLISECONDS;

    public WebCrawler(URL startUrl) {
        urlsToCrawl.add(startUrl);
    }

    public synchronized void start() {
        exec = new TrackingExecutor(Executors.newCachedThreadPool());
        for (URL url : urlsToCrawl) {
            submitCrawlTask(url);
        }
        urlsToCrawl.clear();
    }

    public synchronized void stop() throws InterruptedException {
        try {
            saveUnCrawled(exec.shutdownNow());
            if (exec.awaitTermination(TIMEOUT, UNIT)) {
                saveUnCrawled(exec.getCancelledTasks());
            }
        }finally {
            exec = null;
        }
    }
    protected abstract List<URL> processPage(URL url);

    private void saveUnCrawled(List<Runnable> uncrawled) {
        for (Runnable task : uncrawled) {
            urlsToCrawl.add(((CrawlTask) task).getPage());
        }
    }

    private void submitCrawlTask(URL u) {
        exec.execute(new CrawlTask(u));
    }
    private class CrawlTask implements Runnable {
        private final URL url;

        public CrawlTask(URL url) {
            this.url = url;
        }

        private int count = 1;

        boolean alreadyCrawled() {
            return seen.putIfAbsent(url, true) != null;
        }

        void markUnCrawled() {
            seen.remove(url);
            System.out.printf("marking %s uncrawled%n", url);
        }

        @Override
        public void run() {
            for (URL link : processPage(url)) {
                if (Thread.currentThread().isInterrupted()) {
                    return;
                }
                submitCrawlTask(link);
            }
        }

        public URL getPage() {
            return url;
        }
    }
}

