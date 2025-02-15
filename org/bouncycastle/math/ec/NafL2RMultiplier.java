/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.AbstractECMultiplier;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.WNafUtil;

public class NafL2RMultiplier
extends AbstractECMultiplier {
    protected ECPoint multiplyPositive(ECPoint eCPoint, BigInteger bigInteger) {
        int[] nArray = WNafUtil.generateCompactNaf(bigInteger);
        ECPoint eCPoint2 = eCPoint.normalize();
        ECPoint eCPoint3 = eCPoint2.negate();
        ECPoint eCPoint4 = eCPoint.getCurve().getInfinity();
        int n = nArray.length;
        while (--n >= 0) {
            int n2 = nArray[n];
            int n3 = n2 >> 16;
            int n4 = n2 & 0xFFFF;
            eCPoint4 = eCPoint4.twicePlus(n3 < 0 ? eCPoint3 : eCPoint2);
            eCPoint4 = eCPoint4.timesPow2(n4);
        }
        return eCPoint4;
    }
}

