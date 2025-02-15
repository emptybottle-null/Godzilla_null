/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Checksum;

public class ChecksumVerifyingInputStream
extends InputStream {
    private final InputStream in;
    private long bytesRemaining;
    private final long expectedChecksum;
    private final Checksum checksum;

    public ChecksumVerifyingInputStream(Checksum checksum, InputStream in, long size, long expectedChecksum) {
        this.checksum = checksum;
        this.in = in;
        this.expectedChecksum = expectedChecksum;
        this.bytesRemaining = size;
    }

    @Override
    public int read() throws IOException {
        if (this.bytesRemaining <= 0L) {
            return -1;
        }
        int ret = this.in.read();
        if (ret >= 0) {
            this.checksum.update(ret);
            --this.bytesRemaining;
        }
        if (this.bytesRemaining == 0L && this.expectedChecksum != this.checksum.getValue()) {
            throw new IOException("Checksum verification failed");
        }
        return ret;
    }

    @Override
    public int read(byte[] b) throws IOException {
        return this.read(b, 0, b.length);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int ret = this.in.read(b, off, len);
        if (ret >= 0) {
            this.checksum.update(b, off, ret);
            this.bytesRemaining -= (long)ret;
        }
        if (this.bytesRemaining <= 0L && this.expectedChecksum != this.checksum.getValue()) {
            throw new IOException("Checksum verification failed");
        }
        return ret;
    }

    @Override
    public long skip(long n) throws IOException {
        if (this.read() >= 0) {
            return 1L;
        }
        return 0L;
    }

    @Override
    public void close() throws IOException {
        this.in.close();
    }
}

