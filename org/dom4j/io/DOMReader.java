/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.io;

import java.util.ArrayList;
import org.dom4j.Branch;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.tree.NamespaceStack;
import org.w3c.dom.DocumentType;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMReader {
    private DocumentFactory factory;
    private NamespaceStack namespaceStack;

    public DOMReader() {
        this.factory = DocumentFactory.getInstance();
        this.namespaceStack = new NamespaceStack(this.factory);
    }

    public DOMReader(DocumentFactory factory) {
        this.factory = factory;
        this.namespaceStack = new NamespaceStack(factory);
    }

    public DocumentFactory getDocumentFactory() {
        return this.factory;
    }

    public void setDocumentFactory(DocumentFactory docFactory) {
        this.factory = docFactory;
        this.namespaceStack.setDocumentFactory(this.factory);
    }

    public Document read(org.w3c.dom.Document domDocument) {
        if (domDocument instanceof Document) {
            return (Document)((Object)domDocument);
        }
        Document document = this.createDocument();
        this.clearNamespaceStack();
        NodeList nodeList = domDocument.getChildNodes();
        int size = nodeList.getLength();
        for (int i = 0; i < size; ++i) {
            this.readTree(nodeList.item(i), document);
        }
        return document;
    }

    protected void readTree(Node node, Branch current) {
        Element element = null;
        Document document = null;
        if (current instanceof Element) {
            element = (Element)current;
        } else {
            document = (Document)current;
        }
        switch (node.getNodeType()) {
            case 1: {
                this.readElement(node, current);
                break;
            }
            case 7: {
                if (current instanceof Element) {
                    Element currentEl = (Element)current;
                    currentEl.addProcessingInstruction(node.getNodeName(), node.getNodeValue());
                    break;
                }
                Document currentDoc = (Document)current;
                currentDoc.addProcessingInstruction(node.getNodeName(), node.getNodeValue());
                break;
            }
            case 8: {
                if (current instanceof Element) {
                    ((Element)current).addComment(node.getNodeValue());
                    break;
                }
                ((Document)current).addComment(node.getNodeValue());
                break;
            }
            case 10: {
                DocumentType domDocType = (DocumentType)node;
                document.addDocType(domDocType.getName(), domDocType.getPublicId(), domDocType.getSystemId());
                break;
            }
            case 3: {
                element.addText(node.getNodeValue());
                break;
            }
            case 4: {
                element.addCDATA(node.getNodeValue());
                break;
            }
            case 5: {
                Node firstChild = node.getFirstChild();
                if (firstChild != null) {
                    element.addEntity(node.getNodeName(), firstChild.getNodeValue());
                    break;
                }
                element.addEntity(node.getNodeName(), "");
                break;
            }
            case 6: {
                element.addEntity(node.getNodeName(), node.getNodeValue());
                break;
            }
            default: {
                System.out.println("WARNING: Unknown DOM node type: " + node.getNodeType());
            }
        }
    }

    protected void readElement(Node node, Branch current) {
        Node attribute;
        NamedNodeMap attributeList;
        int previouslyDeclaredNamespaces = this.namespaceStack.size();
        String namespaceUri = node.getNamespaceURI();
        String elementPrefix = node.getPrefix();
        if (elementPrefix == null) {
            elementPrefix = "";
        }
        if ((attributeList = node.getAttributes()) != null && namespaceUri == null && (attribute = attributeList.getNamedItem("xmlns")) != null) {
            namespaceUri = attribute.getNodeValue();
            elementPrefix = "";
        }
        QName qName = this.namespaceStack.getQName(namespaceUri, node.getLocalName(), node.getNodeName());
        Element element = current.addElement(qName);
        if (attributeList != null) {
            Node attribute2;
            int i;
            int size = attributeList.getLength();
            ArrayList<Node> attributes = new ArrayList<Node>(size);
            for (i = 0; i < size; ++i) {
                attribute2 = attributeList.item(i);
                String name = attribute2.getNodeName();
                if (name.startsWith("xmlns")) {
                    String prefix = this.getPrefix(name);
                    String uri = attribute2.getNodeValue();
                    Namespace namespace = this.namespaceStack.addNamespace(prefix, uri);
                    element.add(namespace);
                    continue;
                }
                attributes.add(attribute2);
            }
            size = attributes.size();
            for (i = 0; i < size; ++i) {
                attribute2 = (Node)attributes.get(i);
                QName attributeQName = this.namespaceStack.getQName(attribute2.getNamespaceURI(), attribute2.getLocalName(), attribute2.getNodeName());
                element.addAttribute(attributeQName, attribute2.getNodeValue());
            }
        }
        NodeList children = node.getChildNodes();
        int size = children.getLength();
        for (int i = 0; i < size; ++i) {
            Node child = children.item(i);
            this.readTree(child, element);
        }
        while (this.namespaceStack.size() > previouslyDeclaredNamespaces) {
            this.namespaceStack.pop();
        }
    }

    protected Namespace getNamespace(String prefix, String uri) {
        return this.getDocumentFactory().createNamespace(prefix, uri);
    }

    protected Document createDocument() {
        return this.getDocumentFactory().createDocument();
    }

    protected void clearNamespaceStack() {
        this.namespaceStack.clear();
        if (!this.namespaceStack.contains(Namespace.XML_NAMESPACE)) {
            this.namespaceStack.push(Namespace.XML_NAMESPACE);
        }
    }

    private String getPrefix(String xmlnsDecl) {
        int index = xmlnsDecl.indexOf(58, 5);
        if (index != -1) {
            return xmlnsDecl.substring(index + 1);
        }
        return "";
    }
}

