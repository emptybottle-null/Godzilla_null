/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.ast;

import org.mozilla.javascript.Node;
import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.NodeVisitor;

public class Block
extends AstNode {
    public Block() {
        this.type = 129;
    }

    public Block(int pos) {
        super(pos);
        this.type = 129;
    }

    public Block(int pos, int len) {
        super(pos, len);
        this.type = 129;
    }

    public void addStatement(AstNode statement) {
        this.addChild(statement);
    }

    @Override
    public String toSource(int depth) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.makeIndent(depth));
        sb.append("{\n");
        for (Node kid : this) {
            sb.append(((AstNode)kid).toSource(depth + 1));
        }
        sb.append(this.makeIndent(depth));
        sb.append("}\n");
        return sb.toString();
    }

    @Override
    public void visit(NodeVisitor v) {
        if (v.visit(this)) {
            for (Node kid : this) {
                ((AstNode)kid).visit(v);
            }
        }
    }
}

