/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.tree;

import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;
import org.dom4j.tree.AbstractText;
import org.dom4j.tree.DefaultText;

public class FlyweightText
extends AbstractText
implements Text {
    protected String text;

    public FlyweightText(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    protected Node createXPathResult(Element parent) {
        return new DefaultText(parent, this.getText());
    }
}

