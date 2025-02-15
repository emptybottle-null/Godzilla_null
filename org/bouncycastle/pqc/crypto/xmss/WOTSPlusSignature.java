/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.xmss;

import org.bouncycastle.pqc.crypto.xmss.WOTSPlusParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSUtil;

final class WOTSPlusSignature {
    private byte[][] signature;

    protected WOTSPlusSignature(WOTSPlusParameters wOTSPlusParameters, byte[][] byArray) {
        if (wOTSPlusParameters == null) {
            throw new NullPointerException("params == null");
        }
        if (byArray == null) {
            throw new NullPointerException("signature == null");
        }
        if (XMSSUtil.hasNullPointer(byArray)) {
            throw new NullPointerException("signature byte array == null");
        }
        if (byArray.length != wOTSPlusParameters.getLen()) {
            throw new IllegalArgumentException("wrong signature size");
        }
        for (int i = 0; i < byArray.length; ++i) {
            if (byArray[i].length == wOTSPlusParameters.getDigestSize()) continue;
            throw new IllegalArgumentException("wrong signature format");
        }
        this.signature = XMSSUtil.cloneArray(byArray);
    }

    public byte[][] toByteArray() {
        return XMSSUtil.cloneArray(this.signature);
    }
}

