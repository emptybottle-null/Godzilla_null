/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.util;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.jce.spec.IESParameterSpec;

public class IESUtil {
    public static IESParameterSpec guessParameterSpec(BufferedBlockCipher bufferedBlockCipher, byte[] byArray) {
        if (bufferedBlockCipher == null) {
            return new IESParameterSpec(null, null, 128);
        }
        BlockCipher blockCipher = bufferedBlockCipher.getUnderlyingCipher();
        if (blockCipher.getAlgorithmName().equals("DES") || blockCipher.getAlgorithmName().equals("RC2") || blockCipher.getAlgorithmName().equals("RC5-32") || blockCipher.getAlgorithmName().equals("RC5-64")) {
            return new IESParameterSpec(null, null, 64, 64, byArray);
        }
        if (blockCipher.getAlgorithmName().equals("SKIPJACK")) {
            return new IESParameterSpec(null, null, 80, 80, byArray);
        }
        if (blockCipher.getAlgorithmName().equals("GOST28147")) {
            return new IESParameterSpec(null, null, 256, 256, byArray);
        }
        return new IESParameterSpec(null, null, 128, 128, byArray);
    }
}

