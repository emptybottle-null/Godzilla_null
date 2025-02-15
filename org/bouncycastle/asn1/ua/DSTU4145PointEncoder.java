/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.ua;

import java.math.BigInteger;
import java.util.Random;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECPoint;

public abstract class DSTU4145PointEncoder {
    private static ECFieldElement trace(ECFieldElement eCFieldElement) {
        ECFieldElement eCFieldElement2 = eCFieldElement;
        for (int i = 1; i < eCFieldElement.getFieldSize(); ++i) {
            eCFieldElement2 = eCFieldElement2.square().add(eCFieldElement);
        }
        return eCFieldElement2;
    }

    private static ECFieldElement solveQuadraticEquation(ECCurve eCCurve, ECFieldElement eCFieldElement) {
        if (eCFieldElement.isZero()) {
            return eCFieldElement;
        }
        ECFieldElement eCFieldElement2 = eCCurve.fromBigInteger(ECConstants.ZERO);
        ECFieldElement eCFieldElement3 = null;
        ECFieldElement eCFieldElement4 = null;
        Random random = new Random();
        int n = eCFieldElement.getFieldSize();
        do {
            ECFieldElement eCFieldElement5 = eCCurve.fromBigInteger(new BigInteger(n, random));
            eCFieldElement3 = eCFieldElement2;
            ECFieldElement eCFieldElement6 = eCFieldElement;
            for (int i = 1; i <= n - 1; ++i) {
                ECFieldElement eCFieldElement7 = eCFieldElement6.square();
                eCFieldElement3 = eCFieldElement3.square().add(eCFieldElement7.multiply(eCFieldElement5));
                eCFieldElement6 = eCFieldElement7.add(eCFieldElement);
            }
            if (eCFieldElement6.isZero()) continue;
            return null;
        } while ((eCFieldElement4 = eCFieldElement3.square().add(eCFieldElement3)).isZero());
        return eCFieldElement3;
    }

    public static byte[] encodePoint(ECPoint eCPoint) {
        eCPoint = eCPoint.normalize();
        ECFieldElement eCFieldElement = eCPoint.getAffineXCoord();
        byte[] byArray = eCFieldElement.getEncoded();
        if (!eCFieldElement.isZero()) {
            ECFieldElement eCFieldElement2 = eCPoint.getAffineYCoord().divide(eCFieldElement);
            if (DSTU4145PointEncoder.trace(eCFieldElement2).isOne()) {
                int n = byArray.length - 1;
                byArray[n] = (byte)(byArray[n] | 1);
            } else {
                int n = byArray.length - 1;
                byArray[n] = (byte)(byArray[n] & 0xFE);
            }
        }
        return byArray;
    }

    public static ECPoint decodePoint(ECCurve eCCurve, byte[] byArray) {
        ECFieldElement eCFieldElement = eCCurve.fromBigInteger(BigInteger.valueOf(byArray[byArray.length - 1] & 1));
        ECFieldElement eCFieldElement2 = eCCurve.fromBigInteger(new BigInteger(1, byArray));
        if (!DSTU4145PointEncoder.trace(eCFieldElement2).equals(eCCurve.getA())) {
            eCFieldElement2 = eCFieldElement2.addOne();
        }
        ECFieldElement eCFieldElement3 = null;
        if (eCFieldElement2.isZero()) {
            eCFieldElement3 = eCCurve.getB().sqrt();
        } else {
            ECFieldElement eCFieldElement4 = eCFieldElement2.square().invert().multiply(eCCurve.getB()).add(eCCurve.getA()).add(eCFieldElement2);
            ECFieldElement eCFieldElement5 = DSTU4145PointEncoder.solveQuadraticEquation(eCCurve, eCFieldElement4);
            if (eCFieldElement5 != null) {
                if (!DSTU4145PointEncoder.trace(eCFieldElement5).equals(eCFieldElement)) {
                    eCFieldElement5 = eCFieldElement5.addOne();
                }
                eCFieldElement3 = eCFieldElement2.multiply(eCFieldElement5);
            }
        }
        if (eCFieldElement3 == null) {
            throw new IllegalArgumentException("Invalid point compression");
        }
        return eCCurve.validatePoint(eCFieldElement2.toBigInteger(), eCFieldElement3.toBigInteger());
    }
}

