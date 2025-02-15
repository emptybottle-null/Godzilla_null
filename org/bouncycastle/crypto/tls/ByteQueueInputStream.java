/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.InputStream;
import org.bouncycastle.crypto.tls.ByteQueue;

public class ByteQueueInputStream
extends InputStream {
    private ByteQueue buffer = new ByteQueue();

    public void addBytes(byte[] byArray) {
        this.buffer.addData(byArray, 0, byArray.length);
    }

    public int peek(byte[] byArray) {
        int n = Math.min(this.buffer.available(), byArray.length);
        this.buffer.read(byArray, 0, n, 0);
        return n;
    }

    public int read() {
        if (this.buffer.available() == 0) {
            return -1;
        }
        return this.buffer.removeData(1, 0)[0] & 0xFF;
    }

    public int read(byte[] byArray) {
        return this.read(byArray, 0, byArray.length);
    }

    public int read(byte[] byArray, int n, int n2) {
        int n3 = Math.min(this.buffer.available(), n2);
        this.buffer.removeData(byArray, n, n3, 0);
        return n3;
    }

    public long skip(long l) {
        int n = Math.min((int)l, this.buffer.available());
        this.buffer.removeData(n);
        return n;
    }

    public int available() {
        return this.buffer.available();
    }

    public void close() {
    }
}

