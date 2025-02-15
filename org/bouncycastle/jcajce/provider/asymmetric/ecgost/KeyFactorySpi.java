/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.ecgost;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPrivateKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.jcajce.provider.asymmetric.ecgost.BCECGOST3410PrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ecgost.BCECGOST3410PublicKey;
import org.bouncycastle.jcajce.provider.asymmetric.util.BaseKeyFactorySpi;
import org.bouncycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.jce.spec.ECPublicKeySpec;

public class KeyFactorySpi
extends BaseKeyFactorySpi {
    protected KeySpec engineGetKeySpec(Key key, Class clazz) throws InvalidKeySpecException {
        if (clazz.isAssignableFrom(java.security.spec.ECPublicKeySpec.class) && key instanceof ECPublicKey) {
            ECPublicKey eCPublicKey = (ECPublicKey)key;
            if (eCPublicKey.getParams() != null) {
                return new java.security.spec.ECPublicKeySpec(eCPublicKey.getW(), eCPublicKey.getParams());
            }
            ECParameterSpec eCParameterSpec = BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa();
            return new java.security.spec.ECPublicKeySpec(eCPublicKey.getW(), EC5Util.convertSpec(EC5Util.convertCurve(eCParameterSpec.getCurve(), eCParameterSpec.getSeed()), eCParameterSpec));
        }
        if (clazz.isAssignableFrom(ECPrivateKeySpec.class) && key instanceof ECPrivateKey) {
            ECPrivateKey eCPrivateKey = (ECPrivateKey)key;
            if (eCPrivateKey.getParams() != null) {
                return new ECPrivateKeySpec(eCPrivateKey.getS(), eCPrivateKey.getParams());
            }
            ECParameterSpec eCParameterSpec = BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa();
            return new ECPrivateKeySpec(eCPrivateKey.getS(), EC5Util.convertSpec(EC5Util.convertCurve(eCParameterSpec.getCurve(), eCParameterSpec.getSeed()), eCParameterSpec));
        }
        if (clazz.isAssignableFrom(ECPublicKeySpec.class) && key instanceof ECPublicKey) {
            ECPublicKey eCPublicKey = (ECPublicKey)key;
            if (eCPublicKey.getParams() != null) {
                return new ECPublicKeySpec(EC5Util.convertPoint(eCPublicKey.getParams(), eCPublicKey.getW(), false), EC5Util.convertSpec(eCPublicKey.getParams(), false));
            }
            ECParameterSpec eCParameterSpec = BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa();
            return new ECPublicKeySpec(EC5Util.convertPoint(eCPublicKey.getParams(), eCPublicKey.getW(), false), eCParameterSpec);
        }
        if (clazz.isAssignableFrom(org.bouncycastle.jce.spec.ECPrivateKeySpec.class) && key instanceof ECPrivateKey) {
            ECPrivateKey eCPrivateKey = (ECPrivateKey)key;
            if (eCPrivateKey.getParams() != null) {
                return new org.bouncycastle.jce.spec.ECPrivateKeySpec(eCPrivateKey.getS(), EC5Util.convertSpec(eCPrivateKey.getParams(), false));
            }
            ECParameterSpec eCParameterSpec = BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa();
            return new org.bouncycastle.jce.spec.ECPrivateKeySpec(eCPrivateKey.getS(), eCParameterSpec);
        }
        return super.engineGetKeySpec(key, clazz);
    }

    protected Key engineTranslateKey(Key key) throws InvalidKeyException {
        throw new InvalidKeyException("key type unknown");
    }

    protected PrivateKey engineGeneratePrivate(KeySpec keySpec) throws InvalidKeySpecException {
        if (keySpec instanceof org.bouncycastle.jce.spec.ECPrivateKeySpec) {
            return new BCECGOST3410PrivateKey((org.bouncycastle.jce.spec.ECPrivateKeySpec)keySpec);
        }
        if (keySpec instanceof ECPrivateKeySpec) {
            return new BCECGOST3410PrivateKey((ECPrivateKeySpec)keySpec);
        }
        return super.engineGeneratePrivate(keySpec);
    }

    protected PublicKey engineGeneratePublic(KeySpec keySpec) throws InvalidKeySpecException {
        if (keySpec instanceof ECPublicKeySpec) {
            return new BCECGOST3410PublicKey((ECPublicKeySpec)keySpec, BouncyCastleProvider.CONFIGURATION);
        }
        if (keySpec instanceof java.security.spec.ECPublicKeySpec) {
            return new BCECGOST3410PublicKey((java.security.spec.ECPublicKeySpec)keySpec);
        }
        return super.engineGeneratePublic(keySpec);
    }

    public PrivateKey generatePrivate(PrivateKeyInfo privateKeyInfo) throws IOException {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm();
        if (aSN1ObjectIdentifier.equals(CryptoProObjectIdentifiers.gostR3410_2001)) {
            return new BCECGOST3410PrivateKey(privateKeyInfo);
        }
        if (aSN1ObjectIdentifier.equals(CryptoProObjectIdentifiers.gostR3410_2001DH)) {
            return new BCECGOST3410PrivateKey(privateKeyInfo);
        }
        if (aSN1ObjectIdentifier.equals(CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_ESDH)) {
            return new BCECGOST3410PrivateKey(privateKeyInfo);
        }
        throw new IOException("algorithm identifier " + aSN1ObjectIdentifier + " in key not recognised");
    }

    public PublicKey generatePublic(SubjectPublicKeyInfo subjectPublicKeyInfo) throws IOException {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = subjectPublicKeyInfo.getAlgorithm().getAlgorithm();
        if (aSN1ObjectIdentifier.equals(CryptoProObjectIdentifiers.gostR3410_2001)) {
            return new BCECGOST3410PublicKey(subjectPublicKeyInfo);
        }
        if (aSN1ObjectIdentifier.equals(CryptoProObjectIdentifiers.gostR3410_2001DH)) {
            return new BCECGOST3410PublicKey(subjectPublicKeyInfo);
        }
        if (aSN1ObjectIdentifier.equals(CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_ESDH)) {
            return new BCECGOST3410PublicKey(subjectPublicKeyInfo);
        }
        throw new IOException("algorithm identifier " + aSN1ObjectIdentifier + " in key not recognised");
    }
}

