/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.ast;

import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.NodeVisitor;
import org.mozilla.javascript.ast.XmlFragment;

public class XmlExpression
extends XmlFragment {
    private AstNode expression;
    private boolean isXmlAttribute;

    public XmlExpression() {
    }

    public XmlExpression(int pos) {
        super(pos);
    }

    public XmlExpression(int pos, int len) {
        super(pos, len);
    }

    public XmlExpression(int pos, AstNode expr) {
        super(pos);
        this.setExpression(expr);
    }

    public AstNode getExpression() {
        return this.expression;
    }

    public void setExpression(AstNode expression) {
        this.assertNotNull(expression);
        this.expression = expression;
        expression.setParent(this);
    }

    public boolean isXmlAttribute() {
        return this.isXmlAttribute;
    }

    public void setIsXmlAttribute(boolean isXmlAttribute) {
        this.isXmlAttribute = isXmlAttribute;
    }

    @Override
    public String toSource(int depth) {
        return this.makeIndent(depth) + "{" + this.expression.toSource(depth) + "}";
    }

    @Override
    public void visit(NodeVisitor v) {
        if (v.visit(this)) {
            this.expression.visit(v);
        }
    }
}

