/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.Arrays;

public class ECDomainParameters
implements ECConstants {
    private ECCurve curve;
    private byte[] seed;
    private ECPoint G;
    private BigInteger n;
    private BigInteger h;

    public ECDomainParameters(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger) {
        this(eCCurve, eCPoint, bigInteger, ONE, null);
    }

    public ECDomainParameters(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger, BigInteger bigInteger2) {
        this(eCCurve, eCPoint, bigInteger, bigInteger2, null);
    }

    public ECDomainParameters(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger, BigInteger bigInteger2, byte[] byArray) {
        this.curve = eCCurve;
        this.G = eCPoint.normalize();
        this.n = bigInteger;
        this.h = bigInteger2;
        this.seed = byArray;
    }

    public ECCurve getCurve() {
        return this.curve;
    }

    public ECPoint getG() {
        return this.G;
    }

    public BigInteger getN() {
        return this.n;
    }

    public BigInteger getH() {
        return this.h;
    }

    public byte[] getSeed() {
        return Arrays.clone(this.seed);
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof ECDomainParameters) {
            ECDomainParameters eCDomainParameters = (ECDomainParameters)object;
            return this.curve.equals(eCDomainParameters.curve) && this.G.equals(eCDomainParameters.G) && this.n.equals(eCDomainParameters.n) && this.h.equals(eCDomainParameters.h);
        }
        return false;
    }

    public int hashCode() {
        int n = this.curve.hashCode();
        n *= 37;
        n ^= this.G.hashCode();
        n *= 37;
        n ^= this.n.hashCode();
        n *= 37;
        return n ^= this.h.hashCode();
    }
}

