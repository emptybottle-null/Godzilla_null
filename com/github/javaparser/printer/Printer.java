/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer;

import com.github.javaparser.ast.Node;
import com.github.javaparser.printer.configuration.PrinterConfiguration;

public interface Printer {
    public String print(Node var1);

    public Printer setConfiguration(PrinterConfiguration var1);

    public PrinterConfiguration getConfiguration();
}

