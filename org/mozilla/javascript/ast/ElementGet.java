/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.ast;

import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.NodeVisitor;

public class ElementGet
extends AstNode {
    private AstNode target;
    private AstNode element;
    private int lb = -1;
    private int rb = -1;

    public ElementGet() {
        this.type = 36;
    }

    public ElementGet(int pos) {
        super(pos);
        this.type = 36;
    }

    public ElementGet(int pos, int len) {
        super(pos, len);
        this.type = 36;
    }

    public ElementGet(AstNode target, AstNode element) {
        this.type = 36;
        this.setTarget(target);
        this.setElement(element);
    }

    public AstNode getTarget() {
        return this.target;
    }

    public void setTarget(AstNode target) {
        this.assertNotNull(target);
        this.target = target;
        target.setParent(this);
    }

    public AstNode getElement() {
        return this.element;
    }

    public void setElement(AstNode element) {
        this.assertNotNull(element);
        this.element = element;
        element.setParent(this);
    }

    public int getLb() {
        return this.lb;
    }

    public void setLb(int lb) {
        this.lb = lb;
    }

    public int getRb() {
        return this.rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public void setParens(int lb, int rb) {
        this.lb = lb;
        this.rb = rb;
    }

    @Override
    public String toSource(int depth) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.makeIndent(depth));
        sb.append(this.target.toSource(0));
        sb.append("[");
        sb.append(this.element.toSource(0));
        sb.append("]");
        return sb.toString();
    }

    @Override
    public void visit(NodeVisitor v) {
        if (v.visit(this)) {
            this.target.visit(v);
            this.element.visit(v);
        }
    }
}

