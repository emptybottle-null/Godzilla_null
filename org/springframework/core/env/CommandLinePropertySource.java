/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.env;

import java.util.List;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

public abstract class CommandLinePropertySource<T>
extends EnumerablePropertySource<T> {
    public static final String COMMAND_LINE_PROPERTY_SOURCE_NAME = "commandLineArgs";
    public static final String DEFAULT_NON_OPTION_ARGS_PROPERTY_NAME = "nonOptionArgs";
    private String nonOptionArgsPropertyName = "nonOptionArgs";

    public CommandLinePropertySource(T source) {
        super(COMMAND_LINE_PROPERTY_SOURCE_NAME, source);
    }

    public CommandLinePropertySource(String name, T source) {
        super(name, source);
    }

    public void setNonOptionArgsPropertyName(String nonOptionArgsPropertyName) {
        this.nonOptionArgsPropertyName = nonOptionArgsPropertyName;
    }

    @Override
    public final boolean containsProperty(String name) {
        if (this.nonOptionArgsPropertyName.equals(name)) {
            return !this.getNonOptionArgs().isEmpty();
        }
        return this.containsOption(name);
    }

    @Override
    @Nullable
    public final String getProperty(String name) {
        if (this.nonOptionArgsPropertyName.equals(name)) {
            List<String> nonOptionArguments = this.getNonOptionArgs();
            if (nonOptionArguments.isEmpty()) {
                return null;
            }
            return StringUtils.collectionToCommaDelimitedString(nonOptionArguments);
        }
        List<String> optionValues = this.getOptionValues(name);
        if (optionValues == null) {
            return null;
        }
        return StringUtils.collectionToCommaDelimitedString(optionValues);
    }

    protected abstract boolean containsOption(String var1);

    @Nullable
    protected abstract List<String> getOptionValues(String var1);

    protected abstract List<String> getNonOptionArgs();
}

