/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record.common;

import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

@Internal
public class FormatRun
implements Comparable<FormatRun> {
    final short _character;
    short _fontIndex;

    public FormatRun(short character, short fontIndex) {
        this._character = character;
        this._fontIndex = fontIndex;
    }

    public FormatRun(FormatRun other) {
        this._character = other._character;
        this._fontIndex = other._fontIndex;
    }

    public FormatRun(LittleEndianInput in) {
        this(in.readShort(), in.readShort());
    }

    public short getCharacterPos() {
        return this._character;
    }

    public short getFontIndex() {
        return this._fontIndex;
    }

    public boolean equals(Object o) {
        if (!(o instanceof FormatRun)) {
            return false;
        }
        FormatRun other = (FormatRun)o;
        return this._character == other._character && this._fontIndex == other._fontIndex;
    }

    @Override
    public int compareTo(FormatRun r) {
        if (this._character == r._character && this._fontIndex == r._fontIndex) {
            return 0;
        }
        if (this._character == r._character) {
            return this._fontIndex - r._fontIndex;
        }
        return this._character - r._character;
    }

    public int hashCode() {
        assert (false) : "hashCode not designed";
        return 42;
    }

    public String toString() {
        return "character=" + this._character + ",fontIndex=" + this._fontIndex;
    }

    public void serialize(LittleEndianOutput out) {
        out.writeShort(this._character);
        out.writeShort(this._fontIndex);
    }
}

