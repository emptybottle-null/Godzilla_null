/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.modes.gcm;

import org.bouncycastle.crypto.modes.gcm.GCMMultiplier;
import org.bouncycastle.crypto.modes.gcm.GCMUtil;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

public class Tables64kGCMMultiplier
implements GCMMultiplier {
    private byte[] H;
    private int[][][] M;

    /*
     * Unable to fully structure code
     */
    public void init(byte[] var1_1) {
        if (this.M == null) {
            this.M = new int[16][256][4];
        } else if (Arrays.areEqual(this.H, var1_1)) {
            return;
        }
        this.H = Arrays.clone(var1_1);
        GCMUtil.asInts(var1_1, this.M[0][128]);
        for (var2_2 = 64; var2_2 >= 1; var2_2 >>= 1) {
            GCMUtil.multiplyP(this.M[0][var2_2 + var2_2], this.M[0][var2_2]);
        }
        var2_2 = 0;
        while (true) {
            for (var3_3 = 2; var3_3 < 256; var3_3 += var3_3) {
                for (var4_4 = 1; var4_4 < var3_3; ++var4_4) {
                    GCMUtil.xor(this.M[var2_2][var3_3], this.M[var2_2][var4_4], this.M[var2_2][var3_3 + var4_4]);
                }
            }
            if (++var2_2 == 16) {
                return;
            }
            var3_3 = 128;
            while (true) {
                if (var3_3 <= 0) ** continue;
                GCMUtil.multiplyP8(this.M[var2_2 - 1][var3_3], this.M[var2_2][var3_3]);
                var3_3 >>= 1;
            }
            break;
        }
    }

    public void multiplyH(byte[] byArray) {
        int[] nArray = new int[4];
        for (int i = 15; i >= 0; --i) {
            int[] nArray2 = this.M[i][byArray[i] & 0xFF];
            nArray[0] = nArray[0] ^ nArray2[0];
            nArray[1] = nArray[1] ^ nArray2[1];
            nArray[2] = nArray[2] ^ nArray2[2];
            nArray[3] = nArray[3] ^ nArray2[3];
        }
        Pack.intToBigEndian(nArray, byArray, 0);
    }
}

