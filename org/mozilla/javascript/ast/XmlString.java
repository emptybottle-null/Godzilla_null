/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.ast;

import org.mozilla.javascript.ast.NodeVisitor;
import org.mozilla.javascript.ast.XmlFragment;

public class XmlString
extends XmlFragment {
    private String xml;

    public XmlString() {
    }

    public XmlString(int pos) {
        super(pos);
    }

    public XmlString(int pos, String s) {
        super(pos);
        this.setXml(s);
    }

    public void setXml(String s) {
        this.assertNotNull(s);
        this.xml = s;
        this.setLength(s.length());
    }

    public String getXml() {
        return this.xml;
    }

    @Override
    public String toSource(int depth) {
        return this.makeIndent(depth) + this.xml;
    }

    @Override
    public void visit(NodeVisitor v) {
        v.visit(this);
    }
}

