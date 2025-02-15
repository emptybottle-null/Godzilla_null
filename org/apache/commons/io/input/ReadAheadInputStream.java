/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.input;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReadAheadInputStream
extends InputStream {
    private static final ThreadLocal<byte[]> oneByte = ThreadLocal.withInitial(() -> new byte[1]);
    private final ReentrantLock stateChangeLock = new ReentrantLock();
    private ByteBuffer activeBuffer;
    private ByteBuffer readAheadBuffer;
    private boolean endOfStream;
    private boolean readInProgress;
    private boolean readAborted;
    private Throwable readException;
    private boolean isClosed;
    private boolean isUnderlyingInputStreamBeingClosed;
    private boolean isReading;
    private final AtomicBoolean isWaiting = new AtomicBoolean(false);
    private final InputStream underlyingInputStream;
    private final ExecutorService executorService;
    private final boolean shutdownExecutorService;
    private final Condition asyncReadComplete = this.stateChangeLock.newCondition();

    private static ExecutorService newExecutorService() {
        return Executors.newSingleThreadExecutor(ReadAheadInputStream::newThread);
    }

    private static Thread newThread(Runnable r) {
        Thread thread = new Thread(r, "commons-io-read-ahead");
        thread.setDaemon(true);
        return thread;
    }

    public ReadAheadInputStream(InputStream inputStream, int bufferSizeInBytes) {
        this(inputStream, bufferSizeInBytes, ReadAheadInputStream.newExecutorService(), true);
    }

    public ReadAheadInputStream(InputStream inputStream, int bufferSizeInBytes, ExecutorService executorService) {
        this(inputStream, bufferSizeInBytes, executorService, false);
    }

    private ReadAheadInputStream(InputStream inputStream, int bufferSizeInBytes, ExecutorService executorService, boolean shutdownExecutorService) {
        if (bufferSizeInBytes <= 0) {
            throw new IllegalArgumentException("bufferSizeInBytes should be greater than 0, but the value is " + bufferSizeInBytes);
        }
        this.executorService = Objects.requireNonNull(executorService, "executorService");
        this.underlyingInputStream = Objects.requireNonNull(inputStream, "inputStream");
        this.shutdownExecutorService = shutdownExecutorService;
        this.activeBuffer = ByteBuffer.allocate(bufferSizeInBytes);
        this.readAheadBuffer = ByteBuffer.allocate(bufferSizeInBytes);
        this.activeBuffer.flip();
        this.readAheadBuffer.flip();
    }

    @Override
    public int available() throws IOException {
        this.stateChangeLock.lock();
        try {
            int n = (int)Math.min(Integer.MAX_VALUE, (long)this.activeBuffer.remaining() + (long)this.readAheadBuffer.remaining());
            return n;
        } finally {
            this.stateChangeLock.unlock();
        }
    }

    private void checkReadException() throws IOException {
        if (this.readAborted) {
            if (this.readException instanceof IOException) {
                throw (IOException)this.readException;
            }
            throw new IOException(this.readException);
        }
    }

    @Override
    public void close() throws IOException {
        boolean isSafeToCloseUnderlyingInputStream = false;
        this.stateChangeLock.lock();
        try {
            if (this.isClosed) {
                return;
            }
            this.isClosed = true;
            if (!this.isReading) {
                isSafeToCloseUnderlyingInputStream = true;
                this.isUnderlyingInputStreamBeingClosed = true;
            }
        } finally {
            this.stateChangeLock.unlock();
        }
        if (this.shutdownExecutorService) {
            try {
                this.executorService.shutdownNow();
                this.executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                InterruptedIOException iio = new InterruptedIOException(e.getMessage());
                iio.initCause(e);
                throw iio;
            } finally {
                if (isSafeToCloseUnderlyingInputStream) {
                    this.underlyingInputStream.close();
                }
            }
        }
    }

    private void closeUnderlyingInputStreamIfNecessary() {
        boolean needToCloseUnderlyingInputStream = false;
        this.stateChangeLock.lock();
        try {
            this.isReading = false;
            if (this.isClosed && !this.isUnderlyingInputStreamBeingClosed) {
                needToCloseUnderlyingInputStream = true;
            }
        } finally {
            this.stateChangeLock.unlock();
        }
        if (needToCloseUnderlyingInputStream) {
            try {
                this.underlyingInputStream.close();
            } catch (IOException iOException) {
                // empty catch block
            }
        }
    }

    private boolean isEndOfStream() {
        return !this.activeBuffer.hasRemaining() && !this.readAheadBuffer.hasRemaining() && this.endOfStream;
    }

    @Override
    public int read() throws IOException {
        if (this.activeBuffer.hasRemaining()) {
            return this.activeBuffer.get() & 0xFF;
        }
        byte[] oneByteArray = oneByte.get();
        return this.read(oneByteArray, 0, 1) == -1 ? -1 : oneByteArray[0] & 0xFF;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int read(byte[] b, int offset, int len) throws IOException {
        if (offset < 0 || len < 0 || len > b.length - offset) {
            throw new IndexOutOfBoundsException();
        }
        if (len == 0) {
            return 0;
        }
        if (!this.activeBuffer.hasRemaining()) {
            this.stateChangeLock.lock();
            try {
                this.waitForAsyncReadComplete();
                if (!this.readAheadBuffer.hasRemaining()) {
                    this.readAsync();
                    this.waitForAsyncReadComplete();
                    if (this.isEndOfStream()) {
                        int n = -1;
                        return n;
                    }
                }
                this.swapBuffers();
                this.readAsync();
            } finally {
                this.stateChangeLock.unlock();
            }
        }
        len = Math.min(len, this.activeBuffer.remaining());
        this.activeBuffer.get(b, offset, len);
        return len;
    }

    private void readAsync() throws IOException {
        byte[] arr;
        this.stateChangeLock.lock();
        try {
            arr = this.readAheadBuffer.array();
            if (this.endOfStream || this.readInProgress) {
                return;
            }
            this.checkReadException();
            this.readAheadBuffer.position(0);
            this.readAheadBuffer.flip();
            this.readInProgress = true;
        } finally {
            this.stateChangeLock.unlock();
        }
        this.executorService.execute(() -> {
            this.stateChangeLock.lock();
            try {
                if (this.isClosed) {
                    this.readInProgress = false;
                    return;
                }
                this.isReading = true;
            } finally {
                this.stateChangeLock.unlock();
            }
            int read = 0;
            int off = 0;
            int len = arr.length;
            Throwable exception = null;
            try {
                do {
                    if ((read = this.underlyingInputStream.read(arr, off, len)) <= 0) {
                        break;
                    }
                    off += read;
                } while ((len -= read) > 0 && !this.isWaiting.get());
            } catch (Throwable ex) {
                exception = ex;
                if (ex instanceof Error) {
                    throw (Error)ex;
                }
            } finally {
                this.stateChangeLock.lock();
                try {
                    this.readAheadBuffer.limit(off);
                    if (read < 0 || exception instanceof EOFException) {
                        this.endOfStream = true;
                    } else if (exception != null) {
                        this.readAborted = true;
                        this.readException = exception;
                    }
                    this.readInProgress = false;
                    this.signalAsyncReadComplete();
                } finally {
                    this.stateChangeLock.unlock();
                }
                this.closeUnderlyingInputStreamIfNecessary();
            }
        });
    }

    private void signalAsyncReadComplete() {
        this.stateChangeLock.lock();
        try {
            this.asyncReadComplete.signalAll();
        } finally {
            this.stateChangeLock.unlock();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long skip(long n) throws IOException {
        long skipped;
        if (n <= 0L) {
            return 0L;
        }
        if (n <= (long)this.activeBuffer.remaining()) {
            this.activeBuffer.position((int)n + this.activeBuffer.position());
            return n;
        }
        this.stateChangeLock.lock();
        try {
            skipped = this.skipInternal(n);
        } finally {
            this.stateChangeLock.unlock();
        }
        return skipped;
    }

    private long skipInternal(long n) throws IOException {
        assert (this.stateChangeLock.isLocked());
        this.waitForAsyncReadComplete();
        if (this.isEndOfStream()) {
            return 0L;
        }
        if ((long)this.available() >= n) {
            int toSkip = (int)n;
            assert ((toSkip -= this.activeBuffer.remaining()) > 0);
            this.activeBuffer.position(0);
            this.activeBuffer.flip();
            this.readAheadBuffer.position(toSkip + this.readAheadBuffer.position());
            this.swapBuffers();
            this.readAsync();
            return n;
        }
        int skippedBytes = this.available();
        long toSkip = n - (long)skippedBytes;
        this.activeBuffer.position(0);
        this.activeBuffer.flip();
        this.readAheadBuffer.position(0);
        this.readAheadBuffer.flip();
        long skippedFromInputStream = this.underlyingInputStream.skip(toSkip);
        this.readAsync();
        return (long)skippedBytes + skippedFromInputStream;
    }

    private void swapBuffers() {
        ByteBuffer temp = this.activeBuffer;
        this.activeBuffer = this.readAheadBuffer;
        this.readAheadBuffer = temp;
    }

    private void waitForAsyncReadComplete() throws IOException {
        this.stateChangeLock.lock();
        try {
            this.isWaiting.set(true);
            while (this.readInProgress) {
                this.asyncReadComplete.await();
            }
        } catch (InterruptedException e) {
            InterruptedIOException iio = new InterruptedIOException(e.getMessage());
            iio.initCause(e);
            throw iio;
        } finally {
            this.isWaiting.set(false);
            this.stateChangeLock.unlock();
        }
        this.checkReadException();
    }
}

