/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.random;

import java.io.Serializable;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.FastMath;

public abstract class BitsStreamGenerator
implements RandomGenerator,
Serializable {
    private static final long serialVersionUID = 20130104L;
    private double nextGaussian = Double.NaN;

    public abstract void setSeed(int var1);

    public abstract void setSeed(int[] var1);

    public abstract void setSeed(long var1);

    protected abstract int next(int var1);

    public boolean nextBoolean() {
        return this.next(1) != 0;
    }

    public double nextDouble() {
        long high = (long)this.next(26) << 26;
        int low = this.next(26);
        return (double)(high | (long)low) * 2.220446049250313E-16;
    }

    public float nextFloat() {
        return (float)this.next(23) * 1.1920929E-7f;
    }

    public double nextGaussian() {
        double random;
        if (Double.isNaN(this.nextGaussian)) {
            double x = this.nextDouble();
            double y = this.nextDouble();
            double alpha = Math.PI * 2 * x;
            double r = FastMath.sqrt(-2.0 * FastMath.log(y));
            random = r * FastMath.cos(alpha);
            this.nextGaussian = r * FastMath.sin(alpha);
        } else {
            random = this.nextGaussian;
            this.nextGaussian = Double.NaN;
        }
        return random;
    }

    public int nextInt() {
        return this.next(32);
    }

    public int nextInt(int n) throws IllegalArgumentException {
        if (n > 0) {
            int val;
            int bits;
            if ((n & -n) == n) {
                return (int)((long)n * (long)this.next(31) >> 31);
            }
            while ((bits = this.next(31)) - (val = bits % n) + (n - 1) < 0) {
            }
            return val;
        }
        throw new NotStrictlyPositiveException(n);
    }

    public long nextLong() {
        long high = (long)this.next(32) << 32;
        long low = (long)this.next(32) & 0xFFFFFFFFL;
        return high | low;
    }

    public long nextLong(long n) throws IllegalArgumentException {
        if (n > 0L) {
            long bits;
            long val;
            do {
                bits = (long)this.next(31) << 32;
            } while ((bits |= (long)this.next(32) & 0xFFFFFFFFL) - (val = bits % n) + (n - 1L) < 0L);
            return val;
        }
        throw new NotStrictlyPositiveException(n);
    }

    public void clear() {
        this.nextGaussian = Double.NaN;
    }

    public void nextBytes(byte[] bytes) {
        this.nextBytesFill(bytes, 0, bytes.length);
    }

    public void nextBytes(byte[] bytes, int start, int len) {
        if (start < 0 || start >= bytes.length) {
            throw new OutOfRangeException(start, (Number)0, bytes.length);
        }
        if (len < 0 || len > bytes.length - start) {
            throw new OutOfRangeException(len, (Number)0, bytes.length - start);
        }
        this.nextBytesFill(bytes, start, len);
    }

    private void nextBytesFill(byte[] bytes, int start, int len) {
        int index = start;
        int indexLoopLimit = index + (len & 0x7FFFFFFC);
        while (index < indexLoopLimit) {
            int random = this.next(32);
            bytes[index++] = (byte)random;
            bytes[index++] = (byte)(random >>> 8);
            bytes[index++] = (byte)(random >>> 16);
            bytes[index++] = (byte)(random >>> 24);
        }
        int indexLimit = start + len;
        if (index < indexLimit) {
            int random = this.next(32);
            while (true) {
                bytes[index++] = (byte)random;
                if (index >= indexLimit) break;
                random >>>= 8;
            }
        }
    }
}

