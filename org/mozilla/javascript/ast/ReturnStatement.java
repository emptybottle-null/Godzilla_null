/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.ast;

import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.NodeVisitor;

public class ReturnStatement
extends AstNode {
    private AstNode returnValue;

    public ReturnStatement() {
        this.type = 4;
    }

    public ReturnStatement(int pos) {
        super(pos);
        this.type = 4;
    }

    public ReturnStatement(int pos, int len) {
        super(pos, len);
        this.type = 4;
    }

    public ReturnStatement(int pos, int len, AstNode returnValue) {
        super(pos, len);
        this.type = 4;
        this.setReturnValue(returnValue);
    }

    public AstNode getReturnValue() {
        return this.returnValue;
    }

    public void setReturnValue(AstNode returnValue) {
        this.returnValue = returnValue;
        if (returnValue != null) {
            returnValue.setParent(this);
        }
    }

    @Override
    public String toSource(int depth) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.makeIndent(depth));
        sb.append("return");
        if (this.returnValue != null) {
            sb.append(" ");
            sb.append(this.returnValue.toSource(0));
        }
        sb.append(";\n");
        return sb.toString();
    }

    @Override
    public void visit(NodeVisitor v) {
        if (v.visit(this) && this.returnValue != null) {
            this.returnValue.visit(v);
        }
    }
}

