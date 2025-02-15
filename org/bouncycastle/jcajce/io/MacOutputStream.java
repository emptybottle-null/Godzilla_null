/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.io;

import java.io.IOException;
import java.io.OutputStream;
import javax.crypto.Mac;

public final class MacOutputStream
extends OutputStream {
    private Mac mac;

    public MacOutputStream(Mac mac) {
        this.mac = mac;
    }

    public void write(int n) throws IOException {
        this.mac.update((byte)n);
    }

    public void write(byte[] byArray, int n, int n2) throws IOException {
        this.mac.update(byArray, n, n2);
    }

    public byte[] getMac() {
        return this.mac.doFinal();
    }
}

