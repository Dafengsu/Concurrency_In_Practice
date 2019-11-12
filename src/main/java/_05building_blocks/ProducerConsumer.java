package _05building_blocks;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/1
 */
public class ProducerConsumer {
    static class FileCrawler implements Runnable {
        private final BlockingQueue<File> fileQueue;
        private final FileFilter fileFilter;
        private final File root;

        public FileCrawler(BlockingQueue<File> fileQueue,
                           final FileFilter fileFilter,
                           File root) {
            this.fileQueue = fileQueue;
            this.root = root;
            this.fileFilter = pathname -> pathname.isDirectory() || fileFilter.accept(pathname);
        }

        private boolean alreadyIndexed(File f) {
            return false;
        }

        @Override
        public void run() {
            try {
                crawl(root);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        private void crawl(File root) throws InterruptedException {
            File[] entries = root.listFiles(fileFilter);
            if (entries != null) {
                for (File entry : entries) {
                    if (entry.isDirectory()) {
                        crawl(entry);
                    } else if (!alreadyIndexed(entry)) {
                        fileQueue.put(entry);
                    }
                }
            }
        }
    }
    static class Indexer implements Runnable {
        private final BlockingQueue<File> queue;

        public Indexer(BlockingQueue<File> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {

            try {
                while (true) {
                    indexFile(queue.take());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        private void indexFile(File file) {

        }
    }
    private static final int BOUND = 10;
    private static final int N_CONSUMERS = Runtime.getRuntime().availableProcessors();

    public static void startIndexing(File[] roots) {
        LinkedBlockingQueue<File> queue = new LinkedBlockingQueue<>(BOUND);
        FileFilter filter = pathname -> true;
        for (File root : roots) {
            new Thread(new FileCrawler(queue, filter, root)).start();
        }
        for (int i = 0; i < N_CONSUMERS; i++) {
            new Thread(new Indexer(queue)).start();
        }
    }
}
