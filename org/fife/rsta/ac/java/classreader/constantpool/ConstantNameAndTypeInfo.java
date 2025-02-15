/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.classreader.constantpool;

import org.fife.rsta.ac.java.classreader.constantpool.ConstantPoolInfo;

public class ConstantNameAndTypeInfo
extends ConstantPoolInfo {
    private int nameIndex;
    private int descriptorIndex;

    public ConstantNameAndTypeInfo(int nameIndex, int descriptorIndex) {
        super(12);
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
    }

    public int getDescriptorIndex() {
        return this.descriptorIndex;
    }

    public int getNameIndex() {
        return this.nameIndex;
    }

    public String toString() {
        return "[ConstantNameAndTypeInfo: descriptorIndex=" + this.getDescriptorIndex() + "; nameIndex=" + this.getNameIndex() + "]";
    }
}

