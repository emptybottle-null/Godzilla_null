/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.signers;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.signers.DSAKCalculator;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;

public class HMacDSAKCalculator
implements DSAKCalculator {
    private static final BigInteger ZERO = BigInteger.valueOf(0L);
    private final HMac hMac;
    private final byte[] K;
    private final byte[] V;
    private BigInteger n;

    public HMacDSAKCalculator(Digest digest) {
        this.hMac = new HMac(digest);
        this.V = new byte[this.hMac.getMacSize()];
        this.K = new byte[this.hMac.getMacSize()];
    }

    public boolean isDeterministic() {
        return true;
    }

    public void init(BigInteger bigInteger, SecureRandom secureRandom) {
        throw new IllegalStateException("Operation not supported");
    }

    public void init(BigInteger bigInteger, BigInteger bigInteger2, byte[] byArray) {
        this.n = bigInteger;
        Arrays.fill(this.V, (byte)1);
        Arrays.fill(this.K, (byte)0);
        byte[] byArray2 = new byte[(bigInteger.bitLength() + 7) / 8];
        byte[] byArray3 = BigIntegers.asUnsignedByteArray(bigInteger2);
        System.arraycopy(byArray3, 0, byArray2, byArray2.length - byArray3.length, byArray3.length);
        byte[] byArray4 = new byte[(bigInteger.bitLength() + 7) / 8];
        BigInteger bigInteger3 = this.bitsToInt(byArray);
        if (bigInteger3.compareTo(bigInteger) >= 0) {
            bigInteger3 = bigInteger3.subtract(bigInteger);
        }
        byte[] byArray5 = BigIntegers.asUnsignedByteArray(bigInteger3);
        System.arraycopy(byArray5, 0, byArray4, byArray4.length - byArray5.length, byArray5.length);
        this.hMac.init(new KeyParameter(this.K));
        this.hMac.update(this.V, 0, this.V.length);
        this.hMac.update((byte)0);
        this.hMac.update(byArray2, 0, byArray2.length);
        this.hMac.update(byArray4, 0, byArray4.length);
        this.hMac.doFinal(this.K, 0);
        this.hMac.init(new KeyParameter(this.K));
        this.hMac.update(this.V, 0, this.V.length);
        this.hMac.doFinal(this.V, 0);
        this.hMac.update(this.V, 0, this.V.length);
        this.hMac.update((byte)1);
        this.hMac.update(byArray2, 0, byArray2.length);
        this.hMac.update(byArray4, 0, byArray4.length);
        this.hMac.doFinal(this.K, 0);
        this.hMac.init(new KeyParameter(this.K));
        this.hMac.update(this.V, 0, this.V.length);
        this.hMac.doFinal(this.V, 0);
    }

    public BigInteger nextK() {
        byte[] byArray = new byte[(this.n.bitLength() + 7) / 8];
        while (true) {
            int n;
            for (int i = 0; i < byArray.length; i += n) {
                this.hMac.update(this.V, 0, this.V.length);
                this.hMac.doFinal(this.V, 0);
                n = Math.min(byArray.length - i, this.V.length);
                System.arraycopy(this.V, 0, byArray, i, n);
            }
            BigInteger bigInteger = this.bitsToInt(byArray);
            if (bigInteger.compareTo(ZERO) > 0 && bigInteger.compareTo(this.n) < 0) {
                return bigInteger;
            }
            this.hMac.update(this.V, 0, this.V.length);
            this.hMac.update((byte)0);
            this.hMac.doFinal(this.K, 0);
            this.hMac.init(new KeyParameter(this.K));
            this.hMac.update(this.V, 0, this.V.length);
            this.hMac.doFinal(this.V, 0);
        }
    }

    private BigInteger bitsToInt(byte[] byArray) {
        BigInteger bigInteger = new BigInteger(1, byArray);
        if (byArray.length * 8 > this.n.bitLength()) {
            bigInteger = bigInteger.shiftRight(byArray.length * 8 - this.n.bitLength());
        }
        return bigInteger;
    }
}

