/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.custom.sec.SecP160R1FieldElement;
import org.bouncycastle.math.ec.custom.sec.SecP160R1Point;
import org.bouncycastle.util.encoders.Hex;

public class SecP160R1Curve
extends ECCurve.AbstractFp {
    public static final BigInteger q = new BigInteger(1, Hex.decode("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF7FFFFFFF"));
    private static final int SecP160R1_DEFAULT_COORDS = 2;
    protected SecP160R1Point infinity = new SecP160R1Point(this, null, null);

    public SecP160R1Curve() {
        super(q);
        this.a = this.fromBigInteger(new BigInteger(1, Hex.decode("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF7FFFFFFC")));
        this.b = this.fromBigInteger(new BigInteger(1, Hex.decode("1C97BEFC54BD7A8B65ACF89F81D4D4ADC565FA45")));
        this.order = new BigInteger(1, Hex.decode("0100000000000000000001F4C8F927AED3CA752257"));
        this.cofactor = BigInteger.valueOf(1L);
        this.coord = 2;
    }

    protected ECCurve cloneCurve() {
        return new SecP160R1Curve();
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
        return new SecP160R1FieldElement(bigInteger);
    }

    protected ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean bl) {
        return new SecP160R1Point((ECCurve)this, eCFieldElement, eCFieldElement2, bl);
    }

    protected ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArray, boolean bl) {
        return new SecP160R1Point(this, eCFieldElement, eCFieldElement2, eCFieldElementArray, bl);
    }

    public ECPoint getInfinity() {
        return this.infinity;
    }
}

