/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util.xml;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;

class DomContentHandler
implements ContentHandler {
    private final Document document;
    private final List<Element> elements = new ArrayList<Element>();
    private final Node node;

    DomContentHandler(Node node) {
        this.node = node;
        this.document = node instanceof Document ? (Document)node : node.getOwnerDocument();
    }

    private Node getParent() {
        if (!this.elements.isEmpty()) {
            return this.elements.get(this.elements.size() - 1);
        }
        return this.node;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        Node parent = this.getParent();
        Element element = this.document.createElementNS(uri, qName);
        for (int i = 0; i < attributes.getLength(); ++i) {
            String attrUri = attributes.getURI(i);
            String attrQname = attributes.getQName(i);
            String value = attributes.getValue(i);
            if (attrQname.startsWith("xmlns")) continue;
            element.setAttributeNS(attrUri, attrQname, value);
        }
        element = (Element)parent.appendChild(element);
        this.elements.add(element);
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        this.elements.remove(this.elements.size() - 1);
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length);
        Node parent = this.getParent();
        Node lastChild = parent.getLastChild();
        if (lastChild != null && lastChild.getNodeType() == 3) {
            ((Text)lastChild).appendData(data);
        } else {
            Text text = this.document.createTextNode(data);
            parent.appendChild(text);
        }
    }

    @Override
    public void processingInstruction(String target, String data) {
        Node parent = this.getParent();
        ProcessingInstruction pi = this.document.createProcessingInstruction(target, data);
        parent.appendChild(pi);
    }

    @Override
    public void setDocumentLocator(Locator locator) {
    }

    @Override
    public void startDocument() {
    }

    @Override
    public void endDocument() {
    }

    @Override
    public void startPrefixMapping(String prefix, String uri) {
    }

    @Override
    public void endPrefixMapping(String prefix) {
    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) {
    }

    @Override
    public void skippedEntity(String name) {
    }
}

