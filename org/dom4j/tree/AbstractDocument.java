/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.tree;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import org.dom4j.Comment;
import org.dom4j.Document;
import org.dom4j.DocumentType;
import org.dom4j.Element;
import org.dom4j.IllegalAddException;
import org.dom4j.Node;
import org.dom4j.ProcessingInstruction;
import org.dom4j.QName;
import org.dom4j.Visitor;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.dom4j.tree.AbstractBranch;

public abstract class AbstractDocument
extends AbstractBranch
implements Document {
    protected String encoding;

    @Override
    public short getNodeType() {
        return 9;
    }

    @Override
    public String getPath(Element context) {
        return "/";
    }

    @Override
    public String getUniquePath(Element context) {
        return "/";
    }

    @Override
    public Document getDocument() {
        return this;
    }

    @Override
    public String getXMLEncoding() {
        return null;
    }

    @Override
    public String getStringValue() {
        Element root = this.getRootElement();
        return root != null ? root.getStringValue() : "";
    }

    @Override
    public String asXML() {
        OutputFormat format = new OutputFormat();
        format.setEncoding(this.encoding);
        try {
            StringWriter out = new StringWriter();
            XMLWriter writer = new XMLWriter(out, format);
            writer.write(this);
            writer.flush();
            return out.toString();
        } catch (IOException e) {
            throw new RuntimeException("IOException while generating textual representation: " + e.getMessage());
        }
    }

    @Override
    public void write(Writer out) throws IOException {
        OutputFormat format = new OutputFormat();
        format.setEncoding(this.encoding);
        XMLWriter writer = new XMLWriter(out, format);
        writer.write(this);
    }

    @Override
    public void accept(Visitor visitor) {
        List<Node> content;
        visitor.visit(this);
        DocumentType docType = this.getDocType();
        if (docType != null) {
            visitor.visit(docType);
        }
        if ((content = this.content()) != null) {
            for (Node node : content) {
                node.accept(visitor);
            }
        }
    }

    public String toString() {
        return super.toString() + " [Document: name " + this.getName() + "]";
    }

    @Override
    public void normalize() {
        Element element = this.getRootElement();
        if (element != null) {
            element.normalize();
        }
    }

    @Override
    public Document addComment(String comment) {
        Comment node = this.getDocumentFactory().createComment(comment);
        this.add(node);
        return this;
    }

    @Override
    public Document addProcessingInstruction(String target, String data) {
        ProcessingInstruction node = this.getDocumentFactory().createProcessingInstruction(target, data);
        this.add(node);
        return this;
    }

    @Override
    public Document addProcessingInstruction(String target, Map<String, String> data) {
        ProcessingInstruction node = this.getDocumentFactory().createProcessingInstruction(target, data);
        this.add(node);
        return this;
    }

    @Override
    public Element addElement(String name) {
        Element element = this.getDocumentFactory().createElement(name);
        this.add(element);
        return element;
    }

    @Override
    public Element addElement(String qualifiedName, String namespaceURI) {
        Element element = this.getDocumentFactory().createElement(qualifiedName, namespaceURI);
        this.add(element);
        return element;
    }

    @Override
    public Element addElement(QName qName) {
        Element element = this.getDocumentFactory().createElement(qName);
        this.add(element);
        return element;
    }

    @Override
    public void setRootElement(Element rootElement) {
        this.clearContent();
        if (rootElement != null) {
            super.add(rootElement);
            this.rootElementAdded(rootElement);
        }
    }

    @Override
    public void add(Element element) {
        this.checkAddElementAllowed(element);
        super.add(element);
        this.rootElementAdded(element);
    }

    @Override
    public boolean remove(Element element) {
        boolean answer = super.remove(element);
        Element root = this.getRootElement();
        if (root != null && answer) {
            this.setRootElement(null);
        }
        element.setDocument(null);
        return answer;
    }

    @Override
    public Node asXPathResult(Element parent) {
        return this;
    }

    @Override
    protected void childAdded(Node node) {
        if (node != null) {
            node.setDocument(this);
        }
    }

    @Override
    protected void childRemoved(Node node) {
        if (node != null) {
            node.setDocument(null);
        }
    }

    protected void checkAddElementAllowed(Element element) {
        Element root = this.getRootElement();
        if (root != null) {
            throw new IllegalAddException(this, (Node)element, "Cannot add another element to this Document as it already has a root element of: " + root.getQualifiedName());
        }
    }

    protected abstract void rootElementAdded(Element var1);

    @Override
    public void setXMLEncoding(String enc) {
        this.encoding = enc;
    }
}

