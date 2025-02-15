/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.AbstractECMultiplier;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.FixedPointPreCompInfo;
import org.bouncycastle.math.ec.FixedPointUtil;

public class FixedPointCombMultiplier
extends AbstractECMultiplier {
    protected ECPoint multiplyPositive(ECPoint eCPoint, BigInteger bigInteger) {
        ECCurve eCCurve = eCPoint.getCurve();
        int n = FixedPointUtil.getCombSize(eCCurve);
        if (bigInteger.bitLength() > n) {
            throw new IllegalStateException("fixed-point comb doesn't support scalars larger than the curve order");
        }
        int n2 = this.getWidthForCombSize(n);
        FixedPointPreCompInfo fixedPointPreCompInfo = FixedPointUtil.precompute(eCPoint, n2);
        ECPoint[] eCPointArray = fixedPointPreCompInfo.getPreComp();
        int n3 = fixedPointPreCompInfo.getWidth();
        int n4 = (n + n3 - 1) / n3;
        ECPoint eCPoint2 = eCCurve.getInfinity();
        int n5 = n4 * n3 - 1;
        for (int i = 0; i < n4; ++i) {
            int n6 = 0;
            for (int j = n5 - i; j >= 0; j -= n4) {
                n6 <<= 1;
                if (!bigInteger.testBit(j)) continue;
                n6 |= 1;
            }
            eCPoint2 = eCPoint2.twicePlus(eCPointArray[n6]);
        }
        return eCPoint2.add(fixedPointPreCompInfo.getOffset());
    }

    protected int getWidthForCombSize(int n) {
        return n > 257 ? 6 : 5;
    }
}

