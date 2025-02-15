/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.v8dtoa;

import org.mozilla.javascript.v8dtoa.CachedPowers;
import org.mozilla.javascript.v8dtoa.DiyFp;
import org.mozilla.javascript.v8dtoa.DoubleHelper;
import org.mozilla.javascript.v8dtoa.FastDtoaBuilder;

public class FastDtoa {
    static final int kFastDtoaMaximalLength = 17;
    static final int minimal_target_exponent = -60;
    static final int maximal_target_exponent = -32;
    static final int kTen4 = 10000;
    static final int kTen5 = 100000;
    static final int kTen6 = 1000000;
    static final int kTen7 = 10000000;
    static final int kTen8 = 100000000;
    static final int kTen9 = 1000000000;

    static boolean roundWeed(FastDtoaBuilder buffer, long distance_too_high_w, long unsafe_interval, long rest, long ten_kappa, long unit) {
        long small_distance = distance_too_high_w - unit;
        long big_distance = distance_too_high_w + unit;
        while (rest < small_distance && unsafe_interval - rest >= ten_kappa && (rest + ten_kappa < small_distance || small_distance - rest >= rest + ten_kappa - small_distance)) {
            buffer.decreaseLast();
            rest += ten_kappa;
        }
        if (rest < big_distance && unsafe_interval - rest >= ten_kappa && (rest + ten_kappa < big_distance || big_distance - rest > rest + ten_kappa - big_distance)) {
            return false;
        }
        return 2L * unit <= rest && rest <= unsafe_interval - 4L * unit;
    }

    static long biggestPowerTen(int number, int number_bits) {
        int exponent;
        int power;
        switch (number_bits) {
            case 30: 
            case 31: 
            case 32: {
                if (1000000000 <= number) {
                    power = 1000000000;
                    exponent = 9;
                    break;
                }
            }
            case 27: 
            case 28: 
            case 29: {
                if (100000000 <= number) {
                    power = 100000000;
                    exponent = 8;
                    break;
                }
            }
            case 24: 
            case 25: 
            case 26: {
                if (10000000 <= number) {
                    power = 10000000;
                    exponent = 7;
                    break;
                }
            }
            case 20: 
            case 21: 
            case 22: 
            case 23: {
                if (1000000 <= number) {
                    power = 1000000;
                    exponent = 6;
                    break;
                }
            }
            case 17: 
            case 18: 
            case 19: {
                if (100000 <= number) {
                    power = 100000;
                    exponent = 5;
                    break;
                }
            }
            case 14: 
            case 15: 
            case 16: {
                if (10000 <= number) {
                    power = 10000;
                    exponent = 4;
                    break;
                }
            }
            case 10: 
            case 11: 
            case 12: 
            case 13: {
                if (1000 <= number) {
                    power = 1000;
                    exponent = 3;
                    break;
                }
            }
            case 7: 
            case 8: 
            case 9: {
                if (100 <= number) {
                    power = 100;
                    exponent = 2;
                    break;
                }
            }
            case 4: 
            case 5: 
            case 6: {
                if (10 <= number) {
                    power = 10;
                    exponent = 1;
                    break;
                }
            }
            case 1: 
            case 2: 
            case 3: {
                if (1 <= number) {
                    power = 1;
                    exponent = 0;
                    break;
                }
            }
            case 0: {
                power = 0;
                exponent = -1;
                break;
            }
            default: {
                power = 0;
                exponent = 0;
            }
        }
        return (long)power << 32 | 0xFFFFFFFFL & (long)exponent;
    }

    private static boolean uint64_lte(long a, long b) {
        return a == b || a < b ^ a < 0L ^ b < 0L;
    }

