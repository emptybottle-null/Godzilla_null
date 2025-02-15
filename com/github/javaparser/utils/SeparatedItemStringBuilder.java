/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.utils;

public class SeparatedItemStringBuilder {
    private final String separator;
    private final String postfix;
    private boolean hasItems = false;
    private StringBuilder builder;

    public SeparatedItemStringBuilder(String prefix, String separator, String postfix) {
        this.builder = new StringBuilder(prefix);
        this.separator = separator;
        this.postfix = postfix;
    }

    public SeparatedItemStringBuilder append(CharSequence format, Object ... args) {
        if (this.hasItems) {
            this.builder.append(this.separator);
        }
        this.builder.append(String.format(format.toString(), args));
        this.hasItems = true;
        return this;
    }

    public boolean hasItems() {
        return this.hasItems;
    }

    public String toString() {
        return this.builder.toString() + this.postfix;
    }
}

