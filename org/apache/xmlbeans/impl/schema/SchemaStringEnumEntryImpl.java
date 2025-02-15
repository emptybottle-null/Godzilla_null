/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.schema;

import org.apache.xmlbeans.SchemaStringEnumEntry;

public class SchemaStringEnumEntryImpl
implements SchemaStringEnumEntry {
    private String _string;
    private int _int;
    private String _enumName;

    public SchemaStringEnumEntryImpl(String str, int i, String enumName) {
        this._string = str;
        this._int = i;
        this._enumName = enumName;
    }

    @Override
    public String getString() {
        return this._string;
    }

    @Override
    public int getIntValue() {
        return this._int;
    }

    @Override
    public String getEnumName() {
        return this._enumName;
    }
}

