package _09gui_applications;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/7
 */
public class ListenerExamples {
    private static ExecutorService exec = Executors.newCachedThreadPool();
    private final JButton colorButton = new JButton("Change color");
    private final Random random = new Random();

    private void backgroundRandom() {
        colorButton.addActionListener(e -> colorButton.setBackground(new Color(random.nextInt())));
    }

    private final JButton computeButton = new JButton("Big computation");

    private void longRunningTask() {
        computeButton.addActionListener(e->exec.execute(()->{}));
    }

    private final JButton button = new JButton("DO");
    private final JLabel label = new JLabel("idle");

    private void longRunningTaskWithFeedback() {
        button.addActionListener(e->{
            button.setEnabled(false);
            label.setText("busy");
            exec.execute(()->{
                try {

                }finally {
                    GuiExecutor.instance().execute(()->{
                        button.setEnabled(true);
                        label.setText("idle");
                    });
                }
            });
        });
    }

    private final JButton startButton = new JButton("Start");
    private final JButton cancelButton = new JButton("Cancel");
    private Future<?> runningTask = null;

    private void taskWithCancellation() {
        startButton.addActionListener(e -> {
            if (runningTask != null) {
                runningTask = exec.submit(new Runnable() {
                    @Override
                    public void run() {
                        while (moreWork()) {
                            if (Thread.currentThread().isInterrupted()) {
                                cleanUpPartialWork();
                                break;
                            }
                            doSomeWork();
                        }
                    }

                    private boolean moreWork() {
                        return false;
                    }

                    private void cleanUpPartialWork() {
                    }

                    private void doSomeWork() {
                    }

                });
            }
            ;
        });
        cancelButton.addActionListener(event -> {
            if (runningTask != null) {
                runningTask.cancel(true);
            }
        });
    }

    private void runInBackground(final Runnable task) {
        startButton.addActionListener(e -> {
            class CancelListener implements ActionListener {
                BackgroundTask<?> task;
                @Override
                public void actionPerformed(ActionEvent event) {
                    if (task != null) {
                        task.cancel(true);
                    }
                }
            }
            final CancelListener listener = new CancelListener();
            listener.task = new BackgroundTask<Void>() {
                @Override
                public Void compute() {
                    while (moreWork() && !isCancelled()) {
                        doSomeWork();
                    }
                    return null;
                }

                private boolean moreWork() {
                    return false;
                }

                private void doSomeWork() {
                }

                public void onCompletion(boolean cancelled, String s, Throwable exception) {
                    cancelButton.removeActionListener(listener);
                    label.setText("done");
                }
            };
            cancelButton.addActionListener(listener);
            exec.execute(listener.task);
        });
    }
}

