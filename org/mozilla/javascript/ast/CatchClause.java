/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.ast;

import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.Block;
import org.mozilla.javascript.ast.Name;
import org.mozilla.javascript.ast.NodeVisitor;

public class CatchClause
extends AstNode {
    private Name varName;
    private AstNode catchCondition;
    private Block body;
    private int ifPosition = -1;
    private int lp = -1;
    private int rp = -1;

    public CatchClause() {
        this.type = 124;
    }

    public CatchClause(int pos) {
        super(pos);
        this.type = 124;
    }

    public CatchClause(int pos, int len) {
        super(pos, len);
        this.type = 124;
    }

    public Name getVarName() {
        return this.varName;
    }

    public void setVarName(Name varName) {
        this.assertNotNull(varName);
        this.varName = varName;
        varName.setParent(this);
    }

    public AstNode getCatchCondition() {
        return this.catchCondition;
    }

    public void setCatchCondition(AstNode catchCondition) {
        this.catchCondition = catchCondition;
        if (catchCondition != null) {
            catchCondition.setParent(this);
        }
    }

    public Block getBody() {
        return this.body;
    }

    public void setBody(Block body) {
        this.assertNotNull(body);
        this.body = body;
        body.setParent(this);
    }

    public int getLp() {
        return this.lp;
    }

    public void setLp(int lp) {
        this.lp = lp;
    }

    public int getRp() {
        return this.rp;
    }

    public void setRp(int rp) {
        this.rp = rp;
    }

    public void setParens(int lp, int rp) {
        this.lp = lp;
        this.rp = rp;
    }

    public int getIfPosition() {
        return this.ifPosition;
    }

    public void setIfPosition(int ifPosition) {
        this.ifPosition = ifPosition;
    }

    @Override
    public String toSource(int depth) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.makeIndent(depth));
        sb.append("catch (");
        sb.append(this.varName.toSource(0));
        if (this.catchCondition != null) {
            sb.append(" if ");
            sb.append(this.catchCondition.toSource(0));
        }
        sb.append(") ");
        sb.append(this.body.toSource(0));
        return sb.toString();
    }

    @Override
    public void visit(NodeVisitor v) {
        if (v.visit(this)) {
            this.varName.visit(v);
            if (this.catchCondition != null) {
                this.catchCondition.visit(v);
            }
            this.body.visit(v);
        }
    }
}

