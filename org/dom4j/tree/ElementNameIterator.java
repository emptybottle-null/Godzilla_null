/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.tree;

import java.util.Iterator;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.tree.FilterIterator;

public class ElementNameIterator
extends FilterIterator<Node> {
    private String name;

    public ElementNameIterator(Iterator<Node> proxy, String name) {
        super(proxy);
        this.name = name;
    }

    @Override
    protected boolean matches(Node object) {
        if (object instanceof Element) {
            Element element = (Element)object;
            return this.name.equals(element.getName());
        }
        return false;
    }
}

