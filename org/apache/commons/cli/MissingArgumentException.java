/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.cli;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;

public class MissingArgumentException
extends ParseException {
    private static final long serialVersionUID = -7098538588704965017L;
    private Option option;

    public MissingArgumentException(String message) {
        super(message);
    }

    public MissingArgumentException(Option option) {
        this("Missing argument for option: " + option.getKey());
        this.option = option;
    }

    public Option getOption() {
        return this.option;
    }
}

