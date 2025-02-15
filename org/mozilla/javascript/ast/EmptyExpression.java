/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.ast;

import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.NodeVisitor;

public class EmptyExpression
extends AstNode {
    public EmptyExpression() {
        this.type = 128;
    }

    public EmptyExpression(int pos) {
        super(pos);
        this.type = 128;
    }

    public EmptyExpression(int pos, int len) {
        super(pos, len);
        this.type = 128;
    }

    @Override
    public String toSource(int depth) {
        return this.makeIndent(depth);
    }

    @Override
    public void visit(NodeVisitor v) {
        v.visit(this);
    }
}

