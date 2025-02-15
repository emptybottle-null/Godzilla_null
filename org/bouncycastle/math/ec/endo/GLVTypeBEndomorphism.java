/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec.endo;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPointMap;
import org.bouncycastle.math.ec.ScaleXPointMap;
import org.bouncycastle.math.ec.endo.GLVEndomorphism;
import org.bouncycastle.math.ec.endo.GLVTypeBParameters;

public class GLVTypeBEndomorphism
implements GLVEndomorphism {
    protected final ECCurve curve;
    protected final GLVTypeBParameters parameters;
    protected final ECPointMap pointMap;

    public GLVTypeBEndomorphism(ECCurve eCCurve, GLVTypeBParameters gLVTypeBParameters) {
        this.curve = eCCurve;
        this.parameters = gLVTypeBParameters;
        this.pointMap = new ScaleXPointMap(eCCurve.fromBigInteger(gLVTypeBParameters.getBeta()));
    }

    public BigInteger[] decomposeScalar(BigInteger bigInteger) {
        int n = this.parameters.getBits();
        BigInteger bigInteger2 = this.calculateB(bigInteger, this.parameters.getG1(), n);
        BigInteger bigInteger3 = this.calculateB(bigInteger, this.parameters.getG2(), n);
        GLVTypeBParameters gLVTypeBParameters = this.parameters;
        BigInteger bigInteger4 = bigInteger.subtract(bigInteger2.multiply(gLVTypeBParameters.getV1A()).add(bigInteger3.multiply(gLVTypeBParameters.getV2A())));
        BigInteger bigInteger5 = bigInteger2.multiply(gLVTypeBParameters.getV1B()).add(bigInteger3.multiply(gLVTypeBParameters.getV2B())).negate();
        return new BigInteger[]{bigInteger4, bigInteger5};
    }

    public ECPointMap getPointMap() {
        return this.pointMap;
    }

    public boolean hasEfficientPointMap() {
        return true;
    }

    protected BigInteger calculateB(BigInteger bigInteger, BigInteger bigInteger2, int n) {
        boolean bl = bigInteger2.signum() < 0;
        BigInteger bigInteger3 = bigInteger.multiply(bigInteger2.abs());
        boolean bl2 = bigInteger3.testBit(n - 1);
        bigInteger3 = bigInteger3.shiftRight(n);
        if (bl2) {
            bigInteger3 = bigInteger3.add(ECConstants.ONE);
        }
        return bl ? bigInteger3.negate() : bigInteger3;
    }
}

