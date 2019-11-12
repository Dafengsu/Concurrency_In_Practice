package _09gui_applications;

import java.util.concurrent.*;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/7
 */
public abstract class BackgroundTask<V> implements Runnable, Future<V> {
    private final FutureTask<V> computation = new Computation();

    private class Computation extends FutureTask<V> {
        public Computation() {
            super(BackgroundTask.this::compute);
        }
        @Override
        protected final void done() {
            GuiExecutor.instance().execute(()->{
                V value = null;
                Throwable thrown = null;
                boolean cancelled = false;
                try {
                    value = get();
                } catch (ExecutionException e) {
                    thrown = e.getCause();
                } catch (CancellationException e) {
                    cancelled = true;
                } catch (InterruptedException consumed) {

                } finally {
                    onCompletion(value, thrown, cancelled);
                }
            });
        }
    }

    protected void setProgress(final int current, final int max) {
        GuiExecutor.instance().execute(()->{onProgress(current,max);});
    }


    public abstract V compute();

    protected void onCompletion(V result, Throwable exception,
                                boolean cancelled) {
    }

    protected void onProgress(int current, int max) {
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return computation.cancel(mayInterruptIfRunning);
    }

    @Override
    public V get() throws InterruptedException, ExecutionException {
        return computation.get();
    }

    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return computation.get(timeout, unit);
    }

    @Override
    public boolean isCancelled() {
        return computation.isCancelled();
    }

    @Override
    public boolean isDone() {
        return computation.isDone();
    }

    @Override
    public void run() {
        computation.run();
    }
}
