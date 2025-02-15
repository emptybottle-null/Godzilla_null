/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.x509;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactorySpi;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class KeyFactory
extends KeyFactorySpi {
    protected PrivateKey engineGeneratePrivate(KeySpec keySpec) throws InvalidKeySpecException {
        if (keySpec instanceof PKCS8EncodedKeySpec) {
            try {
                PrivateKeyInfo privateKeyInfo = PrivateKeyInfo.getInstance(((PKCS8EncodedKeySpec)keySpec).getEncoded());
                PrivateKey privateKey = BouncyCastleProvider.getPrivateKey(privateKeyInfo);
                if (privateKey != null) {
                    return privateKey;
                }
                throw new InvalidKeySpecException("no factory found for OID: " + privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm());
            } catch (Exception exception) {
                throw new InvalidKeySpecException(exception.toString());
            }
        }
        throw new InvalidKeySpecException("Unknown KeySpec type: " + keySpec.getClass().getName());
    }

    protected PublicKey engineGeneratePublic(KeySpec keySpec) throws InvalidKeySpecException {
        if (keySpec instanceof X509EncodedKeySpec) {
            try {
                SubjectPublicKeyInfo subjectPublicKeyInfo = SubjectPublicKeyInfo.getInstance(((X509EncodedKeySpec)keySpec).getEncoded());
                PublicKey publicKey = BouncyCastleProvider.getPublicKey(subjectPublicKeyInfo);
                if (publicKey != null) {
                    return publicKey;
                }
                throw new InvalidKeySpecException("no factory found for OID: " + subjectPublicKeyInfo.getAlgorithm().getAlgorithm());
            } catch (Exception exception) {
                throw new InvalidKeySpecException(exception.toString());
            }
        }
        throw new InvalidKeySpecException("Unknown KeySpec type: " + keySpec.getClass().getName());
    }

    protected KeySpec engineGetKeySpec(Key key, Class clazz) throws InvalidKeySpecException {
        if (clazz.isAssignableFrom(PKCS8EncodedKeySpec.class) && key.getFormat().equals("PKCS#8")) {
            return new PKCS8EncodedKeySpec(key.getEncoded());
        }
        if (clazz.isAssignableFrom(X509EncodedKeySpec.class) && key.getFormat().equals("X.509")) {
            return new X509EncodedKeySpec(key.getEncoded());
        }
        throw new InvalidKeySpecException("not implemented yet " + key + " " + clazz);
    }

    protected Key engineTranslateKey(Key key) throws InvalidKeyException {
        throw new InvalidKeyException("not implemented yet " + key);
    }
}

