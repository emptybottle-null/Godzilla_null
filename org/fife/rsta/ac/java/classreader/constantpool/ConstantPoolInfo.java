/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.classreader.constantpool;

import org.fife.rsta.ac.java.classreader.constantpool.ConstantTypes;

public abstract class ConstantPoolInfo
implements ConstantTypes {
    private int tag;

    public ConstantPoolInfo(int tag) {
        this.tag = tag;
    }

    public int getTag() {
        return this.tag;
    }
}

