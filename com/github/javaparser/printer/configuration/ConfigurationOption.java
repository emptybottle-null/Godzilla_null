/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer.configuration;

public interface ConfigurationOption {
    public ConfigurationOption value(Object var1);

    public boolean hasValue();

    public Integer asInteger();

    public String asString();

    public Boolean asBoolean();

    public <T> T asValue();
}

