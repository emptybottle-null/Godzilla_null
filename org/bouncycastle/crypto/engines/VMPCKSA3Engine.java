/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.engines.VMPCEngine;

public class VMPCKSA3Engine
extends VMPCEngine {
    public String getAlgorithmName() {
        return "VMPC-KSA3";
    }

    protected void initKey(byte[] byArray, byte[] byArray2) {
        byte by;
        int n;
        this.s = 0;
        this.P = new byte[256];
        for (n = 0; n < 256; ++n) {
            this.P[n] = (byte)n;
        }
        for (n = 0; n < 768; ++n) {
            this.s = this.P[this.s + this.P[n & 0xFF] + byArray[n % byArray.length] & 0xFF];
            by = this.P[n & 0xFF];
            this.P[n & 0xFF] = this.P[this.s & 0xFF];
            this.P[this.s & 0xFF] = by;
        }
        for (n = 0; n < 768; ++n) {
            this.s = this.P[this.s + this.P[n & 0xFF] + byArray2[n % byArray2.length] & 0xFF];
            by = this.P[n & 0xFF];
            this.P[n & 0xFF] = this.P[this.s & 0xFF];
            this.P[this.s & 0xFF] = by;
        }
        for (n = 0; n < 768; ++n) {
            this.s = this.P[this.s + this.P[n & 0xFF] + byArray[n % byArray.length] & 0xFF];
            by = this.P[n & 0xFF];
            this.P[n & 0xFF] = this.P[this.s & 0xFF];
            this.P[this.s & 0xFF] = by;
        }
        this.n = 0;
    }
}

