/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.cli;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.ParseException;

public class AlreadySelectedException
extends ParseException {
    private static final long serialVersionUID = 3674381532418544760L;
    private OptionGroup group;
    private Option option;

    public AlreadySelectedException(String message) {
        super(message);
    }

    public AlreadySelectedException(OptionGroup group, Option option) {
        this("The option '" + option.getKey() + "' was specified but an option from this group has already been selected: '" + group.getSelected() + "'");
        this.group = group;
        this.option = option;
    }

    public OptionGroup getOptionGroup() {
        return this.group;
    }

    public Option getOption() {
        return this.option;
    }
}

