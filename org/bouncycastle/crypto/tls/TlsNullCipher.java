/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.tls.TlsCipher;
import org.bouncycastle.crypto.tls.TlsContext;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsMac;
import org.bouncycastle.crypto.tls.TlsUtils;
import org.bouncycastle.util.Arrays;

public class TlsNullCipher
implements TlsCipher {
    protected TlsContext context;
    protected TlsMac writeMac;
    protected TlsMac readMac;

    public TlsNullCipher(TlsContext tlsContext) {
        this.context = tlsContext;
        this.writeMac = null;
        this.readMac = null;
    }

    public TlsNullCipher(TlsContext tlsContext, Digest digest, Digest digest2) throws IOException {
        if (digest == null != (digest2 == null)) {
            throw new TlsFatalAlert(80);
        }
        this.context = tlsContext;
        TlsMac tlsMac = null;
        TlsMac tlsMac2 = null;
        if (digest != null) {
            int n = digest.getDigestSize() + digest2.getDigestSize();
            byte[] byArray = TlsUtils.calculateKeyBlock(tlsContext, n);
            int n2 = 0;
            tlsMac = new TlsMac(tlsContext, digest, byArray, n2, digest.getDigestSize());
            tlsMac2 = new TlsMac(tlsContext, digest2, byArray, n2 += digest.getDigestSize(), digest2.getDigestSize());
            if ((n2 += digest2.getDigestSize()) != n) {
                throw new TlsFatalAlert(80);
            }
        }
        if (tlsContext.isServer()) {
            this.writeMac = tlsMac2;
            this.readMac = tlsMac;
        } else {
            this.writeMac = tlsMac;
            this.readMac = tlsMac2;
        }
    }

    public int getPlaintextLimit(int n) {
        int n2 = n;
        if (this.writeMac != null) {
            n2 -= this.writeMac.getSize();
        }
        return n2;
    }

    public byte[] encodePlaintext(long l, short s, byte[] byArray, int n, int n2) throws IOException {
        if (this.writeMac == null) {
            return Arrays.copyOfRange(byArray, n, n + n2);
        }
        byte[] byArray2 = this.writeMac.calculateMac(l, s, byArray, n, n2);
        byte[] byArray3 = new byte[n2 + byArray2.length];
        System.arraycopy(byArray, n, byArray3, 0, n2);
        System.arraycopy(byArray2, 0, byArray3, n2, byArray2.length);
        return byArray3;
    }

    public byte[] decodeCiphertext(long l, short s, byte[] byArray, int n, int n2) throws IOException {
        byte[] byArray2;
        if (this.readMac == null) {
            return Arrays.copyOfRange(byArray, n, n + n2);
        }
        int n3 = this.readMac.getSize();
        if (n2 < n3) {
            throw new TlsFatalAlert(50);
        }
        int n4 = n2 - n3;
        byte[] byArray3 = Arrays.copyOfRange(byArray, n + n4, n + n2);
        if (!Arrays.constantTimeAreEqual(byArray3, byArray2 = this.readMac.calculateMac(l, s, byArray, n, n4))) {
            throw new TlsFatalAlert(20);
        }
        return Arrays.copyOfRange(byArray, n, n + n4);
    }
}

