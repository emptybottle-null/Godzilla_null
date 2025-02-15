/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import org.bouncycastle.crypto.modes.AEADBlockCipher;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.tls.TlsCipher;
import org.bouncycastle.crypto.tls.TlsContext;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsUtils;
import org.bouncycastle.util.Arrays;

public class TlsAEADCipher
implements TlsCipher {
    public static final int NONCE_RFC5288 = 1;
    static final int NONCE_DRAFT_CHACHA20_POLY1305 = 2;
    protected TlsContext context;
    protected int macSize;
    protected int record_iv_length;
    protected AEADBlockCipher encryptCipher;
    protected AEADBlockCipher decryptCipher;
    protected byte[] encryptImplicitNonce;
    protected byte[] decryptImplicitNonce;
    protected int nonceMode;

    public TlsAEADCipher(TlsContext tlsContext, AEADBlockCipher aEADBlockCipher, AEADBlockCipher aEADBlockCipher2, int n, int n2) throws IOException {
        this(tlsContext, aEADBlockCipher, aEADBlockCipher2, n, n2, 1);
    }

    TlsAEADCipher(TlsContext tlsContext, AEADBlockCipher aEADBlockCipher, AEADBlockCipher aEADBlockCipher2, int n, int n2, int n3) throws IOException {
        KeyParameter keyParameter;
        KeyParameter keyParameter2;
        int n4;
        if (!TlsUtils.isTLSv12(tlsContext)) {
            throw new TlsFatalAlert(80);
        }
        this.nonceMode = n3;
        switch (n3) {
            case 1: {
                n4 = 4;
                this.record_iv_length = 8;
                break;
            }
            case 2: {
                n4 = 12;
                this.record_iv_length = 0;
                break;
            }
            default: {
                throw new TlsFatalAlert(80);
            }
        }
        this.context = tlsContext;
        this.macSize = n2;
        int n5 = 2 * n + 2 * n4;
        byte[] byArray = TlsUtils.calculateKeyBlock(tlsContext, n5);
        int n6 = 0;
        KeyParameter keyParameter3 = new KeyParameter(byArray, n6, n);
        KeyParameter keyParameter4 = new KeyParameter(byArray, n6 += n, n);
        byte[] byArray2 = Arrays.copyOfRange(byArray, n6 += n, n6 + n4);
        byte[] byArray3 = Arrays.copyOfRange(byArray, n6 += n4, n6 + n4);
        if ((n6 += n4) != n5) {
            throw new TlsFatalAlert(80);
        }
        if (tlsContext.isServer()) {
            this.encryptCipher = aEADBlockCipher2;
            this.decryptCipher = aEADBlockCipher;
            this.encryptImplicitNonce = byArray3;
            this.decryptImplicitNonce = byArray2;
            keyParameter2 = keyParameter4;
            keyParameter = keyParameter3;
        } else {
            this.encryptCipher = aEADBlockCipher;
            this.decryptCipher = aEADBlockCipher2;
            this.encryptImplicitNonce = byArray2;
            this.decryptImplicitNonce = byArray3;
            keyParameter2 = keyParameter3;
            keyParameter = keyParameter4;
        }
        byte[] byArray4 = new byte[n4 + this.record_iv_length];
        this.encryptCipher.init(true, new AEADParameters(keyParameter2, 8 * n2, byArray4));
        this.decryptCipher.init(false, new AEADParameters(keyParameter, 8 * n2, byArray4));
    }

    public int getPlaintextLimit(int n) {
        return n - this.macSize - this.record_iv_length;
    }

    public byte[] encodePlaintext(long l, short s, byte[] byArray, int n, int n2) throws IOException {
        int n3;
        byte[] byArray2 = new byte[this.encryptImplicitNonce.length + this.record_iv_length];
        switch (this.nonceMode) {
            case 1: {
                System.arraycopy(this.encryptImplicitNonce, 0, byArray2, 0, this.encryptImplicitNonce.length);
                TlsUtils.writeUint64(l, byArray2, this.encryptImplicitNonce.length);
                break;
            }
            case 2: {
                TlsUtils.writeUint64(l, byArray2, byArray2.length - 8);
                for (n3 = 0; n3 < this.encryptImplicitNonce.length; ++n3) {
                    int n4 = n3;
                    byArray2[n4] = (byte)(byArray2[n4] ^ this.encryptImplicitNonce[n3]);
                }
                break;
            }
            default: {
                throw new TlsFatalAlert(80);
            }
        }
        n3 = n;
        int n5 = n2;
        int n6 = this.encryptCipher.getOutputSize(n5);
        byte[] byArray3 = new byte[this.record_iv_length + n6];
        if (this.record_iv_length != 0) {
            System.arraycopy(byArray2, byArray2.length - this.record_iv_length, byArray3, 0, this.record_iv_length);
        }
        int n7 = this.record_iv_length;
        byte[] byArray4 = this.getAdditionalData(l, s, n5);
        AEADParameters aEADParameters = new AEADParameters(null, 8 * this.macSize, byArray2, byArray4);
        try {
            this.encryptCipher.init(true, aEADParameters);
            n7 += this.encryptCipher.processBytes(byArray, n3, n5, byArray3, n7);
            n7 += this.encryptCipher.doFinal(byArray3, n7);
        } catch (Exception exception) {
            throw new TlsFatalAlert(80, (Throwable)exception);
        }
        if (n7 != byArray3.length) {
            throw new TlsFatalAlert(80);
        }
        return byArray3;
    }

    public byte[] decodeCiphertext(long l, short s, byte[] byArray, int n, int n2) throws IOException {
        int n3;
        if (this.getPlaintextLimit(n2) < 0) {
            throw new TlsFatalAlert(50);
        }
        byte[] byArray2 = new byte[this.decryptImplicitNonce.length + this.record_iv_length];
        switch (this.nonceMode) {
            case 1: {
                System.arraycopy(this.decryptImplicitNonce, 0, byArray2, 0, this.decryptImplicitNonce.length);
                System.arraycopy(byArray, n, byArray2, byArray2.length - this.record_iv_length, this.record_iv_length);
                break;
            }
            case 2: {
                TlsUtils.writeUint64(l, byArray2, byArray2.length - 8);
                for (n3 = 0; n3 < this.decryptImplicitNonce.length; ++n3) {
                    int n4 = n3;
                    byArray2[n4] = (byte)(byArray2[n4] ^ this.decryptImplicitNonce[n3]);
                }
                break;
            }
            default: {
                throw new TlsFatalAlert(80);
            }
        }
        n3 = n + this.record_iv_length;
        int n5 = n2 - this.record_iv_length;
        int n6 = this.decryptCipher.getOutputSize(n5);
        byte[] byArray3 = new byte[n6];
        int n7 = 0;
        byte[] byArray4 = this.getAdditionalData(l, s, n6);
        AEADParameters aEADParameters = new AEADParameters(null, 8 * this.macSize, byArray2, byArray4);
        try {
            this.decryptCipher.init(false, aEADParameters);
            n7 += this.decryptCipher.processBytes(byArray, n3, n5, byArray3, n7);
            n7 += this.decryptCipher.doFinal(byArray3, n7);
        } catch (Exception exception) {
            throw new TlsFatalAlert(20, (Throwable)exception);
        }
        if (n7 != byArray3.length) {
            throw new TlsFatalAlert(80);
        }
        return byArray3;
    }

    protected byte[] getAdditionalData(long l, short s, int n) throws IOException {
        byte[] byArray = new byte[13];
        TlsUtils.writeUint64(l, byArray, 0);
        TlsUtils.writeUint8(s, byArray, 8);
        TlsUtils.writeVersion(this.context.getServerVersion(), byArray, 9);
        TlsUtils.writeUint16(n, byArray, 11);
        return byArray;
    }
}

