/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.ast;

import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.Loop;
import org.mozilla.javascript.ast.NodeVisitor;

public class DoLoop
extends Loop {
    private AstNode condition;
    private int whilePosition = -1;

    public DoLoop() {
        this.type = 118;
    }

    public DoLoop(int pos) {
        super(pos);
        this.type = 118;
    }

    public DoLoop(int pos, int len) {
        super(pos, len);
        this.type = 118;
    }

    public AstNode getCondition() {
        return this.condition;
    }

    public void setCondition(AstNode condition) {
        this.assertNotNull(condition);
        this.condition = condition;
        condition.setParent(this);
    }

    public int getWhilePosition() {
        return this.whilePosition;
    }

    public void setWhilePosition(int whilePosition) {
        this.whilePosition = whilePosition;
    }

    @Override
    public String toSource(int depth) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.makeIndent(depth));
        sb.append("do ");
        sb.append(this.body.toSource(depth).trim());
        sb.append(" while (");
        sb.append(this.condition.toSource(0));
        sb.append(");\n");
        return sb.toString();
    }

    @Override
    public void visit(NodeVisitor v) {
        if (v.visit(this)) {
            this.body.visit(v);
            this.condition.visit(v);
        }
    }
}

