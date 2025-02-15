/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.classreader.constantpool;

import org.fife.rsta.ac.java.classreader.constantpool.ConstantPoolInfo;

public class ConstantMethodrefInfo
extends ConstantPoolInfo {
    private int classIndex;
    private int nameAndTypeIndex;

    public ConstantMethodrefInfo(int classIndex, int nameAndTypeIndex) {
        super(10);
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    public int getClassIndex() {
        return this.classIndex;
    }

    public int getNameAndTypeIndex() {
        return this.nameAndTypeIndex;
    }

    public String toString() {
        return "[ConstantMethodrefInfo: classIndex=" + this.getClassIndex() + "; nameAndTypeIndex=" + this.getNameAndTypeIndex() + "]";
    }
}

