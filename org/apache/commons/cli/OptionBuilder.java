/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.cli;

import org.apache.commons.cli.Option;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public final class OptionBuilder {
    private static String longopt;
    private static String description;
    private static String argName;
    private static boolean required;
    private static int numberOfArgs;
    private static Class<?> type;
    private static boolean optionalArg;
    private static char valuesep;
    private static final OptionBuilder INSTANCE;

    private OptionBuilder() {
    }

    private static void reset() {
        description = null;
        argName = null;
        longopt = null;
        type = String.class;
        required = false;
        numberOfArgs = -1;
        optionalArg = false;
        valuesep = '\u0000';
    }

    public static OptionBuilder withLongOpt(String newLongopt) {
        longopt = newLongopt;
        return INSTANCE;
    }

    public static OptionBuilder hasArg() {
        numberOfArgs = 1;
        return INSTANCE;
    }

    public static OptionBuilder hasArg(boolean hasArg) {
        numberOfArgs = hasArg ? 1 : -1;
        return INSTANCE;
    }

    public static OptionBuilder withArgName(String name) {
        argName = name;
        return INSTANCE;
    }

    public static OptionBuilder isRequired() {
        required = true;
        return INSTANCE;
    }

    public static OptionBuilder withValueSeparator(char sep) {
        valuesep = sep;
        return INSTANCE;
    }

    public static OptionBuilder withValueSeparator() {
        valuesep = (char)61;
        return INSTANCE;
    }

    public static OptionBuilder isRequired(boolean newRequired) {
        required = newRequired;
        return INSTANCE;
    }

    public static OptionBuilder hasArgs() {
        numberOfArgs = -2;
        return INSTANCE;
    }

    public static OptionBuilder hasArgs(int num) {
        numberOfArgs = num;
        return INSTANCE;
    }

    public static OptionBuilder hasOptionalArg() {
        numberOfArgs = 1;
        optionalArg = true;
        return INSTANCE;
    }

    public static OptionBuilder hasOptionalArgs() {
        numberOfArgs = -2;
        optionalArg = true;
        return INSTANCE;
    }

    public static OptionBuilder hasOptionalArgs(int numArgs) {
        numberOfArgs = numArgs;
        optionalArg = true;
        return INSTANCE;
    }

    @Deprecated
    public static OptionBuilder withType(Object newType) {
        return OptionBuilder.withType((Class)newType);
    }

    public static OptionBuilder withType(Class<?> newType) {
        type = newType;
        return INSTANCE;
    }

    public static OptionBuilder withDescription(String newDescription) {
        description = newDescription;
        return INSTANCE;
    }

    public static Option create(char opt) throws IllegalArgumentException {
        return OptionBuilder.create(String.valueOf(opt));
    }

    public static Option create() throws IllegalArgumentException {
        if (longopt == null) {
            OptionBuilder.reset();
            throw new IllegalArgumentException("must specify longopt");
        }
        return OptionBuilder.create(null);
    }

    public static Option create(String opt) throws IllegalArgumentException {
        Option option = null;
        try {
            option = new Option(opt, description);
            option.setLongOpt(longopt);
            option.setRequired(required);
            option.setOptionalArg(optionalArg);
            option.setArgs(numberOfArgs);
            option.setType(type);
            option.setValueSeparator(valuesep);
            option.setArgName(argName);
        } finally {
            OptionBuilder.reset();
        }
        return option;
    }

    static {
        numberOfArgs = -1;
        INSTANCE = new OptionBuilder();
        OptionBuilder.reset();
    }
}

