/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.tree;

import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.tree.AbstractAttribute;

public class FlyweightAttribute
extends AbstractAttribute {
    private QName qname;
    protected String value;

    public FlyweightAttribute(QName qname) {
        this.qname = qname;
    }

    public FlyweightAttribute(QName qname, String value) {
        this.qname = qname;
        this.value = value;
    }

    public FlyweightAttribute(String name, String value) {
        this.qname = this.getDocumentFactory().createQName(name);
        this.value = value;
    }

    public FlyweightAttribute(String name, String value, Namespace namespace) {
        this.qname = this.getDocumentFactory().createQName(name, namespace);
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public QName getQName() {
        return this.qname;
    }
}

