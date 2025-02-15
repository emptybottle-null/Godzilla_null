/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.tls.TlsCipher;
import org.bouncycastle.crypto.tls.TlsContext;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsMac;
import org.bouncycastle.crypto.tls.TlsUtils;
import org.bouncycastle.util.Arrays;

public class TlsStreamCipher
implements TlsCipher {
    protected TlsContext context;
    protected StreamCipher encryptCipher;
    protected StreamCipher decryptCipher;
    protected TlsMac writeMac;
    protected TlsMac readMac;
    protected boolean usesNonce;

    public TlsStreamCipher(TlsContext tlsContext, StreamCipher streamCipher, StreamCipher streamCipher2, Digest digest, Digest digest2, int n, boolean bl) throws IOException {
        CipherParameters cipherParameters;
        CipherParameters cipherParameters2;
        boolean bl2 = tlsContext.isServer();
        this.context = tlsContext;
        this.usesNonce = bl;
        this.encryptCipher = streamCipher;
        this.decryptCipher = streamCipher2;
        int n2 = 2 * n + digest.getDigestSize() + digest2.getDigestSize();
        byte[] byArray = TlsUtils.calculateKeyBlock(tlsContext, n2);
        int n3 = 0;
        TlsMac tlsMac = new TlsMac(tlsContext, digest, byArray, n3, digest.getDigestSize());
        TlsMac tlsMac2 = new TlsMac(tlsContext, digest2, byArray, n3 += digest.getDigestSize(), digest2.getDigestSize());
        KeyParameter keyParameter = new KeyParameter(byArray, n3 += digest2.getDigestSize(), n);
        KeyParameter keyParameter2 = new KeyParameter(byArray, n3 += n, n);
        if ((n3 += n) != n2) {
            throw new TlsFatalAlert(80);
        }
        if (bl2) {
            this.writeMac = tlsMac2;
            this.readMac = tlsMac;
            this.encryptCipher = streamCipher2;
            this.decryptCipher = streamCipher;
            cipherParameters2 = keyParameter2;
            cipherParameters = keyParameter;
        } else {
            this.writeMac = tlsMac;
            this.readMac = tlsMac2;
            this.encryptCipher = streamCipher;
            this.decryptCipher = streamCipher2;
            cipherParameters2 = keyParameter;
            cipherParameters = keyParameter2;
        }
        if (bl) {
            byte[] byArray2 = new byte[8];
            cipherParameters2 = new ParametersWithIV(cipherParameters2, byArray2);
            cipherParameters = new ParametersWithIV(cipherParameters, byArray2);
        }
        this.encryptCipher.init(true, cipherParameters2);
        this.decryptCipher.init(false, cipherParameters);
    }

    public int getPlaintextLimit(int n) {
        return n - this.writeMac.getSize();
    }

    public byte[] encodePlaintext(long l, short s, byte[] byArray, int n, int n2) {
        if (this.usesNonce) {
            this.updateIV(this.encryptCipher, true, l);
        }
        byte[] byArray2 = new byte[n2 + this.writeMac.getSize()];
        this.encryptCipher.processBytes(byArray, n, n2, byArray2, 0);
        byte[] byArray3 = this.writeMac.calculateMac(l, s, byArray, n, n2);
        this.encryptCipher.processBytes(byArray3, 0, byArray3.length, byArray2, n2);
        return byArray2;
    }

    public byte[] decodeCiphertext(long l, short s, byte[] byArray, int n, int n2) throws IOException {
        int n3;
        if (this.usesNonce) {
            this.updateIV(this.decryptCipher, false, l);
        }
        if (n2 < (n3 = this.readMac.getSize())) {
            throw new TlsFatalAlert(50);
        }
        int n4 = n2 - n3;
        byte[] byArray2 = new byte[n2];
        this.decryptCipher.processBytes(byArray, n, n2, byArray2, 0);
        this.checkMAC(l, s, byArray2, n4, n2, byArray2, 0, n4);
        return Arrays.copyOfRange(byArray2, 0, n4);
    }

    protected void checkMAC(long l, short s, byte[] byArray, int n, int n2, byte[] byArray2, int n3, int n4) throws IOException {
        byte[] byArray3;
        byte[] byArray4 = Arrays.copyOfRange(byArray, n, n2);
        if (!Arrays.constantTimeAreEqual(byArray4, byArray3 = this.readMac.calculateMac(l, s, byArray2, n3, n4))) {
            throw new TlsFatalAlert(20);
        }
    }

    protected void updateIV(StreamCipher streamCipher, boolean bl, long l) {
        byte[] byArray = new byte[8];
        TlsUtils.writeUint64(l, byArray, 0);
        streamCipher.init(bl, new ParametersWithIV(null, byArray));
    }
}

