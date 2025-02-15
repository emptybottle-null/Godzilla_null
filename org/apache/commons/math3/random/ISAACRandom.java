/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.random;

import java.io.Serializable;
import org.apache.commons.math3.random.BitsStreamGenerator;
import org.apache.commons.math3.util.FastMath;

public class ISAACRandom
extends BitsStreamGenerator
implements Serializable {
    private static final long serialVersionUID = 7288197941165002400L;
    private static final int SIZE_L = 8;
    private static final int SIZE = 256;
    private static final int H_SIZE = 128;
    private static final int MASK = 1020;
    private static final int GLD_RATIO = -1640531527;
    private final int[] rsl = new int[256];
    private final int[] mem = new int[256];
    private int count;
    private int isaacA;
    private int isaacB;
    private int isaacC;
    private final int[] arr = new int[8];
    private int isaacX;
    private int isaacI;
    private int isaacJ;

    public ISAACRandom() {
        this.setSeed(System.currentTimeMillis() + (long)System.identityHashCode(this));
    }

    public ISAACRandom(long seed) {
        this.setSeed(seed);
    }

    public ISAACRandom(int[] seed) {
        this.setSeed(seed);
    }

    public void setSeed(int seed) {
        this.setSeed(new int[]{seed});
    }

    public void setSeed(long seed) {
        this.setSeed(new int[]{(int)(seed >>> 32), (int)(seed & 0xFFFFFFFFL)});
    }

    public void setSeed(int[] seed) {
        if (seed == null) {
            this.setSeed(System.currentTimeMillis() + (long)System.identityHashCode(this));
            return;
        }
        int seedLen = seed.length;
        int rslLen = this.rsl.length;
        System.arraycopy(seed, 0, this.rsl, 0, FastMath.min(seedLen, rslLen));
        if (seedLen < rslLen) {
            for (int j = seedLen; j < rslLen; ++j) {
                long k = this.rsl[j - seedLen];
                this.rsl[j] = (int)(1812433253L * (k ^ k >> 30) + (long)j & 0xFFFFFFFFL);
            }
        }
        this.initState();
    }

    protected int next(int bits) {
        if (this.count < 0) {
            this.isaac();
            this.count = 255;
        }
        return this.rsl[this.count--] >>> 32 - bits;
    }

    private void isaac() {
        this.isaacI = 0;
        this.isaacJ = 128;
        this.isaacB += ++this.isaacC;
        while (this.isaacI < 128) {
            this.isaac2();
        }
        this.isaacJ = 0;
        while (this.isaacJ < 128) {
            this.isaac2();
        }
    }

    private void isaac2() {
        this.isaacX = this.mem[this.isaacI];
        this.isaacA ^= this.isaacA << 13;
        this.isaacA += this.mem[this.isaacJ++];
        this.isaac3();
        this.isaacX = this.mem[this.isaacI];
        this.isaacA ^= this.isaacA >>> 6;
        this.isaacA += this.mem[this.isaacJ++];
        this.isaac3();
        this.isaacX = this.mem[this.isaacI];
        this.isaacA ^= this.isaacA << 2;
        this.isaacA += this.mem[this.isaacJ++];
        this.isaac3();
        this.isaacX = this.mem[this.isaacI];
        this.isaacA ^= this.isaacA >>> 16;
        this.isaacA += this.mem[this.isaacJ++];
        this.isaac3();
    }

    private void isaac3() {
        this.mem[this.isaacI] = this.mem[(this.isaacX & 0x3FC) >> 2] + this.isaacA + this.isaacB;
        this.isaacB = this.mem[(this.mem[this.isaacI] >> 8 & 0x3FC) >> 2] + this.isaacX;
        this.rsl[this.isaacI++] = this.isaacB;
    }

    private void initState() {
        int j;
        this.isaacA = 0;
        this.isaacB = 0;
        this.isaacC = 0;
        for (j = 0; j < this.arr.length; ++j) {
            this.arr[j] = -1640531527;
        }
        for (j = 0; j < 4; ++j) {
            this.shuffle();
        }
        for (j = 0; j < 256; j += 8) {
            this.arr[0] = this.arr[0] + this.rsl[j];
            this.arr[1] = this.arr[1] + this.rsl[j + 1];
            this.arr[2] = this.arr[2] + this.rsl[j + 2];
            this.arr[3] = this.arr[3] + this.rsl[j + 3];
            this.arr[4] = this.arr[4] + this.rsl[j + 4];
            this.arr[5] = this.arr[5] + this.rsl[j + 5];
            this.arr[6] = this.arr[6] + this.rsl[j + 6];
            this.arr[7] = this.arr[7] + this.rsl[j + 7];
            this.shuffle();
            this.setState(j);
        }
        for (j = 0; j < 256; j += 8) {
            this.arr[0] = this.arr[0] + this.mem[j];
            this.arr[1] = this.arr[1] + this.mem[j + 1];
            this.arr[2] = this.arr[2] + this.mem[j + 2];
            this.arr[3] = this.arr[3] + this.mem[j + 3];
            this.arr[4] = this.arr[4] + this.mem[j + 4];
            this.arr[5] = this.arr[5] + this.mem[j + 5];
            this.arr[6] = this.arr[6] + this.mem[j + 6];
            this.arr[7] = this.arr[7] + this.mem[j + 7];
            this.shuffle();
            this.setState(j);
        }
        this.isaac();
        this.count = 255;
        this.clear();
    }

    private void shuffle() {
        this.arr[0] = this.arr[0] ^ this.arr[1] << 11;
        this.arr[3] = this.arr[3] + this.arr[0];
        this.arr[1] = this.arr[1] + this.arr[2];
        this.arr[1] = this.arr[1] ^ this.arr[2] >>> 2;
        this.arr[4] = this.arr[4] + this.arr[1];
        this.arr[2] = this.arr[2] + this.arr[3];
        this.arr[2] = this.arr[2] ^ this.arr[3] << 8;
        this.arr[5] = this.arr[5] + this.arr[2];
        this.arr[3] = this.arr[3] + this.arr[4];
        this.arr[3] = this.arr[3] ^ this.arr[4] >>> 16;
        this.arr[6] = this.arr[6] + this.arr[3];
        this.arr[4] = this.arr[4] + this.arr[5];
        this.arr[4] = this.arr[4] ^ this.arr[5] << 10;
        this.arr[7] = this.arr[7] + this.arr[4];
        this.arr[5] = this.arr[5] + this.arr[6];
        this.arr[5] = this.arr[5] ^ this.arr[6] >>> 4;
        this.arr[0] = this.arr[0] + this.arr[5];
        this.arr[6] = this.arr[6] + this.arr[7];
        this.arr[6] = this.arr[6] ^ this.arr[7] << 8;
        this.arr[1] = this.arr[1] + this.arr[6];
        this.arr[7] = this.arr[7] + this.arr[0];
        this.arr[7] = this.arr[7] ^ this.arr[0] >>> 9;
        this.arr[2] = this.arr[2] + this.arr[7];
        this.arr[0] = this.arr[0] + this.arr[1];
    }

    private void setState(int start) {
        this.mem[start] = this.arr[0];
        this.mem[start + 1] = this.arr[1];
        this.mem[start + 2] = this.arr[2];
        this.mem[start + 3] = this.arr[3];
        this.mem[start + 4] = this.arr[4];
        this.mem[start + 5] = this.arr[5];
        this.mem[start + 6] = this.arr[6];
        this.mem[start + 7] = this.arr[7];
    }
}

