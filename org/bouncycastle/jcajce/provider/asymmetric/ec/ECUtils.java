/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.ec;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.PublicKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECParameterSpec;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.x9.X962Parameters;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.bouncycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.bouncycastle.jce.spec.ECNamedCurveSpec;
import org.bouncycastle.math.ec.ECCurve;

class ECUtils {
    ECUtils() {
    }

    static AsymmetricKeyParameter generatePublicKeyParameter(PublicKey publicKey) throws InvalidKeyException {
        return publicKey instanceof BCECPublicKey ? ((BCECPublicKey)publicKey).engineGetKeyParameters() : ECUtil.generatePublicKeyParameter(publicKey);
    }

    static X9ECParameters getDomainParametersFromGenSpec(ECGenParameterSpec eCGenParameterSpec) {
        return ECUtils.getDomainParametersFromName(eCGenParameterSpec.getName());
    }

    static X9ECParameters getDomainParametersFromName(String string) {
        X9ECParameters x9ECParameters;
        try {
            if (string.charAt(0) >= '0' && string.charAt(0) <= '2') {
                ASN1ObjectIdentifier aSN1ObjectIdentifier = new ASN1ObjectIdentifier(string);
                x9ECParameters = ECUtil.getNamedCurveByOid(aSN1ObjectIdentifier);
            } else if (string.indexOf(32) > 0) {
                string = string.substring(string.indexOf(32) + 1);
                x9ECParameters = ECUtil.getNamedCurveByName(string);
            } else {
                x9ECParameters = ECUtil.getNamedCurveByName(string);
            }
        } catch (IllegalArgumentException illegalArgumentException) {
            x9ECParameters = ECUtil.getNamedCurveByName(string);
        }
        return x9ECParameters;
    }

    static X962Parameters getDomainParametersFromName(ECParameterSpec eCParameterSpec, boolean bl) {
        X962Parameters x962Parameters;
        if (eCParameterSpec instanceof ECNamedCurveSpec) {
            ASN1ObjectIdentifier aSN1ObjectIdentifier = ECUtil.getNamedCurveOid(((ECNamedCurveSpec)eCParameterSpec).getName());
            if (aSN1ObjectIdentifier == null) {
                aSN1ObjectIdentifier = new ASN1ObjectIdentifier(((ECNamedCurveSpec)eCParameterSpec).getName());
            }
            x962Parameters = new X962Parameters(aSN1ObjectIdentifier);
        } else if (eCParameterSpec == null) {
            x962Parameters = new X962Parameters(DERNull.INSTANCE);
        } else {
            ECCurve eCCurve = EC5Util.convertCurve(eCParameterSpec.getCurve());
            X9ECParameters x9ECParameters = new X9ECParameters(eCCurve, EC5Util.convertPoint(eCCurve, eCParameterSpec.getGenerator(), bl), eCParameterSpec.getOrder(), BigInteger.valueOf(eCParameterSpec.getCofactor()), eCParameterSpec.getCurve().getSeed());
            x962Parameters = new X962Parameters(x9ECParameters);
        }
        return x962Parameters;
    }
}

