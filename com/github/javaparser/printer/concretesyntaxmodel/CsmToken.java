/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer.concretesyntaxmodel;

import com.github.javaparser.GeneratedJavaParserConstants;
import com.github.javaparser.TokenTypes;
import com.github.javaparser.ast.Node;
import com.github.javaparser.printer.SourcePrinter;
import com.github.javaparser.printer.concretesyntaxmodel.CsmElement;
import com.github.javaparser.utils.LineSeparator;

public class CsmToken
implements CsmElement {
    private final int tokenType;
    private String content;
    private TokenContentCalculator tokenContentCalculator;

    public int getTokenType() {
        return this.tokenType;
    }

    public String getContent(Node node) {
        if (this.tokenContentCalculator != null) {
            return this.tokenContentCalculator.calculate(node);
        }
        return this.content;
    }

    public CsmToken(int tokenType) {
        this.tokenType = tokenType;
        this.content = GeneratedJavaParserConstants.tokenImage[tokenType];
        if (this.content.startsWith("\"")) {
            this.content = this.content.substring(1, this.content.length() - 1);
        }
        if (TokenTypes.isEndOfLineToken(tokenType)) {
            this.content = LineSeparator.lookupEscaped(this.content).get().asRawString();
        } else if (TokenTypes.isWhitespaceButNotEndOfLine(tokenType)) {
            this.content = " ";
        }
    }

    public CsmToken(int tokenType, String content) {
        this.tokenType = tokenType;
        this.content = content;
    }

    public CsmToken(int tokenType, TokenContentCalculator tokenContentCalculator) {
        this.tokenType = tokenType;
        this.tokenContentCalculator = tokenContentCalculator;
    }

    @Override
    public void prettyPrint(Node node, SourcePrinter printer) {
        if (TokenTypes.isEndOfLineToken(this.tokenType)) {
            printer.println();
        } else {
            printer.print(this.getContent(node));
        }
    }

    public String toString() {
        return "token(" + this.content + ")";
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        CsmToken csmToken = (CsmToken)o;
        if (this.tokenType != csmToken.tokenType) {
            return false;
        }
        if (this.content != null ? !this.content.equals(csmToken.content) : csmToken.content != null) {
            return false;
        }
        return this.tokenContentCalculator != null ? this.tokenContentCalculator.equals(csmToken.tokenContentCalculator) : csmToken.tokenContentCalculator == null;
    }

    public int hashCode() {
        int result = this.tokenType;
        result = 31 * result + (this.content != null ? this.content.hashCode() : 0);
        result = 31 * result + (this.tokenContentCalculator != null ? this.tokenContentCalculator.hashCode() : 0);
        return result;
    }

    public boolean isWhiteSpace() {
        return TokenTypes.isWhitespace(this.tokenType);
    }

    public boolean isNewLine() {
        return TokenTypes.isEndOfLineToken(this.tokenType);
    }

    public static interface TokenContentCalculator {
        public String calculate(Node var1);
    }
}

