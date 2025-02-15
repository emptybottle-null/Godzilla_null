/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.util.io;

import java.io.IOException;
import java.io.OutputStream;

public class TeeOutputStream
extends OutputStream {
    private OutputStream output1;
    private OutputStream output2;

    public TeeOutputStream(OutputStream outputStream, OutputStream outputStream2) {
        this.output1 = outputStream;
        this.output2 = outputStream2;
    }

    public void write(byte[] byArray) throws IOException {
        this.output1.write(byArray);
        this.output2.write(byArray);
    }

    public void write(byte[] byArray, int n, int n2) throws IOException {
        this.output1.write(byArray, n, n2);
        this.output2.write(byArray, n, n2);
    }

    public void write(int n) throws IOException {
        this.output1.write(n);
        this.output2.write(n);
    }

    public void flush() throws IOException {
        this.output1.flush();
        this.output2.flush();
    }

    public void close() throws IOException {
        this.output1.close();
        this.output2.close();
    }
}

