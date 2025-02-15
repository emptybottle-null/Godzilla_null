/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.ast;

import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.NodeVisitor;

public class ErrorNode
extends AstNode {
    private String message;

    public ErrorNode() {
        this.type = -1;
    }

    public ErrorNode(int pos) {
        super(pos);
        this.type = -1;
    }

    public ErrorNode(int pos, int len) {
        super(pos, len);
        this.type = -1;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toSource(int depth) {
        return "";
    }

    @Override
    public void visit(NodeVisitor v) {
        v.visit(this);
    }
}

