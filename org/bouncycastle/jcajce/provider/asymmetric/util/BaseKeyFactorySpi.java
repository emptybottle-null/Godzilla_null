/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.util;

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
import org.bouncycastle.jcajce.provider.util.AsymmetricKeyInfoConverter;

public abstract class BaseKeyFactorySpi
extends KeyFactorySpi
implements AsymmetricKeyInfoConverter {
    protected PrivateKey engineGeneratePrivate(KeySpec keySpec) throws InvalidKeySpecException {
        if (keySpec instanceof PKCS8EncodedKeySpec) {
            try {
                return this.generatePrivate(PrivateKeyInfo.getInstance(((PKCS8EncodedKeySpec)keySpec).getEncoded()));
            } catch (Exception exception) {
                throw new InvalidKeySpecException("encoded key spec not recognized: " + exception.getMessage());
            }
        }
        throw new InvalidKeySpecException("key spec not recognized");
    }

    protected PublicKey engineGeneratePublic(KeySpec keySpec) throws InvalidKeySpecException {
        if (keySpec instanceof X509EncodedKeySpec) {
            try {
                return this.generatePublic(SubjectPublicKeyInfo.getInstance(((X509EncodedKeySpec)keySpec).getEncoded()));
            } catch (Exception exception) {
                throw new InvalidKeySpecException("encoded key spec not recognized: " + exception.getMessage());
            }
        }
        throw new InvalidKeySpecException("key spec not recognized");
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
}

