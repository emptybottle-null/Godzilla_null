/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.ast;

import java.util.ArrayList;
import java.util.List;
import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.NodeVisitor;
import org.mozilla.javascript.ast.XmlFragment;

public class XmlLiteral
extends AstNode {
    private List<XmlFragment> fragments = new ArrayList<XmlFragment>();

    public XmlLiteral() {
        this.type = 145;
    }

    public XmlLiteral(int pos) {
        super(pos);
        this.type = 145;
    }

    public XmlLiteral(int pos, int len) {
        super(pos, len);
        this.type = 145;
    }

    public List<XmlFragment> getFragments() {
        return this.fragments;
    }

    public void setFragments(List<XmlFragment> fragments) {
        this.assertNotNull(fragments);
        this.fragments.clear();
        for (XmlFragment fragment : fragments) {
            this.addFragment(fragment);
        }
    }

    public void addFragment(XmlFragment fragment) {
        this.assertNotNull(fragment);
        this.fragments.add(fragment);
        fragment.setParent(this);
    }

    @Override
    public String toSource(int depth) {
        StringBuilder sb = new StringBuilder(250);
        for (XmlFragment frag : this.fragments) {
            sb.append(frag.toSource(0));
        }
        return sb.toString();
    }

    @Override
    public void visit(NodeVisitor v) {
        if (v.visit(this)) {
            for (XmlFragment frag : this.fragments) {
                frag.visit(v);
            }
        }
    }
}

