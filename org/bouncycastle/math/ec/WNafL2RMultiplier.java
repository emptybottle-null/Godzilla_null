/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.AbstractECMultiplier;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.LongArray;
import org.bouncycastle.math.ec.WNafPreCompInfo;
import org.bouncycastle.math.ec.WNafUtil;

public class WNafL2RMultiplier
extends AbstractECMultiplier {
    protected ECPoint multiplyPositive(ECPoint eCPoint, BigInteger bigInteger) {
        ECPoint[] eCPointArray;
        int n;
        int n2;
        int n3;
        int n4;
        int n5 = Math.max(2, Math.min(16, this.getWindowSize(bigInteger.bitLength())));
        WNafPreCompInfo wNafPreCompInfo = WNafUtil.precompute(eCPoint, n5, true);
        ECPoint[] eCPointArray2 = wNafPreCompInfo.getPreComp();
        ECPoint[] eCPointArray3 = wNafPreCompInfo.getPreCompNeg();
        int[] nArray = WNafUtil.generateCompactWindowNaf(n5, bigInteger);
        ECPoint eCPoint2 = eCPoint.getCurve().getInfinity();
        int n6 = nArray.length;
        if (n6 > 1) {
            n4 = nArray[--n6];
            n3 = n4 >> 16;
            n2 = n4 & 0xFFFF;
            n = Math.abs(n3);
            ECPoint[] eCPointArray4 = eCPointArray = n3 < 0 ? eCPointArray3 : eCPointArray2;
            if (n << 2 < 1 << n5) {
                byte by = LongArray.bitLengths[n];
                int n7 = n5 - by;
                int n8 = n ^ 1 << by - 1;
                int n9 = (1 << n5 - 1) - 1;
                int n10 = (n8 << n7) + 1;
                eCPoint2 = eCPointArray[n9 >>> 1].add(eCPointArray[n10 >>> 1]);
                n2 -= n7;
            } else {
                eCPoint2 = eCPointArray[n >>> 1];
            }
            eCPoint2 = eCPoint2.timesPow2(n2);
        }
        while (n6 > 0) {
            n4 = nArray[--n6];
            n3 = n4 >> 16;
            n2 = n4 & 0xFFFF;
            n = Math.abs(n3);
            eCPointArray = n3 < 0 ? eCPointArray3 : eCPointArray2;
            ECPoint eCPoint3 = eCPointArray[n >>> 1];
            eCPoint2 = eCPoint2.twicePlus(eCPoint3);
            eCPoint2 = eCPoint2.timesPow2(n2);
        }
        return eCPoint2;
    }

    protected int getWindowSize(int n) {
        return WNafUtil.getWindowSize(n);
    }
}

