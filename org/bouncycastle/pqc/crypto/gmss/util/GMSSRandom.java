/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.gmss.util;

import org.bouncycastle.crypto.Digest;

public class GMSSRandom {
    private Digest messDigestTree;

    public GMSSRandom(Digest digest) {
        this.messDigestTree = digest;
    }

    public byte[] nextSeed(byte[] byArray) {
        byte[] byArray2 = new byte[byArray.length];
        this.messDigestTree.update(byArray, 0, byArray.length);
        byArray2 = new byte[this.messDigestTree.getDigestSize()];
        this.messDigestTree.doFinal(byArray2, 0);
        this.addByteArrays(byArray, byArray2);
        this.addOne(byArray);
        return byArray2;
    }

    private void addByteArrays(byte[] byArray, byte[] byArray2) {
        int n = 0;
        for (int i = 0; i < byArray.length; ++i) {
            int n2 = (0xFF & byArray[i]) + (0xFF & byArray2[i]) + n;
            byArray[i] = (byte)n2;
            n = (byte)(n2 >> 8);
        }
    }

    private void addOne(byte[] byArray) {
        int n = 1;
        for (int i = 0; i < byArray.length; ++i) {
            int n2 = (0xFF & byArray[i]) + n;
            byArray[i] = (byte)n2;
            n = (byte)(n2 >> 8);
        }
    }
}

