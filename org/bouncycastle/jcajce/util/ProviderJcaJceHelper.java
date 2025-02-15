/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.util;

import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import org.bouncycastle.jcajce.util.JcaJceHelper;

public class ProviderJcaJceHelper
implements JcaJceHelper {
    protected final Provider provider;

    public ProviderJcaJceHelper(Provider provider) {
        this.provider = provider;
    }

    public Cipher createCipher(String string) throws NoSuchAlgorithmException, NoSuchPaddingException {
        return Cipher.getInstance(string, this.provider);
    }

    public Mac createMac(String string) throws NoSuchAlgorithmException {
        return Mac.getInstance(string, this.provider);
    }

    public KeyAgreement createKeyAgreement(String string) throws NoSuchAlgorithmException {
        return KeyAgreement.getInstance(string, this.provider);
    }

    public AlgorithmParameterGenerator createAlgorithmParameterGenerator(String string) throws NoSuchAlgorithmException {
        return AlgorithmParameterGenerator.getInstance(string, this.provider);
    }

    public AlgorithmParameters createAlgorithmParameters(String string) throws NoSuchAlgorithmException {
        return AlgorithmParameters.getInstance(string, this.provider);
    }

    public KeyGenerator createKeyGenerator(String string) throws NoSuchAlgorithmException {
        return KeyGenerator.getInstance(string, this.provider);
    }

    public KeyFactory createKeyFactory(String string) throws NoSuchAlgorithmException {
        return KeyFactory.getInstance(string, this.provider);
    }

    public SecretKeyFactory createSecretKeyFactory(String string) throws NoSuchAlgorithmException {
        return SecretKeyFactory.getInstance(string, this.provider);
    }

    public KeyPairGenerator createKeyPairGenerator(String string) throws NoSuchAlgorithmException {
        return KeyPairGenerator.getInstance(string, this.provider);
    }

    public MessageDigest createDigest(String string) throws NoSuchAlgorithmException {
        return MessageDigest.getInstance(string, this.provider);
    }

    public Signature createSignature(String string) throws NoSuchAlgorithmException {
        return Signature.getInstance(string, this.provider);
    }

    public CertificateFactory createCertificateFactory(String string) throws CertificateException {
        return CertificateFactory.getInstance(string, this.provider);
    }

    public SecureRandom createSecureRandom(String string) throws NoSuchAlgorithmException {
        return SecureRandom.getInstance(string, this.provider);
    }
}

