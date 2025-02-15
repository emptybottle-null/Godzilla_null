/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.modes.gcm;

import org.bouncycastle.crypto.modes.gcm.GCMMultiplier;
import org.bouncycastle.crypto.modes.gcm.GCMUtil;

public class BasicGCMMultiplier
implements GCMMultiplier {
    private int[] H;

    public void init(byte[] byArray) {
        this.H = GCMUtil.asInts(byArray);
    }

    public void multiplyH(byte[] byArray) {
        int[] nArray = GCMUtil.asInts(byArray);
        GCMUtil.multiply(nArray, this.H);
        GCMUtil.asBytes(nArray, byArray);
    }
}

