/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.ast;

import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.Loop;
import org.mozilla.javascript.ast.NodeVisitor;

public class ForInLoop
extends Loop {
    protected AstNode iterator;
    protected AstNode iteratedObject;
    protected int inPosition = -1;
    protected int eachPosition = -1;
    protected boolean isForEach;

    public ForInLoop() {
        this.type = 119;
    }

    public ForInLoop(int pos) {
        super(pos);
        this.type = 119;
    }

    public ForInLoop(int pos, int len) {
        super(pos, len);
        this.type = 119;
    }

    public AstNode getIterator() {
        return this.iterator;
    }

    public void setIterator(AstNode iterator) {
        this.assertNotNull(iterator);
        this.iterator = iterator;
        iterator.setParent(this);
    }

    public AstNode getIteratedObject() {
        return this.iteratedObject;
    }

    public void setIteratedObject(AstNode object) {
        this.assertNotNull(object);
        this.iteratedObject = object;
        object.setParent(this);
    }

    public boolean isForEach() {
        return this.isForEach;
    }

    public void setIsForEach(boolean isForEach) {
        this.isForEach = isForEach;
    }

    public int getInPosition() {
        return this.inPosition;
    }

    public void setInPosition(int inPosition) {
        this.inPosition = inPosition;
    }

    public int getEachPosition() {
        return this.eachPosition;
    }

    public void setEachPosition(int eachPosition) {
        this.eachPosition = eachPosition;
    }

    @Override
    public String toSource(int depth) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.makeIndent(depth));
        sb.append("for ");
        if (this.isForEach()) {
            sb.append("each ");
        }
        sb.append("(");
        sb.append(this.iterator.toSource(0));
        sb.append(" in ");
        sb.append(this.iteratedObject.toSource(0));
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
            this.iterator.visit(v);
            this.iteratedObject.visit(v);
            this.body.visit(v);
        }
    }
}

