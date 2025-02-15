/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.resources;

import com.kichik.pecoff4j.resources.StringTable;
import com.kichik.pecoff4j.util.Strings;
import java.util.ArrayList;
import java.util.List;

public class StringFileInfo {
    private int length;
    private int valueLength;
    private int type;
    private String key;
    private int padding;
    private List<StringTable> tables = new ArrayList<StringTable>();

    public void add(StringTable table) {
        this.tables.add(table);
    }

    public int getCount() {
        return this.tables.size();
    }

    public StringTable getTable(int index) {
        return this.tables.get(index);
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getValueLength() {
        return this.valueLength;
    }

    public void setValueLength(int valueLength) {
        this.valueLength = valueLength;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getPadding() {
        return this.padding;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }

    public int sizeOf() {
        int actualLength = 6 + this.padding + Strings.getUtf16Length(this.key);
        for (StringTable t : this.tables) {
            actualLength += t.sizeOf();
        }
        return actualLength;
    }
}

