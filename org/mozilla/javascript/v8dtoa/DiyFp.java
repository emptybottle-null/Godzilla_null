/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.v8dtoa;

class DiyFp {
    private long f;
    private int e;
    static final int kSignificandSize = 64;
    static final long kUint64MSB = Long.MIN_VALUE;

    DiyFp() {
        this.f = 0L;
        this.e = 0;
    }

    DiyFp(long f, int e) {
        this.f = f;
        this.e = e;
    }

    private static boolean uint64_gte(long a, long b) {
        return a == b || a > b ^ a < 0L ^ b < 0L;
    }

    void subtract(DiyFp other) {
        assert (this.e == other.e);
        assert (DiyFp.uint64_gte(this.f, other.f));
        this.f -= other.f;
    }

    static DiyFp minus(DiyFp a, DiyFp b) {
        DiyFp result = new DiyFp(a.f, a.e);
        result.subtract(b);
        return result;
    }

    void multiply(DiyFp other) {
        long kM32 = 0xFFFFFFFFL;
        long a = this.f >>> 32;
        long b = this.f & 0xFFFFFFFFL;
        long c = other.f >>> 32;
        long d = other.f & 0xFFFFFFFFL;
        long ac = a * c;
        long bc = b * c;
        long ad = a * d;
        long bd = b * d;
        long tmp = (bd >>> 32) + (ad & 0xFFFFFFFFL) + (bc & 0xFFFFFFFFL);
        long result_f = ac + (ad >>> 32) + (bc >>> 32) + ((tmp += 0x80000000L) >>> 32);
        this.e += other.e + 64;
        this.f = result_f;
    }

    static DiyFp times(DiyFp a, DiyFp b) {
        DiyFp result = new DiyFp(a.f, a.e);
        result.multiply(b);
        return result;
    }

    void normalize() {
        assert (this.f != 0L);
        long f = this.f;
        int e = this.e;
        long k10MSBits = -18014398509481984L;
        while ((f & 0xFFC0000000000000L) == 0L) {
            f <<= 10;
            e -= 10;
        }
        while ((f & Long.MIN_VALUE) == 0L) {
            f <<= 1;
            --e;
        }
        this.f = f;
        this.e = e;
    }

    static DiyFp normalize(DiyFp a) {
        DiyFp result = new DiyFp(a.f, a.e);
        result.normalize();
        return result;
    }

    long f() {
        return this.f;
    }

    int e() {
        return this.e;
    }

    void setF(long new_value) {
        this.f = new_value;
    }

    void setE(int new_value) {
        this.e = new_value;
    }

    public String toString() {
        return "[DiyFp f:" + this.f + ", e:" + this.e + "]";
    }
}

