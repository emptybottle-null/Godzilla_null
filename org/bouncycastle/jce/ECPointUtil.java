/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce;

import java.security.spec.ECFieldF2m;
import java.security.spec.ECFieldFp;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;
import org.bouncycastle.math.ec.ECCurve;

public class ECPointUtil {
    public static ECPoint decodePoint(EllipticCurve ellipticCurve, byte[] byArray) {
        Object object;
        ECCurve eCCurve = null;
        eCCurve = ellipticCurve.getField() instanceof ECFieldFp ? new ECCurve.Fp(((ECFieldFp)ellipticCurve.getField()).getP(), ellipticCurve.getA(), ellipticCurve.getB()) : (((int[])(object = ((ECFieldF2m)ellipticCurve.getField()).getMidTermsOfReductionPolynomial())).length == 3 ? new ECCurve.F2m(((ECFieldF2m)ellipticCurve.getField()).getM(), object[2], (int)object[1], (int)object[0], ellipticCurve.getA(), ellipticCurve.getB()) : new ECCurve.F2m(((ECFieldF2m)ellipticCurve.getField()).getM(), object[0], ellipticCurve.getA(), ellipticCurve.getB()));
        object = eCCurve.decodePoint(byArray);
        return new ECPoint(((org.bouncycastle.math.ec.ECPoint)object).getAffineXCoord().toBigInteger(), ((org.bouncycastle.math.ec.ECPoint)object).getAffineYCoord().toBigInteger());
    }
}

