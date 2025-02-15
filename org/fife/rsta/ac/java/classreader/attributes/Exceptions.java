/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.classreader.attributes;

import org.fife.rsta.ac.java.classreader.ClassFile;
import org.fife.rsta.ac.java.classreader.MethodInfo;
import org.fife.rsta.ac.java.classreader.attributes.AttributeInfo;
import org.fife.rsta.ac.java.classreader.constantpool.ConstantClassInfo;
import org.fife.rsta.ac.java.classreader.constantpool.ConstantPoolInfo;

public class Exceptions
extends AttributeInfo {
    private MethodInfo mi;
    private int[] exceptionIndexTable;

    public Exceptions(MethodInfo mi, int[] exceptionIndexTable) {
        super(mi.getClassFile());
        this.exceptionIndexTable = exceptionIndexTable;
    }

    public String getException(int index) {
        ClassFile cf = this.getClassFile();
        ConstantPoolInfo cpi = cf.getConstantPoolInfo(this.exceptionIndexTable[index]);
        ConstantClassInfo cci = (ConstantClassInfo)cpi;
        int nameIndex = cci.getNameIndex();
        String name = cf.getUtf8ValueFromConstantPool(nameIndex);
        return name.replace('/', '.');
    }

    public int getExceptionCount() {
        return this.exceptionIndexTable == null ? 0 : this.exceptionIndexTable.length;
    }

    public MethodInfo getMethodInfo() {
        return this.mi;
    }
}

