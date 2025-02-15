/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.classreader.constantpool;

import org.fife.rsta.ac.java.classreader.constantpool.ConstantPoolInfo;

public class ConstantMethodTypeInfo
extends ConstantPoolInfo {
    private int descriptorIndex;

    public ConstantMethodTypeInfo(int descriptorIndex) {
        super(16);
        this.descriptorIndex = descriptorIndex;
    }

    public int getDescriptorIndex() {
        return this.descriptorIndex;
    }

    public String toString() {
        return "[ConstantMethodTypeInfo: bootstrapMethodAttrIndex=" + this.getDescriptorIndex() + "]";
    }
}

