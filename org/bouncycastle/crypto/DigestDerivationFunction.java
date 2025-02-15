/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto;

import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.Digest;

public interface DigestDerivationFunction
extends DerivationFunction {
    public Digest getDigest();
}

