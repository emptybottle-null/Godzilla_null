/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.dom4j.Attribute;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.QName;
import org.dom4j.tree.BackedList;
import org.dom4j.tree.DefaultElement;

public class IndexedElement
extends DefaultElement {
    private Map<Object, Object> elementIndex;
    private Map<Object, Attribute> attributeIndex;

    public IndexedElement(String name) {
        super(name);
    }

    public IndexedElement(QName qname) {
        super(qname);
    }

    public IndexedElement(QName qname, int attributeCount) {
        super(qname, attributeCount);
    }

    @Override
    public Attribute attribute(String name) {
        return this.attributeIndex().get(name);
    }

    @Override
    public Attribute attribute(QName qName) {
        return this.attributeIndex().get(qName);
    }

    @Override
    public Element element(String name) {
        return this.asElement(this.elementIndex().get(name));
    }

    @Override
    public Element element(QName qName) {
        return this.asElement(this.elementIndex().get(qName));
    }

    @Override
    public List<Element> elements(String name) {
        return this.asElementList(this.elementIndex().get(name));
    }

    @Override
    public List<Element> elements(QName qName) {
        return this.asElementList(this.elementIndex().get(qName));
    }

    protected Element asElement(Object object) {
        List list;
        if (object instanceof Element) {
            return (Element)object;
        }
        if (object != null && (list = (List)object).size() >= 1) {
            return (Element)list.get(0);
        }
        return null;
    }

    protected List<Element> asElementList(Object object) {
        if (object instanceof Element) {
            return this.createSingleResultList((Element)object);
        }
        if (object != null) {
            List list = (List)object;
            BackedList<Element> answer = this.createResultList();
            for (Element aList : list) {
                answer.addLocal(aList);
            }
            return answer;
        }
        return this.createEmptyList();
    }

    protected Iterator<Element> asElementIterator(Object object) {
        return this.asElementList(object).iterator();
    }

    @Override
    protected void addNode(Node node) {
        super.addNode(node);
        if (this.elementIndex != null && node instanceof Element) {
            this.addToElementIndex((Element)node);
        } else if (this.attributeIndex != null && node instanceof Attribute) {
            this.addToAttributeIndex((Attribute)node);
        }
    }

    @Override
    protected boolean removeNode(Node node) {
        if (super.removeNode(node)) {
            if (this.elementIndex != null && node instanceof Element) {
                this.removeFromElementIndex((Element)node);
            } else if (this.attributeIndex != null && node instanceof Attribute) {
                this.removeFromAttributeIndex((Attribute)node);
            }
            return true;
        }
        return false;
    }

    protected Map<Object, Attribute> attributeIndex() {
        if (this.attributeIndex == null) {
            this.attributeIndex = this.createAttributeIndex();
            Iterator<Attribute> iter = this.attributeIterator();
            while (iter.hasNext()) {
                this.addToAttributeIndex(iter.next());
            }
        }
        return this.attributeIndex;
    }

    protected Map<Object, Object> elementIndex() {
        if (this.elementIndex == null) {
            this.elementIndex = this.createElementIndex();
            Iterator<Element> iter = this.elementIterator();
            while (iter.hasNext()) {
                this.addToElementIndex(iter.next());
            }
        }
        return this.elementIndex;
    }

    protected Map<Object, Attribute> createAttributeIndex() {
        return this.createIndex();
    }

    protected Map<Object, Object> createElementIndex() {
        return this.createIndex();
    }

    protected void addToElementIndex(Element element) {
        QName qName = element.getQName();
        String name = qName.getName();
        this.addToElementIndex(qName, element);
        this.addToElementIndex(name, element);
    }

    protected void addToElementIndex(Object key, Element value) {
        Object oldValue = this.elementIndex.get(key);
        if (oldValue == null) {
            this.elementIndex.put(key, value);
        } else if (oldValue instanceof List) {
            List list = (List)oldValue;
            list.add(value);
        } else {
            List list = this.createList();
            list.add((Element)oldValue);
            list.add(value);
            this.elementIndex.put(key, list);
        }
    }

    protected void removeFromElementIndex(Element element) {
        QName qName = element.getQName();
        String name = qName.getName();
        this.removeFromElementIndex(qName, element);
        this.removeFromElementIndex(name, element);
    }

    protected void removeFromElementIndex(Object key, Element value) {
        Object oldValue = this.elementIndex.get(key);
        if (oldValue instanceof List) {
            List list = (List)oldValue;
            list.remove(value);
        } else {
            this.elementIndex.remove(key);
        }
    }

    protected void addToAttributeIndex(Attribute attribute) {
        QName qName = attribute.getQName();
        String name = qName.getName();
        this.addToAttributeIndex(qName, attribute);
        this.addToAttributeIndex(name, attribute);
    }

    protected void addToAttributeIndex(Object key, Attribute value) {
        Attribute oldValue = this.attributeIndex.get(key);
        if (oldValue != null) {
            this.attributeIndex.put(key, value);
        }
    }

    protected void removeFromAttributeIndex(Attribute attribute) {
        QName qName = attribute.getQName();
        String name = qName.getName();
        this.removeFromAttributeIndex(qName, attribute);
        this.removeFromAttributeIndex(name, attribute);
    }

    protected void removeFromAttributeIndex(Object key, Attribute value) {
        Attribute oldValue = this.attributeIndex.get(key);
        if (oldValue != null && oldValue.equals(value)) {
            this.attributeIndex.remove(key);
        }
    }

    protected <T> Map<Object, T> createIndex() {
        return new HashMap();
    }

    protected <T extends Node> List<T> createList() {
        return new ArrayList();
    }
}

