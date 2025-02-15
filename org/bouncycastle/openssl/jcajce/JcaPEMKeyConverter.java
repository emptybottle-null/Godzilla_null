/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.openssl.jcajce;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.openssl.PEMException;
import org.bouncycastle.openssl.PEMKeyPair;

public class JcaPEMKeyConverter {
    private JcaJceHelper helper = new DefaultJcaJceHelper();
    private static final Map algorithms = new HashMap();

    public JcaPEMKeyConverter setProvider(Provider provider) {
        this.helper = new ProviderJcaJceHelper(provider);
        return this;
    }

    public JcaPEMKeyConverter setProvider(String string) {
        this.helper = new NamedJcaJceHelper(string);
        return this;
    }

    public KeyPair getKeyPair(PEMKeyPair pEMKeyPair) throws PEMException {
        try {
            KeyFactory keyFactory = this.getKeyFactory(pEMKeyPair.getPrivateKeyInfo().getPrivateKeyAlgorithm());
            return new KeyPair(keyFactory.generatePublic(new X509EncodedKeySpec(pEMKeyPair.getPublicKeyInfo().getEncoded())), keyFactory.generatePrivate(new PKCS8EncodedKeySpec(pEMKeyPair.getPrivateKeyInfo().getEncoded())));
        } catch (Exception exception) {
            throw new PEMException("unable to convert key pair: " + exception.getMessage(), exception);
        }
    }

    public PublicKey getPublicKey(SubjectPublicKeyInfo subjectPublicKeyInfo) throws PEMException {
        try {
            KeyFactory keyFactory = this.getKeyFactory(subjectPublicKeyInfo.getAlgorithm());
            return keyFactory.generatePublic(new X509EncodedKeySpec(subjectPublicKeyInfo.getEncoded()));
        } catch (Exception exception) {
            throw new PEMException("unable to convert key pair: " + exception.getMessage(), exception);
        }
    }

    public PrivateKey getPrivateKey(PrivateKeyInfo privateKeyInfo) throws PEMException {
        try {
            KeyFactory keyFactory = this.getKeyFactory(privateKeyInfo.getPrivateKeyAlgorithm());
            return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyInfo.getEncoded()));
        } catch (Exception exception) {
            throw new PEMException("unable to convert key pair: " + exception.getMessage(), exception);
        }
    }

    private KeyFactory getKeyFactory(AlgorithmIdentifier algorithmIdentifier) throws NoSuchAlgorithmException, NoSuchProviderException {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = algorithmIdentifier.getAlgorithm();
        String string = (String)algorithms.get(aSN1ObjectIdentifier);
        if (string == null) {
            string = aSN1ObjectIdentifier.getId();
        }
        try {
            return this.helper.createKeyFactory(string);
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            if (string.equals("ECDSA")) {
                return this.helper.createKeyFactory("EC");
            }
            throw noSuchAlgorithmException;
        }
    }

    static {
        algorithms.put(X9ObjectIdentifiers.id_ecPublicKey, "ECDSA");
        algorithms.put(PKCSObjectIdentifiers.rsaEncryption, "RSA");
        algorithms.put(X9ObjectIdentifiers.id_dsa, "DSA");
    }
}

