/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.tree;

import org.dom4j.Element;
import org.dom4j.tree.FlyweightCDATA;

public class DefaultCDATA
extends FlyweightCDATA {
    private Element parent;

    public DefaultCDATA(String text) {
        super(text);
    }

    public DefaultCDATA(Element parent, String text) {
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

