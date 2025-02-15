/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.partitioning.utilities;

import java.util.Arrays;
import org.apache.commons.math3.util.FastMath;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public class OrderedTuple
implements Comparable<OrderedTuple> {
    private static final long SIGN_MASK = Long.MIN_VALUE;
    private static final long EXPONENT_MASK = 0x7FF0000000000000L;
    private static final long MANTISSA_MASK = 0xFFFFFFFFFFFFFL;
    private static final long IMPLICIT_ONE = 0x10000000000000L;
    private double[] components;
    private int offset;
    private int lsb;
    private long[] encoding;
    private boolean posInf;
    private boolean negInf;
    private boolean nan;

    public OrderedTuple(double ... components) {
        this.components = (double[])components.clone();
        int msb = Integer.MIN_VALUE;
        this.lsb = Integer.MAX_VALUE;
        this.posInf = false;
        this.negInf = false;
        this.nan = false;
        for (int i = 0; i < components.length; ++i) {
            if (Double.isInfinite(components[i])) {
                if (components[i] < 0.0) {
                    this.negInf = true;
                    continue;
                }
                this.posInf = true;
                continue;
            }
            if (Double.isNaN(components[i])) {
                this.nan = true;
                continue;
            }
            long b = Double.doubleToLongBits(components[i]);
            long m = OrderedTuple.mantissa(b);
            if (m == 0L) continue;
            int e = OrderedTuple.exponent(b);
            msb = FastMath.max(msb, e + OrderedTuple.computeMSB(m));
            this.lsb = FastMath.min(this.lsb, e + OrderedTuple.computeLSB(m));
        }
        if (this.posInf && this.negInf) {
            this.posInf = false;
            this.negInf = false;
            this.nan = true;
        }
        if (this.lsb <= msb) {
            this.encode(msb + 16);
        } else {
            this.encoding = new long[]{0L};
        }
    }

    private void encode(int minOffset) {
        this.offset = minOffset + 31;
        this.offset -= this.offset % 32;
        if (this.encoding != null && this.encoding.length == 1 && this.encoding[0] == 0L) {
            return;
        }
        int neededBits = this.offset + 1 - this.lsb;
        int neededLongs = (neededBits + 62) / 63;
        this.encoding = new long[this.components.length * neededLongs];
        int eIndex = 0;
        int shift = 62;
        long word = 0L;
        int k = this.offset;
        while (eIndex < this.encoding.length) {
            for (int vIndex = 0; vIndex < this.components.length; ++vIndex) {
                if (this.getBit(vIndex, k) != 0) {
                    word |= 1L << shift;
                }
                if (shift-- != 0) continue;
                this.encoding[eIndex++] = word;
                word = 0L;
                shift = 62;
            }
            --k;
        }
    }

    @Override
    public int compareTo(OrderedTuple ot) {
        if (this.components.length == ot.components.length) {
            if (this.nan) {
                return 1;
            }
            if (ot.nan) {
                return -1;
            }
            if (this.negInf || ot.posInf) {
                return -1;
            }
            if (this.posInf || ot.negInf) {
                return 1;
            }
            if (this.offset < ot.offset) {
                this.encode(ot.offset);
            } else if (this.offset > ot.offset) {
                ot.encode(this.offset);
            }
            int limit = FastMath.min(this.encoding.length, ot.encoding.length);
            for (int i = 0; i < limit; ++i) {
                if (this.encoding[i] < ot.encoding[i]) {
                    return -1;
                }
                if (this.encoding[i] <= ot.encoding[i]) continue;
                return 1;
            }
            if (this.encoding.length < ot.encoding.length) {
                return -1;
            }
            if (this.encoding.length > ot.encoding.length) {
                return 1;
            }
            return 0;
        }
        return this.components.length - ot.components.length;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof OrderedTuple) {
            return this.compareTo((OrderedTuple)other) == 0;
        }
        return false;
    }

    public int hashCode() {
        int multiplier = 37;
        int trueHash = 97;
        int falseHash = 71;
        int hash = Arrays.hashCode(this.components);
        hash = hash * 37 + this.offset;
        hash = hash * 37 + this.lsb;
        hash = hash * 37 + (this.posInf ? 97 : 71);
        hash = hash * 37 + (this.negInf ? 97 : 71);
        hash = hash * 37 + (this.nan ? 97 : 71);
        return hash;
    }

    public double[] getComponents() {
        return (double[])this.components.clone();
    }

    private static long sign(long bits) {
        return bits & Long.MIN_VALUE;
    }

    private static int exponent(long bits) {
        return (int)((bits & 0x7FF0000000000000L) >> 52) - 1075;
    }

    private static long mantissa(long bits) {
        return (bits & 0x7FF0000000000000L) == 0L ? (bits & 0xFFFFFFFFFFFFFL) << 1 : 0x10000000000000L | bits & 0xFFFFFFFFFFFFFL;
    }

    private static int computeMSB(long l) {
        long ll = l;
        long mask = 0xFFFFFFFFL;
        int scale = 32;
        int msb = 0;
        while (scale != 0) {
            if ((ll & mask) != ll) {
                msb |= scale;
                ll >>= scale;
            }
            mask >>= (scale >>= 1);
        }
        return msb;
    }

    private static int computeLSB(long l) {
        long ll = l;
        long mask = -4294967296L;
        int scale = 32;
        int lsb = 0;
        while (scale != 0) {
            if ((ll & mask) == ll) {
                lsb |= scale;
                ll >>= scale;
            }
            mask >>= (scale >>= 1);
        }
        return lsb;
    }

    private int getBit(int i, int k) {
        long bits = Double.doubleToLongBits(this.components[i]);
        int e = OrderedTuple.exponent(bits);
        if (k < e || k > this.offset) {
            return 0;
        }
        if (k == this.offset) {
            return OrderedTuple.sign(bits) == 0L ? 1 : 0;
        }
        if (k > e + 52) {
            return OrderedTuple.sign(bits) == 0L ? 0 : 1;
        }
        long m = OrderedTuple.sign(bits) == 0L ? OrderedTuple.mantissa(bits) : -OrderedTuple.mantissa(bits);
        return (int)(m >> k - e & 1L);
    }
}

