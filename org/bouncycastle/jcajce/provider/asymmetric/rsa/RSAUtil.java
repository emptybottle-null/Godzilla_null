/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.rsa;

import java.math.BigInteger;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Fingerprint;

public class RSAUtil {
    public static final ASN1ObjectIdentifier[] rsaOids = new ASN1ObjectIdentifier[]{PKCSObjectIdentifiers.rsaEncryption, X509ObjectIdentifiers.id_ea_rsa, PKCSObjectIdentifiers.id_RSAES_OAEP, PKCSObjectIdentifiers.id_RSASSA_PSS};

    public static boolean isRsaOid(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        for (int i = 0; i != rsaOids.length; ++i) {
            if (!aSN1ObjectIdentifier.equals(rsaOids[i])) continue;
            return true;
        }
        return false;
    }

    static RSAKeyParameters generatePublicKeyParameter(RSAPublicKey rSAPublicKey) {
        return new RSAKeyParameters(false, rSAPublicKey.getModulus(), rSAPublicKey.getPublicExponent());
    }

    static RSAKeyParameters generatePrivateKeyParameter(RSAPrivateKey rSAPrivateKey) {
        if (rSAPrivateKey instanceof RSAPrivateCrtKey) {
            RSAPrivateCrtKey rSAPrivateCrtKey = (RSAPrivateCrtKey)rSAPrivateKey;
            return new RSAPrivateCrtKeyParameters(rSAPrivateCrtKey.getModulus(), rSAPrivateCrtKey.getPublicExponent(), rSAPrivateCrtKey.getPrivateExponent(), rSAPrivateCrtKey.getPrimeP(), rSAPrivateCrtKey.getPrimeQ(), rSAPrivateCrtKey.getPrimeExponentP(), rSAPrivateCrtKey.getPrimeExponentQ(), rSAPrivateCrtKey.getCrtCoefficient());
        }
        RSAPrivateKey rSAPrivateKey2 = rSAPrivateKey;
        return new RSAKeyParameters(true, rSAPrivateKey2.getModulus(), rSAPrivateKey2.getPrivateExponent());
    }

    static String generateKeyFingerprint(BigInteger bigInteger, BigInteger bigInteger2) {
        return new Fingerprint(Arrays.concatenate(bigInteger.toByteArray(), bigInteger2.toByteArray())).toString();
    }
}

