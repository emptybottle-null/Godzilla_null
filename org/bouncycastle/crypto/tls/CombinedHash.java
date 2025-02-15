/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.tls.SSL3Mac;
import org.bouncycastle.crypto.tls.TlsContext;
import org.bouncycastle.crypto.tls.TlsHandshakeHash;
import org.bouncycastle.crypto.tls.TlsUtils;

class CombinedHash
implements TlsHandshakeHash {
    protected TlsContext context;
    protected Digest md5;
    protected Digest sha1;

    CombinedHash() {
        this.md5 = TlsUtils.createHash((short)1);
        this.sha1 = TlsUtils.createHash((short)2);
    }

    CombinedHash(CombinedHash combinedHash) {
        this.context = combinedHash.context;
        this.md5 = TlsUtils.cloneHash((short)1, combinedHash.md5);
        this.sha1 = TlsUtils.cloneHash((short)2, combinedHash.sha1);
    }

    public void init(TlsContext tlsContext) {
        this.context = tlsContext;
    }

    public TlsHandshakeHash notifyPRFDetermined() {
        return this;
    }

    public void trackHashAlgorithm(short s) {
        throw new IllegalStateException("CombinedHash only supports calculating the legacy PRF for handshake hash");
    }

    public void sealHashAlgorithms() {
    }

    public TlsHandshakeHash stopTracking() {
        return new CombinedHash(this);
    }

    public Digest forkPRFHash() {
        return new CombinedHash(this);
    }

    public byte[] getFinalHash(short s) {
        throw new IllegalStateException("CombinedHash doesn't support multiple hashes");
    }

    public String getAlgorithmName() {
        return this.md5.getAlgorithmName() + " and " + this.sha1.getAlgorithmName();
    }

    public int getDigestSize() {
        return this.md5.getDigestSize() + this.sha1.getDigestSize();
    }

    public void update(byte by) {
        this.md5.update(by);
        this.sha1.update(by);
    }

    public void update(byte[] byArray, int n, int n2) {
        this.md5.update(byArray, n, n2);
        this.sha1.update(byArray, n, n2);
    }

    public int doFinal(byte[] byArray, int n) {
        if (this.context != null && TlsUtils.isSSL(this.context)) {
            this.ssl3Complete(this.md5, SSL3Mac.IPAD, SSL3Mac.OPAD, 48);
            this.ssl3Complete(this.sha1, SSL3Mac.IPAD, SSL3Mac.OPAD, 40);
        }
        int n2 = this.md5.doFinal(byArray, n);
        int n3 = this.sha1.doFinal(byArray, n + n2);
        return n2 + n3;
    }

    public void reset() {
        this.md5.reset();
        this.sha1.reset();
    }

    protected void ssl3Complete(Digest digest, byte[] byArray, byte[] byArray2, int n) {
        byte[] byArray3 = this.context.getSecurityParameters().masterSecret;
        digest.update(byArray3, 0, byArray3.length);
        digest.update(byArray, 0, n);
        byte[] byArray4 = new byte[digest.getDigestSize()];
        digest.doFinal(byArray4, 0);
        digest.update(byArray3, 0, byArray3.length);
        digest.update(byArray2, 0, n);
        digest.update(byArray4, 0, byArray4.length);
    }
}

