/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.zip;

import java.io.File;
import java.io.IOException;
import java.util.Deque;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.compress.archivers.zip.ScatterStatistics;
import org.apache.commons.compress.archivers.zip.ScatterZipOutputStream;
import org.apache.commons.compress.archivers.zip.StreamCompressor;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntryRequest;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntryRequestSupplier;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.parallel.FileBasedScatterGatherBackingStore;
import org.apache.commons.compress.parallel.InputStreamSupplier;
import org.apache.commons.compress.parallel.ScatterGatherBackingStore;
import org.apache.commons.compress.parallel.ScatterGatherBackingStoreSupplier;

public class ParallelScatterZipCreator {
    private final Deque<ScatterZipOutputStream> streams = new ConcurrentLinkedDeque<ScatterZipOutputStream>();
    private final ExecutorService es;
    private final ScatterGatherBackingStoreSupplier backingStoreSupplier;
    private final Deque<Future<? extends ScatterZipOutputStream>> futures = new ConcurrentLinkedDeque<Future<? extends ScatterZipOutputStream>>();
    private final long startedAt = System.currentTimeMillis();
    private long compressionDoneAt = 0L;
    private long scatterDoneAt;
    private final ThreadLocal<ScatterZipOutputStream> tlScatterStreams = new ThreadLocal<ScatterZipOutputStream>(){

        @Override
        protected ScatterZipOutputStream initialValue() {
            try {
                ScatterZipOutputStream scatterStream = ParallelScatterZipCreator.this.createDeferred(ParallelScatterZipCreator.this.backingStoreSupplier);
                ParallelScatterZipCreator.this.streams.add(scatterStream);
                return scatterStream;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    };

    private ScatterZipOutputStream createDeferred(ScatterGatherBackingStoreSupplier scatterGatherBackingStoreSupplier) throws IOException {
        ScatterGatherBackingStore bs = scatterGatherBackingStoreSupplier.get();
        StreamCompressor sc = StreamCompressor.create(-1, bs);
        return new ScatterZipOutputStream(bs, sc);
    }

    public ParallelScatterZipCreator() {
        this(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));
    }

    public ParallelScatterZipCreator(ExecutorService executorService) {
        this(executorService, new DefaultBackingStoreSupplier());
    }

    public ParallelScatterZipCreator(ExecutorService executorService, ScatterGatherBackingStoreSupplier backingStoreSupplier) {
        this.backingStoreSupplier = backingStoreSupplier;
        this.es = executorService;
    }

    public void addArchiveEntry(ZipArchiveEntry zipArchiveEntry, InputStreamSupplier source) {
        this.submitStreamAwareCallable(this.createCallable(zipArchiveEntry, source));
    }

    public void addArchiveEntry(ZipArchiveEntryRequestSupplier zipArchiveEntryRequestSupplier) {
        this.submitStreamAwareCallable(this.createCallable(zipArchiveEntryRequestSupplier));
    }

    public final void submit(final Callable<? extends Object> callable) {
        this.submitStreamAwareCallable((Callable<? extends ScatterZipOutputStream>)new Callable<ScatterZipOutputStream>(){

            @Override
            public ScatterZipOutputStream call() throws Exception {
                callable.call();
                return (ScatterZipOutputStream)ParallelScatterZipCreator.this.tlScatterStreams.get();
            }
        });
    }

    public final void submitStreamAwareCallable(Callable<? extends ScatterZipOutputStream> callable) {
        this.futures.add(this.es.submit(callable));
    }

    public final Callable<ScatterZipOutputStream> createCallable(ZipArchiveEntry zipArchiveEntry, InputStreamSupplier source) {
        int method = zipArchiveEntry.getMethod();
        if (method == -1) {
            throw new IllegalArgumentException("Method must be set on zipArchiveEntry: " + zipArchiveEntry);
        }
        final ZipArchiveEntryRequest zipArchiveEntryRequest = ZipArchiveEntryRequest.createZipArchiveEntryRequest(zipArchiveEntry, source);
        return new Callable<ScatterZipOutputStream>(){

            @Override
            public ScatterZipOutputStream call() throws Exception {
                ScatterZipOutputStream scatterStream = (ScatterZipOutputStream)ParallelScatterZipCreator.this.tlScatterStreams.get();
                scatterStream.addArchiveEntry(zipArchiveEntryRequest);
                return scatterStream;
            }
        };
    }

    public final Callable<ScatterZipOutputStream> createCallable(final ZipArchiveEntryRequestSupplier zipArchiveEntryRequestSupplier) {
        return new Callable<ScatterZipOutputStream>(){

            @Override
            public ScatterZipOutputStream call() throws Exception {
                ScatterZipOutputStream scatterStream = (ScatterZipOutputStream)ParallelScatterZipCreator.this.tlScatterStreams.get();
                scatterStream.addArchiveEntry(zipArchiveEntryRequestSupplier.get());
                return scatterStream;
            }
        };
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void writeTo(ZipArchiveOutputStream targetStream) throws IOException, InterruptedException, ExecutionException {
        try {
            try {
                for (Future<? extends ScatterZipOutputStream> future : this.futures) {
                    future.get();
                }
            } finally {
                this.es.shutdown();
            }
            this.es.awaitTermination(60000L, TimeUnit.SECONDS);
            this.compressionDoneAt = System.currentTimeMillis();
            for (Future<? extends ScatterZipOutputStream> future : this.futures) {
                ScatterZipOutputStream scatterStream = future.get();
                scatterStream.zipEntryWriter().writeNextZipEntry(targetStream);
            }
            for (ScatterZipOutputStream scatterStream : this.streams) {
                scatterStream.close();
            }
            this.scatterDoneAt = System.currentTimeMillis();
        } finally {
            this.closeAll();
        }
    }

    public ScatterStatistics getStatisticsMessage() {
        return new ScatterStatistics(this.compressionDoneAt - this.startedAt, this.scatterDoneAt - this.compressionDoneAt);
    }

    private void closeAll() {
        for (ScatterZipOutputStream scatterStream : this.streams) {
            try {
                scatterStream.close();
            } catch (IOException iOException) {}
        }
    }

    private static class DefaultBackingStoreSupplier
    implements ScatterGatherBackingStoreSupplier {
        final AtomicInteger storeNum = new AtomicInteger(0);

        private DefaultBackingStoreSupplier() {
        }

        @Override
        public ScatterGatherBackingStore get() throws IOException {
            File tempFile = File.createTempFile("parallelscatter", "n" + this.storeNum.incrementAndGet());
            return new FileBasedScatterGatherBackingStore(tempFile);
        }
    }
}

