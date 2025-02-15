/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j;

class NameValue {
    String key;
    String value;

    public NameValue(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String toString() {
        return this.key + "=" + this.value;
    }
}

