/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer.concretesyntaxmodel;

import com.github.javaparser.TokenTypes;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.printer.SourcePrinter;
import com.github.javaparser.printer.concretesyntaxmodel.CsmAttribute;
import com.github.javaparser.printer.concretesyntaxmodel.CsmChar;
import com.github.javaparser.printer.concretesyntaxmodel.CsmComment;
import com.github.javaparser.printer.concretesyntaxmodel.CsmConditional;
import com.github.javaparser.printer.concretesyntaxmodel.CsmIndent;
import com.github.javaparser.printer.concretesyntaxmodel.CsmList;
import com.github.javaparser.printer.concretesyntaxmodel.CsmNone;
import com.github.javaparser.printer.concretesyntaxmodel.CsmOrphanCommentsEnding;
import com.github.javaparser.printer.concretesyntaxmodel.CsmSequence;
import com.github.javaparser.printer.concretesyntaxmodel.CsmSingleReference;
import com.github.javaparser.printer.concretesyntaxmodel.CsmString;
import com.github.javaparser.printer.concretesyntaxmodel.CsmToken;
import com.github.javaparser.printer.concretesyntaxmodel.CsmUnindent;
import com.github.javaparser.utils.LineSeparator;
import java.util.Arrays;
import java.util.List;

public interface CsmElement {
    public void prettyPrint(Node var1, SourcePrinter var2);

    public static CsmElement child(ObservableProperty property) {
        return new CsmSingleReference(property);
    }

    public static CsmElement attribute(ObservableProperty property) {
        return new CsmAttribute(property);
    }

    public static CsmElement sequence(CsmElement ... elements) {
        return new CsmSequence(Arrays.asList(elements));
    }

    public static CsmElement string(int tokenType, String content) {
        return new CsmToken(tokenType, content);
    }

    public static CsmElement string(int tokenType) {
        return new CsmToken(tokenType);
    }

    public static CsmElement stringToken(ObservableProperty property) {
        return new CsmString(property);
    }

    public static CsmElement textBlockToken(ObservableProperty property) {
        return new CsmString(property);
    }

    public static CsmElement charToken(ObservableProperty property) {
        return new CsmChar(property);
    }

    public static CsmElement token(int tokenType) {
        return new CsmToken(tokenType);
    }

    public static CsmElement token(int tokenType, CsmToken.TokenContentCalculator tokenContentCalculator) {
        return new CsmToken(tokenType, tokenContentCalculator);
    }

    public static CsmElement conditional(ObservableProperty property, CsmConditional.Condition condition, CsmElement thenElement) {
        return new CsmConditional(property, condition, thenElement);
    }

    public static CsmElement conditional(ObservableProperty property, CsmConditional.Condition condition, CsmElement thenElement, CsmElement elseElement) {
        return new CsmConditional(property, condition, thenElement, elseElement);
    }

    public static CsmElement conditional(List<ObservableProperty> properties, CsmConditional.Condition condition, CsmElement thenElement, CsmElement elseElement) {
        return new CsmConditional(properties, condition, thenElement, elseElement);
    }

    public static CsmElement space() {
        return new CsmToken(TokenTypes.spaceTokenKind(), " ");
    }

    public static CsmElement semicolon() {
        return new CsmToken(102);
    }

    public static CsmElement comment() {
        return new CsmComment();
    }

    public static CsmElement newline() {
        return CsmElement.newline(LineSeparator.SYSTEM);
    }

    public static CsmElement newline(LineSeparator lineSeparator) {
        return new CsmToken(TokenTypes.eolTokenKind(lineSeparator), lineSeparator.asRawString());
    }

    public static CsmElement none() {
        return new CsmNone();
    }

    public static CsmElement comma() {
        return new CsmToken(103);
    }

    public static CsmElement list(ObservableProperty property) {
        return new CsmList(property);
    }

    public static CsmElement list(ObservableProperty property, CsmElement separator) {
        return new CsmList(property, CsmElement.none(), separator, new CsmNone(), new CsmNone());
    }

    public static CsmElement list(ObservableProperty property, CsmElement separator, CsmElement preceeding, CsmElement following) {
        return new CsmList(property, CsmElement.none(), separator, preceeding, following);
    }

    public static CsmElement list(ObservableProperty property, CsmElement separatorPre, CsmElement separatorPost, CsmElement preceeding, CsmElement following) {
        return new CsmList(property, separatorPre, separatorPost, preceeding, following);
    }

    public static CsmElement orphanCommentsEnding() {
        return new CsmOrphanCommentsEnding();
    }

    public static CsmElement orphanCommentsBeforeThis() {
        return new CsmNone();
    }

    public static CsmElement indent() {
        return new CsmIndent();
    }

    public static CsmElement unindent() {
        return new CsmUnindent();
    }

    public static CsmElement block(CsmElement content) {
        return CsmElement.sequence(CsmElement.token(98), CsmElement.indent(), content, CsmElement.unindent(), CsmElement.token(99));
    }
}

