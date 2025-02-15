/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.AbstractECMultiplier;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.WNafUtil;

public class MixedNafR2LMultiplier
extends AbstractECMultiplier {
    protected int additionCoord;
    protected int doublingCoord;

    public MixedNafR2LMultiplier() {
        this(2, 4);
    }

    public MixedNafR2LMultiplier(int n, int n2) {
        this.additionCoord = n;
        this.doublingCoord = n2;
    }

    protected ECPoint multiplyPositive(ECPoint eCPoint, BigInteger bigInteger) {
        ECCurve eCCurve = eCPoint.getCurve();
        ECCurve eCCurve2 = this.configureCurve(eCCurve, this.additionCoord);
        ECCurve eCCurve3 = this.configureCurve(eCCurve, this.doublingCoord);
        int[] nArray = WNafUtil.generateCompactNaf(bigInteger);
        ECPoint eCPoint2 = eCCurve2.getInfinity();
        ECPoint eCPoint3 = eCCurve3.importPoint(eCPoint);
        int n = 0;
        for (int i = 0; i < nArray.length; ++i) {
            int n2 = nArray[i];
            int n3 = n2 >> 16;
            eCPoint3 = eCPoint3.timesPow2(n += n2 & 0xFFFF);
            ECPoint eCPoint4 = eCCurve2.importPoint(eCPoint3);
            if (n3 < 0) {
                eCPoint4 = eCPoint4.negate();
            }
            eCPoint2 = eCPoint2.add(eCPoint4);
            n = 1;
        }
        return eCCurve.importPoint(eCPoint2);
    }

    protected ECCurve configureCurve(ECCurve eCCurve, int n) {
        if (eCCurve.getCoordinateSystem() == n) {
            return eCCurve;
        }
        if (!eCCurve.supportsCoordinateSystem(n)) {
            throw new IllegalArgumentException("Coordinate system " + n + " not supported by this curve");
        }
        return eCCurve.configure().setCoordinateSystem(n).create();
    }
}

