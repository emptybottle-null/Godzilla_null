/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.paddings;

import java.security.SecureRandom;
import org.bouncycastle.crypto.InvalidCipherTextException;

public interface BlockCipherPadding {
    public void init(SecureRandom var1) throws IllegalArgumentException;

    public String getPaddingName();

    public int addPadding(byte[] var1, int var2);

    public int padCount(byte[] var1) throws InvalidCipherTextException;
}

