/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.io;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.crypto.Digest;

public class DigestOutputStream
extends OutputStream {
    protected Digest digest;

    public DigestOutputStream(Digest digest) {
        this.digest = digest;
    }

    public void write(int n) throws IOException {
        this.digest.update((byte)n);
    }

    public void write(byte[] byArray, int n, int n2) throws IOException {
        this.digest.update(byArray, n, n2);
    }

    public byte[] getDigest() {
        byte[] byArray = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(byArray, 0);
        return byArray;
    }
}

