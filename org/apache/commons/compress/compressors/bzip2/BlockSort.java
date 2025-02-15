/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.compressors.bzip2;

import java.util.BitSet;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;

class BlockSort {
    private static final int QSORT_STACK_SIZE = 1000;
    private static final int FALLBACK_QSORT_STACK_SIZE = 100;
    private static final int STACK_SIZE = 1000;
    private int workDone;
    private int workLimit;
    private boolean firstAttempt;
    private final int[] stack_ll = new int[1000];
    private final int[] stack_hh = new int[1000];
    private final int[] stack_dd = new int[1000];
    private final int[] mainSort_runningOrder = new int[256];
    private final int[] mainSort_copy = new int[256];
    private final boolean[] mainSort_bigDone = new boolean[256];
    private final int[] ftab = new int[65537];
    private final char[] quadrant;
    private static final int FALLBACK_QSORT_SMALL_THRESH = 10;
    private int[] eclass;
    private static final int[] INCS = new int[]{1, 4, 13, 40, 121, 364, 1093, 3280, 9841, 29524, 88573, 265720, 797161, 2391484};
    private static final int SMALL_THRESH = 20;
    private static final int DEPTH_THRESH = 10;
    private static final int WORK_FACTOR = 30;
    private static final int SETMASK = 0x200000;
    private static final int CLEARMASK = -2097153;

    BlockSort(BZip2CompressorOutputStream.Data data) {
        this.quadrant = data.sfmap;
    }

    void blockSort(BZip2CompressorOutputStream.Data data, int last) {
        this.workLimit = 30 * last;
        this.workDone = 0;
        this.firstAttempt = true;
        if (last + 1 < 10000) {
            this.fallbackSort(data, last);
        } else {
            this.mainSort(data, last);
            if (this.firstAttempt && this.workDone > this.workLimit) {
                this.fallbackSort(data, last);
            }
        }
        int[] fmap = data.fmap;
        data.origPtr = -1;
        for (int i = 0; i <= last; ++i) {
            if (fmap[i] != 0) continue;
            data.origPtr = i;
            break;
        }
    }

    final void fallbackSort(BZip2CompressorOutputStream.Data data, int last) {
        data.block[0] = data.block[last + 1];
        this.fallbackSort(data.fmap, data.block, last + 1);
        int i = 0;
        while (i < last + 1) {
            int n = i++;
            data.fmap[n] = data.fmap[n] - 1;
        }
        for (i = 0; i < last + 1; ++i) {
            if (data.fmap[i] != -1) continue;
            data.fmap[i] = last;
            break;
        }
    }

    private void fallbackSimpleSort(int[] fmap, int[] eclass, int lo, int hi) {
        int j;
        int ec_tmp;
        int tmp;
        int i;
        if (lo == hi) {
            return;
        }
        if (hi - lo > 3) {
            for (i = hi - 4; i >= lo; --i) {
                tmp = fmap[i];
                ec_tmp = eclass[tmp];
                for (j = i + 4; j <= hi && ec_tmp > eclass[fmap[j]]; j += 4) {
                    fmap[j - 4] = fmap[j];
                }
                fmap[j - 4] = tmp;
            }
        }
        for (i = hi - 1; i >= lo; --i) {
            tmp = fmap[i];
            ec_tmp = eclass[tmp];
            for (j = i + 1; j <= hi && ec_tmp > eclass[fmap[j]]; ++j) {
                fmap[j - 1] = fmap[j];
            }
            fmap[j - 1] = tmp;
        }
    }

    private void fswap(int[] fmap, int zz1, int zz2) {
        int zztmp = fmap[zz1];
        fmap[zz1] = fmap[zz2];
        fmap[zz2] = zztmp;
    }

    private void fvswap(int[] fmap, int yyp1, int yyp2, int yyn) {
        while (yyn > 0) {
            this.fswap(fmap, yyp1, yyp2);
            ++yyp1;
            ++yyp2;
            --yyn;
        }
    }

    private int fmin(int a, int b) {
        return a < b ? a : b;
    }

    private void fpush(int sp, int lz, int hz) {
        this.stack_ll[sp] = lz;
        this.stack_hh[sp] = hz;
    }

    private int[] fpop(int sp) {
        return new int[]{this.stack_ll[sp], this.stack_hh[sp]};
    }

