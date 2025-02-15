/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.dsa;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.DSAParams;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.DSAParameters;
import org.bouncycastle.crypto.params.DSAPrivateKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.dsa.BCDSAPublicKey;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Fingerprint;

public class DSAUtil {
    public static final ASN1ObjectIdentifier[] dsaOids = new ASN1ObjectIdentifier[]{X9ObjectIdentifiers.id_dsa, OIWObjectIdentifiers.dsaWithSHA1};

    public static boolean isDsaOid(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        for (int i = 0; i != dsaOids.length; ++i) {
            if (!aSN1ObjectIdentifier.equals(dsaOids[i])) continue;
            return true;
        }
        return false;
    }

    static DSAParameters toDSAParameters(DSAParams dSAParams) {
        if (dSAParams != null) {
            return new DSAParameters(dSAParams.getP(), dSAParams.getQ(), dSAParams.getG());
        }
        return null;
    }

    public static AsymmetricKeyParameter generatePublicKeyParameter(PublicKey publicKey) throws InvalidKeyException {
        if (publicKey instanceof BCDSAPublicKey) {
            return ((BCDSAPublicKey)publicKey).engineGetKeyParameters();
        }
        if (publicKey instanceof DSAPublicKey) {
            return new BCDSAPublicKey((DSAPublicKey)publicKey).engineGetKeyParameters();
        }
        try {
            byte[] byArray = publicKey.getEncoded();
            BCDSAPublicKey bCDSAPublicKey = new BCDSAPublicKey(SubjectPublicKeyInfo.getInstance(byArray));
            return bCDSAPublicKey.engineGetKeyParameters();
        } catch (Exception exception) {
            throw new InvalidKeyException("can't identify DSA public key: " + publicKey.getClass().getName());
        }
    }

    public static AsymmetricKeyParameter generatePrivateKeyParameter(PrivateKey privateKey) throws InvalidKeyException {
        if (privateKey instanceof DSAPrivateKey) {
            DSAPrivateKey dSAPrivateKey = (DSAPrivateKey)privateKey;
            return new DSAPrivateKeyParameters(dSAPrivateKey.getX(), new DSAParameters(dSAPrivateKey.getParams().getP(), dSAPrivateKey.getParams().getQ(), dSAPrivateKey.getParams().getG()));
        }
        throw new InvalidKeyException("can't identify DSA private key.");
    }

    static String generateKeyFingerprint(BigInteger bigInteger, DSAParams dSAParams) {
        return new Fingerprint(Arrays.concatenate(bigInteger.toByteArray(), dSAParams.getP().toByteArray(), dSAParams.getQ().toByteArray(), dSAParams.getG().toByteArray())).toString();
    }
}

