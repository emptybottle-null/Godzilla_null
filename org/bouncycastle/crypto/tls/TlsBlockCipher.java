/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import java.security.SecureRandom;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.tls.ProtocolVersion;
import org.bouncycastle.crypto.tls.TlsCipher;
import org.bouncycastle.crypto.tls.TlsContext;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsMac;
import org.bouncycastle.crypto.tls.TlsUtils;
import org.bouncycastle.util.Arrays;

public class TlsBlockCipher
implements TlsCipher {
    protected TlsContext context;
    protected byte[] randomData;
    protected boolean useExplicitIV;
    protected boolean encryptThenMAC;
    protected BlockCipher encryptCipher;
    protected BlockCipher decryptCipher;
    protected TlsMac writeMac;
    protected TlsMac readMac;

    public TlsMac getWriteMac() {
        return this.writeMac;
    }

    public TlsMac getReadMac() {
        return this.readMac;
    }

    public TlsBlockCipher(TlsContext tlsContext, BlockCipher blockCipher, BlockCipher blockCipher2, Digest digest, Digest digest2, int n) throws IOException {
        ParametersWithIV parametersWithIV;
        ParametersWithIV parametersWithIV2;
        byte[] byArray;
        byte[] byArray2;
        this.context = tlsContext;
        this.randomData = new byte[256];
        tlsContext.getNonceRandomGenerator().nextBytes(this.randomData);
        this.useExplicitIV = TlsUtils.isTLSv11(tlsContext);
        this.encryptThenMAC = tlsContext.getSecurityParameters().encryptThenMAC;
        int n2 = 2 * n + digest.getDigestSize() + digest2.getDigestSize();
        if (!this.useExplicitIV) {
            n2 += blockCipher.getBlockSize() + blockCipher2.getBlockSize();
        }
        byte[] byArray3 = TlsUtils.calculateKeyBlock(tlsContext, n2);
        int n3 = 0;
        TlsMac tlsMac = new TlsMac(tlsContext, digest, byArray3, n3, digest.getDigestSize());
        TlsMac tlsMac2 = new TlsMac(tlsContext, digest2, byArray3, n3 += digest.getDigestSize(), digest2.getDigestSize());
        KeyParameter keyParameter = new KeyParameter(byArray3, n3 += digest2.getDigestSize(), n);
        KeyParameter keyParameter2 = new KeyParameter(byArray3, n3 += n, n);
        n3 += n;
        if (this.useExplicitIV) {
            byArray2 = new byte[blockCipher.getBlockSize()];
            byArray = new byte[blockCipher2.getBlockSize()];
        } else {
            byArray2 = Arrays.copyOfRange(byArray3, n3, n3 + blockCipher.getBlockSize());
            byArray = Arrays.copyOfRange(byArray3, n3 += blockCipher.getBlockSize(), n3 + blockCipher2.getBlockSize());
            n3 += blockCipher2.getBlockSize();
        }
        if (n3 != n2) {
            throw new TlsFatalAlert(80);
        }
        if (tlsContext.isServer()) {
            this.writeMac = tlsMac2;
            this.readMac = tlsMac;
            this.encryptCipher = blockCipher2;
            this.decryptCipher = blockCipher;
            parametersWithIV2 = new ParametersWithIV(keyParameter2, byArray);
            parametersWithIV = new ParametersWithIV(keyParameter, byArray2);
        } else {
            this.writeMac = tlsMac;
            this.readMac = tlsMac2;
            this.encryptCipher = blockCipher;
            this.decryptCipher = blockCipher2;
            parametersWithIV2 = new ParametersWithIV(keyParameter, byArray2);
            parametersWithIV = new ParametersWithIV(keyParameter2, byArray);
        }
        this.encryptCipher.init(true, parametersWithIV2);
        this.decryptCipher.init(false, parametersWithIV);
    }

    public int getPlaintextLimit(int n) {
        int n2 = this.encryptCipher.getBlockSize();
        int n3 = this.writeMac.getSize();
        int n4 = n;
        if (this.useExplicitIV) {
            n4 -= n2;
        }
        if (this.encryptThenMAC) {
            n4 -= n3;
            n4 -= n4 % n2;
        } else {
            n4 -= n4 % n2;
            n4 -= n3;
        }
        return --n4;
    }

    public byte[] encodePlaintext(long l, short s, byte[] byArray, int n, int n2) {
        int n3;
        int n4;
        int n5 = this.encryptCipher.getBlockSize();
        int n6 = this.writeMac.getSize();
        ProtocolVersion protocolVersion = this.context.getServerVersion();
        int n7 = n2;
        if (!this.encryptThenMAC) {
            n7 += n6;
        }
        int n8 = n5 - 1 - n7 % n5;
        if (!(!this.encryptThenMAC && this.context.getSecurityParameters().truncatedHMac || protocolVersion.isDTLS() || protocolVersion.isSSL())) {
            n4 = (255 - n8) / n5;
            int n9 = this.chooseExtraPadBlocks(this.context.getSecureRandom(), n4);
            n8 += n9 * n5;
        }
        n4 = n2 + n6 + n8 + 1;
        if (this.useExplicitIV) {
            n4 += n5;
        }
        byte[] byArray2 = new byte[n4];
        int n10 = 0;
        if (this.useExplicitIV) {
            byte[] byArray3 = new byte[n5];
            this.context.getNonceRandomGenerator().nextBytes(byArray3);
            this.encryptCipher.init(true, new ParametersWithIV(null, byArray3));
            System.arraycopy(byArray3, 0, byArray2, n10, n5);
            n10 += n5;
        }
        int n11 = n10;
        System.arraycopy(byArray, n, byArray2, n10, n2);
        n10 += n2;
        if (!this.encryptThenMAC) {
            byte[] byArray4 = this.writeMac.calculateMac(l, s, byArray, n, n2);
            System.arraycopy(byArray4, 0, byArray2, n10, byArray4.length);
            n10 += byArray4.length;
        }
        for (n3 = 0; n3 <= n8; ++n3) {
            byArray2[n10++] = (byte)n8;
        }
        for (n3 = n11; n3 < n10; n3 += n5) {
            this.encryptCipher.processBlock(byArray2, n3, byArray2, n3);
        }
        if (this.encryptThenMAC) {
            byte[] byArray5 = this.writeMac.calculateMac(l, s, byArray2, 0, n10);
            System.arraycopy(byArray5, 0, byArray2, n10, byArray5.length);
            n10 += byArray5.length;
        }
        return byArray2;
    }

    public byte[] decodeCiphertext(long l, short s, byte[] byArray, int n, int n2) throws IOException {
        int n3;
        int n4;
        int n5 = this.decryptCipher.getBlockSize();
        int n6 = this.readMac.getSize();
        int n7 = n5;
        n7 = this.encryptThenMAC ? (n7 += n6) : Math.max(n7, n6 + 1);
        if (this.useExplicitIV) {
            n7 += n5;
        }
        if (n2 < n7) {
            throw new TlsFatalAlert(50);
        }
        int n8 = n2;
        if (this.encryptThenMAC) {
            n8 -= n6;
        }
        if (n8 % n5 != 0) {
            throw new TlsFatalAlert(21);
        }
        if (this.encryptThenMAC) {
            n4 = n + n2;
            byte[] byArray2 = Arrays.copyOfRange(byArray, n4 - n6, n4);
            byte[] byArray3 = this.readMac.calculateMac(l, s, byArray, n, n2 - n6);
            int n9 = n3 = !Arrays.constantTimeAreEqual(byArray3, byArray2) ? 1 : 0;
            if (n3 != 0) {
                throw new TlsFatalAlert(20);
            }
        }
        if (this.useExplicitIV) {
            this.decryptCipher.init(false, new ParametersWithIV(null, byArray, n, n5));
            n += n5;
            n8 -= n5;
        }
        for (n4 = 0; n4 < n8; n4 += n5) {
            this.decryptCipher.processBlock(byArray, n + n4, byArray, n + n4);
        }
        n4 = this.checkPaddingConstantTime(byArray, n, n8, n5, this.encryptThenMAC ? 0 : n6);
        boolean bl = n4 == 0;
        int n10 = n8 - n4;
        if (!this.encryptThenMAC) {
            n3 = n10 -= n6;
            int n11 = n + n3;
            byte[] byArray4 = Arrays.copyOfRange(byArray, n11, n11 + n6);
            byte[] byArray5 = this.readMac.calculateMacConstantTime(l, s, byArray, n, n3, n8 - n6, this.randomData);
            bl |= !Arrays.constantTimeAreEqual(byArray5, byArray4);
        }
        if (bl) {
            throw new TlsFatalAlert(20);
        }
        return Arrays.copyOfRange(byArray, n, n + n10);
    }

    protected int checkPaddingConstantTime(byte[] byArray, int n, int n2, int n3, int n4) {
        int n5 = n + n2;
        byte by = byArray[n5 - 1];
        int n6 = by & 0xFF;
        int n7 = n6 + 1;
        int n8 = 0;
        int n9 = 0;
        if (TlsUtils.isSSL(this.context) && n7 > n3 || n4 + n7 > n2) {
            n7 = 0;
        } else {
            int n10 = n5 - n7;
            do {
                n9 = (byte)(n9 | byArray[n10++] ^ by);
            } while (n10 < n5);
            n8 = n7;
            if (n9 != 0) {
                n7 = 0;
            }
        }
        byte[] byArray2 = this.randomData;
        while (n8 < 256) {
            n9 = (byte)(n9 | byArray2[n8++] ^ by);
        }
        byArray2[0] = (byte)(byArray2[0] ^ n9);
        return n7;
    }

    protected int chooseExtraPadBlocks(SecureRandom secureRandom, int n) {
        int n2 = secureRandom.nextInt();
        int n3 = this.lowestBitSet(n2);
        return Math.min(n3, n);
    }

    protected int lowestBitSet(int n) {
        if (n == 0) {
            return 32;
        }
        int n2 = 0;
        while ((n & 1) == 0) {
            ++n2;
            n >>= 1;
        }
        return n2;
    }
}

