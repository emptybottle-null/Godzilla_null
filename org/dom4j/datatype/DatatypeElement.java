/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.sun.msv.datatype.DatabindableDatatype
 *  com.sun.msv.datatype.SerializationContext
 *  com.sun.msv.datatype.xsd.XSDatatype
 *  org.relaxng.datatype.DatatypeException
 *  org.relaxng.datatype.ValidationContext
 */
package org.dom4j.datatype;

import com.sun.msv.datatype.DatabindableDatatype;
import com.sun.msv.datatype.SerializationContext;
import com.sun.msv.datatype.xsd.XSDatatype;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.QName;
import org.dom4j.tree.DefaultElement;
import org.relaxng.datatype.DatatypeException;
import org.relaxng.datatype.ValidationContext;

public class DatatypeElement
extends DefaultElement
implements SerializationContext,
ValidationContext {
    private XSDatatype datatype;
    private Object data;

    public DatatypeElement(QName qname, XSDatatype datatype) {
        super(qname);
        this.datatype = datatype;
    }

    public DatatypeElement(QName qname, int attributeCount, XSDatatype type) {
        super(qname, attributeCount);
        this.datatype = type;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + this.hashCode() + " [Element: <" + this.getQualifiedName() + " attributes: " + this.attributeList() + " data: " + this.getData() + " />]";
    }

    public XSDatatype getXSDatatype() {
        return this.datatype;
    }

    public String getNamespacePrefix(String uri) {
        Namespace namespace = this.getNamespaceForURI(uri);
        return namespace != null ? namespace.getPrefix() : null;
    }

    public String getBaseUri() {
        return null;
    }

    public boolean isNotation(String notationName) {
        return false;
    }

    public boolean isUnparsedEntity(String entityName) {
        return true;
    }

    public String resolveNamespacePrefix(String prefix) {
        Namespace namespace = this.getNamespaceForPrefix(prefix);
        if (namespace != null) {
            return namespace.getURI();
        }
        return null;
    }

    @Override
    public Object getData() {
        String text;
        if (this.data == null && (text = this.getTextTrim()) != null && text.length() > 0) {
            if (this.datatype instanceof DatabindableDatatype) {
                XSDatatype bind = this.datatype;
                this.data = bind.createJavaObject(text, (ValidationContext)this);
            } else {
                this.data = this.datatype.createValue(text, (ValidationContext)this);
            }
        }
        return this.data;
    }

    @Override
    public void setData(Object data) {
        String s = this.datatype.convertToLexicalValue(data, (SerializationContext)this);
        this.validate(s);
        this.data = data;
        this.setText(s);
    }

    @Override
    public Element addText(String text) {
        this.validate(text);
        return super.addText(text);
    }

    @Override
    public void setText(String text) {
        this.validate(text);
        super.setText(text);
    }

    @Override
    protected void childAdded(Node node) {
        this.data = null;
        super.childAdded(node);
    }

    @Override
    protected void childRemoved(Node node) {
        this.data = null;
        super.childRemoved(node);
    }

    protected void validate(String text) throws IllegalArgumentException {
        try {
            this.datatype.checkValid(text, (ValidationContext)this);
        } catch (DatatypeException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}

