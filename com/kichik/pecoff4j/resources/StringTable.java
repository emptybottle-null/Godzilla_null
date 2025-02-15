/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.resources;

import com.kichik.pecoff4j.resources.StringPair;
import com.kichik.pecoff4j.util.Strings;
import java.util.ArrayList;
import java.util.List;

public class StringTable {
    private int length;
    private int valueLength;
    private int type;
    private String key;
    private int padding;
    private List<StringPair> strings = new ArrayList<StringPair>();

    public void add(StringPair string) {
        this.strings.add(string);
    }

    public int getCount() {
        return this.strings.size();
    }

    public StringPair getString(int index) {
        return this.strings.get(index);
    }

    public int getLength() {
        return this.length;
    }

    public int getValueLength() {
        return this.valueLength;
    }

    public int getType() {
        return this.type;
    }

    public int getPadding() {
        return this.padding;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setValueLength(int valueLength) {
        this.valueLength = valueLength;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }

    public int sizeOf() {
        int actualLength = 6 + this.padding + Strings.getUtf16Length(this.key);
        for (StringPair s : this.strings) {
            actualLength += s.sizeOf();
        }
        return actualLength;
    }
}

