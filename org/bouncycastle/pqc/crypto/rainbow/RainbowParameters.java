/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.rainbow;

import org.bouncycastle.crypto.CipherParameters;

public class RainbowParameters
implements CipherParameters {
    private final int[] DEFAULT_VI = new int[]{6, 12, 17, 22, 33};
    private int[] vi;

    public RainbowParameters() {
        this.vi = this.DEFAULT_VI;
    }

    public RainbowParameters(int[] nArray) {
        this.vi = nArray;
        try {
            this.checkParams();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void checkParams() throws Exception {
        if (this.vi == null) {
            throw new Exception("no layers defined.");
        }
        if (this.vi.length > 1) {
            for (int i = 0; i < this.vi.length - 1; ++i) {
                if (this.vi[i] < this.vi[i + 1]) continue;
                throw new Exception("v[i] has to be smaller than v[i+1]");
            }
        } else {
            throw new Exception("Rainbow needs at least 1 layer, such that v1 < v2.");
        }
    }

    public int getNumOfLayers() {
        return this.vi.length - 1;
    }

    public int getDocLength() {
        return this.vi[this.vi.length - 1] - this.vi[0];
    }

    public int[] getVi() {
        return this.vi;
    }
}

