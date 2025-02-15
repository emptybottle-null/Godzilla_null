/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.ast;

import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.ForInLoop;
import org.mozilla.javascript.ast.NodeVisitor;

public class ArrayComprehensionLoop
extends ForInLoop {
    public ArrayComprehensionLoop() {
    }

    public ArrayComprehensionLoop(int pos) {
        super(pos);
    }

    public ArrayComprehensionLoop(int pos, int len) {
        super(pos, len);
    }

    @Override
    public AstNode getBody() {
        return null;
    }

    @Override
    public void setBody(AstNode body) {
        throw new UnsupportedOperationException("this node type has no body");
    }

    @Override
    public String toSource(int depth) {
        return this.makeIndent(depth) + " for " + (this.isForEach() ? "each " : "") + "(" + this.iterator.toSource(0) + " in " + this.iteratedObject.toSource(0) + ")";
    }

    @Override
    public void visit(NodeVisitor v) {
        if (v.visit(this)) {
            this.iterator.visit(v);
            this.iteratedObject.visit(v);
        }
    }
}

