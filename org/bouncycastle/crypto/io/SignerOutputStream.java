/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.io;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.crypto.Signer;

public class SignerOutputStream
extends OutputStream {
    protected Signer signer;

    public SignerOutputStream(Signer signer) {
        this.signer = signer;
    }

    public void write(int n) throws IOException {
        this.signer.update((byte)n);
    }

    public void write(byte[] byArray, int n, int n2) throws IOException {
        this.signer.update(byArray, n, n2);
    }

    public Signer getSigner() {
        return this.signer;
    }
}

