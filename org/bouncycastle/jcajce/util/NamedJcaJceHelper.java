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
import java.security.NoSuchProviderException;
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

public class NamedJcaJceHelper
implements JcaJceHelper {
    protected final String providerName;

    public NamedJcaJceHelper(String string) {
        this.providerName = string;
    }

    public Cipher createCipher(String string) throws NoSuchAlgorithmException, NoSuchPaddingException, NoSuchProviderException {
        return Cipher.getInstance(string, this.providerName);
    }

    public Mac createMac(String string) throws NoSuchAlgorithmException, NoSuchProviderException {
        return Mac.getInstance(string, this.providerName);
    }

    public KeyAgreement createKeyAgreement(String string) throws NoSuchAlgorithmException, NoSuchProviderException {
        return KeyAgreement.getInstance(string, this.providerName);
    }

    public AlgorithmParameterGenerator createAlgorithmParameterGenerator(String string) throws NoSuchAlgorithmException, NoSuchProviderException {
        return AlgorithmParameterGenerator.getInstance(string, this.providerName);
    }

    public AlgorithmParameters createAlgorithmParameters(String string) throws NoSuchAlgorithmException, NoSuchProviderException {
        return AlgorithmParameters.getInstance(string, this.providerName);
    }

    public KeyGenerator createKeyGenerator(String string) throws NoSuchAlgorithmException, NoSuchProviderException {
        return KeyGenerator.getInstance(string, this.providerName);
    }

    public KeyFactory createKeyFactory(String string) throws NoSuchAlgorithmException, NoSuchProviderException {
        return KeyFactory.getInstance(string, this.providerName);
    }

    public SecretKeyFactory createSecretKeyFactory(String string) throws NoSuchAlgorithmException, NoSuchProviderException {
        return SecretKeyFactory.getInstance(string, this.providerName);
    }

    public KeyPairGenerator createKeyPairGenerator(String string) throws NoSuchAlgorithmException, NoSuchProviderException {
        return KeyPairGenerator.getInstance(string, this.providerName);
    }

    public MessageDigest createDigest(String string) throws NoSuchAlgorithmException, NoSuchProviderException {
        return MessageDigest.getInstance(string, this.providerName);
    }

    public Signature createSignature(String string) throws NoSuchAlgorithmException, NoSuchProviderException {
        return Signature.getInstance(string, this.providerName);
    }

    public CertificateFactory createCertificateFactory(String string) throws CertificateException, NoSuchProviderException {
        return CertificateFactory.getInstance(string, this.providerName);
    }

    public SecureRandom createSecureRandom(String string) throws NoSuchAlgorithmException, NoSuchProviderException {
        return SecureRandom.getInstance(string, this.providerName);
    }
}

