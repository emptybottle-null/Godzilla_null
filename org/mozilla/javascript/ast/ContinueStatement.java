/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.ast;

import org.mozilla.javascript.ast.Jump;
import org.mozilla.javascript.ast.Loop;
import org.mozilla.javascript.ast.Name;
import org.mozilla.javascript.ast.NodeVisitor;

public class ContinueStatement
extends Jump {
    private Name label;
    private Loop target;

    public ContinueStatement() {
        this.type = 121;
    }

    public ContinueStatement(int pos) {
        this(pos, -1);
    }

    public ContinueStatement(int pos, int len) {
        this.type = 121;
        this.position = pos;
        this.length = len;
    }

    public ContinueStatement(Name label) {
        this.type = 121;
        this.setLabel(label);
    }

    public ContinueStatement(int pos, Name label) {
        this(pos);
        this.setLabel(label);
    }

    public ContinueStatement(int pos, int len, Name label) {
        this(pos, len);
        this.setLabel(label);
    }

    public Loop getTarget() {
        return this.target;
    }

    public void setTarget(Loop target) {
        this.assertNotNull(target);
        this.target = target;
        this.setJumpStatement(target);
    }

    public Name getLabel() {
        return this.label;
    }

    public void setLabel(Name label) {
        this.label = label;
        if (label != null) {
            label.setParent(this);
        }
    }

    @Override
    public String toSource(int depth) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.makeIndent(depth));
        sb.append("continue");
        if (this.label != null) {
            sb.append(" ");
            sb.append(this.label.toSource(0));
        }
        sb.append(";\n");
        return sb.toString();
    }

    @Override
    public void visit(NodeVisitor v) {
        if (v.visit(this) && this.label != null) {
            this.label.visit(v);
        }
    }
}

