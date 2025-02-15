/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.tukaani.xz.FilterOptions
 *  org.tukaani.xz.LZMA2Options
 *  org.tukaani.xz.XZOutputStream
 */
package org.apache.commons.compress.compressors.xz;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.tukaani.xz.FilterOptions;
import org.tukaani.xz.LZMA2Options;
import org.tukaani.xz.XZOutputStream;

public class XZCompressorOutputStream
extends CompressorOutputStream {
    private final XZOutputStream out;

    public XZCompressorOutputStream(OutputStream outputStream) throws IOException {
        this.out = new XZOutputStream(outputStream, (FilterOptions)new LZMA2Options());
    }

    public XZCompressorOutputStream(OutputStream outputStream, int preset) throws IOException {
        this.out = new XZOutputStream(outputStream, (FilterOptions)new LZMA2Options(preset));
    }

    @Override
    public void write(int b) throws IOException {
        this.out.write(b);
    }

    @Override
    public void write(byte[] buf, int off, int len) throws IOException {
        this.out.write(buf, off, len);
    }

    @Override
    public void flush() throws IOException {
        this.out.flush();
    }

    public void finish() throws IOException {
        this.out.finish();
    }

    @Override
    public void close() throws IOException {
        this.out.close();
    }
}

