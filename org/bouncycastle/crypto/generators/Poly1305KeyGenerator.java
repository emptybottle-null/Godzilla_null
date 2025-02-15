/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.generators;

import org.bouncycastle.crypto.CipherKeyGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;

public class Poly1305KeyGenerator
extends CipherKeyGenerator {
    private static final byte R_MASK_LOW_2 = -4;
    private static final byte R_MASK_HIGH_4 = 15;

    public void init(KeyGenerationParameters keyGenerationParameters) {
        super.init(new KeyGenerationParameters(keyGenerationParameters.getRandom(), 256));
    }

    public byte[] generateKey() {
        byte[] byArray = super.generateKey();
        Poly1305KeyGenerator.clamp(byArray);
        return byArray;
    }

    public static void clamp(byte[] byArray) {
        if (byArray.length != 32) {
            throw new IllegalArgumentException("Poly1305 key must be 256 bits.");
        }
        byArray[3] = (byte)(byArray[3] & 0xF);
        byArray[7] = (byte)(byArray[7] & 0xF);
        byArray[11] = (byte)(byArray[11] & 0xF);
        byArray[15] = (byte)(byArray[15] & 0xF);
        byArray[4] = (byte)(byArray[4] & 0xFFFFFFFC);
        byArray[8] = (byte)(byArray[8] & 0xFFFFFFFC);
        byArray[12] = (byte)(byArray[12] & 0xFFFFFFFC);
    }

    public static void checkKey(byte[] byArray) {
        if (byArray.length != 32) {
            throw new IllegalArgumentException("Poly1305 key must be 256 bits.");
        }
        Poly1305KeyGenerator.checkMask(byArray[3], (byte)15);
        Poly1305KeyGenerator.checkMask(byArray[7], (byte)15);
        Poly1305KeyGenerator.checkMask(byArray[11], (byte)15);
        Poly1305KeyGenerator.checkMask(byArray[15], (byte)15);
        Poly1305KeyGenerator.checkMask(byArray[4], (byte)-4);
        Poly1305KeyGenerator.checkMask(byArray[8], (byte)-4);
        Poly1305KeyGenerator.checkMask(byArray[12], (byte)-4);
    }

    private static void checkMask(byte by, byte by2) {
        if ((by & ~by2) != 0) {
            throw new IllegalArgumentException("Invalid format for r portion of Poly1305 key.");
        }
    }
}

