/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.ArrayList;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlNCName;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import org.apache.xmlbeans.impl.xb.xsdschema.AttributeGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.AttributeGroupRef;
import org.apache.xmlbeans.impl.xb.xsdschema.Wildcard;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.AnnotatedImpl;

public class AttributeGroupImpl
extends AnnotatedImpl
implements AttributeGroup {
    private static final long serialVersionUID = 1L;
    private static final QName ATTRIBUTE$0 = new QName("http://www.w3.org/2001/XMLSchema", "attribute");
    private static final QName ATTRIBUTEGROUP$2 = new QName("http://www.w3.org/2001/XMLSchema", "attributeGroup");
    private static final QName ANYATTRIBUTE$4 = new QName("http://www.w3.org/2001/XMLSchema", "anyAttribute");
    private static final QName NAME$6 = new QName("", "name");
    private static final QName REF$8 = new QName("", "ref");

    public AttributeGroupImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Attribute[] getAttributeArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList targetList = new ArrayList();
            this.get_store().find_all_element_users(ATTRIBUTE$0, targetList);
            Attribute[] result = new Attribute[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Attribute getAttributeArray(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Attribute target = null;
            target = (Attribute)((Object)this.get_store().find_element_user(ATTRIBUTE$0, i));
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
    public int sizeOfAttributeArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ATTRIBUTE$0);
        }
    }

    @Override
    public void setAttributeArray(Attribute[] attributeArray) {
        this.check_orphaned();
        this.arraySetterHelper(attributeArray, ATTRIBUTE$0);
    }

    @Override
    public void setAttributeArray(int i, Attribute attribute) {
        this.generatedSetterHelperImpl(attribute, ATTRIBUTE$0, i, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Attribute insertNewAttribute(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Attribute target = null;
            target = (Attribute)((Object)this.get_store().insert_element_user(ATTRIBUTE$0, i));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Attribute addNewAttribute() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Attribute target = null;
            target = (Attribute)((Object)this.get_store().add_element_user(ATTRIBUTE$0));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAttribute(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ATTRIBUTE$0, i);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public AttributeGroupRef[] getAttributeGroupArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList targetList = new ArrayList();
            this.get_store().find_all_element_users(ATTRIBUTEGROUP$2, targetList);
            AttributeGroupRef[] result = new AttributeGroupRef[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public AttributeGroupRef getAttributeGroupArray(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            AttributeGroupRef target = null;
            target = (AttributeGroupRef)((Object)this.get_store().find_element_user(ATTRIBUTEGROUP$2, i));
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
    public int sizeOfAttributeGroupArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ATTRIBUTEGROUP$2);
        }
    }

    @Override
    public void setAttributeGroupArray(AttributeGroupRef[] attributeGroupArray) {
        this.check_orphaned();
        this.arraySetterHelper(attributeGroupArray, ATTRIBUTEGROUP$2);
    }

    @Override
    public void setAttributeGroupArray(int i, AttributeGroupRef attributeGroup) {
        this.generatedSetterHelperImpl(attributeGroup, ATTRIBUTEGROUP$2, i, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public AttributeGroupRef insertNewAttributeGroup(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            AttributeGroupRef target = null;
            target = (AttributeGroupRef)((Object)this.get_store().insert_element_user(ATTRIBUTEGROUP$2, i));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public AttributeGroupRef addNewAttributeGroup() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            AttributeGroupRef target = null;
            target = (AttributeGroupRef)((Object)this.get_store().add_element_user(ATTRIBUTEGROUP$2));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAttributeGroup(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ATTRIBUTEGROUP$2, i);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Wildcard getAnyAttribute() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Wildcard target = null;
            target = (Wildcard)((Object)this.get_store().find_element_user(ANYATTRIBUTE$4, 0));
            if (target == null) {
                return null;
            }
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetAnyAttribute() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ANYATTRIBUTE$4) != 0;
        }
    }

    @Override
    public void setAnyAttribute(Wildcard anyAttribute) {
        this.generatedSetterHelperImpl(anyAttribute, ANYATTRIBUTE$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Wildcard addNewAnyAttribute() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Wildcard target = null;
            target = (Wildcard)((Object)this.get_store().add_element_user(ANYATTRIBUTE$4));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAnyAttribute() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ANYATTRIBUTE$4, 0);
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
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(NAME$6));
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
            target = (XmlNCName)((Object)this.get_store().find_attribute_user(NAME$6));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(NAME$6) != null;
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
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(NAME$6));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(NAME$6));
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
            target = (XmlNCName)((Object)this.get_store().find_attribute_user(NAME$6));
            if (target == null) {
                target = (XmlNCName)((Object)this.get_store().add_attribute_user(NAME$6));
            }
            target.set(name);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(NAME$6);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public QName getRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(REF$8));
            if (target == null) {
                return null;
            }
            return target.getQNameValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlQName xgetRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlQName target = null;
            target = (XmlQName)((Object)this.get_store().find_attribute_user(REF$8));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(REF$8) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setRef(QName ref) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(REF$8));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(REF$8));
            }
            target.setQNameValue(ref);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetRef(XmlQName ref) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlQName target = null;
            target = (XmlQName)((Object)this.get_store().find_attribute_user(REF$8));
            if (target == null) {
                target = (XmlQName)((Object)this.get_store().add_attribute_user(REF$8));
            }
            target.set(ref);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(REF$8);
        }
    }
}

