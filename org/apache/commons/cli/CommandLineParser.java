/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public interface CommandLineParser {
    public CommandLine parse(Options var1, String[] var2) throws ParseException;

    public CommandLine parse(Options var1, String[] var2, boolean var3) throws ParseException;
}

