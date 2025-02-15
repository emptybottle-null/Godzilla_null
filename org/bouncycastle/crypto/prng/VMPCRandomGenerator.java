/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.prng;

import org.bouncycastle.crypto.prng.RandomGenerator;
import org.bouncycastle.util.Pack;

public class VMPCRandomGenerator
implements RandomGenerator {
    private byte n = 0;
    private byte[] P = new byte[]{-69, 44, 98, 127, -75, -86, -44, 13, -127, -2, -78, -126, -53, -96, -95, 8, 24, 113, 86, -24, 73, 2, 16, -60, -34, 53, -91, -20, -128, 18, -72, 105, -38, 47, 117, -52, -94, 9, 54, 3, 97, 45, -3, -32, -35, 5, 67, -112, -83, -56, -31, -81, 87, -101, 76, -40, 81, -82, 80, -123, 60, 10, -28, -13, -100, 38, 35, 83, -55, -125, -105, 70, -79, -103, 100, 49, 119, -43, 29, -42, 120, -67, 94, -80, -118, 34, 56, -8, 104, 43, 42, -59, -45, -9, -68, 111, -33, 4, -27, -107, 62, 37, -122, -90, 11, -113, -15, 36, 14, -41, 64, -77, -49, 126, 6, 21, -102, 77, 28, -93, -37, 50, -110, 88, 17, 39, -12, 89, -48, 78, 106, 23, 91, -84, -1, 7, -64, 101, 121, -4, -57, -51, 118, 66, 93, -25, 58, 52, 122, 48, 40, 15, 115, 1, -7, -47, -46, 25, -23, -111, -71, 90, -19, 65, 109, -76, -61, -98, -65, 99, -6, 31, 51, 96, 71, -119, -16, -106, 26, 95, -109, 61, 55, 75, -39, -88, -63, 27, -10, 57, -117, -73, 12, 32, -50, -120, 110, -74, 116, -114, -115, 22, 41, -14, -121, -11, -21, 112, -29, -5, 85, -97, -58, 68, 74, 69, 125, -30, 107, 92, 108, 102, -87, -116, -18, -124, 19, -89, 30, -99, -36, 103, 72, -70, 46, -26, -92, -85, 124, -108, 0, 33, -17, -22, -66, -54, 114, 79, 82, -104, 63, -62, 20, 123, 59, 84};
    private byte s = (byte)-66;

    public void addSeedMaterial(byte[] byArray) {
        for (int i = 0; i < byArray.length; ++i) {
            this.s = this.P[this.s + this.P[this.n & 0xFF] + byArray[i] & 0xFF];
            byte by = this.P[this.n & 0xFF];
            this.P[this.n & 0xFF] = this.P[this.s & 0xFF];
            this.P[this.s & 0xFF] = by;
            this.n = (byte)(this.n + 1 & 0xFF);
        }
    }

    public void addSeedMaterial(long l) {
        this.addSeedMaterial(Pack.longToBigEndian(l));
    }

    public void nextBytes(byte[] byArray) {
        this.nextBytes(byArray, 0, byArray.length);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void nextBytes(byte[] byArray, int n, int n2) {
        byte[] byArray2 = this.P;
        synchronized (this.P) {
            int n3 = n + n2;
            for (int i = n; i != n3; ++i) {
                this.s = this.P[this.s + this.P[this.n & 0xFF] & 0xFF];
                byArray[i] = this.P[this.P[this.P[this.s & 0xFF] & 0xFF] + 1 & 0xFF];
                byte by = this.P[this.n & 0xFF];
                this.P[this.n & 0xFF] = this.P[this.s & 0xFF];
                this.P[this.s & 0xFF] = by;
                this.n = (byte)(this.n + 1 & 0xFF);
            }
            // ** MonitorExit[var4_4] (shouldn't be in output)
            return;
        }
    }
}

