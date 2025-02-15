/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.crypto.Digest;

public class DigestInputStream
extends FilterInputStream {
    protected Digest digest;

    public DigestInputStream(InputStream inputStream, Digest digest) {
        super(inputStream);
        this.digest = digest;
    }

    public int read() throws IOException {
        int n = this.in.read();
        if (n >= 0) {
            this.digest.update((byte)n);
        }
        return n;
    }

    public int read(byte[] byArray, int n, int n2) throws IOException {
        int n3 = this.in.read(byArray, n, n2);
        if (n3 > 0) {
            this.digest.update(byArray, n, n3);
        }
        return n3;
    }

    public Digest getDigest() {
        return this.digest;
    }
}

