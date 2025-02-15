/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.prng;

import org.bouncycastle.crypto.prng.EntropySource;

public class EntropyUtil {
    public static byte[] generateSeed(EntropySource entropySource, int n) {
        byte[] byArray = new byte[n];
        if (n * 8 <= entropySource.entropySize()) {
            byte[] byArray2 = entropySource.getEntropy();
            System.arraycopy(byArray2, 0, byArray, 0, byArray.length);
        } else {
            int n2 = entropySource.entropySize() / 8;
            for (int i = 0; i < byArray.length; i += n2) {
                byte[] byArray3 = entropySource.getEntropy();
                if (byArray3.length <= byArray.length - i) {
                    System.arraycopy(byArray3, 0, byArray, i, byArray3.length);
                    continue;
                }
                System.arraycopy(byArray3, 0, byArray, i, byArray.length - i);
            }
        }
        return byArray;
    }
}

