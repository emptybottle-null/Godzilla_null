/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.CatchClause;
import org.mozilla.javascript.ast.NodeVisitor;

public class TryStatement
extends AstNode {
    private static final List<CatchClause> NO_CATCHES = Collections.unmodifiableList(new ArrayList());
    private AstNode tryBlock;
    private List<CatchClause> catchClauses;
    private AstNode finallyBlock;
    private int finallyPosition = -1;

    public TryStatement() {
        this.type = 81;
    }

    public TryStatement(int pos) {
        super(pos);
        this.type = 81;
    }

    public TryStatement(int pos, int len) {
        super(pos, len);
        this.type = 81;
    }

    public AstNode getTryBlock() {
        return this.tryBlock;
    }

    public void setTryBlock(AstNode tryBlock) {
        this.assertNotNull(tryBlock);
        this.tryBlock = tryBlock;
        tryBlock.setParent(this);
    }

    public List<CatchClause> getCatchClauses() {
        return this.catchClauses != null ? this.catchClauses : NO_CATCHES;
    }

    public void setCatchClauses(List<CatchClause> catchClauses) {
        if (catchClauses == null) {
            this.catchClauses = null;
        } else {
            if (this.catchClauses != null) {
                this.catchClauses.clear();
            }
            for (CatchClause cc : catchClauses) {
                this.addCatchClause(cc);
            }
        }
    }

    public void addCatchClause(CatchClause clause) {
        this.assertNotNull(clause);
        if (this.catchClauses == null) {
            this.catchClauses = new ArrayList<CatchClause>();
        }
        this.catchClauses.add(clause);
        clause.setParent(this);
    }

    public AstNode getFinallyBlock() {
        return this.finallyBlock;
    }

    public void setFinallyBlock(AstNode finallyBlock) {
        this.finallyBlock = finallyBlock;
        if (finallyBlock != null) {
            finallyBlock.setParent(this);
        }
    }

    public int getFinallyPosition() {
        return this.finallyPosition;
    }

    public void setFinallyPosition(int finallyPosition) {
        this.finallyPosition = finallyPosition;
    }

    @Override
    public String toSource(int depth) {
        StringBuilder sb = new StringBuilder(250);
        sb.append(this.makeIndent(depth));
        sb.append("try ");
        sb.append(this.tryBlock.toSource(depth).trim());
        for (CatchClause cc : this.getCatchClauses()) {
            sb.append(cc.toSource(depth));
        }
        if (this.finallyBlock != null) {
            sb.append(" finally ");
            sb.append(this.finallyBlock.toSource(depth));
        }
        return sb.toString();
    }

    @Override
    public void visit(NodeVisitor v) {
        if (v.visit(this)) {
            this.tryBlock.visit(v);
            for (CatchClause cc : this.getCatchClauses()) {
                cc.visit(v);
            }
            if (this.finallyBlock != null) {
                this.finallyBlock.visit(v);
            }
        }
    }
}

