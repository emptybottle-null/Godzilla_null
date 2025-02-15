/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.digests;

import java.io.ByteArrayOutputStream;
import org.bouncycastle.crypto.Digest;

public class NullDigest
implements Digest {
    private ByteArrayOutputStream bOut = new ByteArrayOutputStream();

    public String getAlgorithmName() {
        return "NULL";
    }

    public int getDigestSize() {
        return this.bOut.size();
    }

    public void update(byte by) {
        this.bOut.write(by);
    }

    public void update(byte[] byArray, int n, int n2) {
        this.bOut.write(byArray, n, n2);
    }

    public int doFinal(byte[] byArray, int n) {
        byte[] byArray2 = this.bOut.toByteArray();
        System.arraycopy(byArray2, 0, byArray, n, byArray2.length);
        this.reset();
        return byArray2.length;
    }

    public void reset() {
        this.bOut.reset();
    }
}

