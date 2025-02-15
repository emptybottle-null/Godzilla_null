/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.ast;

import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.NodeVisitor;

public class KeywordLiteral
extends AstNode {
    public KeywordLiteral() {
    }

    public KeywordLiteral(int pos) {
        super(pos);
    }

    public KeywordLiteral(int pos, int len) {
        super(pos, len);
    }

    public KeywordLiteral(int pos, int len, int nodeType) {
        super(pos, len);
        this.setType(nodeType);
    }

    @Override
    public KeywordLiteral setType(int nodeType) {
        if (nodeType != 43 && nodeType != 42 && nodeType != 45 && nodeType != 44 && nodeType != 160) {
            throw new IllegalArgumentException("Invalid node type: " + nodeType);
        }
        this.type = nodeType;
        return this;
    }

    public boolean isBooleanLiteral() {
        return this.type == 45 || this.type == 44;
    }

    @Override
    public String toSource(int depth) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.makeIndent(depth));
        switch (this.getType()) {
            case 43: {
                sb.append("this");
                break;
            }
            case 42: {
                sb.append("null");
                break;
            }
            case 45: {
                sb.append("true");
                break;
            }
            case 44: {
                sb.append("false");
                break;
            }
            case 160: {
                sb.append("debugger;\n");
                break;
            }
            default: {
                throw new IllegalStateException("Invalid keyword literal type: " + this.getType());
            }
        }
        return sb.toString();
    }

    @Override
    public void visit(NodeVisitor v) {
        v.visit(this);
    }
}

