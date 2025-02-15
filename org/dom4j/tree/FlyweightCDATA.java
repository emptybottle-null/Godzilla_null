/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.tree;

import org.dom4j.CDATA;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.tree.AbstractCDATA;
import org.dom4j.tree.DefaultCDATA;

public class FlyweightCDATA
extends AbstractCDATA
implements CDATA {
    protected String text;

    public FlyweightCDATA(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    protected Node createXPathResult(Element parent) {
        return new DefaultCDATA(parent, this.getText());
    }
}

