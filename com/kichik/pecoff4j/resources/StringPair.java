/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.resources;

import com.kichik.pecoff4j.util.Reflection;
import com.kichik.pecoff4j.util.Strings;

public class StringPair {
    private int length;
    private int valueLength;
    private int type;
    private String key;
    private String value;
    private int padding;

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

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String toString() {
        return Reflection.toString(this);
    }

    public int sizeOf() {
        return 6 + this.padding + Strings.getUtf16Length(this.key) + Strings.getUtf16Length(this.value);
    }

    public int getPadding() {
        return this.padding;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }
}

