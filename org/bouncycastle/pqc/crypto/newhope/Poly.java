/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.newhope;

import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.pqc.crypto.newhope.ChaCha20;
import org.bouncycastle.pqc.crypto.newhope.NTT;
import org.bouncycastle.pqc.crypto.newhope.Precomp;
import org.bouncycastle.pqc.crypto.newhope.Reduce;
import org.bouncycastle.util.Pack;

class Poly {
    Poly() {
    }

    static void add(short[] sArray, short[] sArray2, short[] sArray3) {
        for (int i = 0; i < 1024; ++i) {
            sArray3[i] = Reduce.barrett((short)(sArray[i] + sArray2[i]));
        }
    }

    static void fromBytes(short[] sArray, byte[] byArray) {
        for (int i = 0; i < 256; ++i) {
            int n = 7 * i;
            int n2 = byArray[n + 0] & 0xFF;
            int n3 = byArray[n + 1] & 0xFF;
            int n4 = byArray[n + 2] & 0xFF;
            int n5 = byArray[n + 3] & 0xFF;
            int n6 = byArray[n + 4] & 0xFF;
            int n7 = byArray[n + 5] & 0xFF;
            int n8 = byArray[n + 6] & 0xFF;
            int n9 = 4 * i;
            sArray[n9 + 0] = (short)(n2 | (n3 & 0x3F) << 8);
            sArray[n9 + 1] = (short)(n3 >>> 6 | n4 << 2 | (n5 & 0xF) << 10);
            sArray[n9 + 2] = (short)(n5 >>> 4 | n6 << 4 | (n7 & 3) << 12);
            sArray[n9 + 3] = (short)(n7 >>> 2 | n8 << 6);
        }
    }

    static void fromNTT(short[] sArray) {
        NTT.bitReverse(sArray);
        NTT.core(sArray, Precomp.OMEGAS_INV_MONTGOMERY);
        NTT.mulCoefficients(sArray, Precomp.PSIS_INV_MONTGOMERY);
    }

    static void getNoise(short[] sArray, byte[] byArray, byte by) {
        byte[] byArray2 = new byte[8];
        byArray2[0] = by;
        byte[] byArray3 = new byte[4096];
        ChaCha20.process(byArray, byArray2, byArray3, 0, byArray3.length);
        for (int i = 0; i < 1024; ++i) {
            int n;
            int n2 = Pack.bigEndianToInt(byArray3, i * 4);
            int n3 = 0;
            for (n = 0; n < 8; ++n) {
                n3 += n2 >> n & 0x1010101;
            }
            n = (n3 >>> 24) + (n3 >>> 0) & 0xFF;
            int n4 = (n3 >>> 16) + (n3 >>> 8) & 0xFF;
            sArray[i] = (short)(n + 12289 - n4);
        }
    }

    static void pointWise(short[] sArray, short[] sArray2, short[] sArray3) {
        for (int i = 0; i < 1024; ++i) {
            int n = sArray[i] & 0xFFFF;
            int n2 = sArray2[i] & 0xFFFF;
            short s = Reduce.montgomery(3186 * n2);
            sArray3[i] = Reduce.montgomery(n * (s & 0xFFFF));
        }
    }

    static void toBytes(byte[] byArray, short[] sArray) {
        for (int i = 0; i < 256; ++i) {
            int n = 4 * i;
            short s = Poly.normalize(sArray[n + 0]);
            short s2 = Poly.normalize(sArray[n + 1]);
            short s3 = Poly.normalize(sArray[n + 2]);
            short s4 = Poly.normalize(sArray[n + 3]);
            int n2 = 7 * i;
            byArray[n2 + 0] = (byte)s;
            byArray[n2 + 1] = (byte)(s >> 8 | s2 << 6);
            byArray[n2 + 2] = (byte)(s2 >> 2);
            byArray[n2 + 3] = (byte)(s2 >> 10 | s3 << 4);
            byArray[n2 + 4] = (byte)(s3 >> 4);
            byArray[n2 + 5] = (byte)(s3 >> 12 | s4 << 2);
            byArray[n2 + 6] = (byte)(s4 >> 6);
        }
    }

    static void toNTT(short[] sArray) {
        NTT.mulCoefficients(sArray, Precomp.PSIS_BITREV_MONTGOMERY);
        NTT.core(sArray, Precomp.OMEGAS_MONTGOMERY);
    }

    static void uniform(short[] sArray, byte[] byArray) {
        SHAKEDigest sHAKEDigest = new SHAKEDigest(128);
        sHAKEDigest.update(byArray, 0, byArray.length);
        int n = 0;
        block0: while (true) {
            byte[] byArray2 = new byte[256];
            sHAKEDigest.doOutput(byArray2, 0, byArray2.length);
            int n2 = 0;
            while (true) {
                if (n2 >= byArray2.length) continue block0;
                int n3 = byArray2[n2] & 0xFF | (byArray2[n2 + 1] & 0xFF) << 8;
                if ((n3 &= 0x3FFF) < 12289) {
                    sArray[n++] = (short)n3;
                    if (n == 1024) {
                        return;
                    }
                }
                n2 += 2;
            }
            break;
        }
    }

    private static short normalize(short s) {
        int n = Reduce.barrett(s);
        int n2 = n - 12289;
        int n3 = n2 >> 31;
        n = n2 ^ (n ^ n2) & n3;
        return (short)n;
    }
}

