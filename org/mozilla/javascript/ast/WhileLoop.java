/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.ast;

import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.Loop;
import org.mozilla.javascript.ast.NodeVisitor;

public class WhileLoop
extends Loop {
    private AstNode condition;

    public WhileLoop() {
        this.type = 117;
    }

    public WhileLoop(int pos) {
        super(pos);
        this.type = 117;
    }

    public WhileLoop(int pos, int len) {
        super(pos, len);
        this.type = 117;
    }

    public AstNode getCondition() {
        return this.condition;
    }

    public void setCondition(AstNode condition) {
        this.assertNotNull(condition);
        this.condition = condition;
        condition.setParent(this);
    }

    @Override
    public String toSource(int depth) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.makeIndent(depth));
        sb.append("while (");
        sb.append(this.condition.toSource(0));
        sb.append(") ");
        if (this.body.getType() == 129) {
            sb.append(this.body.toSource(depth).trim());
            sb.append("\n");
        } else {
            sb.append("\n").append(this.body.toSource(depth + 1));
        }
        return sb.toString();
    }

    @Override
    public void visit(NodeVisitor v) {
        if (v.visit(this)) {
            this.condition.visit(v);
            this.body.visit(v);
        }
    }
}

