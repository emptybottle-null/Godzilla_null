/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.jcajce.provider.mceliece;

import java.io.ByteArrayOutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.util.DigestFactory;
import org.bouncycastle.pqc.crypto.mceliece.McElieceCCA2KeyParameters;
import org.bouncycastle.pqc.crypto.mceliece.McElieceKobaraImaiCipher;
import org.bouncycastle.pqc.jcajce.provider.mceliece.McElieceCCA2KeysToParams;
import org.bouncycastle.pqc.jcajce.provider.util.AsymmetricHybridCipher;

public class McElieceKobaraImaiCipherSpi
extends AsymmetricHybridCipher
implements PKCSObjectIdentifiers,
X509ObjectIdentifiers {
    private Digest digest;
    private McElieceKobaraImaiCipher cipher;
    private ByteArrayOutputStream buf = new ByteArrayOutputStream();

    public McElieceKobaraImaiCipherSpi() {
        this.buf = new ByteArrayOutputStream();
    }

    protected McElieceKobaraImaiCipherSpi(Digest digest, McElieceKobaraImaiCipher mcElieceKobaraImaiCipher) {
        this.digest = digest;
        this.cipher = mcElieceKobaraImaiCipher;
        this.buf = new ByteArrayOutputStream();
    }

    public byte[] update(byte[] byArray, int n, int n2) {
        this.buf.write(byArray, n, n2);
        return new byte[0];
    }

    public byte[] doFinal(byte[] byArray, int n, int n2) throws BadPaddingException {
        this.update(byArray, n, n2);
        if (this.opMode == 1) {
            return this.cipher.messageEncrypt(this.pad());
        }
        if (this.opMode == 2) {
            try {
                byte[] byArray2 = this.buf.toByteArray();
                this.buf.reset();
                return this.unpad(this.cipher.messageDecrypt(byArray2));
            } catch (InvalidCipherTextException invalidCipherTextException) {
                throw new BadPaddingException(invalidCipherTextException.getMessage());
            }
        }
        throw new IllegalStateException("unknown mode in doFinal");
    }

    protected int encryptOutputSize(int n) {
        return 0;
    }

    protected int decryptOutputSize(int n) {
        return 0;
    }

    protected void initCipherEncrypt(Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        this.buf.reset();
        CipherParameters cipherParameters = McElieceCCA2KeysToParams.generatePublicKeyParameter((PublicKey)key);
        cipherParameters = new ParametersWithRandom(cipherParameters, secureRandom);
        this.digest.reset();
        this.cipher.init(true, cipherParameters);
    }

    protected void initCipherDecrypt(Key key, AlgorithmParameterSpec algorithmParameterSpec) throws InvalidKeyException, InvalidAlgorithmParameterException {
        this.buf.reset();
        AsymmetricKeyParameter asymmetricKeyParameter = McElieceCCA2KeysToParams.generatePrivateKeyParameter((PrivateKey)key);
        this.digest.reset();
        this.cipher.init(false, asymmetricKeyParameter);
    }

    public String getName() {
        return "McElieceKobaraImaiCipher";
    }

    public int getKeySize(Key key) throws InvalidKeyException {
        if (key instanceof PublicKey) {
            McElieceCCA2KeyParameters mcElieceCCA2KeyParameters = (McElieceCCA2KeyParameters)McElieceCCA2KeysToParams.generatePublicKeyParameter((PublicKey)key);
            return this.cipher.getKeySize(mcElieceCCA2KeyParameters);
        }
        if (key instanceof PrivateKey) {
            McElieceCCA2KeyParameters mcElieceCCA2KeyParameters = (McElieceCCA2KeyParameters)McElieceCCA2KeysToParams.generatePrivateKeyParameter((PrivateKey)key);
            return this.cipher.getKeySize(mcElieceCCA2KeyParameters);
        }
        throw new InvalidKeyException();
    }

    private byte[] pad() {
        this.buf.write(1);
        byte[] byArray = this.buf.toByteArray();
        this.buf.reset();
        return byArray;
    }

    private byte[] unpad(byte[] byArray) throws BadPaddingException {
        int n;
        for (n = byArray.length - 1; n >= 0 && byArray[n] == 0; --n) {
        }
        if (byArray[n] != 1) {
            throw new BadPaddingException("invalid ciphertext");
        }
        byte[] byArray2 = new byte[n];
        System.arraycopy(byArray, 0, byArray2, 0, n);
        return byArray2;
    }

    public static class McElieceKobaraImai
    extends McElieceKobaraImaiCipherSpi {
        public McElieceKobaraImai() {
            super(DigestFactory.createSHA1(), new McElieceKobaraImaiCipher());
        }
    }

    public static class McElieceKobaraImai224
    extends McElieceKobaraImaiCipherSpi {
        public McElieceKobaraImai224() {
            super(DigestFactory.createSHA224(), new McElieceKobaraImaiCipher());
        }
    }

    public static class McElieceKobaraImai256
    extends McElieceKobaraImaiCipherSpi {
        public McElieceKobaraImai256() {
            super(DigestFactory.createSHA256(), new McElieceKobaraImaiCipher());
        }
    }

    public static class McElieceKobaraImai384
    extends McElieceKobaraImaiCipherSpi {
        public McElieceKobaraImai384() {
            super(DigestFactory.createSHA384(), new McElieceKobaraImaiCipher());
        }
    }

    public static class McElieceKobaraImai512
    extends McElieceKobaraImaiCipherSpi {
        public McElieceKobaraImai512() {
            super(DigestFactory.createSHA512(), new McElieceKobaraImaiCipher());
        }
    }
}

