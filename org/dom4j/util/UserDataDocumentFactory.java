/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.util;

import org.dom4j.Attribute;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.util.UserDataAttribute;
import org.dom4j.util.UserDataElement;

public class UserDataDocumentFactory
extends DocumentFactory {
    protected static transient UserDataDocumentFactory singleton = new UserDataDocumentFactory();

    public static DocumentFactory getInstance() {
        return singleton;
    }

    @Override
    public Element createElement(QName qname) {
        return new UserDataElement(qname);
    }

    @Override
    public Attribute createAttribute(Element owner, QName qname, String value) {
        return new UserDataAttribute(qname, value);
    }
}

