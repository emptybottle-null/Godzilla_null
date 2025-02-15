/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.ast;

import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.Name;
import org.mozilla.javascript.ast.NodeVisitor;

public class VariableInitializer
extends AstNode {
    private AstNode target;
    private AstNode initializer;

    public void setNodeType(int nodeType) {
        if (nodeType != 122 && nodeType != 154 && nodeType != 153) {
            throw new IllegalArgumentException("invalid node type");
        }
        this.setType(nodeType);
    }

    public VariableInitializer() {
        this.type = 122;
    }

    public VariableInitializer(int pos) {
        super(pos);
        this.type = 122;
    }

    public VariableInitializer(int pos, int len) {
        super(pos, len);
        this.type = 122;
    }

    public boolean isDestructuring() {
        return !(this.target instanceof Name);
    }

    public AstNode getTarget() {
        return this.target;
    }

    public void setTarget(AstNode target) {
        if (target == null) {
            throw new IllegalArgumentException("invalid target arg");
        }
        this.target = target;
        target.setParent(this);
    }

    public AstNode getInitializer() {
        return this.initializer;
    }

    public void setInitializer(AstNode initializer) {
        this.initializer = initializer;
        if (initializer != null) {
            initializer.setParent(this);
        }
    }

    @Override
    public String toSource(int depth) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.makeIndent(depth));
        sb.append(this.target.toSource(0));
        if (this.initializer != null) {
            sb.append(" = ");
            sb.append(this.initializer.toSource(0));
        }
        return sb.toString();
    }

    @Override
    public void visit(NodeVisitor v) {
        if (v.visit(this)) {
            this.target.visit(v);
            if (this.initializer != null) {
                this.initializer.visit(v);
            }
        }
    }
}

