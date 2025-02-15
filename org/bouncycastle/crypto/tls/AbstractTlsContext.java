/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.security.SecureRandom;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.prng.DigestRandomGenerator;
import org.bouncycastle.crypto.prng.RandomGenerator;
import org.bouncycastle.crypto.tls.ProtocolVersion;
import org.bouncycastle.crypto.tls.SecurityParameters;
import org.bouncycastle.crypto.tls.TlsContext;
import org.bouncycastle.crypto.tls.TlsSession;
import org.bouncycastle.crypto.tls.TlsUtils;
import org.bouncycastle.util.Times;

abstract class AbstractTlsContext
implements TlsContext {
    private static long counter = Times.nanoTime();
    private RandomGenerator nonceRandom;
    private SecureRandom secureRandom;
    private SecurityParameters securityParameters;
    private ProtocolVersion clientVersion = null;
    private ProtocolVersion serverVersion = null;
    private TlsSession session = null;
    private Object userObject = null;

    private static synchronized long nextCounterValue() {
        return ++counter;
    }

    AbstractTlsContext(SecureRandom secureRandom, SecurityParameters securityParameters) {
        Digest digest = TlsUtils.createHash((short)4);
        byte[] byArray = new byte[digest.getDigestSize()];
        secureRandom.nextBytes(byArray);
        this.nonceRandom = new DigestRandomGenerator(digest);
        this.nonceRandom.addSeedMaterial(AbstractTlsContext.nextCounterValue());
        this.nonceRandom.addSeedMaterial(Times.nanoTime());
        this.nonceRandom.addSeedMaterial(byArray);
        this.secureRandom = secureRandom;
        this.securityParameters = securityParameters;
    }

    public RandomGenerator getNonceRandomGenerator() {
        return this.nonceRandom;
    }

    public SecureRandom getSecureRandom() {
        return this.secureRandom;
    }

    public SecurityParameters getSecurityParameters() {
        return this.securityParameters;
    }

    public ProtocolVersion getClientVersion() {
        return this.clientVersion;
    }

    void setClientVersion(ProtocolVersion protocolVersion) {
        this.clientVersion = protocolVersion;
    }

    public ProtocolVersion getServerVersion() {
        return this.serverVersion;
    }

    void setServerVersion(ProtocolVersion protocolVersion) {
        this.serverVersion = protocolVersion;
    }

    public TlsSession getResumableSession() {
        return this.session;
    }

    void setResumableSession(TlsSession tlsSession) {
        this.session = tlsSession;
    }

    public Object getUserObject() {
        return this.userObject;
    }

    public void setUserObject(Object object) {
        this.userObject = object;
    }

    public byte[] exportKeyingMaterial(String string, byte[] byArray, int n) {
        if (byArray != null && !TlsUtils.isValidUint16(byArray.length)) {
            throw new IllegalArgumentException("'context_value' must have length less than 2^16 (or be null)");
        }
        SecurityParameters securityParameters = this.getSecurityParameters();
        byte[] byArray2 = securityParameters.getClientRandom();
        byte[] byArray3 = securityParameters.getServerRandom();
        int n2 = byArray2.length + byArray3.length;
        if (byArray != null) {
            n2 += 2 + byArray.length;
        }
        byte[] byArray4 = new byte[n2];
        int n3 = 0;
        System.arraycopy(byArray2, 0, byArray4, n3, byArray2.length);
        System.arraycopy(byArray3, 0, byArray4, n3 += byArray2.length, byArray3.length);
        n3 += byArray3.length;
        if (byArray != null) {
            TlsUtils.writeUint16(byArray.length, byArray4, n3);
            System.arraycopy(byArray, 0, byArray4, n3 += 2, byArray.length);
            n3 += byArray.length;
        }
        if (n3 != n2) {
            throw new IllegalStateException("error in calculation of seed for export");
        }
        return TlsUtils.PRF(this, securityParameters.getMasterSecret(), string, byArray4, n);
    }
}

