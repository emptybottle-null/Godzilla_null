/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer.configuration;

import com.github.javaparser.printer.configuration.ConfigurationOption;
import com.github.javaparser.printer.configuration.DefaultConfigurationOption;
import com.github.javaparser.printer.configuration.Indentation;
import com.github.javaparser.printer.configuration.PrinterConfiguration;
import com.github.javaparser.utils.Utils;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class DefaultPrinterConfiguration
implements PrinterConfiguration {
    private Set<ConfigurationOption> defaultOptions;

    public DefaultPrinterConfiguration() {
        this.defaultOptions = new HashSet<DefaultConfigurationOption>(Arrays.asList(new DefaultConfigurationOption(ConfigOption.PRINT_COMMENTS, ConfigOption.PRINT_COMMENTS.defaultValue), new DefaultConfigurationOption(ConfigOption.PRINT_JAVADOC, ConfigOption.PRINT_JAVADOC.defaultValue), new DefaultConfigurationOption(ConfigOption.SPACE_AROUND_OPERATORS, ConfigOption.SPACE_AROUND_OPERATORS.defaultValue), new DefaultConfigurationOption(ConfigOption.INDENT_CASE_IN_SWITCH, ConfigOption.INDENT_CASE_IN_SWITCH.defaultValue), new DefaultConfigurationOption(ConfigOption.MAX_ENUM_CONSTANTS_TO_ALIGN_HORIZONTALLY, ConfigOption.MAX_ENUM_CONSTANTS_TO_ALIGN_HORIZONTALLY.defaultValue), new DefaultConfigurationOption(ConfigOption.END_OF_LINE_CHARACTER, ConfigOption.END_OF_LINE_CHARACTER.defaultValue), new DefaultConfigurationOption(ConfigOption.INDENTATION, ConfigOption.INDENTATION.defaultValue)));
    }

    @Override
    public PrinterConfiguration addOption(ConfigurationOption option) {
        this.removeOption(option);
        this.defaultOptions.add(option);
        return this;
    }

    @Override
    public PrinterConfiguration removeOption(ConfigurationOption option) {
        this.defaultOptions.remove(option);
        return this;
    }

    @Override
    public boolean isActivated(ConfigurationOption option) {
        return this.defaultOptions.contains(option);
    }

    @Override
    public Optional<ConfigurationOption> get(ConfigurationOption option) {
        return this.defaultOptions.stream().filter(o -> o.equals(option)).findFirst();
    }

    @Override
    public Set<ConfigurationOption> get() {
        return this.defaultOptions;
    }

    public static enum ConfigOption {
        ORDER_IMPORTS(Boolean.class),
        PRINT_COMMENTS(Boolean.class),
        PRINT_JAVADOC(Boolean.class),
        SPACE_AROUND_OPERATORS(Boolean.class),
        COLUMN_ALIGN_PARAMETERS(Boolean.class),
        COLUMN_ALIGN_FIRST_METHOD_CHAIN(Boolean.class),
        INDENT_CASE_IN_SWITCH(Boolean.class),
        MAX_ENUM_CONSTANTS_TO_ALIGN_HORIZONTALLY(Integer.class, 5),
        END_OF_LINE_CHARACTER(String.class, Utils.SYSTEM_EOL),
        INDENTATION(Indentation.class, new Indentation(Indentation.IndentType.SPACES, 4));

        Object defaultValue;
        Class type;

        private ConfigOption(Class clazz) {
            this.type = clazz;
        }

        private ConfigOption(Class clazz, Object value) {
            this.type = clazz;
            if (!this.type.isAssignableFrom(value.getClass())) {
                throw new IllegalArgumentException(String.format("%s is not an instance of %s", value, this.type.getName()));
            }
            this.defaultValue = value;
        }
    }
}

