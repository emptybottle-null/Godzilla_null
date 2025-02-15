/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.generators;

import java.math.BigInteger;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.MacDerivationFunction;
import org.bouncycastle.crypto.params.KDFCounterParameters;
import org.bouncycastle.crypto.params.KeyParameter;

public class KDFCounterBytesGenerator
implements MacDerivationFunction {
    private static final BigInteger INTEGER_MAX = BigInteger.valueOf(Integer.MAX_VALUE);
    private static final BigInteger TWO = BigInteger.valueOf(2L);
    private final Mac prf;
    private final int h;
    private byte[] fixedInputDataCtrPrefix;
    private byte[] fixedInputData_afterCtr;
    private int maxSizeExcl;
    private byte[] ios;
    private int generatedBytes;
    private byte[] k;

    public KDFCounterBytesGenerator(Mac mac) {
        this.prf = mac;
        this.h = mac.getMacSize();
        this.k = new byte[this.h];
    }

    public void init(DerivationParameters derivationParameters) {
        if (!(derivationParameters instanceof KDFCounterParameters)) {
            throw new IllegalArgumentException("Wrong type of arguments given");
        }
        KDFCounterParameters kDFCounterParameters = (KDFCounterParameters)derivationParameters;
        this.prf.init(new KeyParameter(kDFCounterParameters.getKI()));
        this.fixedInputDataCtrPrefix = kDFCounterParameters.getFixedInputDataCounterPrefix();
        this.fixedInputData_afterCtr = kDFCounterParameters.getFixedInputDataCounterSuffix();
        int n = kDFCounterParameters.getR();
        this.ios = new byte[n / 8];
        BigInteger bigInteger = TWO.pow(n).multiply(BigInteger.valueOf(this.h));
        this.maxSizeExcl = bigInteger.compareTo(INTEGER_MAX) == 1 ? Integer.MAX_VALUE : bigInteger.intValue();
        this.generatedBytes = 0;
    }

    public Mac getMac() {
        return this.prf;
    }

    public int generateBytes(byte[] byArray, int n, int n2) throws DataLengthException, IllegalArgumentException {
        int n3 = this.generatedBytes + n2;
        if (n3 < 0 || n3 >= this.maxSizeExcl) {
            throw new DataLengthException("Current KDFCTR may only be used for " + this.maxSizeExcl + " bytes");
        }
        if (this.generatedBytes % this.h == 0) {
            this.generateNext();
        }
        int n4 = n2;
        int n5 = this.generatedBytes % this.h;
        int n6 = this.h - this.generatedBytes % this.h;
        int n7 = Math.min(n6, n4);
        System.arraycopy(this.k, n5, byArray, n, n7);
        this.generatedBytes += n7;
        n4 -= n7;
        n += n7;
        while (n4 > 0) {
            this.generateNext();
            n7 = Math.min(this.h, n4);
            System.arraycopy(this.k, 0, byArray, n, n7);
            this.generatedBytes += n7;
            n4 -= n7;
            n += n7;
        }
        return n2;
    }

    private void generateNext() {
        int n = this.generatedBytes / this.h + 1;
        switch (this.ios.length) {
            case 4: {
                this.ios[0] = (byte)(n >>> 24);
            }
            case 3: {
                this.ios[this.ios.length - 3] = (byte)(n >>> 16);
            }
            case 2: {
                this.ios[this.ios.length - 2] = (byte)(n >>> 8);
            }
            case 1: {
                this.ios[this.ios.length - 1] = (byte)n;
                break;
            }
            default: {
                throw new IllegalStateException("Unsupported size of counter i");
            }
        }
        this.prf.update(this.fixedInputDataCtrPrefix, 0, this.fixedInputDataCtrPrefix.length);
        this.prf.update(this.ios, 0, this.ios.length);
        this.prf.update(this.fixedInputData_afterCtr, 0, this.fixedInputData_afterCtr.length);
        this.prf.doFinal(this.k, 0);
    }
}

