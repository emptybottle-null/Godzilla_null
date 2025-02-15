/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.util;

import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.tree.BaseElement;

public class NonLazyElement
extends BaseElement {
    public NonLazyElement(String name) {
        super(name);
        this.attributes = this.createAttributeList();
        this.content = this.createContentList();
    }

    public NonLazyElement(QName qname) {
        super(qname);
        this.attributes = this.createAttributeList();
        this.content = this.createContentList();
    }

    public NonLazyElement(String name, Namespace namespace) {
        super(name, namespace);
        this.attributes = this.createAttributeList();
        this.content = this.createContentList();
    }

    public NonLazyElement(QName qname, int attributeCount) {
        super(qname);
        this.attributes = this.createAttributeList(attributeCount);
        this.content = this.createContentList();
    }
}

