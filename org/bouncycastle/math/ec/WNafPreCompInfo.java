/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec;

import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.PreCompInfo;

public class WNafPreCompInfo
implements PreCompInfo {
    protected ECPoint[] preComp = null;
    protected ECPoint[] preCompNeg = null;
    protected ECPoint twice = null;

    public ECPoint[] getPreComp() {
        return this.preComp;
    }

    public void setPreComp(ECPoint[] eCPointArray) {
        this.preComp = eCPointArray;
    }

    public ECPoint[] getPreCompNeg() {
        return this.preCompNeg;
    }

    public void setPreCompNeg(ECPoint[] eCPointArray) {
        this.preCompNeg = eCPointArray;
    }

    public ECPoint getTwice() {
        return this.twice;
    }

    public void setTwice(ECPoint eCPoint) {
        this.twice = eCPoint;
    }
}

