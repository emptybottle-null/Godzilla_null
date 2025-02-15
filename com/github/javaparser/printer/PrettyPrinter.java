/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.printer.PrettyPrintVisitor;
import com.github.javaparser.printer.Printer;
import com.github.javaparser.printer.configuration.PrettyPrinterConfiguration;
import com.github.javaparser.printer.configuration.PrinterConfiguration;
import java.util.function.Function;

@Deprecated
public class PrettyPrinter
implements Printer {
    private PrinterConfiguration configuration;
    private Function<PrettyPrinterConfiguration, VoidVisitor<Void>> visitorFactory;

    public PrettyPrinter() {
        this(new PrettyPrinterConfiguration());
    }

    public PrettyPrinter(PrettyPrinterConfiguration configuration) {
        this(configuration, PrettyPrintVisitor::new);
    }

    public PrettyPrinter(PrettyPrinterConfiguration configuration, Function<PrettyPrinterConfiguration, VoidVisitor<Void>> visitorFactory) {
        this.configuration = configuration;
        this.visitorFactory = visitorFactory;
    }

    @Override
    public PrinterConfiguration getConfiguration() {
        return this.configuration;
    }

    @Override
    public Printer setConfiguration(PrinterConfiguration configuration) {
        if (!(configuration instanceof PrettyPrinterConfiguration)) {
            throw new IllegalArgumentException("PrettyPrinter must be configured with a PrettyPrinterConfiguration class");
        }
        this.configuration = configuration;
        return this;
    }

    @Override
    public String print(Node node) {
        VoidVisitor<Void> visitor = this.visitorFactory.apply((PrettyPrinterConfiguration)this.configuration);
        node.accept(visitor, null);
        return visitor.toString();
    }
}

