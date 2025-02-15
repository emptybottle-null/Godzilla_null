/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.prng;

import org.bouncycastle.crypto.prng.EntropySource;

public interface EntropySourceProvider {
    public EntropySource get(int var1);
}

