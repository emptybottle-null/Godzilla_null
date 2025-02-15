/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.modes.gcm;

import org.bouncycastle.crypto.modes.gcm.GCMMultiplier;
import org.bouncycastle.crypto.modes.gcm.GCMUtil;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

public class Tables8kGCMMultiplier
implements GCMMultiplier {
    private byte[] H;
    private int[][][] M;

    /*
     * Unable to fully structure code
     */
    public void init(byte[] var1_1) {
        if (this.M == null) {
            this.M = new int[32][16][4];
        } else if (Arrays.areEqual(this.H, var1_1)) {
            return;
        }
        this.H = Arrays.clone(var1_1);
        GCMUtil.asInts(var1_1, this.M[1][8]);
        for (var2_2 = 4; var2_2 >= 1; var2_2 >>= 1) {
            GCMUtil.multiplyP(this.M[1][var2_2 + var2_2], this.M[1][var2_2]);
        }
        GCMUtil.multiplyP(this.M[1][1], this.M[0][8]);
        for (var2_2 = 4; var2_2 >= 1; var2_2 >>= 1) {
            GCMUtil.multiplyP(this.M[0][var2_2 + var2_2], this.M[0][var2_2]);
        }
        var2_2 = 0;
        while (true) lbl-1000:
        // 4 sources

        {
            for (var3_3 = 2; var3_3 < 16; var3_3 += var3_3) {
                for (var4_4 = 1; var4_4 < var3_3; ++var4_4) {
                    GCMUtil.xor(this.M[var2_2][var3_3], this.M[var2_2][var4_4], this.M[var2_2][var3_3 + var4_4]);
                }
            }
            if (++var2_2 == 32) {
                return;
            }
            if (var2_2 <= 1) ** continue;
            var3_3 = 8;
            while (true) {
                if (var3_3 > 0) ** break;
                ** continue;
                GCMUtil.multiplyP8(this.M[var2_2 - 2][var3_3], this.M[var2_2][var3_3]);
                var3_3 >>= 1;
            }
            break;
        }
    }

    public void multiplyH(byte[] byArray) {
        int[] nArray = new int[4];
        for (int i = 15; i >= 0; --i) {
            int[] nArray2 = this.M[i + i][byArray[i] & 0xF];
            nArray[0] = nArray[0] ^ nArray2[0];
            nArray[1] = nArray[1] ^ nArray2[1];
            nArray[2] = nArray[2] ^ nArray2[2];
            nArray[3] = nArray[3] ^ nArray2[3];
            nArray2 = this.M[i + i + 1][(byArray[i] & 0xF0) >>> 4];
            nArray[0] = nArray[0] ^ nArray2[0];
            nArray[1] = nArray[1] ^ nArray2[1];
            nArray[2] = nArray[2] ^ nArray2[2];
            nArray[3] = nArray[3] ^ nArray2[3];
        }
        Pack.intToBigEndian(nArray, byArray, 0);
    }
}

