/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.ec;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.ec.ECPair;

public interface ECPairTransform {
    public void init(CipherParameters var1);

    public ECPair transform(ECPair var1);
}

