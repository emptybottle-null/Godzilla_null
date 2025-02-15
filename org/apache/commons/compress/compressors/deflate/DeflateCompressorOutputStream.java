/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.compressors.deflate;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.commons.compress.compressors.deflate.DeflateParameters;

public class DeflateCompressorOutputStream
extends CompressorOutputStream {
    private final DeflaterOutputStream out;
    private final Deflater deflater;

    public DeflateCompressorOutputStream(OutputStream outputStream) throws IOException {
        this(outputStream, new DeflateParameters());
    }

    public DeflateCompressorOutputStream(OutputStream outputStream, DeflateParameters parameters) throws IOException {
        this.deflater = new Deflater(parameters.getCompressionLevel(), !parameters.withZlibHeader());
        this.out = new DeflaterOutputStream(outputStream, this.deflater);
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
        try {
            this.out.close();
        } finally {
            this.deflater.end();
        }
    }
}

