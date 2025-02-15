/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.AbstractECMultiplier;
import org.bouncycastle.math.ec.ECPoint;

public class ZSignedDigitR2LMultiplier
extends AbstractECMultiplier {
    protected ECPoint multiplyPositive(ECPoint eCPoint, BigInteger bigInteger) {
        ECPoint eCPoint2 = eCPoint.getCurve().getInfinity();
        ECPoint eCPoint3 = eCPoint;
        int n = bigInteger.bitLength();
        int n2 = bigInteger.getLowestSetBit();
        eCPoint3 = eCPoint3.timesPow2(n2);
        int n3 = n2;
        while (++n3 < n) {
            eCPoint2 = eCPoint2.add(bigInteger.testBit(n3) ? eCPoint3 : eCPoint3.negate());
            eCPoint3 = eCPoint3.twice();
        }
        eCPoint2 = eCPoint2.add(eCPoint3);
        return eCPoint2;
    }
}

