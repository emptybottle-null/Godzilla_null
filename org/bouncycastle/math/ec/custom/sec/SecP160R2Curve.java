/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.custom.sec.SecP160R2FieldElement;
import org.bouncycastle.math.ec.custom.sec.SecP160R2Point;
import org.bouncycastle.util.encoders.Hex;

public class SecP160R2Curve
extends ECCurve.AbstractFp {
    public static final BigInteger q = new BigInteger(1, Hex.decode("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFAC73"));
    private static final int SecP160R2_DEFAULT_COORDS = 2;
    protected SecP160R2Point infinity = new SecP160R2Point(this, null, null);

    public SecP160R2Curve() {
        super(q);
        this.a = this.fromBigInteger(new BigInteger(1, Hex.decode("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFAC70")));
        this.b = this.fromBigInteger(new BigInteger(1, Hex.decode("B4E134D3FB59EB8BAB57274904664D5AF50388BA")));
        this.order = new BigInteger(1, Hex.decode("0100000000000000000000351EE786A818F3A1A16B"));
        this.cofactor = BigInteger.valueOf(1L);
        this.coord = 2;
    }

    protected ECCurve cloneCurve() {
        return new SecP160R2Curve();
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
        return new SecP160R2Point((ECCurve)this, eCFieldElement, eCFieldElement2, bl);
    }

    protected ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArray, boolean bl) {
        return new SecP160R2Point(this, eCFieldElement, eCFieldElement2, eCFieldElementArray, bl);
    }

    public ECPoint getInfinity() {
        return this.infinity;
    }
}

