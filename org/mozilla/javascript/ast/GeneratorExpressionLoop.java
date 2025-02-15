/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.ast;

import org.mozilla.javascript.ast.ForInLoop;
import org.mozilla.javascript.ast.NodeVisitor;

public class GeneratorExpressionLoop
extends ForInLoop {
    public GeneratorExpressionLoop() {
    }

    public GeneratorExpressionLoop(int pos) {
        super(pos);
    }

    public GeneratorExpressionLoop(int pos, int len) {
        super(pos, len);
    }

    @Override
    public boolean isForEach() {
        return false;
    }

    @Override
    public void setIsForEach(boolean isForEach) {
        throw new UnsupportedOperationException("this node type does not support for each");
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

