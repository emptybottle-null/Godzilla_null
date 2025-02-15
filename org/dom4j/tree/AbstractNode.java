/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.tree;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.NodeFilter;
import org.dom4j.XPath;
import org.dom4j.rule.Pattern;

public abstract class AbstractNode
implements Node,
Cloneable,
Serializable {
    protected static final String[] NODE_TYPE_NAMES = new String[]{"Node", "Element", "Attribute", "Text", "CDATA", "Entity", "Entity", "ProcessingInstruction", "Comment", "Document", "DocumentType", "DocumentFragment", "Notation", "Namespace", "Unknown"};
    private static final DocumentFactory DOCUMENT_FACTORY = DocumentFactory.getInstance();

    @Override
    public short getNodeType() {
        return 14;
    }

    @Override
    public String getNodeTypeName() {
        short type = this.getNodeType();
        if (type < 0 || type >= NODE_TYPE_NAMES.length) {
            return "Unknown";
        }
        return NODE_TYPE_NAMES[type];
    }

    @Override
    public Document getDocument() {
        Element element = this.getParent();
        return element != null ? element.getDocument() : null;
    }

    @Override
    public void setDocument(Document document) {
    }

    @Override
    public Element getParent() {
        return null;
    }

    @Override
    public void setParent(Element parent) {
    }

    @Override
    public boolean supportsParent() {
        return false;
    }

    @Override
    public boolean isReadOnly() {
        return true;
    }

    @Override
    public boolean hasContent() {
        return false;
    }

    @Override
    public String getPath() {
        return this.getPath(null);
    }

    @Override
    public String getUniquePath() {
        return this.getUniquePath(null);
    }

    @Override
    public Object clone() {
        if (this.isReadOnly()) {
            return this;
        }
        try {
            Node answer = (Node)super.clone();
            answer.setParent(null);
            answer.setDocument(null);
            return answer;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("This should never happen. Caught: " + e);
        }
    }

    @Override
    public Node detach() {
        Element parent = this.getParent();
        if (parent != null) {
            parent.remove(this);
        } else {
            Document document = this.getDocument();
            if (document != null) {
                document.remove(this);
            }
        }
        this.setParent(null);
        this.setDocument(null);
        return this;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {
        throw new UnsupportedOperationException("This node cannot be modified");
    }

    @Override
    public String getText() {
        return null;
    }

    @Override
    public String getStringValue() {
        return this.getText();
    }

    @Override
    public void setText(String text) {
        throw new UnsupportedOperationException("This node cannot be modified");
    }

    @Override
    public void write(Writer writer) throws IOException {
        writer.write(this.asXML());
    }

    @Override
    public Object selectObject(String xpathExpression) {
        XPath xpath = this.createXPath(xpathExpression);
        return xpath.evaluate(this);
    }

    @Override
    public List<Node> selectNodes(String xpathExpression) {
        XPath xpath = this.createXPath(xpathExpression);
        return xpath.selectNodes(this);
    }

    @Override
    public List<Node> selectNodes(String xpathExpression, String comparisonXPathExpression) {
        return this.selectNodes(xpathExpression, comparisonXPathExpression, false);
    }

    @Override
    public List<Node> selectNodes(String xpathExpression, String comparisonXPathExpression, boolean removeDuplicates) {
        XPath xpath = this.createXPath(xpathExpression);
        XPath sortBy = this.createXPath(comparisonXPathExpression);
        return xpath.selectNodes(this, sortBy, removeDuplicates);
    }

    @Override
    public Node selectSingleNode(String xpathExpression) {
        XPath xpath = this.createXPath(xpathExpression);
        return xpath.selectSingleNode(this);
    }

    @Override
    public String valueOf(String xpathExpression) {
        XPath xpath = this.createXPath(xpathExpression);
        return xpath.valueOf(this);
    }

    @Override
    public Number numberValueOf(String xpathExpression) {
        XPath xpath = this.createXPath(xpathExpression);
        return xpath.numberValueOf(this);
    }

    @Override
    public boolean matches(String patternText) {
        NodeFilter filter = this.createXPathFilter(patternText);
        return filter.matches(this);
    }

    @Override
    public XPath createXPath(String xpathExpression) {
        return this.getDocumentFactory().createXPath(xpathExpression);
    }

    public NodeFilter createXPathFilter(String patternText) {
        return this.getDocumentFactory().createXPathFilter(patternText);
    }

    public Pattern createPattern(String patternText) {
        return this.getDocumentFactory().createPattern(patternText);
    }

    @Override
    public Node asXPathResult(Element parent) {
        if (this.supportsParent()) {
            return this;
        }
        return this.createXPathResult(parent);
    }

    protected DocumentFactory getDocumentFactory() {
        return DOCUMENT_FACTORY;
    }

    protected Node createXPathResult(Element parent) {
        throw new RuntimeException("asXPathResult() not yet implemented fully for: " + this);
    }
}

