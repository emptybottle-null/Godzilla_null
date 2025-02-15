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
import org.dom4j.QName;
import org.dom4j.tree.AbstractAttribute;
import org.relaxng.datatype.DatatypeException;
import org.relaxng.datatype.ValidationContext;

public class DatatypeAttribute
extends AbstractAttribute
implements SerializationContext,
ValidationContext {
    private Element parent;
    private QName qname;
    private XSDatatype datatype;
    private Object data;
    private String text;

    public DatatypeAttribute(QName qname, XSDatatype datatype) {
        this.qname = qname;
        this.datatype = datatype;
    }

    public DatatypeAttribute(QName qname, XSDatatype datatype, String text) {
        this.qname = qname;
        this.datatype = datatype;
        this.text = text;
        this.data = this.convertToValue(text);
    }

    @Override
    public String toString() {
        return this.getClass().getName() + this.hashCode() + " [Attribute: name " + this.getQualifiedName() + " value \"" + this.getValue() + "\" data: " + this.getData() + "]";
    }

    public XSDatatype getXSDatatype() {
        return this.datatype;
    }

    public String getNamespacePrefix(String uri) {
        Namespace namespace;
        Element parentElement = this.getParent();
        if (parentElement != null && (namespace = parentElement.getNamespaceForURI(uri)) != null) {
            return namespace.getPrefix();
        }
        return null;
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
        Namespace namespace;
        if (prefix.equals(this.getNamespacePrefix())) {
            return this.getNamespaceURI();
        }
        Element parentElement = this.getParent();
        if (parentElement != null && (namespace = parentElement.getNamespaceForPrefix(prefix)) != null) {
            return namespace.getURI();
        }
        return null;
    }

    @Override
    public QName getQName() {
        return this.qname;
    }

    @Override
    public String getValue() {
        return this.text;
    }

    @Override
    public void setValue(String value) {
        this.validate(value);
        this.text = value;
        this.data = this.convertToValue(value);
    }

    @Override
    public Object getData() {
        return this.data;
    }

    @Override
    public void setData(Object data) {
        String s = this.datatype.convertToLexicalValue(data, (SerializationContext)this);
        this.validate(s);
        this.text = s;
        this.data = data;
    }

    @Override
    public Element getParent() {
        return this.parent;
    }

    @Override
    public void setParent(Element parent) {
        this.parent = parent;
    }

    @Override
    public boolean supportsParent() {
        return true;
    }

    @Override
    public boolean isReadOnly() {
        return false;
    }

    protected void validate(String txt) throws IllegalArgumentException {
        try {
            this.datatype.checkValid(txt, (ValidationContext)this);
        } catch (DatatypeException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    protected Object convertToValue(String txt) {
        if (this.datatype instanceof DatabindableDatatype) {
            XSDatatype bindable = this.datatype;
            return bindable.createJavaObject(txt, (ValidationContext)this);
        }
        return this.datatype.createValue(txt, (ValidationContext)this);
    }
}

