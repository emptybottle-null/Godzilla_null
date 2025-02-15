/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import java.math.BigInteger;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

public class CramerShoupCiphertext {
    BigInteger u1;
    BigInteger u2;
    BigInteger e;
    BigInteger v;

    public CramerShoupCiphertext() {
    }

    public CramerShoupCiphertext(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
        this.u1 = bigInteger;
        this.u2 = bigInteger2;
        this.e = bigInteger3;
        this.v = bigInteger4;
    }

    public CramerShoupCiphertext(byte[] byArray) {
        int n = 0;
        int n2 = Pack.bigEndianToInt(byArray, n);
        byte[] byArray2 = Arrays.copyOfRange(byArray, n += 4, n + n2);
        n += n2;
        this.u1 = new BigInteger(byArray2);
        n2 = Pack.bigEndianToInt(byArray, n);
        byArray2 = Arrays.copyOfRange(byArray, n += 4, n + n2);
        n += n2;
        this.u2 = new BigInteger(byArray2);
        n2 = Pack.bigEndianToInt(byArray, n);
        byArray2 = Arrays.copyOfRange(byArray, n += 4, n + n2);
        n += n2;
        this.e = new BigInteger(byArray2);
        n2 = Pack.bigEndianToInt(byArray, n);
        byArray2 = Arrays.copyOfRange(byArray, n += 4, n + n2);
        n += n2;
        this.v = new BigInteger(byArray2);
    }

    public BigInteger getU1() {
        return this.u1;
    }

    public void setU1(BigInteger bigInteger) {
        this.u1 = bigInteger;
    }

    public BigInteger getU2() {
        return this.u2;
    }

    public void setU2(BigInteger bigInteger) {
        this.u2 = bigInteger;
    }

    public BigInteger getE() {
        return this.e;
    }

    public void setE(BigInteger bigInteger) {
        this.e = bigInteger;
    }

    public BigInteger getV() {
        return this.v;
    }

    public void setV(BigInteger bigInteger) {
        this.v = bigInteger;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("u1: " + this.u1.toString());
        stringBuffer.append("\nu2: " + this.u2.toString());
        stringBuffer.append("\ne: " + this.e.toString());
        stringBuffer.append("\nv: " + this.v.toString());
        return stringBuffer.toString();
    }

    public byte[] toByteArray() {
        byte[] byArray = this.u1.toByteArray();
        int n = byArray.length;
        byte[] byArray2 = this.u2.toByteArray();
        int n2 = byArray2.length;
        byte[] byArray3 = this.e.toByteArray();
        int n3 = byArray3.length;
        byte[] byArray4 = this.v.toByteArray();
        int n4 = byArray4.length;
        int n5 = 0;
        byte[] byArray5 = new byte[n + n2 + n3 + n4 + 16];
        Pack.intToBigEndian(n, byArray5, n5);
        System.arraycopy(byArray, 0, byArray5, n5 += 4, n);
        Pack.intToBigEndian(n2, byArray5, n5 += n);
        System.arraycopy(byArray2, 0, byArray5, n5 += 4, n2);
        Pack.intToBigEndian(n3, byArray5, n5 += n2);
        System.arraycopy(byArray3, 0, byArray5, n5 += 4, n3);
        Pack.intToBigEndian(n4, byArray5, n5 += n3);
        System.arraycopy(byArray4, 0, byArray5, n5 += 4, n4);
        n5 += n4;
        return byArray5;
    }
}

