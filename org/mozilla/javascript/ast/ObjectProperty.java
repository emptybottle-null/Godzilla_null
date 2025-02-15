/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.ast;

import org.mozilla.javascript.ast.InfixExpression;

public class ObjectProperty
extends InfixExpression {
    public void setNodeType(int nodeType) {
        if (nodeType != 103 && nodeType != 151 && nodeType != 152) {
            throw new IllegalArgumentException("invalid node type: " + nodeType);
        }
        this.setType(nodeType);
    }

    public ObjectProperty() {
        this.type = 103;
    }

    public ObjectProperty(int pos) {
        super(pos);
        this.type = 103;
    }

    public ObjectProperty(int pos, int len) {
        super(pos, len);
        this.type = 103;
    }

    public void setIsGetter() {
        this.type = 151;
    }

    public boolean isGetter() {
        return this.type == 151;
    }

    public void setIsSetter() {
        this.type = 152;
    }

    public boolean isSetter() {
        return this.type == 152;
    }

    @Override
    public String toSource(int depth) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append(this.makeIndent(depth + 1));
        if (this.isGetter()) {
            sb.append("get ");
        } else if (this.isSetter()) {
            sb.append("set ");
        }
        sb.append(this.left.toSource(this.getType() == 103 ? 0 : depth));
        if (this.type == 103) {
            sb.append(": ");
        }
        sb.append(this.right.toSource(this.getType() == 103 ? 0 : depth + 1));
        return sb.toString();
    }
}

