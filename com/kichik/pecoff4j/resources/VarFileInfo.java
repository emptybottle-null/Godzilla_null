/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.resources;

import java.util.ArrayList;
import java.util.List;

public class VarFileInfo {
    private String key;
    private List<String> names = new ArrayList<String>();
    private List<String> values = new ArrayList<String>();

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int size() {
        return this.names.size();
    }

    public String getName(int index) {
        return this.names.get(index);
    }

    public String getValue(int index) {
        return this.values.get(index);
    }

    public void add(String name, String value) {
        this.names.add(name);
        this.values.add(value);
    }

    public void clear() {
        this.names.clear();
        this.values.clear();
    }
}

