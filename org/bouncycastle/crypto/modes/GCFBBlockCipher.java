/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.StreamBlockCipher;
import org.bouncycastle.crypto.modes.CFBBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.params.ParametersWithSBox;

public class GCFBBlockCipher
extends StreamBlockCipher {
    private static final byte[] C = new byte[]{105, 0, 114, 34, 100, -55, 4, 35, -115, 58, -37, -106, 70, -23, 42, -60, 24, -2, -84, -108, 0, -19, 7, 18, -64, -122, -36, -62, -17, 76, -87, 43};
    private final CFBBlockCipher cfbEngine;
    private KeyParameter key;
    private long counter = 0L;
    private boolean forEncryption;

    public GCFBBlockCipher(BlockCipher blockCipher) {
        super(blockCipher);
        this.cfbEngine = new CFBBlockCipher(blockCipher, blockCipher.getBlockSize() * 8);
    }

    public void init(boolean bl, CipherParameters cipherParameters) throws IllegalArgumentException {
        this.counter = 0L;
        this.cfbEngine.init(bl, cipherParameters);
        this.forEncryption = bl;
        if (cipherParameters instanceof ParametersWithIV) {
            cipherParameters = ((ParametersWithIV)cipherParameters).getParameters();
        }
        if (cipherParameters instanceof ParametersWithRandom) {
            cipherParameters = ((ParametersWithRandom)cipherParameters).getParameters();
        }
        if (cipherParameters instanceof ParametersWithSBox) {
            cipherParameters = ((ParametersWithSBox)cipherParameters).getParameters();
        }
        this.key = (KeyParameter)cipherParameters;
    }

    public String getAlgorithmName() {
        String string = this.cfbEngine.getAlgorithmName();
        return string.substring(0, string.indexOf(47)) + "/G" + string.substring(string.indexOf(47) + 1);
    }

    public int getBlockSize() {
        return this.cfbEngine.getBlockSize();
    }

    public int processBlock(byte[] byArray, int n, byte[] byArray2, int n2) throws DataLengthException, IllegalStateException {
        this.processBytes(byArray, n, this.cfbEngine.getBlockSize(), byArray2, n2);
        return this.cfbEngine.getBlockSize();
    }

    protected byte calculateByte(byte by) {
        if (this.counter > 0L && this.counter % 1024L == 0L) {
            BlockCipher blockCipher = this.cfbEngine.getUnderlyingCipher();
            blockCipher.init(false, this.key);
            byte[] byArray = new byte[32];
            blockCipher.processBlock(C, 0, byArray, 0);
            blockCipher.processBlock(C, 8, byArray, 8);
            blockCipher.processBlock(C, 16, byArray, 16);
            blockCipher.processBlock(C, 24, byArray, 24);
            this.key = new KeyParameter(byArray);
            blockCipher.init(true, this.key);
            byte[] byArray2 = this.cfbEngine.getCurrentIV();
            blockCipher.processBlock(byArray2, 0, byArray2, 0);
            this.cfbEngine.init(this.forEncryption, new ParametersWithIV(this.key, byArray2));
        }
        ++this.counter;
        return this.cfbEngine.calculateByte(by);
    }

    public void reset() {
        this.counter = 0L;
        this.cfbEngine.reset();
    }
}

