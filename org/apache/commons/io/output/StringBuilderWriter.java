/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.output;

import java.io.Serializable;
import java.io.Writer;

public class StringBuilderWriter
extends Writer
implements Serializable {
    private static final long serialVersionUID = -146927496096066153L;
    private final StringBuilder builder;

    public StringBuilderWriter() {
        this.builder = new StringBuilder();
    }

    public StringBuilderWriter(int capacity) {
        this.builder = new StringBuilder(capacity);
    }

    public StringBuilderWriter(StringBuilder builder) {
        this.builder = builder != null ? builder : new StringBuilder();
    }

    @Override
    public Writer append(char value) {
        this.builder.append(value);
        return this;
    }

    @Override
    public Writer append(CharSequence value) {
        this.builder.append(value);
        return this;
    }

    @Override
    public Writer append(CharSequence value, int start, int end) {
        this.builder.append(value, start, end);
        return this;
    }

    @Override
    public void close() {
    }

    @Override
    public void flush() {
    }

    @Override
    public void write(String value) {
        if (value != null) {
            this.builder.append(value);
        }
    }

    @Override
    public void write(char[] value, int offset, int length) {
        if (value != null) {
            this.builder.append(value, offset, length);
        }
    }

    public StringBuilder getBuilder() {
        return this.builder;
    }

    public String toString() {
        return this.builder.toString();
    }
}

