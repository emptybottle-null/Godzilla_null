/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.bean;

import java.util.List;
import org.dom4j.Attribute;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.bean.BeanAttribute;
import org.dom4j.bean.BeanAttributeList;
import org.dom4j.bean.BeanDocumentFactory;
import org.dom4j.tree.DefaultElement;
import org.dom4j.tree.NamespaceStack;
import org.xml.sax.Attributes;

public class BeanElement
extends DefaultElement {
    private static final DocumentFactory DOCUMENT_FACTORY = BeanDocumentFactory.getInstance();
    private Object bean;

    public BeanElement(String name, Object bean) {
        this(DOCUMENT_FACTORY.createQName(name), bean);
    }

    public BeanElement(String name, Namespace namespace, Object bean) {
        this(DOCUMENT_FACTORY.createQName(name, namespace), bean);
    }

    public BeanElement(QName qname, Object bean) {
        super(qname);
        this.bean = bean;
    }

    public BeanElement(QName qname) {
        super(qname);
    }

    @Override
    public Object getData() {
        return this.bean;
    }

    @Override
    public void setData(Object data) {
        this.bean = data;
        this.setAttributeList(null);
    }

    @Override
    public BeanAttribute attribute(String name) {
        return this.getBeanAttributeList().attribute(name);
    }

    @Override
    public BeanAttribute attribute(QName qname) {
        return this.getBeanAttributeList().attribute(qname);
    }

    @Override
    public Element addAttribute(String name, String value) {
        BeanAttribute attribute = this.attribute(name);
        if (attribute != null) {
            attribute.setValue(value);
        }
        return this;
    }

    @Override
    public Element addAttribute(QName qName, String value) {
        BeanAttribute attribute = this.attribute(qName);
        if (attribute != null) {
            attribute.setValue(value);
        }
        return this;
    }

    @Override
    public void setAttributes(List<Attribute> attributes) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public void setAttributes(Attributes attributes, NamespaceStack namespaceStack, boolean noNamespaceAttributes) {
        String className = attributes.getValue("class");
        if (className != null) {
            try {
                Class<?> beanClass = Class.forName(className, true, BeanElement.class.getClassLoader());
                this.setData(beanClass.newInstance());
                for (int i = 0; i < attributes.getLength(); ++i) {
                    String attributeName = attributes.getLocalName(i);
                    if ("class".equalsIgnoreCase(attributeName)) continue;
                    this.addAttribute(attributeName, attributes.getValue(i));
                }
            } catch (Exception ex) {
                ((BeanDocumentFactory)this.getDocumentFactory()).handleException(ex);
            }
        } else {
            super.setAttributes(attributes, namespaceStack, noNamespaceAttributes);
        }
    }

    @Override
    protected DocumentFactory getDocumentFactory() {
        return DOCUMENT_FACTORY;
    }

    protected BeanAttributeList getBeanAttributeList() {
        return (BeanAttributeList)this.attributeList();
    }

    @Override
    protected List<Attribute> createAttributeList() {
        return new BeanAttributeList(this);
    }

    @Override
    protected List<Attribute> createAttributeList(int size) {
        return new BeanAttributeList(this);
    }
}

