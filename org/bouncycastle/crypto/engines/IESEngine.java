/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import org.bouncycastle.crypto.BasicAgreement;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.crypto.EphemeralKeyPair;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.KeyParser;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.generators.EphemeralKeyPairGenerator;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.IESParameters;
import org.bouncycastle.crypto.params.IESWithCipherParameters;
import org.bouncycastle.crypto.params.KDFParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.Pack;

public class IESEngine {
    BasicAgreement agree;
    DerivationFunction kdf;
    Mac mac;
    BufferedBlockCipher cipher;
    byte[] macBuf;
    boolean forEncryption;
    CipherParameters privParam;
    CipherParameters pubParam;
    IESParameters param;
    byte[] V;
    private EphemeralKeyPairGenerator keyPairGenerator;
    private KeyParser keyParser;
    private byte[] IV;

    public IESEngine(BasicAgreement basicAgreement, DerivationFunction derivationFunction, Mac mac) {
        this.agree = basicAgreement;
        this.kdf = derivationFunction;
        this.mac = mac;
        this.macBuf = new byte[mac.getMacSize()];
        this.cipher = null;
    }

    public IESEngine(BasicAgreement basicAgreement, DerivationFunction derivationFunction, Mac mac, BufferedBlockCipher bufferedBlockCipher) {
        this.agree = basicAgreement;
        this.kdf = derivationFunction;
        this.mac = mac;
        this.macBuf = new byte[mac.getMacSize()];
        this.cipher = bufferedBlockCipher;
    }

    public void init(boolean bl, CipherParameters cipherParameters, CipherParameters cipherParameters2, CipherParameters cipherParameters3) {
        this.forEncryption = bl;
        this.privParam = cipherParameters;
        this.pubParam = cipherParameters2;
        this.V = new byte[0];
        this.extractParams(cipherParameters3);
    }

    public void init(AsymmetricKeyParameter asymmetricKeyParameter, CipherParameters cipherParameters, EphemeralKeyPairGenerator ephemeralKeyPairGenerator) {
        this.forEncryption = true;
        this.pubParam = asymmetricKeyParameter;
        this.keyPairGenerator = ephemeralKeyPairGenerator;
        this.extractParams(cipherParameters);
    }

    public void init(AsymmetricKeyParameter asymmetricKeyParameter, CipherParameters cipherParameters, KeyParser keyParser) {
        this.forEncryption = false;
        this.privParam = asymmetricKeyParameter;
        this.keyParser = keyParser;
        this.extractParams(cipherParameters);
    }

