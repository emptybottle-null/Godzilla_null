/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.tree;

import org.dom4j.Element;
import org.dom4j.tree.FlyweightText;

public class DefaultText
extends FlyweightText {
    private Element parent;

    public DefaultText(String text) {
        super(text);
    }

    public DefaultText(Element parent, String text) {
        super(text);
        this.parent = parent;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Element getParent() {
        return this.parent;
    }

    @Override
    public void setParent(Element parent) {
        this.parent = parent;
    }

    @Override
    public boolean supportsParent() {
        return true;
    }

    @Override
    public boolean isReadOnly() {
        return false;
    }
}

