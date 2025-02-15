/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer.configuration;

import com.github.javaparser.printer.configuration.ConfigurationOption;
import com.github.javaparser.printer.configuration.DefaultConfigurationOption;
import com.github.javaparser.printer.configuration.DefaultPrinterConfiguration;
import com.github.javaparser.printer.configuration.Indentation;
import com.github.javaparser.printer.configuration.PrinterConfiguration;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.Set;

@Deprecated
public class PrettyPrinterConfiguration
implements PrinterConfiguration {
    PrinterConfiguration wrappedConfiguration = new DefaultPrinterConfiguration();

    public Indentation getIndentation() {
        return (Indentation)this.wrappedConfiguration.get(new DefaultConfigurationOption(DefaultPrinterConfiguration.ConfigOption.INDENTATION)).get().asValue();
    }

    public PrettyPrinterConfiguration setIndentation(Indentation indentation) {
        this.wrappedConfiguration.addOption(new DefaultConfigurationOption(DefaultPrinterConfiguration.ConfigOption.INDENTATION, indentation));
        return this;
    }

    @Deprecated
    public String getIndent() {
        return this.getIndentation().getIndent();
    }

    @Deprecated
    public int getIndentSize() {
        return this.getIndentation().getSize();
    }

    @Deprecated
    public PrettyPrinterConfiguration setIndentSize(int indentSize) {
        Indentation indentation = this.getIndentation().setSize(Utils.assertNonNegative(indentSize));
        this.setIndentation(indentation);
        return this;
    }

    @Deprecated
    public Indentation.IndentType getIndentType() {
        return this.getIndentation().getType();
    }

    @Deprecated
    public PrettyPrinterConfiguration setIndentType(Indentation.IndentType indentType) {
        Indentation indentation = this.getIndentation().setType(Utils.assertNotNull(indentType));
        this.setIndentation(indentation);
        return this;
    }

    @Deprecated
    public int getTabWidth() {
        return this.getIndentation().getSize();
    }

    @Deprecated
    public PrettyPrinterConfiguration setTabWidth(int tabWidth) {
        this.setIndentSize(Utils.assertPositive(tabWidth));
        return this;
    }

    public boolean isOrderImports() {
        return this.wrappedConfiguration.get(new DefaultConfigurationOption(DefaultPrinterConfiguration.ConfigOption.ORDER_IMPORTS)).isPresent();
    }

    public boolean isPrintComments() {
        return this.wrappedConfiguration.get(new DefaultConfigurationOption(DefaultPrinterConfiguration.ConfigOption.PRINT_COMMENTS)).isPresent();
    }

    public boolean isIgnoreComments() {
        return !this.wrappedConfiguration.get(new DefaultConfigurationOption(DefaultPrinterConfiguration.ConfigOption.PRINT_COMMENTS)).isPresent();
    }

    public boolean isSpaceAroundOperators() {
        return this.wrappedConfiguration.get(new DefaultConfigurationOption(DefaultPrinterConfiguration.ConfigOption.SPACE_AROUND_OPERATORS)).isPresent();
    }

    public boolean isPrintJavadoc() {
        return this.wrappedConfiguration.get(new DefaultConfigurationOption(DefaultPrinterConfiguration.ConfigOption.PRINT_JAVADOC)).isPresent();
    }

    public boolean isColumnAlignParameters() {
        return this.wrappedConfiguration.get(new DefaultConfigurationOption(DefaultPrinterConfiguration.ConfigOption.COLUMN_ALIGN_PARAMETERS)).isPresent();
    }

    public boolean isColumnAlignFirstMethodChain() {
        return this.wrappedConfiguration.get(new DefaultConfigurationOption(DefaultPrinterConfiguration.ConfigOption.COLUMN_ALIGN_FIRST_METHOD_CHAIN)).isPresent();
    }

    public boolean isIndentCaseInSwitch() {
        return this.wrappedConfiguration.get(new DefaultConfigurationOption(DefaultPrinterConfiguration.ConfigOption.INDENT_CASE_IN_SWITCH)).isPresent();
    }

    public PrettyPrinterConfiguration setPrintComments(boolean printComments) {
        this.wrappedConfiguration = printComments ? this.addOption(new DefaultConfigurationOption(DefaultPrinterConfiguration.ConfigOption.PRINT_COMMENTS)) : this.removeOption(new DefaultConfigurationOption(DefaultPrinterConfiguration.ConfigOption.PRINT_COMMENTS));
        return this;
    }

    public PrettyPrinterConfiguration setPrintJavadoc(boolean printJavadoc) {
        this.wrappedConfiguration = printJavadoc ? this.addOption(new DefaultConfigurationOption(DefaultPrinterConfiguration.ConfigOption.PRINT_JAVADOC)) : this.removeOption(new DefaultConfigurationOption(DefaultPrinterConfiguration.ConfigOption.PRINT_JAVADOC));
        return this;
    }

    public PrettyPrinterConfiguration setSpaceAroundOperators(boolean spaceAroundOperators) {
        this.wrappedConfiguration = spaceAroundOperators ? this.addOption(new DefaultConfigurationOption(DefaultPrinterConfiguration.ConfigOption.SPACE_AROUND_OPERATORS)) : this.removeOption(new DefaultConfigurationOption(DefaultPrinterConfiguration.ConfigOption.SPACE_AROUND_OPERATORS));
        return this;
    }

    public PrettyPrinterConfiguration setColumnAlignParameters(boolean columnAlignParameters) {
        this.wrappedConfiguration = columnAlignParameters ? this.addOption(new DefaultConfigurationOption(DefaultPrinterConfiguration.ConfigOption.COLUMN_ALIGN_PARAMETERS)) : this.removeOption(new DefaultConfigurationOption(DefaultPrinterConfiguration.ConfigOption.COLUMN_ALIGN_PARAMETERS));
        return this;
    }

    public PrettyPrinterConfiguration setColumnAlignFirstMethodChain(boolean columnAlignFirstMethodChain) {
        this.wrappedConfiguration = columnAlignFirstMethodChain ? this.addOption(new DefaultConfigurationOption(DefaultPrinterConfiguration.ConfigOption.COLUMN_ALIGN_FIRST_METHOD_CHAIN)) : this.removeOption(new DefaultConfigurationOption(DefaultPrinterConfiguration.ConfigOption.COLUMN_ALIGN_FIRST_METHOD_CHAIN));
        return this;
    }

    public PrettyPrinterConfiguration setIndentCaseInSwitch(boolean indentInSwitch) {
        this.wrappedConfiguration = indentInSwitch ? this.addOption(new DefaultConfigurationOption(DefaultPrinterConfiguration.ConfigOption.INDENT_CASE_IN_SWITCH)) : this.removeOption(new DefaultConfigurationOption(DefaultPrinterConfiguration.ConfigOption.INDENT_CASE_IN_SWITCH));
        return this;
    }

    public String getEndOfLineCharacter() {
        return (String)this.wrappedConfiguration.get(new DefaultConfigurationOption(DefaultPrinterConfiguration.ConfigOption.END_OF_LINE_CHARACTER)).get().asValue();
    }

    public PrettyPrinterConfiguration setEndOfLineCharacter(String endOfLineCharacter) {
        this.addOption(new DefaultConfigurationOption(DefaultPrinterConfiguration.ConfigOption.END_OF_LINE_CHARACTER, endOfLineCharacter));
        return this;
    }

    public PrettyPrinterConfiguration setOrderImports(boolean orderImports) {
        this.wrappedConfiguration = orderImports ? this.addOption(new DefaultConfigurationOption(DefaultPrinterConfiguration.ConfigOption.ORDER_IMPORTS)) : this.removeOption(new DefaultConfigurationOption(DefaultPrinterConfiguration.ConfigOption.ORDER_IMPORTS));
        return this;
    }

    public int getMaxEnumConstantsToAlignHorizontally() {
        return this.wrappedConfiguration.get(new DefaultConfigurationOption(DefaultPrinterConfiguration.ConfigOption.MAX_ENUM_CONSTANTS_TO_ALIGN_HORIZONTALLY)).get().asInteger();
    }

    public PrettyPrinterConfiguration setMaxEnumConstantsToAlignHorizontally(int maxEnumConstantsToAlignHorizontally) {
        this.addOption(new DefaultConfigurationOption(DefaultPrinterConfiguration.ConfigOption.MAX_ENUM_CONSTANTS_TO_ALIGN_HORIZONTALLY, maxEnumConstantsToAlignHorizontally));
        return this;
    }

    @Override
    public PrinterConfiguration addOption(ConfigurationOption option) {
        return this.wrappedConfiguration.addOption(option);
    }

    @Override
    public boolean isActivated(ConfigurationOption option) {
        return this.wrappedConfiguration.isActivated(option);
    }

    @Override
    public Optional<ConfigurationOption> get(ConfigurationOption option) {
        return this.wrappedConfiguration.get(option);
    }

    @Override
    public Set<ConfigurationOption> get() {
        return this.wrappedConfiguration.get();
    }

    @Override
    public PrinterConfiguration removeOption(ConfigurationOption option) {
        return this.wrappedConfiguration.removeOption(option);
    }
}

