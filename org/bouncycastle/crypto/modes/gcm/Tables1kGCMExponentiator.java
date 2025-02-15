/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.modes.gcm;

import java.util.Vector;
import org.bouncycastle.crypto.modes.gcm.GCMExponentiator;
import org.bouncycastle.crypto.modes.gcm.GCMUtil;
import org.bouncycastle.util.Arrays;

public class Tables1kGCMExponentiator
implements GCMExponentiator {
    private Vector lookupPowX2;

    public void init(byte[] byArray) {
        int[] nArray = GCMUtil.asInts(byArray);
        if (this.lookupPowX2 != null && Arrays.areEqual(nArray, (int[])this.lookupPowX2.elementAt(0))) {
            return;
        }
        this.lookupPowX2 = new Vector(8);
        this.lookupPowX2.addElement(nArray);
    }

    public void exponentiateX(long l, byte[] byArray) {
        int[] nArray = GCMUtil.oneAsInts();
        int n = 0;
        while (l > 0L) {
            if ((l & 1L) != 0L) {
                this.ensureAvailable(n);
                GCMUtil.multiply(nArray, (int[])this.lookupPowX2.elementAt(n));
            }
            ++n;
            l >>>= 1;
        }
        GCMUtil.asBytes(nArray, byArray);
    }

    private void ensureAvailable(int n) {
        int n2 = this.lookupPowX2.size();
        if (n2 <= n) {
            int[] nArray = (int[])this.lookupPowX2.elementAt(n2 - 1);
            do {
                nArray = Arrays.clone(nArray);
                GCMUtil.multiply(nArray, nArray);
                this.lookupPowX2.addElement(nArray);
            } while (++n2 <= n);
        }
    }
}

