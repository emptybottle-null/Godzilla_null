/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.dom;

import org.dom4j.dom.DOMElement;
import org.dom4j.dom.DOMNodeHelper;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class DOMAttributeNodeMap
implements NamedNodeMap {
    private DOMElement element;

    public DOMAttributeNodeMap(DOMElement element) {
        this.element = element;
    }

    public void foo() throws DOMException {
        DOMNodeHelper.notSupported();
    }

    @Override
    public Node getNamedItem(String name) {
        return this.element.getAttributeNode(name);
    }

    @Override
    public Node setNamedItem(Node arg) throws DOMException {
        if (arg instanceof Attr) {
            return this.element.setAttributeNode((Attr)arg);
        }
        throw new DOMException(9, "Node is not an Attr: " + arg);
    }

    @Override
    public Node removeNamedItem(String name) throws DOMException {
        Attr attr = this.element.getAttributeNode(name);
        if (attr == null) {
            throw new DOMException(8, "No attribute named " + name);
        }
        return this.element.removeAttributeNode(attr);
    }

    @Override
    public Node item(int index) {
        return DOMNodeHelper.asDOMAttr(this.element.attribute(index));
    }

    @Override
    public int getLength() {
        return this.element.attributeCount();
    }

    @Override
    public Node getNamedItemNS(String namespaceURI, String localName) {
        return this.element.getAttributeNodeNS(namespaceURI, localName);
    }

    @Override
    public Node setNamedItemNS(Node arg) throws DOMException {
        if (arg instanceof Attr) {
            return this.element.setAttributeNodeNS((Attr)arg);
        }
        throw new DOMException(9, "Node is not an Attr: " + arg);
    }

    @Override
    public Node removeNamedItemNS(String namespaceURI, String localName) throws DOMException {
        Attr attr = this.element.getAttributeNodeNS(namespaceURI, localName);
        if (attr != null) {
            return this.element.removeAttributeNode(attr);
        }
        return attr;
    }
}

