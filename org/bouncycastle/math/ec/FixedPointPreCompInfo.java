/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec;

import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.PreCompInfo;

public class FixedPointPreCompInfo
implements PreCompInfo {
    protected ECPoint offset = null;
    protected ECPoint[] preComp = null;
    protected int width = -1;

    public ECPoint getOffset() {
        return this.offset;
    }

    public void setOffset(ECPoint eCPoint) {
        this.offset = eCPoint;
    }

    public ECPoint[] getPreComp() {
        return this.preComp;
    }

    public void setPreComp(ECPoint[] eCPointArray) {
        this.preComp = eCPointArray;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int n) {
        this.width = n;
    }
}