    private void fallbackQSort3(int[] fmap, int[] eclass, int loSt, int hiSt) {
        long r = 0L;
        int sp = 0;
        this.fpush(sp++, loSt, hiSt);
        while (sp > 0) {
            int n;
            int gtHi;
            int ltLo;
            int lo;
            int[] s;
            int hi;
            if ((hi = (s = this.fpop(--sp))[1]) - (lo = s[0]) < 10) {
                this.fallbackSimpleSort(fmap, eclass, lo, hi);
                continue;
            }
            long r3 = (r = (r * 7621L + 1L) % 32768L) % 3L;
            long med = r3 == 0L ? (long)eclass[fmap[lo]] : (r3 == 1L ? (long)eclass[fmap[lo + hi >>> 1]] : (long)eclass[fmap[hi]]);
            int unLo = ltLo = lo;
            int unHi = gtHi = hi;
            while (true) {
                if (unLo <= unHi) {
                    n = eclass[fmap[unLo]] - (int)med;
                    if (n == 0) {
                        this.fswap(fmap, unLo, ltLo);
                        ++ltLo;
                        ++unLo;
                        continue;
                    }
                    if (n <= 0) {
                        ++unLo;
                        continue;
                    }
                }
                while (unLo <= unHi) {
                    n = eclass[fmap[unHi]] - (int)med;
                    if (n == 0) {
                        this.fswap(fmap, unHi, gtHi);
                        --gtHi;
                        --unHi;
                        continue;
                    }
                    if (n < 0) break;
                    --unHi;
                }
                if (unLo > unHi) break;
                this.fswap(fmap, unLo, unHi);
                ++unLo;
                --unHi;
            }
            if (gtHi < ltLo) continue;
            n = this.fmin(ltLo - lo, unLo - ltLo);
            this.fvswap(fmap, lo, unLo - n, n);
            int m = this.fmin(hi - gtHi, gtHi - unHi);
            this.fvswap(fmap, unHi + 1, hi - m + 1, m);
            n = lo + unLo - ltLo - 1;
            m = hi - (gtHi - unHi) + 1;
            if (n - lo > hi - m) {
                this.fpush(sp++, lo, n);
                this.fpush(sp++, m, hi);
                continue;
            }
            this.fpush(sp++, m, hi);
            this.fpush(sp++, lo, n);
        }
    }

    private int[] getEclass() {
        if (this.eclass == null) {
            this.eclass = new int[this.quadrant.length / 2];
        }
        return this.eclass;
    }

    /*
     * Unable to fully structure code
     */
    final void fallbackSort(int[] fmap, byte[] block, int nblock) {
        ftab = new int[257];
        eclass = this.getEclass();
        for (i = 0; i < nblock; ++i) {
            eclass[i] = 0;
        }
        for (i = 0; i < nblock; ++i) {
            v0 = block[i] & 255;
            ftab[v0] = ftab[v0] + 1;
        }
        for (i = 1; i < 257; ++i) {
            v1 = i;
            ftab[v1] = ftab[v1] + ftab[i - 1];
        }
        i = 0;
        while (i < nblock) {
            j = block[i] & 255;
            ftab[j] = k = ftab[j] - 1;
            fmap[k] = i++;
        }
        nBhtab = 64 + nblock;
        bhtab = new BitSet(nBhtab);
        for (i = 0; i < 256; ++i) {
            bhtab.set(ftab[i]);
        }
        for (i = 0; i < 32; ++i) {
            bhtab.set(nblock + 2 * i);
            bhtab.clear(nblock + 2 * i + 1);
        }
        H = 1;
        block6: do {
            j = 0;
            for (i = 0; i < nblock; ++i) {
                if (bhtab.get(i)) {
                    j = i;
                }
                if ((k = fmap[i] - H) < 0) {
                    k += nblock;
                }
                eclass[k] = j;
            }
            nNotDone = 0;
            r = -1;
            block8: while (true) {
                k = r + 1;
                l = (k = bhtab.nextClearBit(k)) - 1;
                if (l >= nblock || (r = (k = bhtab.nextSetBit(k + 1)) - 1) >= nblock) continue block6;
                if (r <= l) continue;
                nNotDone += r - l + 1;
                this.fallbackQSort3(fmap, eclass, l, r);
                cc = -1;
                i = l;
                while (true) {
                    if (i <= r) ** break;
                    continue block8;
                    cc1 = eclass[fmap[i]];
                    if (cc != cc1) {
                        bhtab.set(i);
                        cc = cc1;
                    }
                    ++i;
                }
                break;
            }
        } while ((H *= 2) <= nblock && nNotDone != 0);
    }

