/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.symmetric.util;

import java.lang.reflect.Constructor;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactorySpi;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.jcajce.provider.symmetric.util.PBE;

public class BaseSecretKeyFactory
extends SecretKeyFactorySpi
implements PBE {
    protected String algName;
    protected ASN1ObjectIdentifier algOid;

    protected BaseSecretKeyFactory(String string, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.algName = string;
        this.algOid = aSN1ObjectIdentifier;
    }

    protected SecretKey engineGenerateSecret(KeySpec keySpec) throws InvalidKeySpecException {
        if (keySpec instanceof SecretKeySpec) {
            return new SecretKeySpec(((SecretKeySpec)keySpec).getEncoded(), this.algName);
        }
        throw new InvalidKeySpecException("Invalid KeySpec");
    }

    protected KeySpec engineGetKeySpec(SecretKey secretKey, Class clazz) throws InvalidKeySpecException {
        if (clazz == null) {
            throw new InvalidKeySpecException("keySpec parameter is null");
        }
        if (secretKey == null) {
            throw new InvalidKeySpecException("key parameter is null");
        }
        if (SecretKeySpec.class.isAssignableFrom(clazz)) {
            return new SecretKeySpec(secretKey.getEncoded(), this.algName);
        }
        try {
            Class[] classArray = new Class[]{byte[].class};
            Constructor constructor = clazz.getConstructor(classArray);
            Object[] objectArray = new Object[]{secretKey.getEncoded()};
            return (KeySpec)constructor.newInstance(objectArray);
        } catch (Exception exception) {
            throw new InvalidKeySpecException(exception.toString());
        }
    }

    protected SecretKey engineTranslateKey(SecretKey secretKey) throws InvalidKeyException {
        if (secretKey == null) {
            throw new InvalidKeyException("key parameter is null");
        }
        if (!secretKey.getAlgorithm().equalsIgnoreCase(this.algName)) {
            throw new InvalidKeyException("Key not of type " + this.algName + ".");
        }
        return new SecretKeySpec(secretKey.getEncoded(), this.algName);
    }
}

