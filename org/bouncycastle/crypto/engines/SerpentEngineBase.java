/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;

public abstract class SerpentEngineBase
implements BlockCipher {
    protected static final int BLOCK_SIZE = 16;
    static final int ROUNDS = 32;
    static final int PHI = -1640531527;
    protected boolean encrypting;
    protected int[] wKey;
    protected int X0;
    protected int X1;
    protected int X2;
    protected int X3;

    SerpentEngineBase() {
    }

    public void init(boolean bl, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.encrypting = bl;
            this.wKey = this.makeWorkingKey(((KeyParameter)cipherParameters).getKey());
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to " + this.getAlgorithmName() + " init - " + cipherParameters.getClass().getName());
    }

    public String getAlgorithmName() {
        return "Serpent";
    }

    public int getBlockSize() {
        return 16;
    }

    public final int processBlock(byte[] byArray, int n, byte[] byArray2, int n2) {
        if (this.wKey == null) {
            throw new IllegalStateException(this.getAlgorithmName() + " not initialised");
        }
        if (n + 16 > byArray.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (n2 + 16 > byArray2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        if (this.encrypting) {
            this.encryptBlock(byArray, n, byArray2, n2);
        } else {
            this.decryptBlock(byArray, n, byArray2, n2);
        }
        return 16;
    }

    public void reset() {
    }

    protected static int rotateLeft(int n, int n2) {
        return n << n2 | n >>> -n2;
    }

    protected static int rotateRight(int n, int n2) {
        return n >>> n2 | n << -n2;
    }

    protected final void sb0(int n, int n2, int n3, int n4) {
        int n5 = n ^ n4;
        int n6 = n3 ^ n5;
        int n7 = n2 ^ n6;
        this.X3 = n & n4 ^ n7;
        int n8 = n ^ n2 & n5;
        this.X2 = n7 ^ (n3 | n8);
        int n9 = this.X3 & (n6 ^ n8);
        this.X1 = ~n6 ^ n9;
        this.X0 = n9 ^ ~n8;
    }

    protected final void ib0(int n, int n2, int n3, int n4) {
        int n5 = ~n;
        int n6 = n ^ n2;
        int n7 = n4 ^ (n5 | n6);
        int n8 = n3 ^ n7;
        this.X2 = n6 ^ n8;
        int n9 = n5 ^ n4 & n6;
        this.X1 = n7 ^ this.X2 & n9;
        this.X3 = n & n7 ^ (n8 | this.X1);
        this.X0 = this.X3 ^ (n8 ^ n9);
    }

    protected final void sb1(int n, int n2, int n3, int n4) {
        int n5 = n2 ^ ~n;
        int n6 = n3 ^ (n | n5);
        this.X2 = n4 ^ n6;
        int n7 = n2 ^ (n4 | n5);
        int n8 = n5 ^ this.X2;
        this.X3 = n8 ^ n6 & n7;
        int n9 = n6 ^ n7;
        this.X1 = this.X3 ^ n9;
        this.X0 = n6 ^ n8 & n9;
    }

    protected final void ib1(int n, int n2, int n3, int n4) {
        int n5 = n2 ^ n4;
        int n6 = n ^ n2 & n5;
        int n7 = n5 ^ n6;
        this.X3 = n3 ^ n7;
        int n8 = n2 ^ n5 & n6;
        int n9 = this.X3 | n8;
        this.X1 = n6 ^ n9;
        int n10 = ~this.X1;
        int n11 = this.X3 ^ n8;
        this.X0 = n10 ^ n11;
        this.X2 = n7 ^ (n10 | n11);
    }

    protected final void sb2(int n, int n2, int n3, int n4) {
        int n5 = ~n;
        int n6 = n2 ^ n4;
        int n7 = n3 & n5;
        this.X0 = n6 ^ n7;
        int n8 = n3 ^ n5;
        int n9 = n3 ^ this.X0;
        int n10 = n2 & n9;
        this.X3 = n8 ^ n10;
        this.X2 = n ^ (n4 | n10) & (this.X0 | n8);
        this.X1 = n6 ^ this.X3 ^ (this.X2 ^ (n4 | n5));
    }

    protected final void ib2(int n, int n2, int n3, int n4) {
        int n5 = n2 ^ n4;
        int n6 = ~n5;
        int n7 = n ^ n3;
        int n8 = n3 ^ n5;
        int n9 = n2 & n8;
        this.X0 = n7 ^ n9;
        int n10 = n | n6;
        int n11 = n4 ^ n10;
        int n12 = n7 | n11;
        this.X3 = n5 ^ n12;
        int n13 = ~n8;
        int n14 = this.X0 | this.X3;
        this.X1 = n13 ^ n14;
        this.X2 = n4 & n13 ^ (n7 ^ n14);
    }

    protected final void sb3(int n, int n2, int n3, int n4) {
        int n5 = n ^ n2;
        int n6 = n & n3;
        int n7 = n | n4;
        int n8 = n3 ^ n4;
        int n9 = n5 & n7;
        int n10 = n6 | n9;
        this.X2 = n8 ^ n10;
        int n11 = n2 ^ n7;
        int n12 = n10 ^ n11;
        int n13 = n8 & n12;
        this.X0 = n5 ^ n13;
        int n14 = this.X2 & this.X0;
        this.X1 = n12 ^ n14;
        this.X3 = (n2 | n4) ^ (n8 ^ n14);
    }

    protected final void ib3(int n, int n2, int n3, int n4) {
        int n5 = n | n2;
        int n6 = n2 ^ n3;
        int n7 = n2 & n6;
        int n8 = n ^ n7;
        int n9 = n3 ^ n8;
        int n10 = n4 | n8;
        this.X0 = n6 ^ n10;
        int n11 = n6 | n10;
        int n12 = n4 ^ n11;
        this.X2 = n9 ^ n12;
        int n13 = n5 ^ n12;
        int n14 = this.X0 & n13;
        this.X3 = n8 ^ n14;
        this.X1 = this.X3 ^ (this.X0 ^ n13);
    }

    protected final void sb4(int n, int n2, int n3, int n4) {
        int n5 = n ^ n4;
        int n6 = n4 & n5;
        int n7 = n3 ^ n6;
        int n8 = n2 | n7;
        this.X3 = n5 ^ n8;
        int n9 = ~n2;
        int n10 = n5 | n9;
        this.X0 = n7 ^ n10;
        int n11 = n & this.X0;
        int n12 = n5 ^ n9;
        int n13 = n8 & n12;
        this.X2 = n11 ^ n13;
        this.X1 = n ^ n7 ^ n12 & this.X2;
    }

    protected final void ib4(int n, int n2, int n3, int n4) {
        int n5 = n3 | n4;
        int n6 = n & n5;
        int n7 = n2 ^ n6;
        int n8 = n & n7;
        int n9 = n3 ^ n8;
        this.X1 = n4 ^ n9;
        int n10 = ~n;
        int n11 = n9 & this.X1;
        this.X3 = n7 ^ n11;
        int n12 = this.X1 | n10;
        int n13 = n4 ^ n12;
        this.X0 = this.X3 ^ n13;
        this.X2 = n7 & n13 ^ (this.X1 ^ n10);
    }

    protected final void sb5(int n, int n2, int n3, int n4) {
        int n5 = ~n;
        int n6 = n ^ n2;
        int n7 = n ^ n4;
        int n8 = n3 ^ n5;
        int n9 = n6 | n7;
        this.X0 = n8 ^ n9;
        int n10 = n4 & this.X0;
        int n11 = n6 ^ this.X0;
        this.X1 = n10 ^ n11;
        int n12 = n5 | this.X0;
        int n13 = n6 | n10;
        int n14 = n7 ^ n12;
        this.X2 = n13 ^ n14;
        this.X3 = n2 ^ n10 ^ this.X1 & n14;
    }

    protected final void ib5(int n, int n2, int n3, int n4) {
        int n5 = ~n3;
        int n6 = n2 & n5;
        int n7 = n4 ^ n6;
        int n8 = n & n7;
        int n9 = n2 ^ n5;
        this.X3 = n8 ^ n9;
        int n10 = n2 | this.X3;
        int n11 = n & n10;
        this.X1 = n7 ^ n11;
        int n12 = n | n4;
        int n13 = n5 ^ n10;
        this.X0 = n12 ^ n13;
        this.X2 = n2 & n12 ^ (n8 | n ^ n3);
    }

    protected final void sb6(int n, int n2, int n3, int n4) {
        int n5 = ~n;
        int n6 = n ^ n4;
        int n7 = n2 ^ n6;
        int n8 = n5 | n6;
        int n9 = n3 ^ n8;
        this.X1 = n2 ^ n9;
        int n10 = n6 | this.X1;
        int n11 = n4 ^ n10;
        int n12 = n9 & n11;
        this.X2 = n7 ^ n12;
        int n13 = n9 ^ n11;
        this.X0 = this.X2 ^ n13;
        this.X3 = ~n9 ^ n7 & n13;
    }

    protected final void ib6(int n, int n2, int n3, int n4) {
        int n5 = ~n;
        int n6 = n ^ n2;
        int n7 = n3 ^ n6;
        int n8 = n3 | n5;
        int n9 = n4 ^ n8;
        this.X1 = n7 ^ n9;
        int n10 = n7 & n9;
        int n11 = n6 ^ n10;
        int n12 = n2 | n11;
        this.X3 = n9 ^ n12;
        int n13 = n2 | this.X3;
        this.X0 = n11 ^ n13;
        this.X2 = n4 & n5 ^ (n7 ^ n13);
    }

    protected final void sb7(int n, int n2, int n3, int n4) {
        int n5 = n2 ^ n3;
        int n6 = n3 & n5;
        int n7 = n4 ^ n6;
        int n8 = n ^ n7;
        int n9 = n4 | n5;
        int n10 = n8 & n9;
        this.X1 = n2 ^ n10;
        int n11 = n7 | this.X1;
        int n12 = n & n8;
        this.X3 = n5 ^ n12;
        int n13 = n8 ^ n11;
        int n14 = this.X3 & n13;
        this.X2 = n7 ^ n14;
        this.X0 = ~n13 ^ this.X3 & this.X2;
    }

    protected final void ib7(int n, int n2, int n3, int n4) {
        int n5 = n3 | n & n2;
        int n6 = n4 & (n | n2);
        this.X3 = n5 ^ n6;
        int n7 = ~n4;
        int n8 = n2 ^ n6;
        int n9 = n8 | this.X3 ^ n7;
        this.X1 = n ^ n9;
        this.X0 = n3 ^ n8 ^ (n4 | this.X1);
        this.X2 = n5 ^ this.X1 ^ (this.X0 ^ n & this.X3);
    }

    protected final void LT() {
        int n = SerpentEngineBase.rotateLeft(this.X0, 13);
        int n2 = SerpentEngineBase.rotateLeft(this.X2, 3);
        int n3 = this.X1 ^ n ^ n2;
        int n4 = this.X3 ^ n2 ^ n << 3;
        this.X1 = SerpentEngineBase.rotateLeft(n3, 1);
        this.X3 = SerpentEngineBase.rotateLeft(n4, 7);
        this.X0 = SerpentEngineBase.rotateLeft(n ^ this.X1 ^ this.X3, 5);
        this.X2 = SerpentEngineBase.rotateLeft(n2 ^ this.X3 ^ this.X1 << 7, 22);
    }

    protected final void inverseLT() {
        int n = SerpentEngineBase.rotateRight(this.X2, 22) ^ this.X3 ^ this.X1 << 7;
        int n2 = SerpentEngineBase.rotateRight(this.X0, 5) ^ this.X1 ^ this.X3;
        int n3 = SerpentEngineBase.rotateRight(this.X3, 7);
        int n4 = SerpentEngineBase.rotateRight(this.X1, 1);
        this.X3 = n3 ^ n ^ n2 << 3;
        this.X1 = n4 ^ n2 ^ n;
        this.X2 = SerpentEngineBase.rotateRight(n, 3);
        this.X0 = SerpentEngineBase.rotateRight(n2, 13);
    }

    protected abstract int[] makeWorkingKey(byte[] var1);

    protected abstract void encryptBlock(byte[] var1, int var2, byte[] var3, int var4);

    protected abstract void decryptBlock(byte[] var1, int var2, byte[] var3, int var4);
}

