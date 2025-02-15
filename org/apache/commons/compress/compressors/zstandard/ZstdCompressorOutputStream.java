/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.github.luben.zstd.ZstdOutputStream
 */
package org.apache.commons.compress.compressors.zstandard;

import com.github.luben.zstd.ZstdOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.compress.compressors.CompressorOutputStream;

public class ZstdCompressorOutputStream
extends CompressorOutputStream {
    private final ZstdOutputStream encOS;

    public ZstdCompressorOutputStream(OutputStream outStream, int level, boolean closeFrameOnFlush, boolean useChecksum) throws IOException {
        this.encOS = new ZstdOutputStream(outStream, level, closeFrameOnFlush, useChecksum);
    }

    public ZstdCompressorOutputStream(OutputStream outStream, int level, boolean closeFrameOnFlush) throws IOException {
        this.encOS = new ZstdOutputStream(outStream, level, closeFrameOnFlush);
    }

    public ZstdCompressorOutputStream(OutputStream outStream, int level) throws IOException {
        this.encOS = new ZstdOutputStream(outStream, level);
    }

    public ZstdCompressorOutputStream(OutputStream outStream) throws IOException {
        this.encOS = new ZstdOutputStream(outStream);
    }

    @Override
    public void close() throws IOException {
        this.encOS.close();
    }

    @Override
    public void write(int b) throws IOException {
        this.encOS.write(b);
    }

    @Override
    public void write(byte[] buf, int off, int len) throws IOException {
        this.encOS.write(buf, off, len);
    }

    public String toString() {
        return this.encOS.toString();
    }

    @Override
    public void flush() throws IOException {
        this.encOS.flush();
    }
}

