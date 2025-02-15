/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.tukaani.xz.LZMAInputStream
 *  org.tukaani.xz.MemoryLimitException
 */
package org.apache.commons.compress.compressors.lzma;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.MemoryLimitException;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.utils.CountingInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.InputStreamStatistics;
import org.tukaani.xz.LZMAInputStream;

public class LZMACompressorInputStream
extends CompressorInputStream
implements InputStreamStatistics {
    private final CountingInputStream countingStream;
    private final InputStream in;

    public LZMACompressorInputStream(InputStream inputStream) throws IOException {
        this.countingStream = new CountingInputStream(inputStream);
        this.in = new LZMAInputStream((InputStream)this.countingStream, -1);
    }

    public LZMACompressorInputStream(InputStream inputStream, int memoryLimitInKb) throws IOException {
        try {
            this.countingStream = new CountingInputStream(inputStream);
            this.in = new LZMAInputStream((InputStream)this.countingStream, memoryLimitInKb);
        } catch (org.tukaani.xz.MemoryLimitException e) {
            throw new MemoryLimitException(e.getMemoryNeeded(), e.getMemoryLimit(), (Exception)((Object)e));
        }
    }

    @Override
    public int read() throws IOException {
        int ret = this.in.read();
        this.count(ret == -1 ? 0 : 1);
        return ret;
    }

    @Override
    public int read(byte[] buf, int off, int len) throws IOException {
        int ret = this.in.read(buf, off, len);
        this.count(ret);
        return ret;
    }

    @Override
    public long skip(long n) throws IOException {
        return IOUtils.skip(this.in, n);
    }

    @Override
    public int available() throws IOException {
        return this.in.available();
    }

    @Override
    public void close() throws IOException {
        this.in.close();
    }

    @Override
    public long getCompressedCount() {
        return this.countingStream.getBytesRead();
    }

    public static boolean matches(byte[] signature, int length) {
        return signature != null && length >= 3 && signature[0] == 93 && signature[1] == 0 && signature[2] == 0;
    }
}

