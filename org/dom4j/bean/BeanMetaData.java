/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.dom4j.DocumentFactory;
import org.dom4j.QName;
import org.dom4j.bean.BeanAttributeList;
import org.dom4j.bean.BeanDocumentFactory;
import org.dom4j.bean.BeanElement;

public class BeanMetaData {
    protected static final Object[] NULL_ARGS = new Object[0];
    private static Map<Class<?>, BeanMetaData> singletonCache = new HashMap();
    private static final DocumentFactory DOCUMENT_FACTORY = BeanDocumentFactory.getInstance();
    private Class<?> beanClass;
    private PropertyDescriptor[] propertyDescriptors;
    private QName[] qNames;
    private Method[] readMethods;
    private Method[] writeMethods;
    private Map<Object, Integer> nameMap = new HashMap<Object, Integer>();

    public BeanMetaData(Class<?> beanClass) {
        this.beanClass = beanClass;
        if (beanClass != null) {
            try {
                BeanInfo beanInfo = Introspector.getBeanInfo(beanClass);
                this.propertyDescriptors = beanInfo.getPropertyDescriptors();
            } catch (IntrospectionException e) {
                this.handleException(e);
            }
        }
        if (this.propertyDescriptors == null) {
            this.propertyDescriptors = new PropertyDescriptor[0];
        }
        int size = this.propertyDescriptors.length;
        this.qNames = new QName[size];
        this.readMethods = new Method[size];
        this.writeMethods = new Method[size];
        for (int i = 0; i < size; ++i) {
            QName qName;
            PropertyDescriptor propertyDescriptor = this.propertyDescriptors[i];
            String name = propertyDescriptor.getName();
            this.qNames[i] = qName = DOCUMENT_FACTORY.createQName(name);
            this.readMethods[i] = propertyDescriptor.getReadMethod();
            this.writeMethods[i] = propertyDescriptor.getWriteMethod();
            this.nameMap.put(name, i);
            this.nameMap.put(qName, i);
        }
    }

    public static BeanMetaData get(Class<?> beanClass) {
        BeanMetaData answer = singletonCache.get(beanClass);
        if (answer == null) {
            answer = new BeanMetaData(beanClass);
            singletonCache.put(beanClass, answer);
        }
        return answer;
    }

    public int attributeCount() {
        return this.propertyDescriptors.length;
    }

    public BeanAttributeList createAttributeList(BeanElement parent) {
        return new BeanAttributeList(parent, this);
    }

    public QName getQName(int index) {
        return this.qNames[index];
    }

    public int getIndex(String name) {
        Integer index = this.nameMap.get(name);
        return index != null ? index : -1;
    }

    public int getIndex(QName qName) {
        Integer index = this.nameMap.get(qName);
        return index != null ? index : -1;
    }

    public Object getData(int index, Object bean) {
        try {
            Method method = this.readMethods[index];
            return method.invoke(bean, NULL_ARGS);
        } catch (Exception e) {
            this.handleException(e);
            return null;
        }
    }

    public void setData(int index, Object bean, Object data) {
        try {
            Method method = this.writeMethods[index];
            Object[] args = new Object[]{data};
            method.invoke(bean, args);
        } catch (Exception e) {
            this.handleException(e);
        }
    }

    protected void handleException(Exception e) {
    }
}

