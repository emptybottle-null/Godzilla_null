/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.classfile;

import org.mozilla.classfile.ClassFileWriter;

final class ClassFileField {
    private short itsNameIndex;
    private short itsTypeIndex;
    private short itsFlags;
    private boolean itsHasAttributes;
    private short itsAttr1;
    private short itsAttr2;
    private short itsAttr3;
    private int itsIndex;

    ClassFileField(short nameIndex, short typeIndex, short flags) {
        this.itsNameIndex = nameIndex;
        this.itsTypeIndex = typeIndex;
        this.itsFlags = flags;
        this.itsHasAttributes = false;
    }

    void setAttributes(short attr1, short attr2, short attr3, int index) {
        this.itsHasAttributes = true;
        this.itsAttr1 = attr1;
        this.itsAttr2 = attr2;
        this.itsAttr3 = attr3;
        this.itsIndex = index;
    }

    int write(byte[] data, int offset) {
        offset = ClassFileWriter.putInt16(this.itsFlags, data, offset);
        offset = ClassFileWriter.putInt16(this.itsNameIndex, data, offset);
        offset = ClassFileWriter.putInt16(this.itsTypeIndex, data, offset);
        if (!this.itsHasAttributes) {
            offset = ClassFileWriter.putInt16(0, data, offset);
        } else {
            offset = ClassFileWriter.putInt16(1, data, offset);
            offset = ClassFileWriter.putInt16(this.itsAttr1, data, offset);
            offset = ClassFileWriter.putInt16(this.itsAttr2, data, offset);
            offset = ClassFileWriter.putInt16(this.itsAttr3, data, offset);
            offset = ClassFileWriter.putInt16(this.itsIndex, data, offset);
        }
        return offset;
    }

    int getWriteSize() {
        int size = 6;
        size = !this.itsHasAttributes ? (size += 2) : (size += 10);
        return size;
    }
}

