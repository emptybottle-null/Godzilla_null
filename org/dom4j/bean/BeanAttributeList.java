/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.bean;

import java.util.AbstractList;
import org.dom4j.Attribute;
import org.dom4j.QName;
import org.dom4j.bean.BeanAttribute;
import org.dom4j.bean.BeanElement;
import org.dom4j.bean.BeanMetaData;

public class BeanAttributeList
extends AbstractList<Attribute> {
    private BeanElement parent;
    private BeanMetaData beanMetaData;
    private BeanAttribute[] attributes;

    public BeanAttributeList(BeanElement parent, BeanMetaData beanMetaData) {
        this.parent = parent;
        this.beanMetaData = beanMetaData;
        this.attributes = new BeanAttribute[beanMetaData.attributeCount()];
    }

    public BeanAttributeList(BeanElement parent) {
        this.parent = parent;
        Object data = parent.getData();
        Class<?> beanClass = data != null ? data.getClass() : null;
        this.beanMetaData = BeanMetaData.get(beanClass);
        this.attributes = new BeanAttribute[this.beanMetaData.attributeCount()];
    }

    public BeanAttribute attribute(String name) {
        int index = this.beanMetaData.getIndex(name);
        return this.attribute(index);
    }

    public BeanAttribute attribute(QName qname) {
        int index = this.beanMetaData.getIndex(qname);
        return this.attribute(index);
    }

    public BeanAttribute attribute(int index) {
        if (index >= 0 && index <= this.attributes.length) {
            BeanAttribute attribute = this.attributes[index];
            if (attribute == null) {
                this.attributes[index] = attribute = this.createAttribute(this.parent, index);
            }
            return attribute;
        }
        return null;
    }

    public BeanElement getParent() {
        return this.parent;
    }

    public QName getQName(int index) {
        return this.beanMetaData.getQName(index);
    }

    public Object getData(int index) {
        return this.beanMetaData.getData(index, this.parent.getData());
    }

    public void setData(int index, Object data) {
        this.beanMetaData.setData(index, this.parent.getData(), data);
    }

    @Override
    public int size() {
        return this.attributes.length;
    }

    @Override
    public BeanAttribute get(int index) {
        BeanAttribute attribute = this.attributes[index];
        if (attribute == null) {
            this.attributes[index] = attribute = this.createAttribute(this.parent, index);
        }
        return attribute;
    }

    @Override
    public boolean add(BeanAttribute object) {
        throw new UnsupportedOperationException("add(Object) unsupported");
    }

    @Override
    public void add(int index, BeanAttribute object) {
        throw new UnsupportedOperationException("add(int,Object) unsupported");
    }

    @Override
    public BeanAttribute set(int index, BeanAttribute object) {
        throw new UnsupportedOperationException("set(int,Object) unsupported");
    }

    @Override
    public boolean remove(Object object) {
        return false;
    }

    @Override
    public BeanAttribute remove(int index) {
        BeanAttribute attribute = this.get(index);
        attribute.setValue(null);
        return attribute;
    }

    @Override
    public void clear() {
        for (BeanAttribute attribute : this.attributes) {
            if (attribute == null) continue;
            attribute.setValue(null);
        }
    }

    protected BeanAttribute createAttribute(BeanElement element, int index) {
        return new BeanAttribute(this, index);
    }
}

