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
import org.bouncycastle.pqc.crypto.mceliece.McEliecePointchevalCipher;
import org.bouncycastle.pqc.jcajce.provider.mceliece.McElieceCCA2KeysToParams;
import org.bouncycastle.pqc.jcajce.provider.util.AsymmetricHybridCipher;

public class McEliecePointchevalCipherSpi
extends AsymmetricHybridCipher
implements PKCSObjectIdentifiers,
X509ObjectIdentifiers {
    private Digest digest;
    private McEliecePointchevalCipher cipher;
    private ByteArrayOutputStream buf = new ByteArrayOutputStream();

    protected McEliecePointchevalCipherSpi(Digest digest, McEliecePointchevalCipher mcEliecePointchevalCipher) {
        this.digest = digest;
        this.cipher = mcEliecePointchevalCipher;
        this.buf = new ByteArrayOutputStream();
    }

    public byte[] update(byte[] byArray, int n, int n2) {
        this.buf.write(byArray, n, n2);
        return new byte[0];
    }

    public byte[] doFinal(byte[] byArray, int n, int n2) throws BadPaddingException {
        this.update(byArray, n, n2);
        byte[] byArray2 = this.buf.toByteArray();
        this.buf.reset();
        if (this.opMode == 1) {
            return this.cipher.messageEncrypt(byArray2);
        }
        if (this.opMode == 2) {
            try {
                return this.cipher.messageDecrypt(byArray2);
            } catch (InvalidCipherTextException invalidCipherTextException) {
                throw new BadPaddingException(invalidCipherTextException.getMessage());
            }
        }
        return null;
    }

    protected int encryptOutputSize(int n) {
        return 0;
    }

    protected int decryptOutputSize(int n) {
        return 0;
    }

    protected void initCipherEncrypt(Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        CipherParameters cipherParameters = McElieceCCA2KeysToParams.generatePublicKeyParameter((PublicKey)key);
        cipherParameters = new ParametersWithRandom(cipherParameters, secureRandom);
        this.digest.reset();
        this.cipher.init(true, cipherParameters);
    }

    protected void initCipherDecrypt(Key key, AlgorithmParameterSpec algorithmParameterSpec) throws InvalidKeyException, InvalidAlgorithmParameterException {
        AsymmetricKeyParameter asymmetricKeyParameter = McElieceCCA2KeysToParams.generatePrivateKeyParameter((PrivateKey)key);
        this.digest.reset();
        this.cipher.init(false, asymmetricKeyParameter);
    }

    public String getName() {
        return "McEliecePointchevalCipher";
    }

    public int getKeySize(Key key) throws InvalidKeyException {
        McElieceCCA2KeyParameters mcElieceCCA2KeyParameters = key instanceof PublicKey ? (McElieceCCA2KeyParameters)McElieceCCA2KeysToParams.generatePublicKeyParameter((PublicKey)key) : (McElieceCCA2KeyParameters)McElieceCCA2KeysToParams.generatePrivateKeyParameter((PrivateKey)key);
        return this.cipher.getKeySize(mcElieceCCA2KeyParameters);
    }

    public static class McEliecePointcheval
    extends McEliecePointchevalCipherSpi {
        public McEliecePointcheval() {
            super(DigestFactory.createSHA1(), new McEliecePointchevalCipher());
        }
    }

    public static class McEliecePointcheval224
    extends McEliecePointchevalCipherSpi {
        public McEliecePointcheval224() {
            super(DigestFactory.createSHA224(), new McEliecePointchevalCipher());
        }
    }

    public static class McEliecePointcheval256
    extends McEliecePointchevalCipherSpi {
        public McEliecePointcheval256() {
            super(DigestFactory.createSHA256(), new McEliecePointchevalCipher());
        }
    }

    public static class McEliecePointcheval384
    extends McEliecePointchevalCipherSpi {
        public McEliecePointcheval384() {
            super(DigestFactory.createSHA384(), new McEliecePointchevalCipher());
        }
    }

    public static class McEliecePointcheval512
    extends McEliecePointchevalCipherSpi {
        public McEliecePointcheval512() {
            super(DigestFactory.createSHA512(), new McEliecePointchevalCipher());
        }
    }
}

