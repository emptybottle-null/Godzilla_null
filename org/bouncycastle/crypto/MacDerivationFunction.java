/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto;

import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.Mac;

public interface MacDerivationFunction
extends DerivationFunction {
    public Mac getMac();
}

