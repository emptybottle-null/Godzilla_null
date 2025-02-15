/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.tree;

import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.tree.FlyweightAttribute;

public class DefaultAttribute
extends FlyweightAttribute {
    private Element parent;

    public DefaultAttribute(QName qname) {
        super(qname);
    }

    public DefaultAttribute(QName qname, String value) {
        super(qname, value);
    }

    public DefaultAttribute(Element parent, QName qname, String value) {
        super(qname, value);
        this.parent = parent;
    }

    public DefaultAttribute(String name, String value) {
        super(name, value);
    }

    public DefaultAttribute(String name, String value, Namespace namespace) {
        super(name, value, namespace);
    }

    public DefaultAttribute(Element parent, String name, String value, Namespace namespace) {
        super(name, value, namespace);
        this.parent = parent;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
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

