/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.engines.Salsa20Engine;
import org.bouncycastle.util.Pack;

public class XSalsa20Engine
extends Salsa20Engine {
    public String getAlgorithmName() {
        return "XSalsa20";
    }

    protected int getNonceSize() {
        return 24;
    }

    protected void setKey(byte[] byArray, byte[] byArray2) {
        if (byArray == null) {
            throw new IllegalArgumentException(this.getAlgorithmName() + " doesn't support re-init with null key");
        }
        if (byArray.length != 32) {
            throw new IllegalArgumentException(this.getAlgorithmName() + " requires a 256 bit key");
        }
        super.setKey(byArray, byArray2);
        Pack.littleEndianToInt(byArray2, 8, this.engineState, 8, 2);
        int[] nArray = new int[this.engineState.length];
        XSalsa20Engine.salsaCore(20, this.engineState, nArray);
        this.engineState[1] = nArray[0] - this.engineState[0];
        this.engineState[2] = nArray[5] - this.engineState[5];
        this.engineState[3] = nArray[10] - this.engineState[10];
        this.engineState[4] = nArray[15] - this.engineState[15];
        this.engineState[11] = nArray[6] - this.engineState[6];
        this.engineState[12] = nArray[7] - this.engineState[7];
        this.engineState[13] = nArray[8] - this.engineState[8];
        this.engineState[14] = nArray[9] - this.engineState[9];
        Pack.littleEndianToInt(byArray2, 16, this.engineState, 6, 2);
    }
}

