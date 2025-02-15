/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.classreader.attributes;

import org.fife.rsta.ac.java.classreader.ClassFile;
import org.fife.rsta.ac.java.classreader.attributes.AttributeInfo;

public class SourceFile
extends AttributeInfo {
    private int sourceFileIndex;

    public SourceFile(ClassFile cf, int sourceFileIndex) {
        super(cf);
        this.sourceFileIndex = sourceFileIndex;
    }

    public String getSourceFileName() {
        return this.getClassFile().getUtf8ValueFromConstantPool(this.sourceFileIndex);
    }

    public String toString() {
        return "[SourceFile: file=" + this.getSourceFileName() + "]";
    }
}

