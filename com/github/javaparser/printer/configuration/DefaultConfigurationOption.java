/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer.configuration;

import com.github.javaparser.printer.configuration.ConfigurationOption;
import com.github.javaparser.printer.configuration.DefaultPrinterConfiguration;
import com.github.javaparser.utils.Utils;

public class DefaultConfigurationOption
implements ConfigurationOption {
    DefaultPrinterConfiguration.ConfigOption configOption;
    Object currentValue;

    public DefaultConfigurationOption(DefaultPrinterConfiguration.ConfigOption configOption) {
        this(configOption, null);
    }

    public DefaultConfigurationOption(DefaultPrinterConfiguration.ConfigOption configOption, Object value) {
        this.configOption = configOption;
        if (value != null) {
            this.value(value);
        }
    }

    public boolean equals(Object o) {
        if (o == null || !(o instanceof DefaultConfigurationOption)) {
            return false;
        }
        DefaultConfigurationOption other = (DefaultConfigurationOption)o;
        return this.configOption.equals((Object)other.configOption);
    }

    public int hashCode() {
        return this.configOption.hashCode();
    }

    @Override
    public ConfigurationOption value(Object value) {
        Utils.assertNotNull(value);
        this.currentValue = value;
        if (!this.configOption.type.isAssignableFrom(value.getClass())) {
            throw new IllegalArgumentException(String.format("%s is not an instance of %s", value, this.configOption.type.getName()));
        }
        return this;
    }

    @Override
    public boolean hasValue() {
        return this.currentValue != null;
    }

    @Override
    public Integer asInteger() {
        return (Integer)this.cast();
    }

    @Override
    public String asString() {
        return (String)this.cast();
    }

    @Override
    public Boolean asBoolean() {
        return (Boolean)this.cast();
    }

    @Override
    public <T> T asValue() {
        return this.cast();
    }

    private <T> T cast() {
        if (!this.hasValue()) {
            throw new IllegalArgumentException(String.format("The option %s has no currentValue", this.configOption.name()));
        }
        if (this.configOption.type.isAssignableFrom(this.currentValue.getClass())) {
            return this.configOption.type.cast(this.currentValue);
        }
        throw new IllegalArgumentException(String.format("%s cannot be cast to %s", this.currentValue, this.configOption.type.getName()));
    }
}

