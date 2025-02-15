/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.classreader.constantpool;

import org.fife.rsta.ac.java.classreader.constantpool.ConstantPoolInfo;

public class ConstantDoubleInfo
extends ConstantPoolInfo {
    private int highBytes;
    private int lowBytes;

    public ConstantDoubleInfo(int highBytes, int lowBytes) {
        super(6);
        this.highBytes = highBytes;
        this.lowBytes = lowBytes;
    }

    public double getDoubleValue() {
        long bits = ((long)this.highBytes << 32) + (long)this.lowBytes;
        return Double.longBitsToDouble(bits);
    }

    public int getHighBytes() {
        return this.highBytes;
    }

    public int getLowBytes() {
        return this.lowBytes;
    }

    public String toString() {
        return "[ConstantDoubleInfo: value=" + this.getDoubleValue() + "]";
    }
}

