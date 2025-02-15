/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.ast;

import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.NodeVisitor;

public class StringLiteral
extends AstNode {
    private String value;
    private char quoteChar;

    public StringLiteral() {
        this.type = 41;
    }

    public StringLiteral(int pos) {
        super(pos);
        this.type = 41;
    }

    public StringLiteral(int pos, int len) {
        super(pos, len);
        this.type = 41;
    }

    public String getValue() {
        return this.value;
    }

    public String getValue(boolean includeQuotes) {
        if (!includeQuotes) {
            return this.value;
        }
        return this.quoteChar + this.value + this.quoteChar;
    }

    public void setValue(String value) {
        this.assertNotNull(value);
        this.value = value;
    }

    public char getQuoteCharacter() {
        return this.quoteChar;
    }

    public void setQuoteCharacter(char c) {
        this.quoteChar = c;
    }

    @Override
    public String toSource(int depth) {
        return this.makeIndent(depth) + this.quoteChar + ScriptRuntime.escapeString(this.value, this.quoteChar) + this.quoteChar;
    }

    @Override
    public void visit(NodeVisitor v) {
        v.visit(this);
    }
}

