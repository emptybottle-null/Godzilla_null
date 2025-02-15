/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.modes.gcm;

import org.bouncycastle.crypto.modes.gcm.GCMExponentiator;
import org.bouncycastle.crypto.modes.gcm.GCMUtil;
import org.bouncycastle.util.Arrays;

public class BasicGCMExponentiator
implements GCMExponentiator {
    private int[] x;

    public void init(byte[] byArray) {
        this.x = GCMUtil.asInts(byArray);
    }

    public void exponentiateX(long l, byte[] byArray) {
        int[] nArray = GCMUtil.oneAsInts();
        if (l > 0L) {
            int[] nArray2 = Arrays.clone(this.x);
            do {
                if ((l & 1L) != 0L) {
                    GCMUtil.multiply(nArray, nArray2);
                }
                GCMUtil.multiply(nArray2, nArray2);
            } while ((l >>>= 1) > 0L);
        }
        GCMUtil.asBytes(nArray, byArray);
    }
}

