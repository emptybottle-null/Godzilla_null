/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce.spec;

import java.math.BigInteger;
import java.security.spec.ECField;
import java.security.spec.ECFieldF2m;
import java.security.spec.ECFieldFp;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;
import org.bouncycastle.math.ec.ECAlgorithms;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.field.FiniteField;
import org.bouncycastle.math.field.Polynomial;
import org.bouncycastle.math.field.PolynomialExtensionField;
import org.bouncycastle.util.Arrays;

public class ECNamedCurveSpec
extends ECParameterSpec {
    private String name;

    private static EllipticCurve convertCurve(ECCurve eCCurve, byte[] byArray) {
        ECField eCField = ECNamedCurveSpec.convertField(eCCurve.getField());
        BigInteger bigInteger = eCCurve.getA().toBigInteger();
        BigInteger bigInteger2 = eCCurve.getB().toBigInteger();
        return new EllipticCurve(eCField, bigInteger, bigInteger2, byArray);
    }

    private static ECField convertField(FiniteField finiteField) {
        if (ECAlgorithms.isFpField(finiteField)) {
            return new ECFieldFp(finiteField.getCharacteristic());
        }
        Polynomial polynomial = ((PolynomialExtensionField)finiteField).getMinimalPolynomial();
        int[] nArray = polynomial.getExponentsPresent();
        int[] nArray2 = Arrays.reverse(Arrays.copyOfRange(nArray, 1, nArray.length - 1));
        return new ECFieldF2m(polynomial.getDegree(), nArray2);
    }

    private static ECPoint convertPoint(org.bouncycastle.math.ec.ECPoint eCPoint) {
        eCPoint = eCPoint.normalize();
        return new ECPoint(eCPoint.getAffineXCoord().toBigInteger(), eCPoint.getAffineYCoord().toBigInteger());
    }

    public ECNamedCurveSpec(String string, ECCurve eCCurve, org.bouncycastle.math.ec.ECPoint eCPoint, BigInteger bigInteger) {
        super(ECNamedCurveSpec.convertCurve(eCCurve, null), ECNamedCurveSpec.convertPoint(eCPoint), bigInteger, 1);
        this.name = string;
    }

    public ECNamedCurveSpec(String string, EllipticCurve ellipticCurve, ECPoint eCPoint, BigInteger bigInteger) {
        super(ellipticCurve, eCPoint, bigInteger, 1);
        this.name = string;
    }

    public ECNamedCurveSpec(String string, ECCurve eCCurve, org.bouncycastle.math.ec.ECPoint eCPoint, BigInteger bigInteger, BigInteger bigInteger2) {
        super(ECNamedCurveSpec.convertCurve(eCCurve, null), ECNamedCurveSpec.convertPoint(eCPoint), bigInteger, bigInteger2.intValue());
        this.name = string;
    }

    public ECNamedCurveSpec(String string, EllipticCurve ellipticCurve, ECPoint eCPoint, BigInteger bigInteger, BigInteger bigInteger2) {
        super(ellipticCurve, eCPoint, bigInteger, bigInteger2.intValue());
        this.name = string;
    }

    public ECNamedCurveSpec(String string, ECCurve eCCurve, org.bouncycastle.math.ec.ECPoint eCPoint, BigInteger bigInteger, BigInteger bigInteger2, byte[] byArray) {
        super(ECNamedCurveSpec.convertCurve(eCCurve, byArray), ECNamedCurveSpec.convertPoint(eCPoint), bigInteger, bigInteger2.intValue());
        this.name = string;
    }

    public String getName() {
        return this.name;
    }
}

