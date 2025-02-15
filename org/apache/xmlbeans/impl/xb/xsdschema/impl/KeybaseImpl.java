/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.ArrayList;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlNCName;
import org.apache.xmlbeans.impl.xb.xsdschema.FieldDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.Keybase;
import org.apache.xmlbeans.impl.xb.xsdschema.SelectorDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.AnnotatedImpl;

public class KeybaseImpl
extends AnnotatedImpl
implements Keybase {
    private static final long serialVersionUID = 1L;
    private static final QName SELECTOR$0 = new QName("http://www.w3.org/2001/XMLSchema", "selector");
    private static final QName FIELD$2 = new QName("http://www.w3.org/2001/XMLSchema", "field");
    private static final QName NAME$4 = new QName("", "name");

    public KeybaseImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SelectorDocument.Selector getSelector() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SelectorDocument.Selector target = null;
            target = (SelectorDocument.Selector)((Object)this.get_store().find_element_user(SELECTOR$0, 0));
            if (target == null) {
                return null;
            }
            return target;
        }
    }

    @Override
    public void setSelector(SelectorDocument.Selector selector) {
        this.generatedSetterHelperImpl(selector, SELECTOR$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SelectorDocument.Selector addNewSelector() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SelectorDocument.Selector target = null;
            target = (SelectorDocument.Selector)((Object)this.get_store().add_element_user(SELECTOR$0));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public FieldDocument.Field[] getFieldArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList targetList = new ArrayList();
            this.get_store().find_all_element_users(FIELD$2, targetList);
            FieldDocument.Field[] result = new FieldDocument.Field[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public FieldDocument.Field getFieldArray(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            FieldDocument.Field target = null;
            target = (FieldDocument.Field)((Object)this.get_store().find_element_user(FIELD$2, i));
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfFieldArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FIELD$2);
        }
    }

    @Override
    public void setFieldArray(FieldDocument.Field[] fieldArray) {
        this.check_orphaned();
        this.arraySetterHelper(fieldArray, FIELD$2);
    }

    @Override
    public void setFieldArray(int i, FieldDocument.Field field) {
        this.generatedSetterHelperImpl(field, FIELD$2, i, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public FieldDocument.Field insertNewField(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            FieldDocument.Field target = null;
            target = (FieldDocument.Field)((Object)this.get_store().insert_element_user(FIELD$2, i));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public FieldDocument.Field addNewField() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            FieldDocument.Field target = null;
            target = (FieldDocument.Field)((Object)this.get_store().add_element_user(FIELD$2));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeField(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FIELD$2, i);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(NAME$4));
            if (target == null) {
                return null;
            }
            return target.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlNCName xgetName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlNCName target = null;
            target = (XmlNCName)((Object)this.get_store().find_attribute_user(NAME$4));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setName(String name) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(NAME$4));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(NAME$4));
            }
            target.setStringValue(name);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetName(XmlNCName name) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlNCName target = null;
            target = (XmlNCName)((Object)this.get_store().find_attribute_user(NAME$4));
            if (target == null) {
                target = (XmlNCName)((Object)this.get_store().add_attribute_user(NAME$4));
            }
            target.set(name);
        }
    }
}

