/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.classreader.constantpool;

import org.fife.rsta.ac.java.classreader.ClassFile;
import org.fife.rsta.ac.java.classreader.constantpool.ConstantPoolInfo;

public class ConstantStringInfo
extends ConstantPoolInfo {
    private ClassFile cf;
    private int stringIndex;

    public ConstantStringInfo(ClassFile cf, int stringIndex) {
        super(8);
        this.cf = cf;
        this.stringIndex = stringIndex;
    }

    public int getStringIndex() {
        return this.stringIndex;
    }

    public String getStringValue() {
        return '\"' + this.cf.getUtf8ValueFromConstantPool(this.getStringIndex()) + '\"';
    }

    public String toString() {
        return "[ConstantStringInfo: stringIndex=" + this.getStringIndex() + "]";
    }
}

