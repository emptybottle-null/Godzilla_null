/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.newhope;

import java.security.SecureRandom;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.pqc.crypto.newhope.ErrorCorrection;
import org.bouncycastle.pqc.crypto.newhope.Poly;

class NewHope {
    private static final boolean STATISTICAL_TEST = false;
    public static final int AGREEMENT_SIZE = 32;
    public static final int POLY_SIZE = 1024;
    public static final int SENDA_BYTES = 1824;
    public static final int SENDB_BYTES = 2048;

    NewHope() {
    }

    public static void keygen(SecureRandom secureRandom, byte[] byArray, short[] sArray) {
        byte[] byArray2 = new byte[32];
        secureRandom.nextBytes(byArray2);
        short[] sArray2 = new short[1024];
        NewHope.generateA(sArray2, byArray2);
        byte[] byArray3 = new byte[32];
        secureRandom.nextBytes(byArray3);
        Poly.getNoise(sArray, byArray3, (byte)0);
        Poly.toNTT(sArray);
        short[] sArray3 = new short[1024];
        Poly.getNoise(sArray3, byArray3, (byte)1);
        Poly.toNTT(sArray3);
        short[] sArray4 = new short[1024];
        Poly.pointWise(sArray2, sArray, sArray4);
        short[] sArray5 = new short[1024];
        Poly.add(sArray4, sArray3, sArray5);
        NewHope.encodeA(byArray, sArray5, byArray2);
    }

    public static void sharedB(SecureRandom secureRandom, byte[] byArray, byte[] byArray2, byte[] byArray3) {
        short[] sArray = new short[1024];
        byte[] byArray4 = new byte[32];
        NewHope.decodeA(sArray, byArray4, byArray3);
        short[] sArray2 = new short[1024];
        NewHope.generateA(sArray2, byArray4);
        byte[] byArray5 = new byte[32];
        secureRandom.nextBytes(byArray5);
        short[] sArray3 = new short[1024];
        Poly.getNoise(sArray3, byArray5, (byte)0);
        Poly.toNTT(sArray3);
        short[] sArray4 = new short[1024];
        Poly.getNoise(sArray4, byArray5, (byte)1);
        Poly.toNTT(sArray4);
        short[] sArray5 = new short[1024];
        Poly.pointWise(sArray2, sArray3, sArray5);
        Poly.add(sArray5, sArray4, sArray5);
        short[] sArray6 = new short[1024];
        Poly.pointWise(sArray, sArray3, sArray6);
        Poly.fromNTT(sArray6);
        short[] sArray7 = new short[1024];
        Poly.getNoise(sArray7, byArray5, (byte)2);
        Poly.add(sArray6, sArray7, sArray6);
        short[] sArray8 = new short[1024];
        ErrorCorrection.helpRec(sArray8, sArray6, byArray5, (byte)3);
        NewHope.encodeB(byArray2, sArray5, sArray8);
        ErrorCorrection.rec(byArray, sArray6, sArray8);
        NewHope.sha3(byArray);
    }

    public static void sharedA(byte[] byArray, short[] sArray, byte[] byArray2) {
        short[] sArray2 = new short[1024];
        short[] sArray3 = new short[1024];
        NewHope.decodeB(sArray2, sArray3, byArray2);
        short[] sArray4 = new short[1024];
        Poly.pointWise(sArray, sArray2, sArray4);
        Poly.fromNTT(sArray4);
        ErrorCorrection.rec(byArray, sArray4, sArray3);
        NewHope.sha3(byArray);
    }

    static void decodeA(short[] sArray, byte[] byArray, byte[] byArray2) {
        Poly.fromBytes(sArray, byArray2);
        System.arraycopy(byArray2, 1792, byArray, 0, 32);
    }

    static void decodeB(short[] sArray, short[] sArray2, byte[] byArray) {
        Poly.fromBytes(sArray, byArray);
        for (int i = 0; i < 256; ++i) {
            int n = 4 * i;
            int n2 = byArray[1792 + i] & 0xFF;
            sArray2[n + 0] = (short)(n2 & 3);
            sArray2[n + 1] = (short)(n2 >>> 2 & 3);
            sArray2[n + 2] = (short)(n2 >>> 4 & 3);
            sArray2[n + 3] = (short)(n2 >>> 6);
        }
    }

    static void encodeA(byte[] byArray, short[] sArray, byte[] byArray2) {
        Poly.toBytes(byArray, sArray);
        System.arraycopy(byArray2, 0, byArray, 1792, 32);
    }

    static void encodeB(byte[] byArray, short[] sArray, short[] sArray2) {
        Poly.toBytes(byArray, sArray);
        for (int i = 0; i < 256; ++i) {
            int n = 4 * i;
            byArray[1792 + i] = (byte)(sArray2[n] | sArray2[n + 1] << 2 | sArray2[n + 2] << 4 | sArray2[n + 3] << 6);
        }
    }

    static void generateA(short[] sArray, byte[] byArray) {
        Poly.uniform(sArray, byArray);
    }

    static void sha3(byte[] byArray) {
        SHA3Digest sHA3Digest = new SHA3Digest(256);
        sHA3Digest.update(byArray, 0, 32);
        sHA3Digest.doFinal(byArray, 0);
    }
}

