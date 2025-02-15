/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.engines.ChaCha7539Engine;
import org.bouncycastle.crypto.macs.Poly1305;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.tls.TlsCipher;
import org.bouncycastle.crypto.tls.TlsContext;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsUtils;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

public class Chacha20Poly1305
implements TlsCipher {
    private static final byte[] ZEROES = new byte[15];
    protected TlsContext context;
    protected ChaCha7539Engine encryptCipher;
    protected ChaCha7539Engine decryptCipher;
    protected byte[] encryptIV;
    protected byte[] decryptIV;

    public Chacha20Poly1305(TlsContext tlsContext) throws IOException {
        KeyParameter keyParameter;
        KeyParameter keyParameter2;
        if (!TlsUtils.isTLSv12(tlsContext)) {
            throw new TlsFatalAlert(80);
        }
        this.context = tlsContext;
        int n = 32;
        int n2 = 12;
        int n3 = 2 * n + 2 * n2;
        byte[] byArray = TlsUtils.calculateKeyBlock(tlsContext, n3);
        int n4 = 0;
        KeyParameter keyParameter3 = new KeyParameter(byArray, n4, n);
        KeyParameter keyParameter4 = new KeyParameter(byArray, n4 += n, n);
        byte[] byArray2 = Arrays.copyOfRange(byArray, n4 += n, n4 + n2);
        byte[] byArray3 = Arrays.copyOfRange(byArray, n4 += n2, n4 + n2);
        if ((n4 += n2) != n3) {
            throw new TlsFatalAlert(80);
        }
        this.encryptCipher = new ChaCha7539Engine();
        this.decryptCipher = new ChaCha7539Engine();
        if (tlsContext.isServer()) {
            keyParameter2 = keyParameter4;
            keyParameter = keyParameter3;
            this.encryptIV = byArray3;
            this.decryptIV = byArray2;
        } else {
            keyParameter2 = keyParameter3;
            keyParameter = keyParameter4;
            this.encryptIV = byArray2;
            this.decryptIV = byArray3;
        }
        this.encryptCipher.init(true, new ParametersWithIV(keyParameter2, this.encryptIV));
        this.decryptCipher.init(false, new ParametersWithIV(keyParameter, this.decryptIV));
    }

    public int getPlaintextLimit(int n) {
        return n - 16;
    }

    public byte[] encodePlaintext(long l, short s, byte[] byArray, int n, int n2) throws IOException {
        KeyParameter keyParameter = this.initRecord(this.encryptCipher, true, l, this.encryptIV);
        byte[] byArray2 = new byte[n2 + 16];
        this.encryptCipher.processBytes(byArray, n, n2, byArray2, 0);
        byte[] byArray3 = this.getAdditionalData(l, s, n2);
        byte[] byArray4 = this.calculateRecordMAC(keyParameter, byArray3, byArray2, 0, n2);
        System.arraycopy(byArray4, 0, byArray2, n2, byArray4.length);
        return byArray2;
    }

    public byte[] decodeCiphertext(long l, short s, byte[] byArray, int n, int n2) throws IOException {
        byte[] byArray2;
        int n3;
        byte[] byArray3;
        if (this.getPlaintextLimit(n2) < 0) {
            throw new TlsFatalAlert(50);
        }
        KeyParameter keyParameter = this.initRecord(this.decryptCipher, false, l, this.decryptIV);
        byte[] byArray4 = this.calculateRecordMAC(keyParameter, byArray3 = this.getAdditionalData(l, s, n3 = n2 - 16), byArray, n, n3);
        if (!Arrays.constantTimeAreEqual(byArray4, byArray2 = Arrays.copyOfRange(byArray, n + n3, n + n2))) {
            throw new TlsFatalAlert(20);
        }
        byte[] byArray5 = new byte[n3];
        this.decryptCipher.processBytes(byArray, n, n3, byArray5, 0);
        return byArray5;
    }

    protected KeyParameter initRecord(StreamCipher streamCipher, boolean bl, long l, byte[] byArray) {
        byte[] byArray2 = this.calculateNonce(l, byArray);
        streamCipher.init(bl, new ParametersWithIV(null, byArray2));
        return this.generateRecordMACKey(streamCipher);
    }

    protected byte[] calculateNonce(long l, byte[] byArray) {
        byte[] byArray2 = new byte[12];
        TlsUtils.writeUint64(l, byArray2, 4);
        for (int i = 0; i < 12; ++i) {
            int n = i;
            byArray2[n] = (byte)(byArray2[n] ^ byArray[i]);
        }
        return byArray2;
    }

    protected KeyParameter generateRecordMACKey(StreamCipher streamCipher) {
        byte[] byArray = new byte[64];
        streamCipher.processBytes(byArray, 0, byArray.length, byArray, 0);
        KeyParameter keyParameter = new KeyParameter(byArray, 0, 32);
        Arrays.fill(byArray, (byte)0);
        return keyParameter;
    }

    protected byte[] calculateRecordMAC(KeyParameter keyParameter, byte[] byArray, byte[] byArray2, int n, int n2) {
        Poly1305 poly1305 = new Poly1305();
        poly1305.init(keyParameter);
        this.updateRecordMACText(poly1305, byArray, 0, byArray.length);
        this.updateRecordMACText(poly1305, byArray2, n, n2);
        this.updateRecordMACLength(poly1305, byArray.length);
        this.updateRecordMACLength(poly1305, n2);
        byte[] byArray3 = new byte[poly1305.getMacSize()];
        poly1305.doFinal(byArray3, 0);
        return byArray3;
    }

    protected void updateRecordMACLength(Mac mac, int n) {
        byte[] byArray = Pack.longToLittleEndian((long)n & 0xFFFFFFFFL);
        mac.update(byArray, 0, byArray.length);
    }

    protected void updateRecordMACText(Mac mac, byte[] byArray, int n, int n2) {
        mac.update(byArray, n, n2);
        int n3 = n2 % 16;
        if (n3 != 0) {
            mac.update(ZEROES, 0, 16 - n3);
        }
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

