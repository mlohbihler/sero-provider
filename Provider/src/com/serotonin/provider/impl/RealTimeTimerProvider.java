package com.serotonin.provider.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.serotonin.provider.TimerProvider;
import com.serotonin.timer.RealTimeTimer;

public class RealTimeTimerProvider implements TimerProvider<RealTimeTimer> {
    static final Log LOG = LogFactory.getLog(RealTimeTimerProvider.class);

    private RealTimeTimer timer;
    private TerminationWaiter waiter;

    public void initialize() {
        timer = new RealTimeTimer();
        timer.init(new ThreadPoolExecutor(0, 1000, 30L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>()));
        waiter = new TerminationWaiter(timer.getExecutorService());
    }

    public void terminate() {
        timer.cancel();
        timer.getExecutorService().shutdown();

        // This method very well could be running in an executor task for all we know, so running the awaitTermination
        // will never end because it is waiting for itself. So, run this in a separate thread.
        new Thread(waiter).start();
    }

    @Override
    public RealTimeTimer getTimer() {
        return timer;
    }

    class TerminationWaiter implements Runnable {
        private final ExecutorService executorService;

        public TerminationWaiter(ExecutorService executorService) {
            this.executorService = executorService;
        }

        @Override
        public void run() {
            try {
                boolean waited = false;

                while (!executorService.awaitTermination(1, TimeUnit.SECONDS)) {
                    LOG.warn("Waiting for executor service to shut down");
                    waited = true;
                }

                if (waited)
                    LOG.warn("Done waiting for executor service to shut down");
            }
            catch (InterruptedException e) {
                LOG.error(e);
            }
        }
    }
}
