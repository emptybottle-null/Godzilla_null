/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.crypto.tls.TlsUtils;

public class ByteQueue {
    private static final int DEFAULT_CAPACITY = 1024;
    private byte[] databuf;
    private int skipped = 0;
    private int available = 0;
    private boolean readOnlyBuf = false;

    public static int nextTwoPow(int n) {
        n |= n >> 1;
        n |= n >> 2;
        n |= n >> 4;
        n |= n >> 8;
        n |= n >> 16;
        return n + 1;
    }

    public ByteQueue() {
        this(1024);
    }

    public ByteQueue(int n) {
        this.databuf = n == 0 ? TlsUtils.EMPTY_BYTES : new byte[n];
    }

    public ByteQueue(byte[] byArray, int n, int n2) {
        this.databuf = byArray;
        this.skipped = n;
        this.available = n2;
        this.readOnlyBuf = true;
    }

    public void addData(byte[] byArray, int n, int n2) {
        if (this.readOnlyBuf) {
            throw new IllegalStateException("Cannot add data to read-only buffer");
        }
        if (this.skipped + this.available + n2 > this.databuf.length) {
            int n3 = ByteQueue.nextTwoPow(this.available + n2);
            if (n3 > this.databuf.length) {
                byte[] byArray2 = new byte[n3];
                System.arraycopy(this.databuf, this.skipped, byArray2, 0, this.available);
                this.databuf = byArray2;
            } else {
                System.arraycopy(this.databuf, this.skipped, this.databuf, 0, this.available);
            }
            this.skipped = 0;
        }
        System.arraycopy(byArray, n, this.databuf, this.skipped + this.available, n2);
        this.available += n2;
    }

    public int available() {
        return this.available;
    }

    public void copyTo(OutputStream outputStream, int n) throws IOException {
        if (n > this.available) {
            throw new IllegalStateException("Cannot copy " + n + " bytes, only got " + this.available);
        }
        outputStream.write(this.databuf, this.skipped, n);
    }

    public void read(byte[] byArray, int n, int n2, int n3) {
        if (byArray.length - n < n2) {
            throw new IllegalArgumentException("Buffer size of " + byArray.length + " is too small for a read of " + n2 + " bytes");
        }
        if (this.available - n3 < n2) {
            throw new IllegalStateException("Not enough data to read");
        }
        System.arraycopy(this.databuf, this.skipped + n3, byArray, n, n2);
    }

    public ByteArrayInputStream readFrom(int n) {
        if (n > this.available) {
            throw new IllegalStateException("Cannot read " + n + " bytes, only got " + this.available);
        }
        int n2 = this.skipped;
        this.available -= n;
        this.skipped += n;
        return new ByteArrayInputStream(this.databuf, n2, n);
    }

    public void removeData(int n) {
        if (n > this.available) {
            throw new IllegalStateException("Cannot remove " + n + " bytes, only got " + this.available);
        }
        this.available -= n;
        this.skipped += n;
    }

    public void removeData(byte[] byArray, int n, int n2, int n3) {
        this.read(byArray, n, n2, n3);
        this.removeData(n3 + n2);
    }

    public byte[] removeData(int n, int n2) {
        byte[] byArray = new byte[n];
        this.removeData(byArray, 0, n, n2);
        return byArray;
    }

    public void shrink() {
        if (this.available == 0) {
            this.databuf = TlsUtils.EMPTY_BYTES;
            this.skipped = 0;
        } else {
            int n = ByteQueue.nextTwoPow(this.available);
            if (n < this.databuf.length) {
                byte[] byArray = new byte[n];
                System.arraycopy(this.databuf, this.skipped, byArray, 0, this.available);
                this.databuf = byArray;
                this.skipped = 0;
            }
        }
    }
}

