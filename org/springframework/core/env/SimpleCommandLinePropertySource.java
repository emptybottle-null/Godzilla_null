/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.env;

import java.util.List;
import org.springframework.core.env.CommandLineArgs;
import org.springframework.core.env.CommandLinePropertySource;
import org.springframework.core.env.SimpleCommandLineArgsParser;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

public class SimpleCommandLinePropertySource
extends CommandLinePropertySource<CommandLineArgs> {
    public SimpleCommandLinePropertySource(String ... args) {
        super(new SimpleCommandLineArgsParser().parse(args));
    }

    public SimpleCommandLinePropertySource(String name, String[] args) {
        super(name, new SimpleCommandLineArgsParser().parse(args));
    }

    @Override
    public String[] getPropertyNames() {
        return StringUtils.toStringArray(((CommandLineArgs)this.source).getOptionNames());
    }

    @Override
    protected boolean containsOption(String name) {
        return ((CommandLineArgs)this.source).containsOption(name);
    }

    @Override
    @Nullable
    protected List<String> getOptionValues(String name) {
        return ((CommandLineArgs)this.source).getOptionValues(name);
    }

    @Override
    protected List<String> getNonOptionArgs() {
        return ((CommandLineArgs)this.source).getNonOptionArgs();
    }
}

