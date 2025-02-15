/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.paddings;

import java.security.SecureRandom;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.paddings.BlockCipherPadding;

public class ZeroBytePadding
implements BlockCipherPadding {
    public void init(SecureRandom secureRandom) throws IllegalArgumentException {
    }

    public String getPaddingName() {
        return "ZeroByte";
    }

    public int addPadding(byte[] byArray, int n) {
        int n2 = byArray.length - n;
        while (n < byArray.length) {
            byArray[n] = 0;
            ++n;
        }
        return n2;
    }

    public int padCount(byte[] byArray) throws InvalidCipherTextException {
        int n;
        for (n = byArray.length; n > 0 && byArray[n - 1] == 0; --n) {
        }
        return byArray.length - n;
    }
}

