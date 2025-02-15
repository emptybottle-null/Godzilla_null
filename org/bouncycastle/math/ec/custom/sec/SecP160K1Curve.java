/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.custom.sec.SecP160K1Point;
import org.bouncycastle.math.ec.custom.sec.SecP160R2Curve;
import org.bouncycastle.math.ec.custom.sec.SecP160R2FieldElement;
import org.bouncycastle.util.encoders.Hex;

public class SecP160K1Curve
extends ECCurve.AbstractFp {
    public static final BigInteger q = SecP160R2Curve.q;
    private static final int SECP160K1_DEFAULT_COORDS = 2;
    protected SecP160K1Point infinity = new SecP160K1Point(this, null, null);

    public SecP160K1Curve() {
        super(q);
        this.a = this.fromBigInteger(ECConstants.ZERO);
        this.b = this.fromBigInteger(BigInteger.valueOf(7L));
        this.order = new BigInteger(1, Hex.decode("0100000000000000000001B8FA16DFAB9ACA16B6B3"));
        this.cofactor = BigInteger.valueOf(1L);
        this.coord = 2;
    }

    protected ECCurve cloneCurve() {
        return new SecP160K1Curve();
    }

    public boolean supportsCoordinateSystem(int n) {
        switch (n) {
            case 2: {
                return true;
            }
        }
        return false;
    }

    public BigInteger getQ() {
        return q;
    }

    public int getFieldSize() {
        return q.bitLength();
    }

    public ECFieldElement fromBigInteger(BigInteger bigInteger) {
        return new SecP160R2FieldElement(bigInteger);
    }

    protected ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean bl) {
        return new SecP160K1Point((ECCurve)this, eCFieldElement, eCFieldElement2, bl);
    }

    protected ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArray, boolean bl) {
        return new SecP160K1Point(this, eCFieldElement, eCFieldElement2, eCFieldElementArray, bl);
    }

    public ECPoint getInfinity() {
        return this.infinity;
    }
}

