/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.ArrayList;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import org.apache.xmlbeans.impl.xb.xsdschema.AttributeGroupRef;
import org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType;
import org.apache.xmlbeans.impl.xb.xsdschema.GroupRef;
import org.apache.xmlbeans.impl.xb.xsdschema.Wildcard;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.AnnotatedImpl;

public class ExtensionTypeImpl
extends AnnotatedImpl
implements ExtensionType {
    private static final long serialVersionUID = 1L;
    private static final QName GROUP$0 = new QName("http://www.w3.org/2001/XMLSchema", "group");
    private static final QName ALL$2 = new QName("http://www.w3.org/2001/XMLSchema", "all");
    private static final QName CHOICE$4 = new QName("http://www.w3.org/2001/XMLSchema", "choice");
    private static final QName SEQUENCE$6 = new QName("http://www.w3.org/2001/XMLSchema", "sequence");
    private static final QName ATTRIBUTE$8 = new QName("http://www.w3.org/2001/XMLSchema", "attribute");
    private static final QName ATTRIBUTEGROUP$10 = new QName("http://www.w3.org/2001/XMLSchema", "attributeGroup");
    private static final QName ANYATTRIBUTE$12 = new QName("http://www.w3.org/2001/XMLSchema", "anyAttribute");
    private static final QName BASE$14 = new QName("", "base");

    public ExtensionTypeImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public GroupRef getGroup() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            GroupRef target = null;
            target = (GroupRef)((Object)this.get_store().find_element_user(GROUP$0, 0));
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
    public boolean isSetGroup() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(GROUP$0) != 0;
        }
    }

    @Override
    public void setGroup(GroupRef group) {
        this.generatedSetterHelperImpl(group, GROUP$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public GroupRef addNewGroup() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            GroupRef target = null;
            target = (GroupRef)((Object)this.get_store().add_element_user(GROUP$0));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetGroup() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(GROUP$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public All getAll() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            All target = null;
            target = (All)((Object)this.get_store().find_element_user(ALL$2, 0));
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
    public boolean isSetAll() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ALL$2) != 0;
        }
    }

    @Override
    public void setAll(All all) {
        this.generatedSetterHelperImpl(all, ALL$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public All addNewAll() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            All target = null;
            target = (All)((Object)this.get_store().add_element_user(ALL$2));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAll() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ALL$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ExplicitGroup getChoice() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ExplicitGroup target = null;
            target = (ExplicitGroup)((Object)this.get_store().find_element_user(CHOICE$4, 0));
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
    public boolean isSetChoice() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CHOICE$4) != 0;
        }
    }

    @Override
    public void setChoice(ExplicitGroup choice) {
        this.generatedSetterHelperImpl(choice, CHOICE$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ExplicitGroup addNewChoice() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ExplicitGroup target = null;
            target = (ExplicitGroup)((Object)this.get_store().add_element_user(CHOICE$4));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetChoice() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CHOICE$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ExplicitGroup getSequence() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ExplicitGroup target = null;
            target = (ExplicitGroup)((Object)this.get_store().find_element_user(SEQUENCE$6, 0));
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
    public boolean isSetSequence() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SEQUENCE$6) != 0;
        }
    }

    @Override
    public void setSequence(ExplicitGroup sequence) {
        this.generatedSetterHelperImpl(sequence, SEQUENCE$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ExplicitGroup addNewSequence() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ExplicitGroup target = null;
            target = (ExplicitGroup)((Object)this.get_store().add_element_user(SEQUENCE$6));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSequence() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SEQUENCE$6, 0);
        }
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
            this.get_store().find_all_element_users(ATTRIBUTE$8, targetList);
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
            target = (Attribute)((Object)this.get_store().find_element_user(ATTRIBUTE$8, i));
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
            return this.get_store().count_elements(ATTRIBUTE$8);
        }
    }

    @Override
    public void setAttributeArray(Attribute[] attributeArray) {
        this.check_orphaned();
        this.arraySetterHelper(attributeArray, ATTRIBUTE$8);
    }

    @Override
    public void setAttributeArray(int i, Attribute attribute) {
        this.generatedSetterHelperImpl(attribute, ATTRIBUTE$8, i, (short)2);
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
            target = (Attribute)((Object)this.get_store().insert_element_user(ATTRIBUTE$8, i));
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
            target = (Attribute)((Object)this.get_store().add_element_user(ATTRIBUTE$8));
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
            this.get_store().remove_element(ATTRIBUTE$8, i);
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
            this.get_store().find_all_element_users(ATTRIBUTEGROUP$10, targetList);
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
            target = (AttributeGroupRef)((Object)this.get_store().find_element_user(ATTRIBUTEGROUP$10, i));
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
            return this.get_store().count_elements(ATTRIBUTEGROUP$10);
        }
    }

    @Override
    public void setAttributeGroupArray(AttributeGroupRef[] attributeGroupArray) {
        this.check_orphaned();
        this.arraySetterHelper(attributeGroupArray, ATTRIBUTEGROUP$10);
    }

    @Override
    public void setAttributeGroupArray(int i, AttributeGroupRef attributeGroup) {
        this.generatedSetterHelperImpl(attributeGroup, ATTRIBUTEGROUP$10, i, (short)2);
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
            target = (AttributeGroupRef)((Object)this.get_store().insert_element_user(ATTRIBUTEGROUP$10, i));
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
            target = (AttributeGroupRef)((Object)this.get_store().add_element_user(ATTRIBUTEGROUP$10));
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
            this.get_store().remove_element(ATTRIBUTEGROUP$10, i);
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
            target = (Wildcard)((Object)this.get_store().find_element_user(ANYATTRIBUTE$12, 0));
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
            return this.get_store().count_elements(ANYATTRIBUTE$12) != 0;
        }
    }

    @Override
    public void setAnyAttribute(Wildcard anyAttribute) {
        this.generatedSetterHelperImpl(anyAttribute, ANYATTRIBUTE$12, 0, (short)1);
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
            target = (Wildcard)((Object)this.get_store().add_element_user(ANYATTRIBUTE$12));
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
            this.get_store().remove_element(ANYATTRIBUTE$12, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public QName getBase() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(BASE$14));
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
    public XmlQName xgetBase() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlQName target = null;
            target = (XmlQName)((Object)this.get_store().find_attribute_user(BASE$14));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setBase(QName base) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(BASE$14));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(BASE$14));
            }
            target.setQNameValue(base);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetBase(XmlQName base) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlQName target = null;
            target = (XmlQName)((Object)this.get_store().find_attribute_user(BASE$14));
            if (target == null) {
                target = (XmlQName)((Object)this.get_store().add_attribute_user(BASE$14));
            }
            target.set(base);
        }
    }
}

