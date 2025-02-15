/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.jcajce.provider.rainbow;

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
import org.bouncycastle.pqc.asn1.RainbowPrivateKey;
import org.bouncycastle.pqc.asn1.RainbowPublicKey;
import org.bouncycastle.pqc.jcajce.provider.rainbow.BCRainbowPrivateKey;
import org.bouncycastle.pqc.jcajce.provider.rainbow.BCRainbowPublicKey;
import org.bouncycastle.pqc.jcajce.spec.RainbowPrivateKeySpec;
import org.bouncycastle.pqc.jcajce.spec.RainbowPublicKeySpec;

public class RainbowKeyFactorySpi
extends KeyFactorySpi
implements AsymmetricKeyInfoConverter {
    public PrivateKey engineGeneratePrivate(KeySpec keySpec) throws InvalidKeySpecException {
        if (keySpec instanceof RainbowPrivateKeySpec) {
            return new BCRainbowPrivateKey((RainbowPrivateKeySpec)keySpec);
        }
        if (keySpec instanceof PKCS8EncodedKeySpec) {
            byte[] byArray = ((PKCS8EncodedKeySpec)keySpec).getEncoded();
            try {
                return this.generatePrivate(PrivateKeyInfo.getInstance(ASN1Primitive.fromByteArray(byArray)));
            } catch (Exception exception) {
                throw new InvalidKeySpecException(exception.toString());
            }
        }
        throw new InvalidKeySpecException("Unsupported key specification: " + keySpec.getClass() + ".");
    }

    public PublicKey engineGeneratePublic(KeySpec keySpec) throws InvalidKeySpecException {
        if (keySpec instanceof RainbowPublicKeySpec) {
            return new BCRainbowPublicKey((RainbowPublicKeySpec)keySpec);
        }
        if (keySpec instanceof X509EncodedKeySpec) {
            byte[] byArray = ((X509EncodedKeySpec)keySpec).getEncoded();
            try {
                return this.generatePublic(SubjectPublicKeyInfo.getInstance(byArray));
            } catch (Exception exception) {
                throw new InvalidKeySpecException(exception.toString());
            }
        }
        throw new InvalidKeySpecException("Unknown key specification: " + keySpec + ".");
    }

    public final KeySpec engineGetKeySpec(Key key, Class clazz) throws InvalidKeySpecException {
        if (key instanceof BCRainbowPrivateKey) {
            if (PKCS8EncodedKeySpec.class.isAssignableFrom(clazz)) {
                return new PKCS8EncodedKeySpec(key.getEncoded());
            }
            if (RainbowPrivateKeySpec.class.isAssignableFrom(clazz)) {
                BCRainbowPrivateKey bCRainbowPrivateKey = (BCRainbowPrivateKey)key;
                return new RainbowPrivateKeySpec(bCRainbowPrivateKey.getInvA1(), bCRainbowPrivateKey.getB1(), bCRainbowPrivateKey.getInvA2(), bCRainbowPrivateKey.getB2(), bCRainbowPrivateKey.getVi(), bCRainbowPrivateKey.getLayers());
            }
        } else if (key instanceof BCRainbowPublicKey) {
            if (X509EncodedKeySpec.class.isAssignableFrom(clazz)) {
                return new X509EncodedKeySpec(key.getEncoded());
            }
            if (RainbowPublicKeySpec.class.isAssignableFrom(clazz)) {
                BCRainbowPublicKey bCRainbowPublicKey = (BCRainbowPublicKey)key;
                return new RainbowPublicKeySpec(bCRainbowPublicKey.getDocLength(), bCRainbowPublicKey.getCoeffQuadratic(), bCRainbowPublicKey.getCoeffSingular(), bCRainbowPublicKey.getCoeffScalar());
            }
        } else {
            throw new InvalidKeySpecException("Unsupported key type: " + key.getClass() + ".");
        }
        throw new InvalidKeySpecException("Unknown key specification: " + clazz + ".");
    }

    public final Key engineTranslateKey(Key key) throws InvalidKeyException {
        if (key instanceof BCRainbowPrivateKey || key instanceof BCRainbowPublicKey) {
            return key;
        }
        throw new InvalidKeyException("Unsupported key type");
    }

    public PrivateKey generatePrivate(PrivateKeyInfo privateKeyInfo) throws IOException {
        RainbowPrivateKey rainbowPrivateKey = RainbowPrivateKey.getInstance(privateKeyInfo.parsePrivateKey());
        return new BCRainbowPrivateKey(rainbowPrivateKey.getInvA1(), rainbowPrivateKey.getB1(), rainbowPrivateKey.getInvA2(), rainbowPrivateKey.getB2(), rainbowPrivateKey.getVi(), rainbowPrivateKey.getLayers());
    }

    public PublicKey generatePublic(SubjectPublicKeyInfo subjectPublicKeyInfo) throws IOException {
        RainbowPublicKey rainbowPublicKey = RainbowPublicKey.getInstance(subjectPublicKeyInfo.parsePublicKey());
        return new BCRainbowPublicKey(rainbowPublicKey.getDocLength(), rainbowPublicKey.getCoeffQuadratic(), rainbowPublicKey.getCoeffSingular(), rainbowPublicKey.getCoeffScalar());
    }
}

