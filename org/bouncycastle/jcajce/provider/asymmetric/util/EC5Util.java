/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.util;

import java.math.BigInteger;
import java.security.spec.ECField;
import java.security.spec.ECFieldF2m;
import java.security.spec.ECFieldFp;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x9.ECNamedCurveTable;
import org.bouncycastle.asn1.x9.X962Parameters;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.ec.CustomNamedCurves;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.bouncycastle.jcajce.provider.config.ProviderConfiguration;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.jce.spec.ECNamedCurveSpec;
import org.bouncycastle.math.ec.ECAlgorithms;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.field.FiniteField;
import org.bouncycastle.math.field.Polynomial;
import org.bouncycastle.math.field.PolynomialExtensionField;
import org.bouncycastle.util.Arrays;

public class EC5Util {
    private static Map customCurves;

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static ECCurve getCurve(ProviderConfiguration providerConfiguration, X962Parameters x962Parameters) {
        Set set = providerConfiguration.getAcceptableNamedCurves();
        if (x962Parameters.isNamedCurve()) {
            X9ECParameters x9ECParameters;
            ASN1ObjectIdentifier aSN1ObjectIdentifier = ASN1ObjectIdentifier.getInstance(x962Parameters.getParameters());
            if (!set.isEmpty()) {
                if (!set.contains(aSN1ObjectIdentifier)) throw new IllegalStateException("named curve not acceptable");
            }
            if ((x9ECParameters = ECUtil.getNamedCurveByOid(aSN1ObjectIdentifier)) != null) return x9ECParameters.getCurve();
            x9ECParameters = (X9ECParameters)providerConfiguration.getAdditionalECParameters().get(aSN1ObjectIdentifier);
            return x9ECParameters.getCurve();
        }
        if (x962Parameters.isImplicitlyCA()) {
            return providerConfiguration.getEcImplicitlyCa().getCurve();
        }
        if (!set.isEmpty()) throw new IllegalStateException("encoded parameters not acceptable");
        X9ECParameters x9ECParameters = X9ECParameters.getInstance(x962Parameters.getParameters());
        return x9ECParameters.getCurve();
    }

    public static ECDomainParameters getDomainParameters(ProviderConfiguration providerConfiguration, ECParameterSpec eCParameterSpec) {
        ECDomainParameters eCDomainParameters;
        if (eCParameterSpec == null) {
            org.bouncycastle.jce.spec.ECParameterSpec eCParameterSpec2 = providerConfiguration.getEcImplicitlyCa();
            eCDomainParameters = new ECDomainParameters(eCParameterSpec2.getCurve(), eCParameterSpec2.getG(), eCParameterSpec2.getN(), eCParameterSpec2.getH(), eCParameterSpec2.getSeed());
        } else {
            eCDomainParameters = ECUtil.getDomainParameters(providerConfiguration, EC5Util.convertSpec(eCParameterSpec, false));
        }
        return eCDomainParameters;
    }

