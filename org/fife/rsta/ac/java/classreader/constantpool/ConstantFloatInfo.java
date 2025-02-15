/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.classreader.constantpool;

import org.fife.rsta.ac.java.classreader.constantpool.ConstantPoolInfo;

public class ConstantFloatInfo
extends ConstantPoolInfo {
    private int bytes;

    public ConstantFloatInfo(int bytes) {
        super(4);
        this.bytes = bytes;
    }

    public long getBytes() {
        return this.bytes;
    }

    public float getFloatValue() {
        return Float.intBitsToFloat(this.bytes);
    }

    public String toString() {
        return "[ConstantFloatInfo: value=" + this.getFloatValue() + "]";
    }
}

