/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.paddings;

import java.security.SecureRandom;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.paddings.BlockCipherPadding;

public class X923Padding
implements BlockCipherPadding {
    SecureRandom random = null;

    public void init(SecureRandom secureRandom) throws IllegalArgumentException {
        this.random = secureRandom;
    }

    public String getPaddingName() {
        return "X9.23";
    }

    public int addPadding(byte[] byArray, int n) {
        byte by = (byte)(byArray.length - n);
        while (n < byArray.length - 1) {
            byArray[n] = this.random == null ? (byte)0 : (byte)this.random.nextInt();
            ++n;
        }
        byArray[n] = by;
        return by;
    }

    public int padCount(byte[] byArray) throws InvalidCipherTextException {
        int n = byArray[byArray.length - 1] & 0xFF;
        if (n > byArray.length) {
            throw new InvalidCipherTextException("pad block corrupted");
        }
        return n;
    }
}

