/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.jcajce.provider.xmss;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactorySpi;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.jcajce.provider.util.AsymmetricKeyInfoConverter;
import org.bouncycastle.pqc.jcajce.provider.xmss.BCXMSSPrivateKey;
import org.bouncycastle.pqc.jcajce.provider.xmss.BCXMSSPublicKey;

public class XMSSKeyFactorySpi
extends KeyFactorySpi
implements AsymmetricKeyInfoConverter {
    public PrivateKey engineGeneratePrivate(KeySpec keySpec) throws InvalidKeySpecException {
        if (keySpec instanceof PKCS8EncodedKeySpec) {
            byte[] byArray = ((PKCS8EncodedKeySpec)keySpec).getEncoded();
            try {
                return this.generatePrivate(PrivateKeyInfo.getInstance(ASN1Primitive.fromByteArray(byArray)));
            } catch (Exception exception) {
                throw new InvalidKeySpecException(exception.toString());
            }
        }
        throw new InvalidKeySpecException("unsupported key specification: " + keySpec.getClass() + ".");
    }

    public PublicKey engineGeneratePublic(KeySpec keySpec) throws InvalidKeySpecException {
        if (keySpec instanceof X509EncodedKeySpec) {
            byte[] byArray = ((X509EncodedKeySpec)keySpec).getEncoded();
            try {
                return this.generatePublic(SubjectPublicKeyInfo.getInstance(byArray));
            } catch (Exception exception) {
                throw new InvalidKeySpecException(exception.toString());
            }
        }
        throw new InvalidKeySpecException("unknown key specification: " + keySpec + ".");
    }

    public final KeySpec engineGetKeySpec(Key key, Class clazz) throws InvalidKeySpecException {
        if (key instanceof BCXMSSPrivateKey) {
            if (PKCS8EncodedKeySpec.class.isAssignableFrom(clazz)) {
                return new PKCS8EncodedKeySpec(key.getEncoded());
            }
        } else if (key instanceof BCXMSSPublicKey) {
            if (X509EncodedKeySpec.class.isAssignableFrom(clazz)) {
                return new X509EncodedKeySpec(key.getEncoded());
            }
        } else {
            throw new InvalidKeySpecException("unsupported key type: " + key.getClass() + ".");
        }
        throw new InvalidKeySpecException("unknown key specification: " + clazz + ".");
    }

    public final Key engineTranslateKey(Key key) throws InvalidKeyException {
        if (key instanceof BCXMSSPrivateKey || key instanceof BCXMSSPublicKey) {
            return key;
        }
        throw new InvalidKeyException("unsupported key type");
    }

    public PrivateKey generatePrivate(PrivateKeyInfo privateKeyInfo) throws IOException {
        return new BCXMSSPrivateKey(privateKeyInfo);
    }

    public PublicKey generatePublic(SubjectPublicKeyInfo subjectPublicKeyInfo) throws IOException {
        return new BCXMSSPublicKey(subjectPublicKeyInfo);
    }
}

