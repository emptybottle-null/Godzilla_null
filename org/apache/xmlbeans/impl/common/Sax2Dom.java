/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.common;

import java.util.Stack;
import java.util.Vector;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.xmlbeans.impl.common.DocumentHelper;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.ProcessingInstruction;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.DefaultHandler;

public class Sax2Dom
extends DefaultHandler
implements ContentHandler,
LexicalHandler {
    public static final String EMPTYSTRING = "";
    public static final String XML_PREFIX = "xml";
    public static final String XMLNS_PREFIX = "xmlns";
    public static final String XMLNS_STRING = "xmlns:";
    public static final String XMLNS_URI = "http://www.w3.org/2000/xmlns/";
    private Node _root = null;
    private Document _document = null;
    private Stack _nodeStk = new Stack();
    private Vector _namespaceDecls = null;

    public Sax2Dom() throws ParserConfigurationException {
        this._document = DocumentHelper.createDocument();
        this._root = this._document;
    }

    public Sax2Dom(Node root) throws ParserConfigurationException {
        this._root = root;
        if (root instanceof Document) {
            this._document = (Document)root;
        } else if (root != null) {
            this._document = root.getOwnerDocument();
        } else {
            this._document = DocumentHelper.createDocument();
            this._root = this._document;
        }
    }

    public Node getDOM() {
        return this._root;
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        Node last = (Node)this._nodeStk.peek();
        if (last != this._document) {
            String text = new String(ch, start, length);
            last.appendChild(this._document.createTextNode(text));
        }
    }

    @Override
    public void startDocument() {
        this._nodeStk.push(this._root);
    }

    @Override
    public void endDocument() {
        this._nodeStk.pop();
    }

    @Override
    public void startElement(String namespace, String localName, String qName, Attributes attrs) {
        int i;
        Element tmp = this._document.createElementNS(namespace, qName);
        if (this._namespaceDecls != null) {
            int nDecls = this._namespaceDecls.size();
            for (i = 0; i < nDecls; ++i) {
                String prefix;
                if ((prefix = (String)this._namespaceDecls.elementAt(i++)) == null || prefix.equals(EMPTYSTRING)) {
                    tmp.setAttributeNS(XMLNS_URI, XMLNS_PREFIX, (String)this._namespaceDecls.elementAt(i));
                    continue;
                }
                tmp.setAttributeNS(XMLNS_URI, XMLNS_STRING + prefix, (String)this._namespaceDecls.elementAt(i));
            }
            this._namespaceDecls.clear();
        }
        int nattrs = attrs.getLength();
        for (i = 0; i < nattrs; ++i) {
            if (attrs.getLocalName(i) == null) {
                tmp.setAttribute(attrs.getQName(i), attrs.getValue(i));
                continue;
            }
            tmp.setAttributeNS(attrs.getURI(i), attrs.getQName(i), attrs.getValue(i));
        }
        Node last = (Node)this._nodeStk.peek();
        last.appendChild(tmp);
        this._nodeStk.push(tmp);
    }

    @Override
    public void endElement(String namespace, String localName, String qName) {
        this._nodeStk.pop();
    }

    @Override
    public void startPrefixMapping(String prefix, String uri) {
        if (this._namespaceDecls == null) {
            this._namespaceDecls = new Vector(2);
        }
        this._namespaceDecls.addElement(prefix);
        this._namespaceDecls.addElement(uri);
    }

    @Override
    public void endPrefixMapping(String prefix) {
    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) {
    }

    @Override
    public void processingInstruction(String target, String data) {
        Node last = (Node)this._nodeStk.peek();
        ProcessingInstruction pi = this._document.createProcessingInstruction(target, data);
        if (pi != null) {
            last.appendChild(pi);
        }
    }

    @Override
    public void setDocumentLocator(Locator locator) {
    }

    @Override
    public void skippedEntity(String name) {
    }

    @Override
    public void comment(char[] ch, int start, int length) {
        Node last = (Node)this._nodeStk.peek();
        Comment comment = this._document.createComment(new String(ch, start, length));
        if (comment != null) {
            last.appendChild(comment);
        }
    }

    @Override
    public void startCDATA() {
    }

    @Override
    public void endCDATA() {
    }

    @Override
    public void startEntity(String name) {
    }

    @Override
    public void endEntity(String name) {
    }

    @Override
    public void startDTD(String name, String publicId, String systemId) throws SAXException {
    }

    @Override
    public void endDTD() {
    }
}

