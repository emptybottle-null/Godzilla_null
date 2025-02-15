/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.util;

import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.util.IndexedElement;

public class IndexedDocumentFactory
extends DocumentFactory {
    protected static transient IndexedDocumentFactory singleton = new IndexedDocumentFactory();

    public static DocumentFactory getInstance() {
        return singleton;
    }

    @Override
    public Element createElement(QName qname) {
        return new IndexedElement(qname);
    }

    public Element createElement(QName qname, int attributeCount) {
        return new IndexedElement(qname, attributeCount);
    }
}

