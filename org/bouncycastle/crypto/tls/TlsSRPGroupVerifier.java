/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import org.bouncycastle.crypto.params.SRP6GroupParameters;

public interface TlsSRPGroupVerifier {
    public boolean accept(SRP6GroupParameters var1);
}

