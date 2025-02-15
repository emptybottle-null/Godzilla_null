/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.ast;

import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.InfixExpression;
import org.mozilla.javascript.ast.Name;
import org.mozilla.javascript.ast.NodeVisitor;

public class PropertyGet
extends InfixExpression {
    public PropertyGet() {
        this.type = 33;
    }

    public PropertyGet(int pos) {
        super(pos);
        this.type = 33;
    }

    public PropertyGet(int pos, int len) {
        super(pos, len);
        this.type = 33;
    }

    public PropertyGet(int pos, int len, AstNode target, Name property) {
        super(pos, len, target, property);
        this.type = 33;
    }

    public PropertyGet(AstNode target, Name property) {
        super(target, property);
        this.type = 33;
    }

    public PropertyGet(AstNode target, Name property, int dotPosition) {
        super(33, target, (AstNode)property, dotPosition);
        this.type = 33;
    }

    public AstNode getTarget() {
        return this.getLeft();
    }

    public void setTarget(AstNode target) {
        this.setLeft(target);
    }

    public Name getProperty() {
        return (Name)this.getRight();
    }

    public void setProperty(Name property) {
        this.setRight(property);
    }

    @Override
    public String toSource(int depth) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.makeIndent(depth));
        sb.append(this.getLeft().toSource(0));
        sb.append(".");
        sb.append(this.getRight().toSource(0));
        return sb.toString();
    }

    @Override
    public void visit(NodeVisitor v) {
        if (v.visit(this)) {
            this.getTarget().visit(v);
            this.getProperty().visit(v);
        }
    }
}