    private boolean mainSimpleSort(BZip2CompressorOutputStream.Data dataShadow, int lo, int hi, int d, int lastShadow) {
        int bigN = hi - lo + 1;
        if (bigN < 2) {
            return this.firstAttempt && this.workDone > this.workLimit;
        }
        int hp = 0;
        while (INCS[hp] < bigN) {
            ++hp;
        }
        int[] fmap = dataShadow.fmap;
        char[] quadrant = this.quadrant;
        byte[] block = dataShadow.block;
        int lastPlus1 = lastShadow + 1;
        boolean firstAttemptShadow = this.firstAttempt;
        int workLimitShadow = this.workLimit;
        int workDoneShadow = this.workDone;
        block1: while (--hp >= 0) {
            int h = INCS[hp];
            int mj = lo + h - 1;
            int i = lo + h;
            while (i <= hi) {
                int k = 3;
                while (i <= hi && --k >= 0) {
                    int v = fmap[i];
                    int vd = v + d;
                    int j = i;
                    boolean onceRunned = false;
                    int a = 0;
                    block4: while (true) {
                        int i2;
                        int i1;
                        if (onceRunned) {
                            fmap[j] = a;
                            if ((j -= h) <= mj) {
                                break;
                            }
                        } else {
                            onceRunned = true;
                        }
                        if (block[(i1 = (a = fmap[j - h]) + d) + 1] == block[(i2 = vd) + 1]) {
                            if (block[i1 + 2] == block[i2 + 2]) {
                                if (block[i1 + 3] == block[i2 + 3]) {
                                    if (block[i1 + 4] == block[i2 + 4]) {
                                        if (block[i1 + 5] == block[i2 + 5]) {
                                            if (block[i1 += 6] == block[i2 += 6]) {
                                                int x = lastShadow;
                                                while (x > 0) {
                                                    x -= 4;
                                                    if (block[i1 + 1] == block[i2 + 1]) {
                                                        if (quadrant[i1] == quadrant[i2]) {
                                                            if (block[i1 + 2] == block[i2 + 2]) {
                                                                if (quadrant[i1 + 1] == quadrant[i2 + 1]) {
                                                                    if (block[i1 + 3] == block[i2 + 3]) {
                                                                        if (quadrant[i1 + 2] == quadrant[i2 + 2]) {
                                                                            if (block[i1 + 4] == block[i2 + 4]) {
                                                                                if (quadrant[i1 + 3] == quadrant[i2 + 3]) {
                                                                                    if ((i1 += 4) >= lastPlus1) {
                                                                                        i1 -= lastPlus1;
                                                                                    }
                                                                                    if ((i2 += 4) >= lastPlus1) {
                                                                                        i2 -= lastPlus1;
                                                                                    }
                                                                                    ++workDoneShadow;
                                                                                    continue;
                                                                                }
                                                                                if (quadrant[i1 + 3] <= quadrant[i2 + 3]) break block4;
                                                                                continue block4;
                                                                            }
                                                                            if ((block[i1 + 4] & 0xFF) <= (block[i2 + 4] & 0xFF)) break block4;
                                                                            continue block4;
                                                                        }
                                                                        if (quadrant[i1 + 2] <= quadrant[i2 + 2]) break block4;
                                                                        continue block4;
                                                                    }
                                                                    if ((block[i1 + 3] & 0xFF) <= (block[i2 + 3] & 0xFF)) break block4;
                                                                    continue block4;
                                                                }
                                                                if (quadrant[i1 + 1] <= quadrant[i2 + 1]) break block4;
                                                                continue block4;
                                                            }
                                                            if ((block[i1 + 2] & 0xFF) <= (block[i2 + 2] & 0xFF)) break block4;
                                                            continue block4;
                                                        }
                                                        if (quadrant[i1] <= quadrant[i2]) break block4;
                                                        continue block4;
                                                    }
                                                    if ((block[i1 + 1] & 0xFF) <= (block[i2 + 1] & 0xFF)) break block4;
                                                    continue block4;
                                                }
                                                break;
                                            }
                                            if ((block[i1] & 0xFF) <= (block[i2] & 0xFF)) break;
                                            continue;
                                        }
                                        if ((block[i1 + 5] & 0xFF) <= (block[i2 + 5] & 0xFF)) break;
                                        continue;
                                    }
                                    if ((block[i1 + 4] & 0xFF) <= (block[i2 + 4] & 0xFF)) break;
                                    continue;
                                }
                                if ((block[i1 + 3] & 0xFF) <= (block[i2 + 3] & 0xFF)) break;
                                continue;
                            }
                            if ((block[i1 + 2] & 0xFF) <= (block[i2 + 2] & 0xFF)) break;
                            continue;
                        }
                        if ((block[i1 + 1] & 0xFF) <= (block[i2 + 1] & 0xFF)) break;
                    }
                    fmap[j] = v;
                    ++i;
                }
                if (!firstAttemptShadow || i > hi || workDoneShadow <= workLimitShadow) continue;
                break block1;
            }
        }
        this.workDone = workDoneShadow;
        return firstAttemptShadow && workDoneShadow > workLimitShadow;
    }

