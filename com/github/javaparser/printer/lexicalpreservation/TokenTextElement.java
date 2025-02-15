/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer.lexicalpreservation;

import com.github.javaparser.JavaToken;
import com.github.javaparser.Range;
import com.github.javaparser.ast.Node;
import com.github.javaparser.printer.lexicalpreservation.TextElement;
import java.util.Optional;

class TokenTextElement
extends TextElement {
    private final JavaToken token;

    TokenTextElement(JavaToken token) {
        this.token = token;
    }

    TokenTextElement(int tokenKind, String text) {
        this(new JavaToken(tokenKind, text));
    }

    TokenTextElement(int tokenKind) {
        this(new JavaToken(tokenKind));
    }

    @Override
    String expand() {
        return this.token.getText();
    }

    String getText() {
        return this.token.getText();
    }

    int getTokenKind() {
        return this.token.getKind();
    }

    public JavaToken getToken() {
        return this.token;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        TokenTextElement that = (TokenTextElement)o;
        return this.token.equals(that.token);
    }

    public int hashCode() {
        return this.token.hashCode();
    }

    public String toString() {
        return this.token.toString();
    }

    @Override
    boolean isToken(int tokenKind) {
        return this.token.getKind() == tokenKind;
    }

    @Override
    boolean isNode(Node node) {
        return false;
    }

    @Override
    public boolean isWhiteSpace() {
        return this.token.getCategory().isWhitespace();
    }

    @Override
    public boolean isSpaceOrTab() {
        return this.token.getCategory().isWhitespaceButNotEndOfLine();
    }

    @Override
    public boolean isComment() {
        return this.token.getCategory().isComment();
    }

    @Override
    public boolean isSeparator() {
        return this.token.getCategory().isSeparator();
    }

    @Override
    public boolean isNewline() {
        return this.token.getCategory().isEndOfLine();
    }

    @Override
    public boolean isChildOfClass(Class<? extends Node> nodeClass) {
        return false;
    }

    @Override
    public boolean isIdentifier() {
        return this.getToken().getCategory().isIdentifier();
    }

    @Override
    public boolean isLiteral() {
        return this.getToken().getCategory().isLiteral();
    }

    @Override
    public boolean isPrimitive() {
        return JavaToken.Kind.valueOf(this.getTokenKind()).isPrimitive();
    }

    @Override
    Optional<Range> getRange() {
        return this.token.getRange();
    }
}

