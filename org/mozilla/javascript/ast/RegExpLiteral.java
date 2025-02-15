/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.ast;

import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.NodeVisitor;

public class RegExpLiteral
extends AstNode {
    private String value;
    private String flags;

    public RegExpLiteral() {
        this.type = 48;
    }

    public RegExpLiteral(int pos) {
        super(pos);
        this.type = 48;
    }

    public RegExpLiteral(int pos, int len) {
        super(pos, len);
        this.type = 48;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.assertNotNull(value);
        this.value = value;
    }

    public String getFlags() {
        return this.flags;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }

    @Override
    public String toSource(int depth) {
        return this.makeIndent(depth) + "/" + this.value + "/" + (this.flags == null ? "" : this.flags);
    }

    @Override
    public void visit(NodeVisitor v) {
        v.visit(this);
    }
}

