/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.ec;

import java.io.IOException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.x9.ECNamedCurveTable;
import org.bouncycastle.asn1.x9.X962Parameters;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.jcajce.provider.asymmetric.ec.ECUtils;
import org.bouncycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.bouncycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveSpec;
import org.bouncycastle.math.ec.ECCurve;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class AlgorithmParametersSpi
extends java.security.AlgorithmParametersSpi {
    private ECParameterSpec ecParameterSpec;
    private String curveName;

    protected boolean isASN1FormatString(String string) {
        return string == null || string.equals("ASN.1");
    }

    @Override
    protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
        if (algorithmParameterSpec instanceof ECGenParameterSpec) {
            ECGenParameterSpec eCGenParameterSpec = (ECGenParameterSpec)algorithmParameterSpec;
            X9ECParameters x9ECParameters = ECUtils.getDomainParametersFromGenSpec(eCGenParameterSpec);
            if (x9ECParameters == null) {
                throw new InvalidParameterSpecException("EC curve name not recognized: " + eCGenParameterSpec.getName());
            }
            this.curveName = eCGenParameterSpec.getName();
            this.ecParameterSpec = EC5Util.convertToSpec(x9ECParameters);
        } else if (algorithmParameterSpec instanceof ECParameterSpec) {
            this.curveName = algorithmParameterSpec instanceof ECNamedCurveSpec ? ((ECNamedCurveSpec)algorithmParameterSpec).getName() : null;
            this.ecParameterSpec = (ECParameterSpec)algorithmParameterSpec;
        } else {
            throw new InvalidParameterSpecException("AlgorithmParameterSpec class not recognized: " + algorithmParameterSpec.getClass().getName());
        }
    }

    @Override
    protected void engineInit(byte[] byArray) throws IOException {
        this.engineInit(byArray, "ASN.1");
    }

    @Override
    protected void engineInit(byte[] byArray, String string) throws IOException {
        ECCurve eCCurve;
        X962Parameters x962Parameters;
        if (this.isASN1FormatString(string)) {
            x962Parameters = X962Parameters.getInstance(byArray);
            eCCurve = EC5Util.getCurve(BouncyCastleProvider.CONFIGURATION, x962Parameters);
            if (x962Parameters.isNamedCurve()) {
                ASN1ObjectIdentifier aSN1ObjectIdentifier = ASN1ObjectIdentifier.getInstance(x962Parameters.getParameters());
                this.curveName = ECNamedCurveTable.getName(aSN1ObjectIdentifier);
                if (this.curveName == null) {
                    this.curveName = aSN1ObjectIdentifier.getId();
                }
            }
        } else {
            throw new IOException("Unknown encoded parameters format in AlgorithmParameters object: " + string);
        }
        this.ecParameterSpec = EC5Util.convertToSpec(x962Parameters, eCCurve);
    }

    @Override
    protected <T extends AlgorithmParameterSpec> T engineGetParameterSpec(Class<T> clazz) throws InvalidParameterSpecException {
        if (ECParameterSpec.class.isAssignableFrom(clazz) || clazz == AlgorithmParameterSpec.class) {
            return (T)this.ecParameterSpec;
        }
        if (ECGenParameterSpec.class.isAssignableFrom(clazz)) {
            if (this.curveName != null) {
                ASN1ObjectIdentifier aSN1ObjectIdentifier = ECUtil.getNamedCurveOid(this.curveName);
                if (aSN1ObjectIdentifier != null) {
                    return (T)new ECGenParameterSpec(aSN1ObjectIdentifier.getId());
                }
                return (T)new ECGenParameterSpec(this.curveName);
            }
            ASN1ObjectIdentifier aSN1ObjectIdentifier = ECUtil.getNamedCurveOid(EC5Util.convertSpec(this.ecParameterSpec, false));
            if (aSN1ObjectIdentifier != null) {
                return (T)new ECGenParameterSpec(aSN1ObjectIdentifier.getId());
            }
        }
        throw new InvalidParameterSpecException("EC AlgorithmParameters cannot convert to " + clazz.getName());
    }

    @Override
    protected byte[] engineGetEncoded() throws IOException {
        return this.engineGetEncoded("ASN.1");
    }

    @Override
    protected byte[] engineGetEncoded(String string) throws IOException {
        if (this.isASN1FormatString(string)) {
            X962Parameters x962Parameters;
            if (this.ecParameterSpec == null) {
                x962Parameters = new X962Parameters(DERNull.INSTANCE);
            } else if (this.curveName != null) {
                x962Parameters = new X962Parameters(ECUtil.getNamedCurveOid(this.curveName));
            } else {
                org.bouncycastle.jce.spec.ECParameterSpec eCParameterSpec = EC5Util.convertSpec(this.ecParameterSpec, false);
                X9ECParameters x9ECParameters = new X9ECParameters(eCParameterSpec.getCurve(), eCParameterSpec.getG(), eCParameterSpec.getN(), eCParameterSpec.getH(), eCParameterSpec.getSeed());
                x962Parameters = new X962Parameters(x9ECParameters);
            }
            return x962Parameters.getEncoded();
        }
        throw new IOException("Unknown parameters format in AlgorithmParameters object: " + string);
    }

    @Override
    protected String engineToString() {
        return "EC AlgorithmParameters ";
    }
}

