/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.bean;

import org.dom4j.Attribute;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.bean.BeanElement;
import org.dom4j.tree.DefaultAttribute;
import org.xml.sax.Attributes;

public class BeanDocumentFactory
extends DocumentFactory {
    private static BeanDocumentFactory singleton = new BeanDocumentFactory();

    public static DocumentFactory getInstance() {
        return singleton;
    }

    @Override
    public Element createElement(QName qname) {
        Object bean = this.createBean(qname);
        if (bean == null) {
            return new BeanElement(qname);
        }
        return new BeanElement(qname, bean);
    }

    public Element createElement(QName qname, Attributes attributes) {
        Object bean = this.createBean(qname, attributes);
        if (bean == null) {
            return new BeanElement(qname);
        }
        return new BeanElement(qname, bean);
    }

    @Override
    public Attribute createAttribute(Element owner, QName qname, String value) {
        return new DefaultAttribute(qname, value);
    }

    protected Object createBean(QName qname) {
        return null;
    }

    protected Object createBean(QName qname, Attributes attributes) {
        String value = attributes.getValue("class");
        if (value != null) {
            try {
                Class<?> beanClass = Class.forName(value, true, BeanDocumentFactory.class.getClassLoader());
                return beanClass.newInstance();
            } catch (Exception e) {
                this.handleException(e);
            }
        }
        return null;
    }

    protected void handleException(Exception e) {
        System.out.println("#### Warning: couldn't create bean: " + e);
    }
}

