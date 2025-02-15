/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.brotli.dec.BrotliInputStream
 */
package org.apache.commons.compress.compressors.brotli;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.utils.CountingInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.InputStreamStatistics;
import org.brotli.dec.BrotliInputStream;

public class BrotliCompressorInputStream
extends CompressorInputStream
implements InputStreamStatistics {
    private final CountingInputStream countingStream;
    private final BrotliInputStream decIS;

    public BrotliCompressorInputStream(InputStream in) throws IOException {
        this.countingStream = new CountingInputStream(in);
        this.decIS = new BrotliInputStream((InputStream)this.countingStream);
    }

    @Override
    public int available() throws IOException {
        return this.decIS.available();
    }

    @Override
    public void close() throws IOException {
        this.decIS.close();
    }

    @Override
    public int read(byte[] b) throws IOException {
        return this.decIS.read(b);
    }

    @Override
    public long skip(long n) throws IOException {
        return IOUtils.skip((InputStream)this.decIS, n);
    }

    @Override
    public void mark(int readlimit) {
        this.decIS.mark(readlimit);
    }

    @Override
    public boolean markSupported() {
        return this.decIS.markSupported();
    }

    @Override
    public int read() throws IOException {
        int ret = this.decIS.read();
        this.count(ret == -1 ? 0 : 1);
        return ret;
    }

    @Override
    public int read(byte[] buf, int off, int len) throws IOException {
        int ret = this.decIS.read(buf, off, len);
        this.count(ret);
        return ret;
    }

    public String toString() {
        return this.decIS.toString();
    }

    @Override
    public void reset() throws IOException {
        this.decIS.reset();
    }

    @Override
    public long getCompressedCount() {
        return this.countingStream.getBytesRead();
    }
}

