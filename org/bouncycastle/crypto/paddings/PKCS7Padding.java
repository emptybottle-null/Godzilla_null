/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.paddings;

import java.security.SecureRandom;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.paddings.BlockCipherPadding;

public class PKCS7Padding
implements BlockCipherPadding {
    public void init(SecureRandom secureRandom) throws IllegalArgumentException {
    }

    public String getPaddingName() {
        return "PKCS7";
    }

    public int addPadding(byte[] byArray, int n) {
        byte by = (byte)(byArray.length - n);
        while (n < byArray.length) {
            byArray[n] = by;
            ++n;
        }
        return by;
    }

    public int padCount(byte[] byArray) throws InvalidCipherTextException {
        int n = byArray[byArray.length - 1] & 0xFF;
        byte by = (byte)n;
        boolean bl = n > byArray.length | n == 0;
        for (int i = 0; i < byArray.length; ++i) {
            bl |= byArray.length - i <= n & byArray[i] != by;
        }
        if (bl) {
            throw new InvalidCipherTextException("pad block corrupted");
        }
        return n;
    }
}