    static boolean digitGen(DiyFp low, DiyFp w, DiyFp high, FastDtoaBuilder buffer, int mk) {
        int digit;
        assert (low.e() == w.e() && w.e() == high.e());
        assert (FastDtoa.uint64_lte(low.f() + 1L, high.f() - 1L));
        assert (-60 <= w.e() && w.e() <= -32);
        long unit = 1L;
        DiyFp too_low = new DiyFp(low.f() - unit, low.e());
        DiyFp too_high = new DiyFp(high.f() + unit, high.e());
        DiyFp unsafe_interval = DiyFp.minus(too_high, too_low);
        DiyFp one = new DiyFp(1L << -w.e(), w.e());
        int integrals = (int)(too_high.f() >>> -one.e() & 0xFFFFFFFFL);
        long fractionals = too_high.f() & one.f() - 1L;
        long result = FastDtoa.biggestPowerTen(integrals, 64 - -one.e());
        int divider = (int)(result >>> 32 & 0xFFFFFFFFL);
        int divider_exponent = (int)(result & 0xFFFFFFFFL);
        int kappa = divider_exponent + 1;
        while (kappa > 0) {
            digit = integrals / divider;
            buffer.append((char)(48 + digit));
            --kappa;
            long rest = ((long)(integrals %= divider) << -one.e()) + fractionals;
            if (rest < unsafe_interval.f()) {
                buffer.point = buffer.end - mk + kappa;
                return FastDtoa.roundWeed(buffer, DiyFp.minus(too_high, w).f(), unsafe_interval.f(), rest, (long)divider << -one.e(), unit);
            }
            divider /= 10;
        }
        do {
            fractionals *= 5L;
            unit *= 5L;
            unsafe_interval.setF(unsafe_interval.f() * 5L);
            unsafe_interval.setE(unsafe_interval.e() + 1);
            one.setF(one.f() >>> 1);
            one.setE(one.e() + 1);
            digit = (int)(fractionals >>> -one.e() & 0xFFFFFFFFL);
            buffer.append((char)(48 + digit));
            --kappa;
        } while ((fractionals &= one.f() - 1L) >= unsafe_interval.f());
        buffer.point = buffer.end - mk + kappa;
        return FastDtoa.roundWeed(buffer, DiyFp.minus(too_high, w).f() * unit, unsafe_interval.f(), fractionals, one.f(), unit);
    }

    static boolean grisu3(double v, FastDtoaBuilder buffer) {
        long bits = Double.doubleToLongBits(v);
        DiyFp w = DoubleHelper.asNormalizedDiyFp(bits);
        DiyFp boundary_minus = new DiyFp();
        DiyFp boundary_plus = new DiyFp();
        DoubleHelper.normalizedBoundaries(bits, boundary_minus, boundary_plus);
        assert (boundary_plus.e() == w.e());
        DiyFp ten_mk = new DiyFp();
        int mk = CachedPowers.getCachedPower(w.e() + 64, -60, -32, ten_mk);
        assert (-60 <= w.e() + ten_mk.e() + 64 && -32 >= w.e() + ten_mk.e() + 64);
        DiyFp scaled_w = DiyFp.times(w, ten_mk);
        assert (scaled_w.e() == boundary_plus.e() + ten_mk.e() + 64);
        DiyFp scaled_boundary_minus = DiyFp.times(boundary_minus, ten_mk);
        DiyFp scaled_boundary_plus = DiyFp.times(boundary_plus, ten_mk);
        return FastDtoa.digitGen(scaled_boundary_minus, scaled_w, scaled_boundary_plus, buffer, mk);
    }

    public static boolean dtoa(double v, FastDtoaBuilder buffer) {
        assert (v > 0.0);
        assert (!Double.isNaN(v));
        assert (!Double.isInfinite(v));
        return FastDtoa.grisu3(v, buffer);
    }

    public static String numberToString(double v) {
        FastDtoaBuilder buffer = new FastDtoaBuilder();
        return FastDtoa.numberToString(v, buffer) ? buffer.format() : null;
    }

    public static boolean numberToString(double v, FastDtoaBuilder buffer) {
        buffer.reset();
        if (v < 0.0) {
            buffer.append('-');
            v = -v;
        }
        return FastDtoa.dtoa(v, buffer);
    }
}

