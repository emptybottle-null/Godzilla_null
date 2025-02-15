/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.classreader.constantpool;

import org.fife.rsta.ac.java.classreader.constantpool.ConstantPoolInfo;

public class ConstantClassInfo
extends ConstantPoolInfo {
    private int nameIndex;

    public ConstantClassInfo(int nameIndex) {
        super(7);
        this.nameIndex = nameIndex;
    }

    public int getNameIndex() {
        return this.nameIndex;
    }

    public String toString() {
        return "[ConstantClassInfo: nameIndex=" + this.getNameIndex() + "]";
    }
}

