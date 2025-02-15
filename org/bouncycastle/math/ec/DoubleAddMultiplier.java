/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.AbstractECMultiplier;
import org.bouncycastle.math.ec.ECPoint;

public class DoubleAddMultiplier
extends AbstractECMultiplier {
    protected ECPoint multiplyPositive(ECPoint eCPoint, BigInteger bigInteger) {
        ECPoint[] eCPointArray = new ECPoint[]{eCPoint.getCurve().getInfinity(), eCPoint};
        int n = bigInteger.bitLength();
        for (int i = 0; i < n; ++i) {
            int n2 = bigInteger.testBit(i) ? 1 : 0;
            int n3 = 1 - n2;
            eCPointArray[n3] = eCPointArray[n3].twicePlus(eCPointArray[n2]);
        }
        return eCPointArray[0];
    }
}

