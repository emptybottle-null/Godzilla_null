/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.SkippingStreamCipher;
import org.bouncycastle.crypto.StreamBlockCipher;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

public class SICBlockCipher
extends StreamBlockCipher
implements SkippingStreamCipher {
    private final BlockCipher cipher;
    private final int blockSize;
    private byte[] IV;
    private byte[] counter;
    private byte[] counterOut;
    private int byteCount;

    public SICBlockCipher(BlockCipher blockCipher) {
        super(blockCipher);
        this.cipher = blockCipher;
        this.blockSize = this.cipher.getBlockSize();
        this.IV = new byte[this.blockSize];
        this.counter = new byte[this.blockSize];
        this.counterOut = new byte[this.blockSize];
        this.byteCount = 0;
    }

    public void init(boolean bl, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (cipherParameters instanceof ParametersWithIV) {
            int n;
            ParametersWithIV parametersWithIV = (ParametersWithIV)cipherParameters;
            this.IV = Arrays.clone(parametersWithIV.getIV());
            if (this.blockSize < this.IV.length) {
                throw new IllegalArgumentException("CTR/SIC mode requires IV no greater than: " + this.blockSize + " bytes.");
            }
            int n2 = n = 8 > this.blockSize / 2 ? this.blockSize / 2 : 8;
            if (this.blockSize - this.IV.length > n) {
                throw new IllegalArgumentException("CTR/SIC mode requires IV of at least: " + (this.blockSize - n) + " bytes.");
            }
            if (parametersWithIV.getParameters() != null) {
                this.cipher.init(true, parametersWithIV.getParameters());
            }
        } else {
            throw new IllegalArgumentException("CTR/SIC mode requires ParametersWithIV");
        }
        this.reset();
    }

    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName() + "/SIC";
    }

    public int getBlockSize() {
        return this.cipher.getBlockSize();
    }

    public int processBlock(byte[] byArray, int n, byte[] byArray2, int n2) throws DataLengthException, IllegalStateException {
        this.processBytes(byArray, n, this.blockSize, byArray2, n2);
        return this.blockSize;
    }

    protected byte calculateByte(byte by) throws DataLengthException, IllegalStateException {
        if (this.byteCount == 0) {
            this.cipher.processBlock(this.counter, 0, this.counterOut, 0);
            return (byte)(this.counterOut[this.byteCount++] ^ by);
        }
        byte by2 = (byte)(this.counterOut[this.byteCount++] ^ by);
        if (this.byteCount == this.counter.length) {
            this.byteCount = 0;
            this.incrementCounterAt(0);
            this.checkCounter();
        }
        return by2;
    }

    private void checkCounter() {
        if (this.IV.length < this.blockSize) {
            for (int i = 0; i != this.IV.length; ++i) {
                if (this.counter[i] == this.IV[i]) continue;
                throw new IllegalStateException("Counter in CTR/SIC mode out of range.");
            }
        }
    }

    private void incrementCounterAt(int n) {
        int n2 = this.counter.length - n;
        while (--n2 >= 0) {
            int n3 = n2;
            this.counter[n3] = (byte)(this.counter[n3] + 1);
            if (this.counter[n3] == 0) continue;
            break;
        }
    }

    private void incrementCounter(int n) {
        byte by = this.counter[this.counter.length - 1];
        int n2 = this.counter.length - 1;
        this.counter[n2] = (byte)(this.counter[n2] + n);
        if (by != 0 && this.counter[this.counter.length - 1] < by) {
            this.incrementCounterAt(1);
        }
    }

    private void decrementCounterAt(int n) {
        int n2 = this.counter.length - n;
        while (--n2 >= 0) {
            int n3 = n2;
            this.counter[n3] = (byte)(this.counter[n3] - 1);
            if (this.counter[n3] == -1) continue;
            return;
        }
    }

    private void adjustCounter(long l) {
        if (l >= 0L) {
            long l2 = (l + (long)this.byteCount) / (long)this.blockSize;
            long l3 = l2;
            if (l3 > 255L) {
                for (int i = 5; i >= 1; --i) {
                    long l4 = 1L << 8 * i;
                    while (l3 >= l4) {
                        this.incrementCounterAt(i);
                        l3 -= l4;
                    }
                }
            }
            this.incrementCounter((int)l3);
            this.byteCount = (int)(l + (long)this.byteCount - (long)this.blockSize * l2);
        } else {
            long l5 = (-l - (long)this.byteCount) / (long)this.blockSize;
            long l6 = l5;
            if (l6 > 255L) {
                for (int i = 5; i >= 1; --i) {
                    long l7 = 1L << 8 * i;
                    while (l6 > l7) {
                        this.decrementCounterAt(i);
                        l6 -= l7;
                    }
                }
            }
            for (long i = 0L; i != l6; ++i) {
                this.decrementCounterAt(0);
            }
            int n = (int)((long)this.byteCount + l + (long)this.blockSize * l5);
            if (n >= 0) {
                this.byteCount = 0;
            } else {
                this.decrementCounterAt(0);
                this.byteCount = this.blockSize + n;
            }
        }
    }

    public void reset() {
        Arrays.fill(this.counter, (byte)0);
        System.arraycopy(this.IV, 0, this.counter, 0, this.IV.length);
        this.cipher.reset();
        this.byteCount = 0;
    }

    public long skip(long l) {
        this.adjustCounter(l);
        this.checkCounter();
        this.cipher.processBlock(this.counter, 0, this.counterOut, 0);
        return l;
    }

    public long seekTo(long l) {
        this.reset();
        return this.skip(l);
    }

    public long getPosition() {
        byte[] byArray = new byte[this.counter.length];
        System.arraycopy(this.counter, 0, byArray, 0, byArray.length);
        for (int i = byArray.length - 1; i >= 1; --i) {
            int n = i < this.IV.length ? (byArray[i] & 0xFF) - (this.IV[i] & 0xFF) : byArray[i] & 0xFF;
            if (n < 0) {
                int n2 = i - 1;
                byArray[n2] = (byte)(byArray[n2] - 1);
                n += 256;
            }
            byArray[i] = (byte)n;
        }
        return Pack.bigEndianToLong(byArray, byArray.length - 8) * (long)this.blockSize + (long)this.byteCount;
    }
}

