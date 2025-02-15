/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x9;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;

public class X9IntegerConverter {
    public int getByteLength(ECCurve eCCurve) {
        return (eCCurve.getFieldSize() + 7) / 8;
    }

    public int getByteLength(ECFieldElement eCFieldElement) {
        return (eCFieldElement.getFieldSize() + 7) / 8;
    }

    public byte[] integerToBytes(BigInteger bigInteger, int n) {
        byte[] byArray = bigInteger.toByteArray();
        if (n < byArray.length) {
            byte[] byArray2 = new byte[n];
            System.arraycopy(byArray, byArray.length - byArray2.length, byArray2, 0, byArray2.length);
            return byArray2;
        }
        if (n > byArray.length) {
            byte[] byArray3 = new byte[n];
            System.arraycopy(byArray, 0, byArray3, byArray3.length - byArray.length, byArray.length);
            return byArray3;
        }
        return byArray;
    }
}

