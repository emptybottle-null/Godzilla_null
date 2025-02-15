/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.classreader;

import java.io.DataInputStream;
import java.io.IOException;
import org.fife.rsta.ac.java.classreader.ClassFile;

public class ExceptionTableEntry {
    private ClassFile cf;
    private int startPC;
    private int endPC;
    private int handlerPC;
    private int catchType;

    public ExceptionTableEntry(ClassFile cf) {
        this.cf = cf;
    }

    public String getCaughtThrowableType(boolean fullyQualified) {
        return this.catchType == 0 ? null : this.cf.getClassNameFromConstantPool(this.catchType, fullyQualified);
    }

    public int getEndPC() {
        return this.endPC;
    }

    public int getHandlerPC() {
        return this.handlerPC;
    }

    public int getStartPC() {
        return this.startPC;
    }

    public static ExceptionTableEntry read(ClassFile cf, DataInputStream in) throws IOException {
        ExceptionTableEntry entry = new ExceptionTableEntry(cf);
        entry.startPC = in.readUnsignedShort();
        entry.endPC = in.readUnsignedShort();
        entry.handlerPC = in.readUnsignedShort();
        entry.catchType = in.readUnsignedShort();
        return entry;
    }
}