    private static void vswap(int[] fmap, int p1, int p2, int n) {
        n += p1;
        while (p1 < n) {
            int t = fmap[p1];
            fmap[p1++] = fmap[p2];
            fmap[p2++] = t;
        }
    }

    private static byte med3(byte a, byte b, byte c) {
        return a < b ? (b < c ? b : (a < c ? c : a)) : (b > c ? b : (a > c ? c : a));
    }

    private void mainQSort3(BZip2CompressorOutputStream.Data dataShadow, int loSt, int hiSt, int dSt, int last) {
        int[] stack_ll = this.stack_ll;
        int[] stack_hh = this.stack_hh;
        int[] stack_dd = this.stack_dd;
        int[] fmap = dataShadow.fmap;
        byte[] block = dataShadow.block;
        stack_ll[0] = loSt;
        stack_hh[0] = hiSt;
        stack_dd[0] = dSt;
        int sp = 1;
        while (--sp >= 0) {
            int n;
            int lo = stack_ll[sp];
            int hi = stack_hh[sp];
            int d = stack_dd[sp];
            if (hi - lo < 20 || d > 10) {
                if (!this.mainSimpleSort(dataShadow, lo, hi, d, last)) continue;
                return;
            }
            int d1 = d + 1;
            int med = BlockSort.med3(block[fmap[lo] + d1], block[fmap[hi] + d1], block[fmap[lo + hi >>> 1] + d1]) & 0xFF;
            int unLo = lo;
            int unHi = hi;
            int ltLo = lo;
            int gtHi = hi;
            while (true) {
                int temp;
                if (unLo <= unHi) {
                    n = (block[fmap[unLo] + d1] & 0xFF) - med;
                    if (n == 0) {
                        temp = fmap[unLo];
                        fmap[unLo++] = fmap[ltLo];
                        fmap[ltLo++] = temp;
                        continue;
                    }
                    if (n < 0) {
                        ++unLo;
                        continue;
                    }
                }
                while (unLo <= unHi) {
                    n = (block[fmap[unHi] + d1] & 0xFF) - med;
                    if (n == 0) {
                        temp = fmap[unHi];
                        fmap[unHi--] = fmap[gtHi];
                        fmap[gtHi--] = temp;
                        continue;
                    }
                    if (n <= 0) break;
                    --unHi;
                }
                if (unLo > unHi) break;
                int temp2 = fmap[unLo];
                fmap[unLo++] = fmap[unHi];
                fmap[unHi--] = temp2;
            }
            if (gtHi < ltLo) {
                stack_ll[sp] = lo;
                stack_hh[sp] = hi;
                stack_dd[sp] = d1;
                ++sp;
                continue;
            }
            n = ltLo - lo < unLo - ltLo ? ltLo - lo : unLo - ltLo;
            BlockSort.vswap(fmap, lo, unLo - n, n);
            int m = hi - gtHi < gtHi - unHi ? hi - gtHi : gtHi - unHi;
            BlockSort.vswap(fmap, unLo, hi - m + 1, m);
            n = lo + unLo - ltLo - 1;
            m = hi - (gtHi - unHi) + 1;
            stack_ll[sp] = lo;
            stack_hh[sp] = n;
            stack_dd[sp] = d;
            stack_ll[++sp] = n + 1;
            stack_hh[sp] = m - 1;
            stack_dd[sp] = d1;
            stack_ll[++sp] = m;
            stack_hh[sp] = hi;
            stack_dd[sp] = d;
            ++sp;
        }
    }

