/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.codec.language.bm;

public enum NameType {
    ASHKENAZI("ash"),
    GENERIC("gen"),
    SEPHARDIC("sep");

    private final String name;

    private NameType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}

