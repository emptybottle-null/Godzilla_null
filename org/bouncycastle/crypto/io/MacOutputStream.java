/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.io;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.crypto.Mac;

public class MacOutputStream
extends OutputStream {
    protected Mac mac;

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
        byte[] byArray = new byte[this.mac.getMacSize()];
        this.mac.doFinal(byArray, 0);
        return byArray;
    }
}