    final void mainSort(BZip2CompressorOutputStream.Data dataShadow, int lastShadow) {
        int j;
        int c2;
        int i;
        int[] runningOrder = this.mainSort_runningOrder;
        int[] copy = this.mainSort_copy;
        boolean[] bigDone = this.mainSort_bigDone;
        int[] ftab = this.ftab;
        byte[] block = dataShadow.block;
        int[] fmap = dataShadow.fmap;
        char[] quadrant = this.quadrant;
        int workLimitShadow = this.workLimit;
        boolean firstAttemptShadow = this.firstAttempt;
        int i2 = 65537;
        while (--i2 >= 0) {
            ftab[i2] = 0;
        }
        for (i2 = 0; i2 < 20; ++i2) {
            block[lastShadow + i2 + 2] = block[i2 % (lastShadow + 1) + 1];
        }
        i2 = lastShadow + 20 + 1;
        while (--i2 >= 0) {
            quadrant[i2] = '\u0000';
        }
        block[0] = block[lastShadow + 1];
        int c1 = block[0] & 0xFF;
        for (i = 0; i <= lastShadow; ++i) {
            c2 = block[i + 1] & 0xFF;
            int n = (c1 << 8) + c2;
            ftab[n] = ftab[n] + 1;
            c1 = c2;
        }
        for (i = 1; i <= 65536; ++i) {
            int n = i;
            ftab[n] = ftab[n] + ftab[i - 1];
        }
        c1 = block[1] & 0xFF;
        i = 0;
        while (i < lastShadow) {
            c2 = block[i + 2] & 0xFF;
            int n = (c1 << 8) + c2;
            int n2 = ftab[n] - 1;
            ftab[n] = n2;
            fmap[n2] = i++;
            c1 = c2;
        }
        int n = ((block[lastShadow + 1] & 0xFF) << 8) + (block[1] & 0xFF);
        int n3 = ftab[n] - 1;
        ftab[n] = n3;
        fmap[n3] = lastShadow;
        i = 256;
        while (--i >= 0) {
            bigDone[i] = false;
            runningOrder[i] = i;
        }
        int h = 364;
        while (h != 1) {
            for (int i3 = h /= 3; i3 <= 255; ++i3) {
                int vv = runningOrder[i3];
                int a = ftab[vv + 1 << 8] - ftab[vv << 8];
                int b = h - 1;
                j = i3;
                int ro = runningOrder[j - h];
                while (ftab[ro + 1 << 8] - ftab[ro << 8] > a) {
                    runningOrder[j] = ro;
                    if ((j -= h) <= b) break;
                    ro = runningOrder[j - h];
                }
                runningOrder[j] = vv;
            }
        }
        for (i = 0; i <= 255; ++i) {
            int j2;
            int ss = runningOrder[i];
            for (j2 = 0; j2 <= 255; ++j2) {
                int sb = (ss << 8) + j2;
                int ftab_sb = ftab[sb];
                if ((ftab_sb & 0x200000) == 0x200000) continue;
                int hi = (ftab[sb + 1] & 0xFFDFFFFF) - 1;
                int lo = ftab_sb & 0xFFDFFFFF;
                if (hi > lo) {
                    this.mainQSort3(dataShadow, lo, hi, 2, lastShadow);
                    if (firstAttemptShadow && this.workDone > workLimitShadow) {
                        return;
                    }
                }
                ftab[sb] = ftab_sb | 0x200000;
            }
            for (j2 = 0; j2 <= 255; ++j2) {
                copy[j2] = ftab[(j2 << 8) + ss] & 0xFFDFFFFF;
            }
            int hj = ftab[ss + 1 << 8] & 0xFFDFFFFF;
            for (j2 = ftab[ss << 8] & 0xFFDFFFFF; j2 < hj; ++j2) {
                int fmap_j = fmap[j2];
                c1 = block[fmap_j] & 0xFF;
                if (bigDone[c1]) continue;
                fmap[copy[c1]] = fmap_j == 0 ? lastShadow : fmap_j - 1;
                int n4 = c1;
                copy[n4] = copy[n4] + 1;
            }
            j2 = 256;
            while (--j2 >= 0) {
                int n5 = (j2 << 8) + ss;
                ftab[n5] = ftab[n5] | 0x200000;
            }
            bigDone[ss] = true;
            if (i >= 255) continue;
            int bbStart = ftab[ss << 8] & 0xFFDFFFFF;
            int bbSize = (ftab[ss + 1 << 8] & 0xFFDFFFFF) - bbStart;
            int shifts = 0;
            while (bbSize >> shifts > 65534) {
                ++shifts;
            }
            for (j = 0; j < bbSize; ++j) {
                char qVal;
                int a2update = fmap[bbStart + j];
                quadrant[a2update] = qVal = (char)(j >> shifts);
                if (a2update >= 20) continue;
                quadrant[a2update + lastShadow + 1] = qVal;
            }
        }
    }
}

