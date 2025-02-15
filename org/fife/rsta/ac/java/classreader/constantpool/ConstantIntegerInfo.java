/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.classreader.constantpool;

import org.fife.rsta.ac.java.classreader.constantpool.ConstantPoolInfo;

public class ConstantIntegerInfo
extends ConstantPoolInfo {
    private long bytes;

    public ConstantIntegerInfo(long bytes) {
        super(8);
        this.bytes = bytes;
    }

    public long getBytes() {
        return this.bytes;
    }

    public int getIntValue() {
        return (int)this.bytes;
    }

    public String toString() {
        return "[ConstantIntegerInfo: bytes=" + this.getBytes() + "]";
    }
}

