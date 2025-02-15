/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.ast;

import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.Loop;
import org.mozilla.javascript.ast.NodeVisitor;

public class ForLoop
extends Loop {
    private AstNode initializer;
    private AstNode condition;
    private AstNode increment;

    public ForLoop() {
        this.type = 119;
    }

    public ForLoop(int pos) {
        super(pos);
        this.type = 119;
    }

    public ForLoop(int pos, int len) {
        super(pos, len);
        this.type = 119;
    }

    public AstNode getInitializer() {
        return this.initializer;
    }

    public void setInitializer(AstNode initializer) {
        this.assertNotNull(initializer);
        this.initializer = initializer;
        initializer.setParent(this);
    }

    public AstNode getCondition() {
        return this.condition;
    }

    public void setCondition(AstNode condition) {
        this.assertNotNull(condition);
        this.condition = condition;
        condition.setParent(this);
    }

    public AstNode getIncrement() {
        return this.increment;
    }

    public void setIncrement(AstNode increment) {
        this.assertNotNull(increment);
        this.increment = increment;
        increment.setParent(this);
    }

    @Override
    public String toSource(int depth) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.makeIndent(depth));
        sb.append("for (");
        sb.append(this.initializer.toSource(0));
        sb.append("; ");
        sb.append(this.condition.toSource(0));
        sb.append("; ");
        sb.append(this.increment.toSource(0));
        sb.append(") ");
        if (this.body.getType() == 129) {
            sb.append(this.body.toSource(depth).trim()).append("\n");
        } else {
            sb.append("\n").append(this.body.toSource(depth + 1));
        }
        return sb.toString();
    }

    @Override
    public void visit(NodeVisitor v) {
        if (v.visit(this)) {
            this.initializer.visit(v);
            this.condition.visit(v);
            this.increment.visit(v);
            this.body.visit(v);
        }
    }
}

