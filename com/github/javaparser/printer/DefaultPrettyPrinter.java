/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.printer.DefaultPrettyPrinterVisitor;
import com.github.javaparser.printer.Printer;
import com.github.javaparser.printer.SourcePrinter;
import com.github.javaparser.printer.configuration.DefaultPrinterConfiguration;
import com.github.javaparser.printer.configuration.PrinterConfiguration;
import java.util.function.Function;

public class DefaultPrettyPrinter
implements Printer {
    private PrinterConfiguration configuration;
    Function<PrinterConfiguration, VoidVisitor<Void>> visitorFactory;

    private static Function<PrinterConfiguration, VoidVisitor<Void>> createDefaultVisitor() {
        PrinterConfiguration configuration = DefaultPrettyPrinter.createDefaultConfiguration();
        return DefaultPrettyPrinter.createDefaultVisitor(configuration);
    }

    private static Function<PrinterConfiguration, VoidVisitor<Void>> createDefaultVisitor(PrinterConfiguration configuration) {
        return config -> new DefaultPrettyPrinterVisitor((PrinterConfiguration)config, new SourcePrinter((PrinterConfiguration)config));
    }

    private static PrinterConfiguration createDefaultConfiguration() {
        return new DefaultPrinterConfiguration();
    }

    public DefaultPrettyPrinter() {
        this(DefaultPrettyPrinter.createDefaultVisitor(), DefaultPrettyPrinter.createDefaultConfiguration());
    }

    public DefaultPrettyPrinter(PrinterConfiguration configuration) {
        this(DefaultPrettyPrinter.createDefaultVisitor(configuration), configuration);
    }

    public DefaultPrettyPrinter(Function<PrinterConfiguration, VoidVisitor<Void>> visitorFactory, PrinterConfiguration configuration) {
        this.configuration = configuration;
        this.visitorFactory = visitorFactory;
    }

    @Override
    public PrinterConfiguration getConfiguration() {
        return this.configuration;
    }

    @Override
    public Printer setConfiguration(PrinterConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    public String print(Node node) {
        VoidVisitor<Void> visitor = this.visitorFactory.apply(this.configuration);
        node.accept(visitor, null);
        return visitor.toString();
    }
}

