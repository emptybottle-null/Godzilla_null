/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.AbstractECMultiplier;
import org.bouncycastle.math.ec.ECPoint;

public class ZSignedDigitL2RMultiplier
extends AbstractECMultiplier {
    protected ECPoint multiplyPositive(ECPoint eCPoint, BigInteger bigInteger) {
        ECPoint eCPoint2 = eCPoint.normalize();
        ECPoint eCPoint3 = eCPoint2.negate();
        ECPoint eCPoint4 = eCPoint2;
        int n = bigInteger.bitLength();
        int n2 = bigInteger.getLowestSetBit();
        int n3 = n;
        while (--n3 > n2) {
            eCPoint4 = eCPoint4.twicePlus(bigInteger.testBit(n3) ? eCPoint2 : eCPoint3);
        }
        eCPoint4 = eCPoint4.timesPow2(n2);
        return eCPoint4;
    }
}

