/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.util.io;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.util.Arrays;

public class BufferingOutputStream
extends OutputStream {
    private final OutputStream other;
    private final byte[] buf;
    private int bufOff;

    public BufferingOutputStream(OutputStream outputStream) {
        this.other = outputStream;
        this.buf = new byte[4096];
    }

    public BufferingOutputStream(OutputStream outputStream, int n) {
        this.other = outputStream;
        this.buf = new byte[n];
    }

    public void write(byte[] byArray, int n, int n2) throws IOException {
        if (n2 < this.buf.length - this.bufOff) {
            System.arraycopy(byArray, n, this.buf, this.bufOff, n2);
            this.bufOff += n2;
        } else {
            int n3 = this.buf.length - this.bufOff;
            System.arraycopy(byArray, n, this.buf, this.bufOff, n3);
            this.bufOff += n3;
            this.flush();
            n += n3;
            n2 -= n3;
            while (n2 >= this.buf.length) {
                this.other.write(byArray, n, this.buf.length);
                n += this.buf.length;
                n2 -= this.buf.length;
            }
            if (n2 > 0) {
                System.arraycopy(byArray, n, this.buf, this.bufOff, n2);
                this.bufOff += n2;
            }
        }
    }

    public void write(int n) throws IOException {
        this.buf[this.bufOff++] = (byte)n;
        if (this.bufOff == this.buf.length) {
            this.flush();
        }
    }

    public void flush() throws IOException {
        this.other.write(this.buf, 0, this.bufOff);
        this.bufOff = 0;
        Arrays.fill(this.buf, (byte)0);
    }

    public void close() throws IOException {
        this.flush();
        this.other.close();
    }
}

