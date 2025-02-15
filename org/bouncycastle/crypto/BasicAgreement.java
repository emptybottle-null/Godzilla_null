/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;

public interface BasicAgreement {
    public void init(CipherParameters var1);

    public int getFieldSize();

    public BigInteger calculateAgreement(CipherParameters var1);
}

