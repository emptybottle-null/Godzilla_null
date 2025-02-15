/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.xml.cpx;

import com.kitfox.svg.xml.cpx.CPXConsts;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.Deflater;

public class CPXOutputStream
extends FilterOutputStream
implements CPXConsts {
    Deflater deflater = new Deflater(9);
    byte[] deflateBuffer = new byte[2048];

    public CPXOutputStream(OutputStream os) throws IOException {
        super(os);
        os.write(MAGIC_NUMBER);
    }

    @Override
    public void write(int b) throws IOException {
        byte[] buf = new byte[]{(byte)b};
        this.write(buf, 0, 1);
    }

    @Override
    public void write(byte[] b) throws IOException {
        this.write(b, 0, b.length);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        this.deflater.setInput(b, off, len);
        this.processAllData();
    }

    protected void processAllData() throws IOException {
        int numDeflatedBytes;
        while ((numDeflatedBytes = this.deflater.deflate(this.deflateBuffer)) != 0) {
            this.out.write(this.deflateBuffer, 0, numDeflatedBytes);
        }
    }

    @Override
    public void flush() throws IOException {
        this.out.flush();
    }

    @Override
    public void close() throws IOException {
        this.deflater.finish();
        this.processAllData();
        try {
            this.flush();
        } catch (IOException iOException) {
            // empty catch block
        }
        this.out.close();
    }
}

