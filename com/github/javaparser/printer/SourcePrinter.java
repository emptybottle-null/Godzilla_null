/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer;

import com.github.javaparser.Position;
import com.github.javaparser.printer.configuration.DefaultConfigurationOption;
import com.github.javaparser.printer.configuration.DefaultPrinterConfiguration;
import com.github.javaparser.printer.configuration.Indentation;
import com.github.javaparser.printer.configuration.PrettyPrinterConfiguration;
import com.github.javaparser.printer.configuration.PrinterConfiguration;
import com.github.javaparser.utils.Utils;
import java.util.Deque;
import java.util.LinkedList;

public class SourcePrinter {
    private String endOfLineCharacter;
    private Indentation indentation;
    private final Deque<String> indents = new LinkedList<String>();
    private final Deque<String> reindentedIndents = new LinkedList<String>();
    private String lastPrintedIndent = "";
    private final StringBuilder buf = new StringBuilder();
    private Position cursor = new Position(1, 0);
    private boolean indented = false;

    SourcePrinter() {
        this(new DefaultPrinterConfiguration());
    }

    SourcePrinter(PrettyPrinterConfiguration configuration) {
        this(configuration.getIndentation(), configuration.getEndOfLineCharacter());
    }

    SourcePrinter(PrinterConfiguration configuration) {
        this((Indentation)configuration.get(new DefaultConfigurationOption(DefaultPrinterConfiguration.ConfigOption.INDENTATION)).get().asValue(), configuration.get(new DefaultConfigurationOption(DefaultPrinterConfiguration.ConfigOption.END_OF_LINE_CHARACTER)).get().asString());
    }

    SourcePrinter(Indentation indentation, String eol) {
        this.indentation = indentation;
        this.endOfLineCharacter = eol;
        this.indents.push("");
    }

    public SourcePrinter indent() {
        String currentIndent = this.indents.peek();
        switch (this.indentation.getType()) {
            case SPACES: 
            case TABS_WITH_SPACE_ALIGN: {
                this.indents.push(currentIndent + this.indentation.getIndent());
                break;
            }
            case TABS: {
                this.indents.push(this.indentation.getIndent() + currentIndent);
                break;
            }
            default: {
                throw new AssertionError((Object)"Unhandled indent type");
            }
        }
        return this;
    }

    public SourcePrinter indentWithAlignTo(int column) {
        this.indents.push(this.calculateIndentWithAlignTo(column));
        return this;
    }

    private String calculateIndentWithAlignTo(int column) {
        if (column < this.lastPrintedIndent.length()) {
            throw new IllegalStateException("Attempt to indent less than the previous indent.");
        }
        StringBuilder newIndent = new StringBuilder(this.lastPrintedIndent);
        switch (this.indentation.getType()) {
            case SPACES: 
            case TABS_WITH_SPACE_ALIGN: {
                while (newIndent.length() < column) {
                    newIndent.append(Indentation.IndentType.SPACES.getCar());
                }
                break;
            }
            case TABS: {
                Indentation.IndentType currentIndentType = this.indentation.getType();
                int logicalIndentLength = newIndent.length();
                while (logicalIndentLength + currentIndentType.getWidth() <= column) {
                    newIndent.insert(0, currentIndentType.getCar());
                    logicalIndentLength += currentIndentType.getWidth();
                }
                while (logicalIndentLength < column) {
                    newIndent.append(Indentation.IndentType.SPACES.getCar());
                    ++logicalIndentLength;
                }
                StringBuilder fullTab = new StringBuilder();
                for (int i = 0; i < currentIndentType.getWidth(); ++i) {
                    fullTab.append(Indentation.IndentType.SPACES.getCar());
                }
                String fullTabString = fullTab.toString();
                if (newIndent.length() < currentIndentType.getWidth() || !newIndent.substring(newIndent.length() - currentIndentType.getWidth()).equals(fullTabString)) break;
                int i = newIndent.indexOf(fullTabString);
                newIndent.replace(i, i + currentIndentType.getWidth(), currentIndentType.getCar().toString());
                break;
            }
            default: {
                throw new AssertionError((Object)"Unhandled indent type");
            }
        }
        return newIndent.toString();
    }

    public SourcePrinter unindent() {
        if (this.indents.isEmpty()) {
            throw new IllegalStateException("Indent/unindent calls are not well-balanced.");
        }
        this.indents.pop();
        return this;
    }

    private void append(String arg) {
        this.buf.append(arg);
        this.cursor = this.cursor.withColumn(this.cursor.column + arg.length());
    }

    public SourcePrinter print(String arg) {
        if (!this.indented) {
            this.lastPrintedIndent = this.indents.peek();
            this.append(this.lastPrintedIndent);
            this.indented = true;
        }
        this.append(arg);
        return this;
    }

    public SourcePrinter println(String arg) {
        this.print(arg);
        this.println();
        return this;
    }

    public SourcePrinter println() {
        this.buf.append(this.endOfLineCharacter);
        this.cursor = new Position(this.cursor.line + 1, 0);
        this.indented = false;
        return this;
    }

    public Position getCursor() {
        return this.cursor;
    }

    @Deprecated
    public String getSource() {
        return this.toString();
    }

    public String toString() {
        return this.buf.toString();
    }

    public String normalizeEolInTextBlock(String content) {
        return Utils.normalizeEolInTextBlock(content, this.endOfLineCharacter);
    }

    public void reindentWithAlignToCursor() {
        String newIndent = this.calculateIndentWithAlignTo(this.cursor.column);
        this.reindentedIndents.push(this.indents.pop());
        this.indents.push(newIndent);
    }

    public void reindentToPreviousLevel() {
        if (this.reindentedIndents.isEmpty()) {
            throw new IllegalStateException("Reindent calls are not well-balanced.");
        }
        this.indents.pop();
        this.indents.push(this.reindentedIndents.pop());
    }

    public void duplicateIndent() {
        this.indents.push(this.indents.peek());
    }
}

