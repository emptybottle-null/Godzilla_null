/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.ast;

import java.util.ArrayList;
import java.util.List;
import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.Label;
import org.mozilla.javascript.ast.NodeVisitor;

public class LabeledStatement
extends AstNode {
    private List<Label> labels = new ArrayList<Label>();
    private AstNode statement;

    public LabeledStatement() {
        this.type = 133;
    }

    public LabeledStatement(int pos) {
        super(pos);
        this.type = 133;
    }

    public LabeledStatement(int pos, int len) {
        super(pos, len);
        this.type = 133;
    }

    public List<Label> getLabels() {
        return this.labels;
    }

    public void setLabels(List<Label> labels) {
        this.assertNotNull(labels);
        if (this.labels != null) {
            this.labels.clear();
        }
        for (Label l : labels) {
            this.addLabel(l);
        }
    }

    public void addLabel(Label label) {
        this.assertNotNull(label);
        this.labels.add(label);
        label.setParent(this);
    }

    public AstNode getStatement() {
        return this.statement;
    }

    public Label getLabelByName(String name) {
        for (Label label : this.labels) {
            if (!name.equals(label.getName())) continue;
            return label;
        }
        return null;
    }

    public void setStatement(AstNode statement) {
        this.assertNotNull(statement);
        this.statement = statement;
        statement.setParent(this);
    }

    public Label getFirstLabel() {
        return this.labels.get(0);
    }

    @Override
    public boolean hasSideEffects() {
        return true;
    }

    @Override
    public String toSource(int depth) {
        StringBuilder sb = new StringBuilder();
        for (Label label : this.labels) {
            sb.append(label.toSource(depth));
        }
        sb.append(this.statement.toSource(depth + 1));
        return sb.toString();
    }

    @Override
    public void visit(NodeVisitor v) {
        if (v.visit(this)) {
            for (AstNode astNode : this.labels) {
                astNode.visit(v);
            }
            this.statement.visit(v);
        }
    }
}

