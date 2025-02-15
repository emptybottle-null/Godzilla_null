/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.classfile;

import org.mozilla.classfile.ClassFileWriter;

final class ClassFileMethod {
    private String itsName;
    private String itsType;
    private short itsNameIndex;
    private short itsTypeIndex;
    private short itsFlags;
    private byte[] itsCodeAttribute;

    ClassFileMethod(String name, short nameIndex, String type, short typeIndex, short flags) {
        this.itsName = name;
        this.itsNameIndex = nameIndex;
        this.itsType = type;
        this.itsTypeIndex = typeIndex;
        this.itsFlags = flags;
    }

    void setCodeAttribute(byte[] codeAttribute) {
        this.itsCodeAttribute = codeAttribute;
    }

    int write(byte[] data, int offset) {
        offset = ClassFileWriter.putInt16(this.itsFlags, data, offset);
        offset = ClassFileWriter.putInt16(this.itsNameIndex, data, offset);
        offset = ClassFileWriter.putInt16(this.itsTypeIndex, data, offset);
        offset = ClassFileWriter.putInt16(1, data, offset);
        System.arraycopy(this.itsCodeAttribute, 0, data, offset, this.itsCodeAttribute.length);
        return offset += this.itsCodeAttribute.length;
    }

    int getWriteSize() {
        return 8 + this.itsCodeAttribute.length;
    }

    String getName() {
        return this.itsName;
    }

    String getType() {
        return this.itsType;
    }

    short getFlags() {
        return this.itsFlags;
    }
}

