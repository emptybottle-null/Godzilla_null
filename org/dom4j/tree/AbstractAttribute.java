/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.tree;

import java.io.IOException;
import java.io.Writer;
import org.dom4j.Attribute;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.Visitor;
import org.dom4j.tree.AbstractNode;
import org.dom4j.tree.DefaultAttribute;

public abstract class AbstractAttribute
extends AbstractNode
implements Attribute {
    @Override
    public short getNodeType() {
        return 2;
    }

    @Override
    public void setNamespace(Namespace namespace) {
        String msg = "This Attribute is read only and cannot be changed";
        throw new UnsupportedOperationException(msg);
    }

    @Override
    public String getText() {
        return this.getValue();
    }

    @Override
    public void setText(String text) {
        this.setValue(text);
    }

    @Override
    public void setValue(String value) {
        String msg = "This Attribute is read only and cannot be changed";
        throw new UnsupportedOperationException(msg);
    }

    @Override
    public Object getData() {
        return this.getValue();
    }

    @Override
    public void setData(Object data) {
        this.setValue(data == null ? null : data.toString());
    }

    public String toString() {
        return super.toString() + " [Attribute: name " + this.getQualifiedName() + " value \"" + this.getValue() + "\"]";
    }

    @Override
    public String asXML() {
        return this.getQualifiedName() + "=\"" + this.getValue() + "\"";
    }

    @Override
    public void write(Writer writer) throws IOException {
        writer.write(this.getQualifiedName());
        writer.write("=\"");
        writer.write(this.getValue());
        writer.write("\"");
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Namespace getNamespace() {
        return this.getQName().getNamespace();
    }

    @Override
    public String getName() {
        return this.getQName().getName();
    }

    @Override
    public String getNamespacePrefix() {
        return this.getQName().getNamespacePrefix();
    }

    @Override
    public String getNamespaceURI() {
        return this.getQName().getNamespaceURI();
    }

    @Override
    public String getQualifiedName() {
        return this.getQName().getQualifiedName();
    }

    @Override
    public String getPath(Element context) {
        StringBuilder result = new StringBuilder();
        Element parent = this.getParent();
        if (parent != null && parent != context) {
            result.append(parent.getPath(context));
            result.append("/");
        }
        result.append("@");
        String uri = this.getNamespaceURI();
        String prefix = this.getNamespacePrefix();
        if (uri == null || uri.length() == 0 || prefix == null || prefix.length() == 0) {
            result.append(this.getName());
        } else {
            result.append(this.getQualifiedName());
        }
        return result.toString();
    }

    @Override
    public String getUniquePath(Element context) {
        StringBuilder result = new StringBuilder();
        Element parent = this.getParent();
        if (parent != null && parent != context) {
            result.append(parent.getUniquePath(context));
            result.append("/");
        }
        result.append("@");
        String uri = this.getNamespaceURI();
        String prefix = this.getNamespacePrefix();
        if (uri == null || uri.length() == 0 || prefix == null || prefix.length() == 0) {
            result.append(this.getName());
        } else {
            result.append(this.getQualifiedName());
        }
        return result.toString();
    }

    @Override
    protected Node createXPathResult(Element parent) {
        return new DefaultAttribute(parent, this.getQName(), this.getValue());
    }
}

