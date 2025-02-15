/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.crypto.tls.ByteQueue;

public class ByteQueueOutputStream
extends OutputStream {
    private ByteQueue buffer = new ByteQueue();

    public ByteQueue getBuffer() {
        return this.buffer;
    }

    public void write(int n) throws IOException {
        this.buffer.addData(new byte[]{(byte)n}, 0, 1);
    }

    public void write(byte[] byArray, int n, int n2) throws IOException {
        this.buffer.addData(byArray, n, n2);
    }
}

