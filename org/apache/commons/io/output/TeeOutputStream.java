/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.output;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.io.output.ProxyOutputStream;

public class TeeOutputStream
extends ProxyOutputStream {
    protected OutputStream branch;

    public TeeOutputStream(OutputStream out, OutputStream branch) {
        super(out);
        this.branch = branch;
    }

    @Override
    public synchronized void write(byte[] b) throws IOException {
        super.write(b);
        this.branch.write(b);
    }

    @Override
    public synchronized void write(byte[] b, int off, int len) throws IOException {
        super.write(b, off, len);
        this.branch.write(b, off, len);
    }

    @Override
    public synchronized void write(int b) throws IOException {
        super.write(b);
        this.branch.write(b);
    }

    @Override
    public void flush() throws IOException {
        super.flush();
        this.branch.flush();
    }

    @Override
    public void close() throws IOException {
        try {
            super.close();
        } finally {
            this.branch.close();
        }
    }
}

