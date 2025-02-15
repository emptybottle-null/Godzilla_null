/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.AbstractECMultiplier;
import org.bouncycastle.math.ec.ECPoint;

public class MontgomeryLadderMultiplier
extends AbstractECMultiplier {
    protected ECPoint multiplyPositive(ECPoint eCPoint, BigInteger bigInteger) {
        int n;
        ECPoint[] eCPointArray = new ECPoint[]{eCPoint.getCurve().getInfinity(), eCPoint};
        int n2 = n = bigInteger.bitLength();
        while (--n2 >= 0) {
            int n3 = bigInteger.testBit(n2) ? 1 : 0;
            int n4 = 1 - n3;
            eCPointArray[n4] = eCPointArray[n4].add(eCPointArray[n3]);
            eCPointArray[n3] = eCPointArray[n3].twice();
        }
        return eCPointArray[0];
    }
}

