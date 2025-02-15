/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.sphincs;

import org.bouncycastle.util.Pack;

class Permute {
    private static final int CHACHA_ROUNDS = 12;

    Permute() {
    }

    protected static int rotl(int n, int n2) {
        return n << n2 | n >>> -n2;
    }

    public static void permute(int n, int[] nArray) {
        if (nArray.length != 16) {
            throw new IllegalArgumentException();
        }
        if (n % 2 != 0) {
            throw new IllegalArgumentException("Number of rounds must be even");
        }
        int n2 = nArray[0];
        int n3 = nArray[1];
        int n4 = nArray[2];
        int n5 = nArray[3];
        int n6 = nArray[4];
        int n7 = nArray[5];
        int n8 = nArray[6];
        int n9 = nArray[7];
        int n10 = nArray[8];
        int n11 = nArray[9];
        int n12 = nArray[10];
        int n13 = nArray[11];
        int n14 = nArray[12];
        int n15 = nArray[13];
        int n16 = nArray[14];
        int n17 = nArray[15];
        for (int i = n; i > 0; i -= 2) {
            n14 = Permute.rotl(n14 ^ (n2 += n6), 16);
            n6 = Permute.rotl(n6 ^ (n10 += n14), 12);
            n14 = Permute.rotl(n14 ^ (n2 += n6), 8);
            n6 = Permute.rotl(n6 ^ (n10 += n14), 7);
            n15 = Permute.rotl(n15 ^ (n3 += n7), 16);
            n7 = Permute.rotl(n7 ^ (n11 += n15), 12);
            n15 = Permute.rotl(n15 ^ (n3 += n7), 8);
            n7 = Permute.rotl(n7 ^ (n11 += n15), 7);
            n16 = Permute.rotl(n16 ^ (n4 += n8), 16);
            n8 = Permute.rotl(n8 ^ (n12 += n16), 12);
            n16 = Permute.rotl(n16 ^ (n4 += n8), 8);
            n8 = Permute.rotl(n8 ^ (n12 += n16), 7);
            n17 = Permute.rotl(n17 ^ (n5 += n9), 16);
            n9 = Permute.rotl(n9 ^ (n13 += n17), 12);
            n17 = Permute.rotl(n17 ^ (n5 += n9), 8);
            n9 = Permute.rotl(n9 ^ (n13 += n17), 7);
            n17 = Permute.rotl(n17 ^ (n2 += n7), 16);
            n7 = Permute.rotl(n7 ^ (n12 += n17), 12);
            n17 = Permute.rotl(n17 ^ (n2 += n7), 8);
            n7 = Permute.rotl(n7 ^ (n12 += n17), 7);
            n14 = Permute.rotl(n14 ^ (n3 += n8), 16);
            n8 = Permute.rotl(n8 ^ (n13 += n14), 12);
            n14 = Permute.rotl(n14 ^ (n3 += n8), 8);
            n8 = Permute.rotl(n8 ^ (n13 += n14), 7);
            n15 = Permute.rotl(n15 ^ (n4 += n9), 16);
            n9 = Permute.rotl(n9 ^ (n10 += n15), 12);
            n15 = Permute.rotl(n15 ^ (n4 += n9), 8);
            n9 = Permute.rotl(n9 ^ (n10 += n15), 7);
            n16 = Permute.rotl(n16 ^ (n5 += n6), 16);
            n6 = Permute.rotl(n6 ^ (n11 += n16), 12);
            n16 = Permute.rotl(n16 ^ (n5 += n6), 8);
            n6 = Permute.rotl(n6 ^ (n11 += n16), 7);
        }
        nArray[0] = n2;
        nArray[1] = n3;
        nArray[2] = n4;
        nArray[3] = n5;
        nArray[4] = n6;
        nArray[5] = n7;
        nArray[6] = n8;
        nArray[7] = n9;
        nArray[8] = n10;
        nArray[9] = n11;
        nArray[10] = n12;
        nArray[11] = n13;
        nArray[12] = n14;
        nArray[13] = n15;
        nArray[14] = n16;
        nArray[15] = n17;
    }

    void chacha_permute(byte[] byArray, byte[] byArray2) {
        int n;
        int[] nArray = new int[16];
        for (n = 0; n < 16; ++n) {
            nArray[n] = Pack.littleEndianToInt(byArray2, 4 * n);
        }
        Permute.permute(12, nArray);
        for (n = 0; n < 16; ++n) {
            Pack.intToLittleEndian(nArray[n], byArray, 4 * n);
        }
    }
}

