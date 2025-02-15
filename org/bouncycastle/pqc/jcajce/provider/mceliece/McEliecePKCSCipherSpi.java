/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.jcajce.provider.mceliece;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.pqc.crypto.mceliece.McElieceCipher;
import org.bouncycastle.pqc.crypto.mceliece.McElieceKeyParameters;
import org.bouncycastle.pqc.jcajce.provider.mceliece.McElieceKeysToParams;
import org.bouncycastle.pqc.jcajce.provider.util.AsymmetricBlockCipher;

public class McEliecePKCSCipherSpi
extends AsymmetricBlockCipher
implements PKCSObjectIdentifiers,
X509ObjectIdentifiers {
    private McElieceCipher cipher;

    public McEliecePKCSCipherSpi(McElieceCipher mcElieceCipher) {
        this.cipher = mcElieceCipher;
    }

    protected void initCipherEncrypt(Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        CipherParameters cipherParameters = McElieceKeysToParams.generatePublicKeyParameter((PublicKey)key);
        cipherParameters = new ParametersWithRandom(cipherParameters, secureRandom);
        this.cipher.init(true, cipherParameters);
        this.maxPlainTextSize = this.cipher.maxPlainTextSize;
        this.cipherTextSize = this.cipher.cipherTextSize;
    }

    protected void initCipherDecrypt(Key key, AlgorithmParameterSpec algorithmParameterSpec) throws InvalidKeyException, InvalidAlgorithmParameterException {
        AsymmetricKeyParameter asymmetricKeyParameter = McElieceKeysToParams.generatePrivateKeyParameter((PrivateKey)key);
        this.cipher.init(false, asymmetricKeyParameter);
        this.maxPlainTextSize = this.cipher.maxPlainTextSize;
        this.cipherTextSize = this.cipher.cipherTextSize;
    }

    protected byte[] messageEncrypt(byte[] byArray) throws IllegalBlockSizeException, BadPaddingException {
        byte[] byArray2 = null;
        try {
            byArray2 = this.cipher.messageEncrypt(byArray);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return byArray2;
    }

    protected byte[] messageDecrypt(byte[] byArray) throws IllegalBlockSizeException, BadPaddingException {
        byte[] byArray2 = null;
        try {
            byArray2 = this.cipher.messageDecrypt(byArray);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return byArray2;
    }

    public String getName() {
        return "McEliecePKCS";
    }

    public int getKeySize(Key key) throws InvalidKeyException {
        McElieceKeyParameters mcElieceKeyParameters = key instanceof PublicKey ? (McElieceKeyParameters)McElieceKeysToParams.generatePublicKeyParameter((PublicKey)key) : (McElieceKeyParameters)McElieceKeysToParams.generatePrivateKeyParameter((PrivateKey)key);
        return this.cipher.getKeySize(mcElieceKeyParameters);
    }

    public static class McEliecePKCS
    extends McEliecePKCSCipherSpi {
        public McEliecePKCS() {
            super(new McElieceCipher());
        }
    }
}

