/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.ec;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.ec.ECPair;
import org.bouncycastle.math.ec.ECPoint;

public interface ECEncryptor {
    public void init(CipherParameters var1);

    public ECPair encrypt(ECPoint var1);
}

