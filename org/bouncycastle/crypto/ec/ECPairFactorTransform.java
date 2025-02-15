/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.ec;

import java.math.BigInteger;
import org.bouncycastle.crypto.ec.ECPairTransform;

public interface ECPairFactorTransform
extends ECPairTransform {
    public BigInteger getTransformValue();
}

