/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.tree;

import java.util.List;
import org.dom4j.Attribute;
import org.dom4j.Branch;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.QName;
import org.dom4j.tree.AbstractElement;
import org.dom4j.tree.ContentListFacade;

public class BaseElement
extends AbstractElement {
    private QName qname;
    private Branch parentBranch;
    protected List<Node> content;
    protected List<Attribute> attributes;

    public BaseElement(String name) {
        this.qname = this.getDocumentFactory().createQName(name);
    }

    public BaseElement(QName qname) {
        this.qname = qname;
    }

    public BaseElement(String name, Namespace namespace) {
        this.qname = this.getDocumentFactory().createQName(name, namespace);
    }

    @Override
    public Element getParent() {
        Element result = null;
        if (this.parentBranch instanceof Element) {
            result = (Element)this.parentBranch;
        }
        return result;
    }

    @Override
    public void setParent(Element parent) {
        if (this.parentBranch instanceof Element || parent != null) {
            this.parentBranch = parent;
        }
    }

    @Override
    public Document getDocument() {
        if (this.parentBranch instanceof Document) {
            return (Document)this.parentBranch;
        }
        if (this.parentBranch instanceof Element) {
            Element parent = (Element)this.parentBranch;
            return parent.getDocument();
        }
        return null;
    }

    @Override
    public void setDocument(Document document) {
        if (this.parentBranch instanceof Document || document != null) {
            this.parentBranch = document;
        }
    }

    @Override
    public boolean supportsParent() {
        return true;
    }

    @Override
    public QName getQName() {
        return this.qname;
    }

    @Override
    public void setQName(QName name) {
        this.qname = name;
    }

    @Override
    public void clearContent() {
        this.contentList().clear();
    }

    @Override
    public void setContent(List<Node> content) {
        this.content = content;
        if (content instanceof ContentListFacade) {
            this.content = ((ContentListFacade)content).getBackingList();
        }
    }

    @Override
    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
        if (attributes instanceof ContentListFacade) {
            this.attributes = ((ContentListFacade)attributes).getBackingList();
        }
    }

    @Override
    protected List<Node> contentList() {
        if (this.content == null) {
            this.content = this.createContentList();
        }
        return this.content;
    }

    @Override
    protected List<Attribute> attributeList() {
        if (this.attributes == null) {
            this.attributes = this.createAttributeList();
        }
        return this.attributes;
    }

    @Override
    protected List<Attribute> attributeList(int size) {
        if (this.attributes == null) {
            this.attributes = this.createAttributeList(size);
        }
        return this.attributes;
    }

    protected void setAttributeList(List<Attribute> attributeList) {
        this.attributes = attributeList;
    }
}

