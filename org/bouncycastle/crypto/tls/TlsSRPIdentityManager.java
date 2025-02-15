/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import org.bouncycastle.crypto.tls.TlsSRPLoginParameters;

public interface TlsSRPIdentityManager {
    public TlsSRPLoginParameters getLoginParameters(byte[] var1);
}

