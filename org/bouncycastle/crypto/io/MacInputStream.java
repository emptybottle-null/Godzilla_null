/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.crypto.Mac;

public class MacInputStream
extends FilterInputStream {
    protected Mac mac;

    public MacInputStream(InputStream inputStream, Mac mac) {
        super(inputStream);
        this.mac = mac;
    }

    public int read() throws IOException {
        int n = this.in.read();
        if (n >= 0) {
            this.mac.update((byte)n);
        }
        return n;
    }

    public int read(byte[] byArray, int n, int n2) throws IOException {
        int n3 = this.in.read(byArray, n, n2);
        if (n3 >= 0) {
            this.mac.update(byArray, n, n3);
        }
        return n3;
    }

    public Mac getMac() {
        return this.mac;
    }
}

