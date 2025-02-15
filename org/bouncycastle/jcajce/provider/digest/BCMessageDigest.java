/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.digest;

import java.security.MessageDigest;
import org.bouncycastle.crypto.Digest;

public class BCMessageDigest
extends MessageDigest {
    protected Digest digest;

    protected BCMessageDigest(Digest digest) {
        super(digest.getAlgorithmName());
        this.digest = digest;
    }

    public void engineReset() {
        this.digest.reset();
    }

    public void engineUpdate(byte by) {
        this.digest.update(by);
    }

    public void engineUpdate(byte[] byArray, int n, int n2) {
        this.digest.update(byArray, n, n2);
    }

    public byte[] engineDigest() {
        byte[] byArray = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(byArray, 0);
        return byArray;
    }
}

