/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.security.SecureRandom;
import org.bouncycastle.crypto.prng.RandomGenerator;
import org.bouncycastle.crypto.tls.ProtocolVersion;
import org.bouncycastle.crypto.tls.SecurityParameters;
import org.bouncycastle.crypto.tls.TlsSession;

public interface TlsContext {
    public RandomGenerator getNonceRandomGenerator();

    public SecureRandom getSecureRandom();

    public SecurityParameters getSecurityParameters();

    public boolean isServer();

    public ProtocolVersion getClientVersion();

    public ProtocolVersion getServerVersion();

    public TlsSession getResumableSession();

    public Object getUserObject();

    public void setUserObject(Object var1);

    public byte[] exportKeyingMaterial(String var1, byte[] var2, int var3);
}

