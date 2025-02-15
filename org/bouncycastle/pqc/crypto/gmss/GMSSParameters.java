/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.gmss;

import org.bouncycastle.util.Arrays;

public class GMSSParameters {
    private int numOfLayers;
    private int[] heightOfTrees;
    private int[] winternitzParameter;
    private int[] K;

    public GMSSParameters(int n, int[] nArray, int[] nArray2, int[] nArray3) throws IllegalArgumentException {
        this.init(n, nArray, nArray2, nArray3);
    }

    private void init(int n, int[] nArray, int[] nArray2, int[] nArray3) throws IllegalArgumentException {
        boolean bl = true;
        String string = "";
        this.numOfLayers = n;
        if (this.numOfLayers != nArray2.length || this.numOfLayers != nArray.length || this.numOfLayers != nArray3.length) {
            bl = false;
            string = "Unexpected parameterset format";
        }
        for (int i = 0; i < this.numOfLayers; ++i) {
            if (nArray3[i] < 2 || (nArray[i] - nArray3[i]) % 2 != 0) {
                bl = false;
                string = "Wrong parameter K (K >= 2 and H-K even required)!";
            }
            if (nArray[i] >= 4 && nArray2[i] >= 2) continue;
            bl = false;
            string = "Wrong parameter H or w (H > 3 and w > 1 required)!";
        }
        if (!bl) {
            throw new IllegalArgumentException(string);
        }
        this.heightOfTrees = Arrays.clone(nArray);
        this.winternitzParameter = Arrays.clone(nArray2);
        this.K = Arrays.clone(nArray3);
    }

    public GMSSParameters(int n) throws IllegalArgumentException {
        if (n <= 10) {
            int[] nArray = new int[]{10};
            int[] nArray2 = new int[]{3};
            int[] nArray3 = new int[]{2};
            this.init(nArray.length, nArray, nArray2, nArray3);
        } else if (n <= 20) {
            int[] nArray = new int[]{10, 10};
            int[] nArray4 = new int[]{5, 4};
            int[] nArray5 = new int[]{2, 2};
            this.init(nArray.length, nArray, nArray4, nArray5);
        } else {
            int[] nArray = new int[]{10, 10, 10, 10};
            int[] nArray6 = new int[]{9, 9, 9, 3};
            int[] nArray7 = new int[]{2, 2, 2, 2};
            this.init(nArray.length, nArray, nArray6, nArray7);
        }
    }

    public int getNumOfLayers() {
        return this.numOfLayers;
    }

    public int[] getHeightOfTrees() {
        return Arrays.clone(this.heightOfTrees);
    }

    public int[] getWinternitzParameter() {
        return Arrays.clone(this.winternitzParameter);
    }

    public int[] getK() {
        return Arrays.clone(this.K);
    }
}

