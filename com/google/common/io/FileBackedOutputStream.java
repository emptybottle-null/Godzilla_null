/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.io.ByteSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.checkerframework.checker.nullness.qual.Nullable;

@Beta
@GwtIncompatible
public final class FileBackedOutputStream
extends OutputStream {
    private final int fileThreshold;
    private final boolean resetOnFinalize;
    private final ByteSource source;
    private OutputStream out;
    private MemoryOutput memory;
    private @Nullable File file;

    @VisibleForTesting
    synchronized File getFile() {
        return this.file;
    }

    public FileBackedOutputStream(int fileThreshold) {
        this(fileThreshold, false);
    }

    public FileBackedOutputStream(int fileThreshold, boolean resetOnFinalize) {
        this.fileThreshold = fileThreshold;
        this.resetOnFinalize = resetOnFinalize;
        this.memory = new MemoryOutput();
        this.out = this.memory;
        this.source = resetOnFinalize ? new ByteSource(){

            @Override
            public InputStream openStream() throws IOException {
                return FileBackedOutputStream.this.openInputStream();
            }

            protected void finalize() {
                try {
                    FileBackedOutputStream.this.reset();
                } catch (Throwable t) {
                    t.printStackTrace(System.err);
                }
            }
        } : new ByteSource(){

            @Override
            public InputStream openStream() throws IOException {
                return FileBackedOutputStream.this.openInputStream();
            }
        };
    }

    public ByteSource asByteSource() {
        return this.source;
    }

    private synchronized InputStream openInputStream() throws IOException {
        if (this.file != null) {
            return new FileInputStream(this.file);
        }
        return new ByteArrayInputStream(this.memory.getBuffer(), 0, this.memory.getCount());
    }

    public synchronized void reset() throws IOException {
        try {
            this.close();
        } finally {
            if (this.memory == null) {
                this.memory = new MemoryOutput();
            } else {
                this.memory.reset();
            }
            this.out = this.memory;
            if (this.file != null) {
                File deleteMe = this.file;
                this.file = null;
                if (!deleteMe.delete()) {
                    throw new IOException("Could not delete: " + deleteMe);
                }
            }
        }
    }

    @Override
    public synchronized void write(int b) throws IOException {
        this.update(1);
        this.out.write(b);
    }

    @Override
    public synchronized void write(byte[] b) throws IOException {
        this.write(b, 0, b.length);
    }

    @Override
    public synchronized void write(byte[] b, int off, int len) throws IOException {
        this.update(len);
        this.out.write(b, off, len);
    }

    @Override
    public synchronized void close() throws IOException {
        this.out.close();
    }

    @Override
    public synchronized void flush() throws IOException {
        this.out.flush();
    }

    private void update(int len) throws IOException {
        if (this.file == null && this.memory.getCount() + len > this.fileThreshold) {
            File temp = File.createTempFile("FileBackedOutputStream", null);
            if (this.resetOnFinalize) {
                temp.deleteOnExit();
            }
            FileOutputStream transfer = new FileOutputStream(temp);
            transfer.write(this.memory.getBuffer(), 0, this.memory.getCount());
            transfer.flush();
            this.out = transfer;
            this.file = temp;
            this.memory = null;
        }
    }

    private static class MemoryOutput
    extends ByteArrayOutputStream {
        private MemoryOutput() {
        }

        byte[] getBuffer() {
            return this.buf;
        }

        int getCount() {
            return this.count;
        }
    }
}

