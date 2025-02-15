/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.x509;

import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.PSSParameterSpec;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Null;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.RSASSAPSSparams;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.jcajce.util.MessageDigestUtils;

class X509SignatureUtil {
    private static final ASN1Null derNull = DERNull.INSTANCE;

    X509SignatureUtil() {
    }

    static void setSignatureParameters(Signature signature, ASN1Encodable aSN1Encodable) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        if (aSN1Encodable != null && !derNull.equals(aSN1Encodable)) {
            AlgorithmParameters algorithmParameters = AlgorithmParameters.getInstance(signature.getAlgorithm(), signature.getProvider());
            try {
                algorithmParameters.init(aSN1Encodable.toASN1Primitive().getEncoded());
            } catch (IOException iOException) {
                throw new SignatureException("IOException decoding parameters: " + iOException.getMessage());
            }
            if (signature.getAlgorithm().endsWith("MGF1")) {
                try {
                    signature.setParameter(algorithmParameters.getParameterSpec(PSSParameterSpec.class));
                } catch (GeneralSecurityException generalSecurityException) {
                    throw new SignatureException("Exception extracting parameters: " + generalSecurityException.getMessage());
                }
            }
        }
    }

    static String getSignatureName(AlgorithmIdentifier algorithmIdentifier) {
        Object object;
        Provider provider;
        ASN1Encodable aSN1Encodable = algorithmIdentifier.getParameters();
        if (aSN1Encodable != null && !derNull.equals(aSN1Encodable)) {
            if (algorithmIdentifier.getAlgorithm().equals(PKCSObjectIdentifiers.id_RSASSA_PSS)) {
                RSASSAPSSparams rSASSAPSSparams = RSASSAPSSparams.getInstance(aSN1Encodable);
                return X509SignatureUtil.getDigestAlgName(rSASSAPSSparams.getHashAlgorithm().getAlgorithm()) + "withRSAandMGF1";
            }
            if (algorithmIdentifier.getAlgorithm().equals(X9ObjectIdentifiers.ecdsa_with_SHA2)) {
                ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(aSN1Encodable);
                return X509SignatureUtil.getDigestAlgName((ASN1ObjectIdentifier)aSN1Sequence.getObjectAt(0)) + "withECDSA";
            }
        }
        if ((provider = Security.getProvider("BC")) != null && (object = provider.getProperty("Alg.Alias.Signature." + algorithmIdentifier.getAlgorithm().getId())) != null) {
            return object;
        }
        object = Security.getProviders();
        for (int i = 0; i != ((Provider[])object).length; ++i) {
            String string = object[i].getProperty("Alg.Alias.Signature." + algorithmIdentifier.getAlgorithm().getId());
            if (string == null) continue;
            return string;
        }
        return algorithmIdentifier.getAlgorithm().getId();
    }

    private static String getDigestAlgName(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String string = MessageDigestUtils.getDigestName(aSN1ObjectIdentifier);
        int n = string.indexOf(45);
        if (n > 0 && !string.startsWith("SHA3")) {
            return string.substring(0, n) + string.substring(n + 1);
        }
        return MessageDigestUtils.getDigestName(aSN1ObjectIdentifier);
    }
}

