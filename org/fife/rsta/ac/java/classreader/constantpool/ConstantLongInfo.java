/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.classreader.constantpool;

import org.fife.rsta.ac.java.classreader.constantpool.ConstantPoolInfo;

public class ConstantLongInfo
extends ConstantPoolInfo {
    private int highBytes;
    private int lowBytes;

    public ConstantLongInfo(int highBytes, int lowBytes) {
        super(5);
        this.highBytes = highBytes;
        this.lowBytes = lowBytes;
    }

    public int getHighBytes() {
        return this.highBytes;
    }

    public long getLongValue() {
        return ((long)this.highBytes << 32) + (long)this.lowBytes;
    }

    public int getLowBytes() {
        return this.lowBytes;
    }

    public String toString() {
        return "[ConstantLongInfo: value=" + this.getLongValue() + "]";
    }
}

