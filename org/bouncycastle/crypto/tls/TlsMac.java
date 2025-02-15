/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.digests.LongDigest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.tls.ProtocolVersion;
import org.bouncycastle.crypto.tls.SSL3Mac;
import org.bouncycastle.crypto.tls.TlsContext;
import org.bouncycastle.crypto.tls.TlsUtils;
import org.bouncycastle.util.Arrays;

public class TlsMac {
    protected TlsContext context;
    protected byte[] secret;
    protected Mac mac;
    protected int digestBlockSize;
    protected int digestOverhead;
    protected int macLength;

    public TlsMac(TlsContext tlsContext, Digest digest, byte[] byArray, int n, int n2) {
        this.context = tlsContext;
        KeyParameter keyParameter = new KeyParameter(byArray, n, n2);
        this.secret = Arrays.clone(keyParameter.getKey());
        if (digest instanceof LongDigest) {
            this.digestBlockSize = 128;
            this.digestOverhead = 16;
        } else {
            this.digestBlockSize = 64;
            this.digestOverhead = 8;
        }
        if (TlsUtils.isSSL(tlsContext)) {
            this.mac = new SSL3Mac(digest);
            if (digest.getDigestSize() == 20) {
                this.digestOverhead = 4;
            }
        } else {
            this.mac = new HMac(digest);
        }
        this.mac.init(keyParameter);
        this.macLength = this.mac.getMacSize();
        if (tlsContext.getSecurityParameters().truncatedHMac) {
            this.macLength = Math.min(this.macLength, 10);
        }
    }

    public byte[] getMACSecret() {
        return this.secret;
    }

    public int getSize() {
        return this.macLength;
    }

    public byte[] calculateMac(long l, short s, byte[] byArray, int n, int n2) {
        ProtocolVersion protocolVersion = this.context.getServerVersion();
        boolean bl = protocolVersion.isSSL();
        byte[] byArray2 = new byte[bl ? 11 : 13];
        TlsUtils.writeUint64(l, byArray2, 0);
        TlsUtils.writeUint8(s, byArray2, 8);
        if (!bl) {
            TlsUtils.writeVersion(protocolVersion, byArray2, 9);
        }
        TlsUtils.writeUint16(n2, byArray2, byArray2.length - 2);
        this.mac.update(byArray2, 0, byArray2.length);
        this.mac.update(byArray, n, n2);
        byte[] byArray3 = new byte[this.mac.getMacSize()];
        this.mac.doFinal(byArray3, 0);
        return this.truncate(byArray3);
    }

    public byte[] calculateMacConstantTime(long l, short s, byte[] byArray, int n, int n2, int n3, byte[] byArray2) {
        byte[] byArray3 = this.calculateMac(l, s, byArray, n, n2);
        int n4 = TlsUtils.isSSL(this.context) ? 11 : 13;
        int n5 = this.getDigestBlockCount(n4 + n3) - this.getDigestBlockCount(n4 + n2);
        while (--n5 >= 0) {
            this.mac.update(byArray2, 0, this.digestBlockSize);
        }
        this.mac.update(byArray2[0]);
        this.mac.reset();
        return byArray3;
    }

    protected int getDigestBlockCount(int n) {
        return (n + this.digestOverhead) / this.digestBlockSize;
    }

    protected byte[] truncate(byte[] byArray) {
        if (byArray.length <= this.macLength) {
            return byArray;
        }
        return Arrays.copyOf(byArray, this.macLength);
    }
}

