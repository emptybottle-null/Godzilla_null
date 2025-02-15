/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.AbstractECMultiplier;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.WNafUtil;

public class NafR2LMultiplier
extends AbstractECMultiplier {
    protected ECPoint multiplyPositive(ECPoint eCPoint, BigInteger bigInteger) {
        int[] nArray = WNafUtil.generateCompactNaf(bigInteger);
        ECPoint eCPoint2 = eCPoint.getCurve().getInfinity();
        ECPoint eCPoint3 = eCPoint;
        int n = 0;
        for (int i = 0; i < nArray.length; ++i) {
            int n2 = nArray[i];
            int n3 = n2 >> 16;
            eCPoint3 = eCPoint3.timesPow2(n += n2 & 0xFFFF);
            eCPoint2 = eCPoint2.add(n3 < 0 ? eCPoint3.negate() : eCPoint3);
            n = 1;
        }
        return eCPoint2;
    }
}

