/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.util.Enumeration;
import java.util.Hashtable;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.tls.CombinedHash;
import org.bouncycastle.crypto.tls.DigestInputBuffer;
import org.bouncycastle.crypto.tls.HashAlgorithm;
import org.bouncycastle.crypto.tls.TlsContext;
import org.bouncycastle.crypto.tls.TlsHandshakeHash;
import org.bouncycastle.crypto.tls.TlsUtils;
import org.bouncycastle.util.Shorts;

class DeferredHash
implements TlsHandshakeHash {
    protected static final int BUFFERING_HASH_LIMIT = 4;
    protected TlsContext context;
    private DigestInputBuffer buf;
    private Hashtable hashes;
    private Short prfHashAlgorithm;

    DeferredHash() {
        this.buf = new DigestInputBuffer();
        this.hashes = new Hashtable();
        this.prfHashAlgorithm = null;
    }

    private DeferredHash(Short s, Digest digest) {
        this.buf = null;
        this.hashes = new Hashtable();
        this.prfHashAlgorithm = s;
        this.hashes.put(s, digest);
    }

    public void init(TlsContext tlsContext) {
        this.context = tlsContext;
    }

    public TlsHandshakeHash notifyPRFDetermined() {
        int n = this.context.getSecurityParameters().getPrfAlgorithm();
        if (n == 0) {
            CombinedHash combinedHash = new CombinedHash();
            combinedHash.init(this.context);
            this.buf.updateDigest(combinedHash);
            return combinedHash.notifyPRFDetermined();
        }
        this.prfHashAlgorithm = Shorts.valueOf(TlsUtils.getHashAlgorithmForPRFAlgorithm(n));
        this.checkTrackingHash(this.prfHashAlgorithm);
        return this;
    }

    public void trackHashAlgorithm(short s) {
        if (this.buf == null) {
            throw new IllegalStateException("Too late to track more hash algorithms");
        }
        this.checkTrackingHash(Shorts.valueOf(s));
    }

    public void sealHashAlgorithms() {
        this.checkStopBuffering();
    }

    public TlsHandshakeHash stopTracking() {
        Digest digest = TlsUtils.cloneHash(this.prfHashAlgorithm, (Digest)this.hashes.get(this.prfHashAlgorithm));
        if (this.buf != null) {
            this.buf.updateDigest(digest);
        }
        DeferredHash deferredHash = new DeferredHash(this.prfHashAlgorithm, digest);
        deferredHash.init(this.context);
        return deferredHash;
    }

    public Digest forkPRFHash() {
        this.checkStopBuffering();
        if (this.buf != null) {
            Digest digest = TlsUtils.createHash(this.prfHashAlgorithm);
            this.buf.updateDigest(digest);
            return digest;
        }
        return TlsUtils.cloneHash(this.prfHashAlgorithm, (Digest)this.hashes.get(this.prfHashAlgorithm));
    }

    public byte[] getFinalHash(short s) {
        Digest digest = (Digest)this.hashes.get(Shorts.valueOf(s));
        if (digest == null) {
            throw new IllegalStateException("HashAlgorithm." + HashAlgorithm.getText(s) + " is not being tracked");
        }
        digest = TlsUtils.cloneHash(s, digest);
        if (this.buf != null) {
            this.buf.updateDigest(digest);
        }
        byte[] byArray = new byte[digest.getDigestSize()];
        digest.doFinal(byArray, 0);
        return byArray;
    }

    public String getAlgorithmName() {
        throw new IllegalStateException("Use fork() to get a definite Digest");
    }

    public int getDigestSize() {
        throw new IllegalStateException("Use fork() to get a definite Digest");
    }

    public void update(byte by) {
        if (this.buf != null) {
            this.buf.write(by);
            return;
        }
        Enumeration enumeration = this.hashes.elements();
        while (enumeration.hasMoreElements()) {
            Digest digest = (Digest)enumeration.nextElement();
            digest.update(by);
        }
    }

    public void update(byte[] byArray, int n, int n2) {
        if (this.buf != null) {
            this.buf.write(byArray, n, n2);
            return;
        }
        Enumeration enumeration = this.hashes.elements();
        while (enumeration.hasMoreElements()) {
            Digest digest = (Digest)enumeration.nextElement();
            digest.update(byArray, n, n2);
        }
    }

    public int doFinal(byte[] byArray, int n) {
        throw new IllegalStateException("Use fork() to get a definite Digest");
    }

    public void reset() {
        if (this.buf != null) {
            this.buf.reset();
            return;
        }
        Enumeration enumeration = this.hashes.elements();
        while (enumeration.hasMoreElements()) {
            Digest digest = (Digest)enumeration.nextElement();
            digest.reset();
        }
    }

    protected void checkStopBuffering() {
        if (this.buf != null && this.hashes.size() <= 4) {
            Enumeration enumeration = this.hashes.elements();
            while (enumeration.hasMoreElements()) {
                Digest digest = (Digest)enumeration.nextElement();
                this.buf.updateDigest(digest);
            }
            this.buf = null;
        }
    }

    protected void checkTrackingHash(Short s) {
        if (!this.hashes.containsKey(s)) {
            Digest digest = TlsUtils.createHash(s);
            this.hashes.put(s, digest);
        }
    }
}

