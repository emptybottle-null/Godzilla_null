/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.xmss;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.pqc.crypto.xmss.DefaultXMSSOid;
import org.bouncycastle.pqc.crypto.xmss.WOTSPlus;
import org.bouncycastle.pqc.crypto.xmss.WOTSPlusParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSOid;

public final class XMSSParameters {
    private final XMSSOid oid;
    private final WOTSPlus wotsPlus;
    private final int height;
    private final int k;

    public XMSSParameters(int n, Digest digest) {
        if (n < 2) {
            throw new IllegalArgumentException("height must be >= 2");
        }
        if (digest == null) {
            throw new NullPointerException("digest == null");
        }
        this.wotsPlus = new WOTSPlus(new WOTSPlusParameters(digest));
        this.height = n;
        this.k = this.determineMinK();
        this.oid = DefaultXMSSOid.lookup(this.getDigest().getAlgorithmName(), this.getDigestSize(), this.getWinternitzParameter(), this.wotsPlus.getParams().getLen(), n);
    }

    private int determineMinK() {
        for (int i = 2; i <= this.height; ++i) {
            if ((this.height - i) % 2 != 0) continue;
            return i;
        }
        throw new IllegalStateException("should never happen...");
    }

    protected Digest getDigest() {
        return this.wotsPlus.getParams().getDigest();
    }

    public int getDigestSize() {
        return this.wotsPlus.getParams().getDigestSize();
    }

    public int getWinternitzParameter() {
        return this.wotsPlus.getParams().getWinternitzParameter();
    }

    public int getHeight() {
        return this.height;
    }

    WOTSPlus getWOTSPlus() {
        return this.wotsPlus;
    }

    int getK() {
        return this.k;
    }
}

