/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal;

import com.google.common.base.Predicate;
import com.jediterm.terminal.TtyConnector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import org.apache.log4j.Logger;

public class TtyConnectorWaitFor {
    private static final Logger LOG = Logger.getLogger(TtyConnectorWaitFor.class);
    private final Future<?> myWaitForThreadFuture;
    private final BlockingQueue<Predicate<Integer>> myTerminationCallback = new ArrayBlockingQueue<Predicate<Integer>>(1);

    public void detach() {
        this.myWaitForThreadFuture.cancel(true);
    }

    public TtyConnectorWaitFor(final TtyConnector ttyConnector, ExecutorService executor) {
        this.myWaitForThreadFuture = executor.submit(new Runnable(){

            @Override
            public void run() {
                int exitCode = 0;
                try {
                    while (true) {
                        try {
                            exitCode = ttyConnector.waitFor();
                        } catch (InterruptedException e) {
                            LOG.debug(e);
                            continue;
                        }
                        break;
                    }
                } finally {
                    try {
                        if (!TtyConnectorWaitFor.this.myWaitForThreadFuture.isCancelled()) {
                            ((Predicate)TtyConnectorWaitFor.this.myTerminationCallback.take()).apply(exitCode);
                        }
                    } catch (InterruptedException e) {
                        LOG.info(e);
                    }
                }
            }
        });
    }

    public void setTerminationCallback(Predicate<Integer> r) {
        this.myTerminationCallback.offer(r);
    }
}

