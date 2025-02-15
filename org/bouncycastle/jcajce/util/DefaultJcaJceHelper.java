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

public class DefaultJcaJceHelper
implements JcaJceHelper {
    public Cipher createCipher(String string) throws NoSuchAlgorithmException, NoSuchPaddingException {
        return Cipher.getInstance(string);
    }

    public Mac createMac(String string) throws NoSuchAlgorithmException {
        return Mac.getInstance(string);
    }

    public KeyAgreement createKeyAgreement(String string) throws NoSuchAlgorithmException {
        return KeyAgreement.getInstance(string);
    }

    public AlgorithmParameterGenerator createAlgorithmParameterGenerator(String string) throws NoSuchAlgorithmException {
        return AlgorithmParameterGenerator.getInstance(string);
    }

    public AlgorithmParameters createAlgorithmParameters(String string) throws NoSuchAlgorithmException {
        return AlgorithmParameters.getInstance(string);
    }

    public KeyGenerator createKeyGenerator(String string) throws NoSuchAlgorithmException {
        return KeyGenerator.getInstance(string);
    }

    public KeyFactory createKeyFactory(String string) throws NoSuchAlgorithmException {
        return KeyFactory.getInstance(string);
    }

    public SecretKeyFactory createSecretKeyFactory(String string) throws NoSuchAlgorithmException {
        return SecretKeyFactory.getInstance(string);
    }

    public KeyPairGenerator createKeyPairGenerator(String string) throws NoSuchAlgorithmException {
        return KeyPairGenerator.getInstance(string);
    }

    public MessageDigest createDigest(String string) throws NoSuchAlgorithmException {
        return MessageDigest.getInstance(string);
    }

    public Signature createSignature(String string) throws NoSuchAlgorithmException {
        return Signature.getInstance(string);
    }

    public CertificateFactory createCertificateFactory(String string) throws CertificateException {
        return CertificateFactory.getInstance(string);
    }

    public SecureRandom createSecureRandom(String string) throws NoSuchAlgorithmException {
        return SecureRandom.getInstance(string);
    }
}

