/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.crypto.Signer;

public class SignerInputStream
extends FilterInputStream {
    protected Signer signer;

    public SignerInputStream(InputStream inputStream, Signer signer) {
        super(inputStream);
        this.signer = signer;
    }

    public int read() throws IOException {
        int n = this.in.read();
        if (n >= 0) {
            this.signer.update((byte)n);
        }
        return n;
    }

    public int read(byte[] byArray, int n, int n2) throws IOException {
        int n3 = this.in.read(byArray, n, n2);
        if (n3 > 0) {
            this.signer.update(byArray, n, n3);
        }
        return n3;
    }

    public Signer getSigner() {
        return this.signer;
    }
}

