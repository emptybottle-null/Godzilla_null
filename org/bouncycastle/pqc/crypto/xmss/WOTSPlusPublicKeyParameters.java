/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.xmss;

import org.bouncycastle.pqc.crypto.xmss.WOTSPlusParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSUtil;

final class WOTSPlusPublicKeyParameters {
    private final byte[][] publicKey;

    protected WOTSPlusPublicKeyParameters(WOTSPlusParameters wOTSPlusParameters, byte[][] byArray) {
        if (wOTSPlusParameters == null) {
            throw new NullPointerException("params == null");
        }
        if (byArray == null) {
            throw new NullPointerException("publicKey == null");
        }
        if (XMSSUtil.hasNullPointer(byArray)) {
            throw new NullPointerException("publicKey byte array == null");
        }
        if (byArray.length != wOTSPlusParameters.getLen()) {
            throw new IllegalArgumentException("wrong publicKey size");
        }
        for (int i = 0; i < byArray.length; ++i) {
            if (byArray[i].length == wOTSPlusParameters.getDigestSize()) continue;
            throw new IllegalArgumentException("wrong publicKey format");
        }
        this.publicKey = XMSSUtil.cloneArray(byArray);
    }

    protected byte[][] toByteArray() {
        return XMSSUtil.cloneArray(this.publicKey);
    }
}

