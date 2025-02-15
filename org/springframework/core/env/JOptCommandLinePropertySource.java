/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  joptsimple.OptionSet
 *  joptsimple.OptionSpec
 */
package org.springframework.core.env;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;
import org.springframework.core.env.CommandLinePropertySource;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

public class JOptCommandLinePropertySource
extends CommandLinePropertySource<OptionSet> {
    public JOptCommandLinePropertySource(OptionSet options) {
        super(options);
    }

    public JOptCommandLinePropertySource(String name, OptionSet options) {
        super(name, options);
    }

    @Override
    protected boolean containsOption(String name) {
        return ((OptionSet)this.source).has(name);
    }

    @Override
    public String[] getPropertyNames() {
        ArrayList<String> names = new ArrayList<String>();
        for (OptionSpec spec : ((OptionSet)this.source).specs()) {
            String lastOption = (String)CollectionUtils.lastElement(spec.options());
            if (lastOption == null) continue;
            names.add(lastOption);
        }
        return StringUtils.toStringArray(names);
    }

    @Override
    @Nullable
    public List<String> getOptionValues(String name) {
        List argValues = ((OptionSet)this.source).valuesOf(name);
        ArrayList<String> stringArgValues = new ArrayList<String>();
        for (Object argValue : argValues) {
            stringArgValues.add(argValue.toString());
        }
        if (stringArgValues.isEmpty()) {
            return ((OptionSet)this.source).has(name) ? Collections.emptyList() : null;
        }
        return Collections.unmodifiableList(stringArgValues);
    }

    @Override
    protected List<String> getNonOptionArgs() {
        List argValues = ((OptionSet)this.source).nonOptionArguments();
        ArrayList<String> stringArgValues = new ArrayList<String>();
        for (Object argValue : argValues) {
            stringArgValues.add(argValue.toString());
        }
        return stringArgValues.isEmpty() ? Collections.emptyList() : Collections.unmodifiableList(stringArgValues);
    }
}

