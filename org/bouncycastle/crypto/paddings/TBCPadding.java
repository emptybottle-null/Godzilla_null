/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.paddings;

import java.security.SecureRandom;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.paddings.BlockCipherPadding;

public class TBCPadding
implements BlockCipherPadding {
    public void init(SecureRandom secureRandom) throws IllegalArgumentException {
    }

    public String getPaddingName() {
        return "TBC";
    }

    public int addPadding(byte[] byArray, int n) {
        int n2 = byArray.length - n;
        byte by = n > 0 ? (byte)((byArray[n - 1] & 1) == 0 ? 255 : 0) : (byte)((byArray[byArray.length - 1] & 1) == 0 ? 255 : 0);
        while (n < byArray.length) {
            byArray[n] = by;
            ++n;
        }
        return n2;
    }

    public int padCount(byte[] byArray) throws InvalidCipherTextException {
        int n;
        byte by = byArray[byArray.length - 1];
        for (n = byArray.length - 1; n > 0 && byArray[n - 1] == by; --n) {
        }
        return byArray.length - n;
    }
}

