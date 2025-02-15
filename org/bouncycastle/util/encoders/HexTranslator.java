/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.util.encoders;

import org.bouncycastle.util.encoders.Translator;

public class HexTranslator
implements Translator {
    private static final byte[] hexTable = new byte[]{48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};

    public int getEncodedBlockSize() {
        return 2;
    }

    public int encode(byte[] byArray, int n, int n2, byte[] byArray2, int n3) {
        int n4 = 0;
        int n5 = 0;
        while (n4 < n2) {
            byArray2[n3 + n5] = hexTable[byArray[n] >> 4 & 0xF];
            byArray2[n3 + n5 + 1] = hexTable[byArray[n] & 0xF];
            ++n;
            ++n4;
            n5 += 2;
        }
        return n2 * 2;
    }

    public int getDecodedBlockSize() {
        return 1;
    }

    public int decode(byte[] byArray, int n, int n2, byte[] byArray2, int n3) {
        int n4 = n2 / 2;
        for (int i = 0; i < n4; ++i) {
            byte by = byArray[n + i * 2];
            byte by2 = byArray[n + i * 2 + 1];
            byArray2[n3] = by < 97 ? (byte)(by - 48 << 4) : (byte)(by - 97 + 10 << 4);
            if (by2 < 97) {
                int n5 = n3;
                byArray2[n5] = (byte)(byArray2[n5] + (byte)(by2 - 48));
            } else {
                int n6 = n3;
                byArray2[n6] = (byte)(byArray2[n6] + (byte)(by2 - 97 + 10));
            }
            ++n3;
        }
        return n4;
    }
}

