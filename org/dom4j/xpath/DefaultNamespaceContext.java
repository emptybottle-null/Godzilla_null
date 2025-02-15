/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.jaxen.NamespaceContext
 */
package org.dom4j.xpath;

import java.io.Serializable;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.jaxen.NamespaceContext;

public class DefaultNamespaceContext
implements NamespaceContext,
Serializable {
    private final Element element;

    public DefaultNamespaceContext(Element element) {
        this.element = element;
    }

    public static DefaultNamespaceContext create(Object node) {
        Element element = null;
        if (node instanceof Element) {
            element = (Element)node;
        } else if (node instanceof Document) {
            Document doc = (Document)node;
            element = doc.getRootElement();
        } else if (node instanceof Node) {
            element = ((Node)node).getParent();
        }
        if (element != null) {
            return new DefaultNamespaceContext(element);
        }
        return null;
    }

    public String translateNamespacePrefixToUri(String prefix) {
        Namespace ns;
        if (prefix != null && prefix.length() > 0 && (ns = this.element.getNamespaceForPrefix(prefix)) != null) {
            return ns.getURI();
        }
        return null;
    }
}