    private void extractParams(CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithIV) {
            this.IV = ((ParametersWithIV)cipherParameters).getIV();
            this.param = (IESParameters)((ParametersWithIV)cipherParameters).getParameters();
        } else {
            this.IV = null;
            this.param = (IESParameters)cipherParameters;
        }
    }

    public BufferedBlockCipher getCipher() {
        return this.cipher;
    }

    public Mac getMac() {
        return this.mac;
    }

    private byte[] encryptBlock(byte[] byArray, int n, int n2) throws InvalidCipherTextException {
        int n3;
        byte[] byArray2 = null;
        byte[] byArray3 = null;
        byte[] byArray4 = null;
        byte[] byArray5 = null;
        if (this.cipher == null) {
            byArray4 = new byte[n2];
            byArray5 = new byte[this.param.getMacKeySize() / 8];
            byArray3 = new byte[byArray4.length + byArray5.length];
            this.kdf.generateBytes(byArray3, 0, byArray3.length);
            if (this.V.length != 0) {
                System.arraycopy(byArray3, 0, byArray5, 0, byArray5.length);
                System.arraycopy(byArray3, byArray5.length, byArray4, 0, byArray4.length);
            } else {
                System.arraycopy(byArray3, 0, byArray4, 0, byArray4.length);
                System.arraycopy(byArray3, n2, byArray5, 0, byArray5.length);
            }
            byArray2 = new byte[n2];
            for (int i = 0; i != n2; ++i) {
                byArray2[i] = (byte)(byArray[n + i] ^ byArray4[i]);
            }
            n3 = n2;
        } else {
            byArray4 = new byte[((IESWithCipherParameters)this.param).getCipherKeySize() / 8];
            byArray5 = new byte[this.param.getMacKeySize() / 8];
            byArray3 = new byte[byArray4.length + byArray5.length];
            this.kdf.generateBytes(byArray3, 0, byArray3.length);
            System.arraycopy(byArray3, 0, byArray4, 0, byArray4.length);
            System.arraycopy(byArray3, byArray4.length, byArray5, 0, byArray5.length);
            if (this.IV != null) {
                this.cipher.init(true, new ParametersWithIV(new KeyParameter(byArray4), this.IV));
            } else {
                this.cipher.init(true, new KeyParameter(byArray4));
            }
            byArray2 = new byte[this.cipher.getOutputSize(n2)];
            n3 = this.cipher.processBytes(byArray, n, n2, byArray2, 0);
            n3 += this.cipher.doFinal(byArray2, n3);
        }
        byte[] byArray6 = this.param.getEncodingV();
        byte[] byArray7 = null;
        if (this.V.length != 0) {
            byArray7 = this.getLengthTag(byArray6);
        }
        byte[] byArray8 = new byte[this.mac.getMacSize()];
        this.mac.init(new KeyParameter(byArray5));
        this.mac.update(byArray2, 0, byArray2.length);
        if (byArray6 != null) {
            this.mac.update(byArray6, 0, byArray6.length);
        }
        if (this.V.length != 0) {
            this.mac.update(byArray7, 0, byArray7.length);
        }
        this.mac.doFinal(byArray8, 0);
        byte[] byArray9 = new byte[this.V.length + n3 + byArray8.length];
        System.arraycopy(this.V, 0, byArray9, 0, this.V.length);
        System.arraycopy(byArray2, 0, byArray9, this.V.length, n3);
        System.arraycopy(byArray8, 0, byArray9, this.V.length + n3, byArray8.length);
        return byArray9;
    }

    private byte[] decryptBlock(byte[] byArray, int n, int n2) throws InvalidCipherTextException {
        byte[] byArray2;
        byte[] byArray3;
        int n3 = 0;
        if (n2 < this.V.length + this.mac.getMacSize()) {
            throw new InvalidCipherTextException("Length of input must be greater than the MAC and V combined");
        }
        if (this.cipher == null) {
            byte[] byArray4 = new byte[n2 - this.V.length - this.mac.getMacSize()];
            byArray3 = new byte[this.param.getMacKeySize() / 8];
            byte[] byArray5 = new byte[byArray4.length + byArray3.length];
            this.kdf.generateBytes(byArray5, 0, byArray5.length);
            if (this.V.length != 0) {
                System.arraycopy(byArray5, 0, byArray3, 0, byArray3.length);
                System.arraycopy(byArray5, byArray3.length, byArray4, 0, byArray4.length);
            } else {
                System.arraycopy(byArray5, 0, byArray4, 0, byArray4.length);
                System.arraycopy(byArray5, byArray4.length, byArray3, 0, byArray3.length);
            }
            byArray2 = new byte[byArray4.length];
            for (int i = 0; i != byArray4.length; ++i) {
                byArray2[i] = (byte)(byArray[n + this.V.length + i] ^ byArray4[i]);
            }
        } else {
            byte[] byArray6 = new byte[((IESWithCipherParameters)this.param).getCipherKeySize() / 8];
            byArray3 = new byte[this.param.getMacKeySize() / 8];
            byte[] byArray7 = new byte[byArray6.length + byArray3.length];
            this.kdf.generateBytes(byArray7, 0, byArray7.length);
            System.arraycopy(byArray7, 0, byArray6, 0, byArray6.length);
            System.arraycopy(byArray7, byArray6.length, byArray3, 0, byArray3.length);
            CipherParameters cipherParameters = new KeyParameter(byArray6);
            if (this.IV != null) {
                cipherParameters = new ParametersWithIV(cipherParameters, this.IV);
            }
            this.cipher.init(false, cipherParameters);
            byArray2 = new byte[this.cipher.getOutputSize(n2 - this.V.length - this.mac.getMacSize())];
            n3 = this.cipher.processBytes(byArray, n + this.V.length, n2 - this.V.length - this.mac.getMacSize(), byArray2, 0);
        }
        byte[] byArray8 = this.param.getEncodingV();
        byte[] byArray9 = null;
        if (this.V.length != 0) {
            byArray9 = this.getLengthTag(byArray8);
        }
        int n4 = n + n2;
        byte[] byArray10 = Arrays.copyOfRange(byArray, n4 - this.mac.getMacSize(), n4);
        byte[] byArray11 = new byte[byArray10.length];
        this.mac.init(new KeyParameter(byArray3));
        this.mac.update(byArray, n + this.V.length, n2 - this.V.length - byArray11.length);
        if (byArray8 != null) {
            this.mac.update(byArray8, 0, byArray8.length);
        }
        if (this.V.length != 0) {
            this.mac.update(byArray9, 0, byArray9.length);
        }
        this.mac.doFinal(byArray11, 0);
        if (!Arrays.constantTimeAreEqual(byArray10, byArray11)) {
            throw new InvalidCipherTextException("invalid MAC");
        }
        if (this.cipher == null) {
            return byArray2;
        }
        n3 += this.cipher.doFinal(byArray2, n3);
        return Arrays.copyOfRange(byArray2, 0, n3);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public byte[] processBlock(byte[] byArray, int n, int n2) throws InvalidCipherTextException {
        Object object;
        Object object2;
        if (this.forEncryption) {
            if (this.keyPairGenerator != null) {
                object2 = this.keyPairGenerator.generate();
                this.privParam = ((EphemeralKeyPair)object2).getKeyPair().getPrivate();
                this.V = ((EphemeralKeyPair)object2).getEncodedPublicKey();
            }
        } else if (this.keyParser != null) {
            object2 = new ByteArrayInputStream(byArray, n, n2);
            try {
                this.pubParam = this.keyParser.readKey((InputStream)object2);
            } catch (IOException iOException) {
                throw new InvalidCipherTextException("unable to recover ephemeral public key: " + iOException.getMessage(), iOException);
            } catch (IllegalArgumentException illegalArgumentException) {
                throw new InvalidCipherTextException("unable to recover ephemeral public key: " + illegalArgumentException.getMessage(), illegalArgumentException);
            }
            int n3 = n2 - ((ByteArrayInputStream)object2).available();
            this.V = Arrays.copyOfRange(byArray, n, n + n3);
        }
        this.agree.init(this.privParam);
        object2 = this.agree.calculateAgreement(this.pubParam);
        byte[] byArray2 = BigIntegers.asUnsignedByteArray(this.agree.getFieldSize(), (BigInteger)object2);
        if (this.V.length != 0) {
            object = Arrays.concatenate(this.V, byArray2);
            Arrays.fill(byArray2, (byte)0);
            byArray2 = object;
        }
        try {
            object = new KDFParameters(byArray2, this.param.getDerivationV());
            this.kdf.init((DerivationParameters)object);
            byte[] byArray3 = this.forEncryption ? this.encryptBlock(byArray, n, n2) : this.decryptBlock(byArray, n, n2);
            return byArray3;
        } finally {
            Arrays.fill(byArray2, (byte)0);
        }
    }

    protected byte[] getLengthTag(byte[] byArray) {
        byte[] byArray2 = new byte[8];
        if (byArray != null) {
            Pack.longToBigEndian((long)byArray.length * 8L, byArray2, 0);
        }
        return byArray2;
    }
}

