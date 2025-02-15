/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer.configuration;

import com.github.javaparser.printer.configuration.ConfigurationOption;
import java.util.Optional;
import java.util.Set;

public interface PrinterConfiguration {
    public PrinterConfiguration addOption(ConfigurationOption var1);

    public PrinterConfiguration removeOption(ConfigurationOption var1);

    public boolean isActivated(ConfigurationOption var1);

    public Optional<ConfigurationOption> get(ConfigurationOption var1);

    public Set<ConfigurationOption> get();
}

