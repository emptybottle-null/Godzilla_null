/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.env;

import org.springframework.core.env.CommandLineArgs;

class SimpleCommandLineArgsParser {
    SimpleCommandLineArgsParser() {
    }

    public CommandLineArgs parse(String ... args) {
        CommandLineArgs commandLineArgs = new CommandLineArgs();
        for (String arg : args) {
            if (arg.startsWith("--")) {
                String optionName;
                String optionText = arg.substring(2);
                String optionValue = null;
                int indexOfEqualsSign = optionText.indexOf(61);
                if (indexOfEqualsSign > -1) {
                    optionName = optionText.substring(0, indexOfEqualsSign);
                    optionValue = optionText.substring(indexOfEqualsSign + 1);
                } else {
                    optionName = optionText;
                }
                if (optionName.isEmpty()) {
                    throw new IllegalArgumentException("Invalid argument syntax: " + arg);
                }
                commandLineArgs.addOptionArg(optionName, optionValue);
                continue;
            }
            commandLineArgs.addNonOptionArg(arg);
        }
        return commandLineArgs;
    }
}

