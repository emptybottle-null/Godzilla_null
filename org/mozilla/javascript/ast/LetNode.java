/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.ast;

import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.NodeVisitor;
import org.mozilla.javascript.ast.Scope;
import org.mozilla.javascript.ast.VariableDeclaration;

public class LetNode
extends Scope {
    private VariableDeclaration variables;
    private AstNode body;
    private int lp = -1;
    private int rp = -1;

    public LetNode() {
        this.type = 158;
    }

    public LetNode(int pos) {
        super(pos);
        this.type = 158;
    }

    public LetNode(int pos, int len) {
        super(pos, len);
        this.type = 158;
    }

    public VariableDeclaration getVariables() {
        return this.variables;
    }

    public void setVariables(VariableDeclaration variables) {
        this.assertNotNull(variables);
        this.variables = variables;
        variables.setParent(this);
    }

    public AstNode getBody() {
        return this.body;
    }

    public void setBody(AstNode body) {
        this.body = body;
        if (body != null) {
            body.setParent(this);
        }
    }

    public int getLp() {
        return this.lp;
    }

    public void setLp(int lp) {
        this.lp = lp;
    }

    public int getRp() {
        return this.rp;
    }

    public void setRp(int rp) {
        this.rp = rp;
    }

    public void setParens(int lp, int rp) {
        this.lp = lp;
        this.rp = rp;
    }

    @Override
    public String toSource(int depth) {
        String pad = this.makeIndent(depth);
        StringBuilder sb = new StringBuilder();
        sb.append(pad);
        sb.append("let (");
        this.printList(this.variables.getVariables(), sb);
        sb.append(") ");
        if (this.body != null) {
            sb.append(this.body.toSource(depth));
        }
        return sb.toString();
    }

    @Override
    public void visit(NodeVisitor v) {
        if (v.visit(this)) {
            this.variables.visit(v);
            if (this.body != null) {
                this.body.visit(v);
            }
        }
    }
}

