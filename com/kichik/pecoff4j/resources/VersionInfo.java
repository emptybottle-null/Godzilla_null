/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.resources;

import com.kichik.pecoff4j.resources.FixedFileInfo;
import com.kichik.pecoff4j.resources.StringFileInfo;
import com.kichik.pecoff4j.resources.VarFileInfo;

public class VersionInfo {
    private int length;
    private int valueLength;
    private int type;
    private String key;
    private FixedFileInfo fixedFileInfo;
    private StringFileInfo stringFileInfo;
    private VarFileInfo varFileInfo;

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

    public FixedFileInfo getFixedFileInfo() {
        return this.fixedFileInfo;
    }

    public void setFixedFileInfo(FixedFileInfo fixedFileInfo) {
        this.fixedFileInfo = fixedFileInfo;
    }

    public StringFileInfo getStringFileInfo() {
        return this.stringFileInfo;
    }

    public void setStringFileInfo(StringFileInfo stringFileInfo) {
        this.stringFileInfo = stringFileInfo;
    }

    public VarFileInfo getVarFileInfo() {
        return this.varFileInfo;
    }

    public void setVarFileInfo(VarFileInfo varFileInfo) {
        this.varFileInfo = varFileInfo;
    }
}

