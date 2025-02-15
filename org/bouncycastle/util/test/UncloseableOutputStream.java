/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.util.test;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class UncloseableOutputStream
extends FilterOutputStream {
    public UncloseableOutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    public void close() {
        throw new RuntimeException("close() called on UncloseableOutputStream");
    }

    public void write(byte[] byArray, int n, int n2) throws IOException {
        this.out.write(byArray, n, n2);
    }
}