    public static ECParameterSpec convertToSpec(X962Parameters x962Parameters, ECCurve eCCurve) {
        ECParameterSpec eCParameterSpec;
        if (x962Parameters.isNamedCurve()) {
            Map map;
            ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier)x962Parameters.getParameters();
            X9ECParameters x9ECParameters = ECUtil.getNamedCurveByOid(aSN1ObjectIdentifier);
            if (x9ECParameters == null && !(map = BouncyCastleProvider.CONFIGURATION.getAdditionalECParameters()).isEmpty()) {
                x9ECParameters = (X9ECParameters)map.get(aSN1ObjectIdentifier);
            }
            EllipticCurve ellipticCurve = EC5Util.convertCurve(eCCurve, x9ECParameters.getSeed());
            eCParameterSpec = new ECNamedCurveSpec(ECUtil.getCurveName(aSN1ObjectIdentifier), ellipticCurve, new ECPoint(x9ECParameters.getG().getAffineXCoord().toBigInteger(), x9ECParameters.getG().getAffineYCoord().toBigInteger()), x9ECParameters.getN(), x9ECParameters.getH());
        } else if (x962Parameters.isImplicitlyCA()) {
            eCParameterSpec = null;
        } else {
            X9ECParameters x9ECParameters = X9ECParameters.getInstance(x962Parameters.getParameters());
            EllipticCurve ellipticCurve = EC5Util.convertCurve(eCCurve, x9ECParameters.getSeed());
            eCParameterSpec = x9ECParameters.getH() != null ? new ECParameterSpec(ellipticCurve, new ECPoint(x9ECParameters.getG().getAffineXCoord().toBigInteger(), x9ECParameters.getG().getAffineYCoord().toBigInteger()), x9ECParameters.getN(), x9ECParameters.getH().intValue()) : new ECParameterSpec(ellipticCurve, new ECPoint(x9ECParameters.getG().getAffineXCoord().toBigInteger(), x9ECParameters.getG().getAffineYCoord().toBigInteger()), x9ECParameters.getN(), 1);
        }
        return eCParameterSpec;
    }

    public static ECParameterSpec convertToSpec(X9ECParameters x9ECParameters) {
        return new ECParameterSpec(EC5Util.convertCurve(x9ECParameters.getCurve(), null), new ECPoint(x9ECParameters.getG().getAffineXCoord().toBigInteger(), x9ECParameters.getG().getAffineYCoord().toBigInteger()), x9ECParameters.getN(), x9ECParameters.getH().intValue());
    }

    public static EllipticCurve convertCurve(ECCurve eCCurve, byte[] byArray) {
        ECField eCField = EC5Util.convertField(eCCurve.getField());
        BigInteger bigInteger = eCCurve.getA().toBigInteger();
        BigInteger bigInteger2 = eCCurve.getB().toBigInteger();
        return new EllipticCurve(eCField, bigInteger, bigInteger2, null);
    }

    public static ECCurve convertCurve(EllipticCurve ellipticCurve) {
        ECField eCField = ellipticCurve.getField();
        BigInteger bigInteger = ellipticCurve.getA();
        BigInteger bigInteger2 = ellipticCurve.getB();
        if (eCField instanceof ECFieldFp) {
            ECCurve.Fp fp = new ECCurve.Fp(((ECFieldFp)eCField).getP(), bigInteger, bigInteger2);
            if (customCurves.containsKey(fp)) {
                return (ECCurve)customCurves.get(fp);
            }
            return fp;
        }
        ECFieldF2m eCFieldF2m = (ECFieldF2m)eCField;
        int n = eCFieldF2m.getM();
        int[] nArray = ECUtil.convertMidTerms(eCFieldF2m.getMidTermsOfReductionPolynomial());
        return new ECCurve.F2m(n, nArray[0], nArray[1], nArray[2], bigInteger, bigInteger2);
    }

    public static ECField convertField(FiniteField finiteField) {
        if (ECAlgorithms.isFpField(finiteField)) {
            return new ECFieldFp(finiteField.getCharacteristic());
        }
        Polynomial polynomial = ((PolynomialExtensionField)finiteField).getMinimalPolynomial();
        int[] nArray = polynomial.getExponentsPresent();
        int[] nArray2 = Arrays.reverse(Arrays.copyOfRange(nArray, 1, nArray.length - 1));
        return new ECFieldF2m(polynomial.getDegree(), nArray2);
    }

    public static ECParameterSpec convertSpec(EllipticCurve ellipticCurve, org.bouncycastle.jce.spec.ECParameterSpec eCParameterSpec) {
        if (eCParameterSpec instanceof ECNamedCurveParameterSpec) {
            return new ECNamedCurveSpec(((ECNamedCurveParameterSpec)eCParameterSpec).getName(), ellipticCurve, new ECPoint(eCParameterSpec.getG().getAffineXCoord().toBigInteger(), eCParameterSpec.getG().getAffineYCoord().toBigInteger()), eCParameterSpec.getN(), eCParameterSpec.getH());
        }
        return new ECParameterSpec(ellipticCurve, new ECPoint(eCParameterSpec.getG().getAffineXCoord().toBigInteger(), eCParameterSpec.getG().getAffineYCoord().toBigInteger()), eCParameterSpec.getN(), eCParameterSpec.getH().intValue());
    }

    public static org.bouncycastle.jce.spec.ECParameterSpec convertSpec(ECParameterSpec eCParameterSpec, boolean bl) {
        ECCurve eCCurve = EC5Util.convertCurve(eCParameterSpec.getCurve());
        return new org.bouncycastle.jce.spec.ECParameterSpec(eCCurve, EC5Util.convertPoint(eCCurve, eCParameterSpec.getGenerator(), bl), eCParameterSpec.getOrder(), BigInteger.valueOf(eCParameterSpec.getCofactor()), eCParameterSpec.getCurve().getSeed());
    }

    public static org.bouncycastle.math.ec.ECPoint convertPoint(ECParameterSpec eCParameterSpec, ECPoint eCPoint, boolean bl) {
        return EC5Util.convertPoint(EC5Util.convertCurve(eCParameterSpec.getCurve()), eCPoint, bl);
    }

    public static org.bouncycastle.math.ec.ECPoint convertPoint(ECCurve eCCurve, ECPoint eCPoint, boolean bl) {
        return eCCurve.createPoint(eCPoint.getAffineX(), eCPoint.getAffineY());
    }

    static {
        Object object;
        customCurves = new HashMap();
        Enumeration enumeration = CustomNamedCurves.getNames();
        while (enumeration.hasMoreElements()) {
            object = (String)enumeration.nextElement();
            X9ECParameters x9ECParameters = ECNamedCurveTable.getByName((String)object);
            if (x9ECParameters == null) continue;
            customCurves.put(x9ECParameters.getCurve(), CustomNamedCurves.getByName((String)object).getCurve());
        }
        object = CustomNamedCurves.getByName("Curve25519");
        customCurves.put(new ECCurve.Fp(((X9ECParameters)object).getCurve().getField().getCharacteristic(), ((X9ECParameters)object).getCurve().getA().toBigInteger(), ((X9ECParameters)object).getCurve().getB().toBigInteger()), ((X9ECParameters)object).getCurve());
    }
}

