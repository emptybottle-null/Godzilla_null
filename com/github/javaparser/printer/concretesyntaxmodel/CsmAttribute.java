/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer.concretesyntaxmodel;

import com.github.javaparser.GeneratedJavaParserConstants;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.printer.SourcePrinter;
import com.github.javaparser.printer.concretesyntaxmodel.CsmElement;
import com.github.javaparser.printer.concretesyntaxmodel.PrintingHelper;
import com.github.javaparser.utils.CodeGenerationUtils;

public class CsmAttribute
implements CsmElement {
    private final ObservableProperty property;

    public ObservableProperty getProperty() {
        return this.property;
    }

    public CsmAttribute(ObservableProperty property) {
        this.property = property;
    }

    @Override
    public void prettyPrint(Node node, SourcePrinter printer) {
        Object value = this.property.getRawValue(node);
        printer.print(PrintingHelper.printToString(value));
    }

    public int getTokenType(Node node, String text, String tokenText) {
        switch (this.property) {
            case IDENTIFIER: {
                return 93;
            }
            case TYPE: {
                String expectedImage = "\"" + text.toLowerCase() + "\"";
                for (int i = 0; i < GeneratedJavaParserConstants.tokenImage.length; ++i) {
                    if (!GeneratedJavaParserConstants.tokenImage[i].equals(expectedImage)) continue;
                    return i;
                }
                throw new RuntimeException(CodeGenerationUtils.f("Attribute '%s' does not corresponding to any expected value. Text: %s", this.property.camelCaseName(), text));
            }
            case KEYWORD: 
            case OPERATOR: {
                String expectedImage = "\"" + tokenText.toLowerCase() + "\"";
                for (int i = 0; i < GeneratedJavaParserConstants.tokenImage.length; ++i) {
                    if (!GeneratedJavaParserConstants.tokenImage[i].equals(expectedImage)) continue;
                    return i;
                }
                throw new RuntimeException(CodeGenerationUtils.f("Attribute '%s' does not corresponding to any expected value. Text: %s", this.property.camelCaseName(), tokenText));
            }
            case VALUE: {
                if (node instanceof IntegerLiteralExpr) {
                    return 76;
                }
            }
            case NAME: {
                return 93;
            }
        }
        throw new UnsupportedOperationException("getTokenType does not know how to handle property " + (Object)((Object)this.property) + " with text: " + text);
    }
}

