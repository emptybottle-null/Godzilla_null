/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.ArrayList;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlNCName;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import org.apache.xmlbeans.impl.xb.xsdschema.AttributeGroupRef;
import org.apache.xmlbeans.impl.xb.xsdschema.ComplexContentDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.ComplexType;
import org.apache.xmlbeans.impl.xb.xsdschema.DerivationSet;
import org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.GroupRef;
import org.apache.xmlbeans.impl.xb.xsdschema.SimpleContentDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.Wildcard;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.AnnotatedImpl;

public class ComplexTypeImpl
extends AnnotatedImpl
implements ComplexType {
    private static final long serialVersionUID = 1L;
    private static final QName SIMPLECONTENT$0 = new QName("http://www.w3.org/2001/XMLSchema", "simpleContent");
    private static final QName COMPLEXCONTENT$2 = new QName("http://www.w3.org/2001/XMLSchema", "complexContent");
    private static final QName GROUP$4 = new QName("http://www.w3.org/2001/XMLSchema", "group");
    private static final QName ALL$6 = new QName("http://www.w3.org/2001/XMLSchema", "all");
    private static final QName CHOICE$8 = new QName("http://www.w3.org/2001/XMLSchema", "choice");
    private static final QName SEQUENCE$10 = new QName("http://www.w3.org/2001/XMLSchema", "sequence");
    private static final QName ATTRIBUTE$12 = new QName("http://www.w3.org/2001/XMLSchema", "attribute");
    private static final QName ATTRIBUTEGROUP$14 = new QName("http://www.w3.org/2001/XMLSchema", "attributeGroup");
    private static final QName ANYATTRIBUTE$16 = new QName("http://www.w3.org/2001/XMLSchema", "anyAttribute");
    private static final QName NAME$18 = new QName("", "name");
    private static final QName MIXED$20 = new QName("", "mixed");
    private static final QName ABSTRACT$22 = new QName("", "abstract");
    private static final QName FINAL$24 = new QName("", "final");
    private static final QName BLOCK$26 = new QName("", "block");

    public ComplexTypeImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SimpleContentDocument.SimpleContent getSimpleContent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleContentDocument.SimpleContent target = null;
            target = (SimpleContentDocument.SimpleContent)((Object)this.get_store().find_element_user(SIMPLECONTENT$0, 0));
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
    public boolean isSetSimpleContent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SIMPLECONTENT$0) != 0;
        }
    }

    @Override
    public void setSimpleContent(SimpleContentDocument.SimpleContent simpleContent) {
        this.generatedSetterHelperImpl(simpleContent, SIMPLECONTENT$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SimpleContentDocument.SimpleContent addNewSimpleContent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleContentDocument.SimpleContent target = null;
            target = (SimpleContentDocument.SimpleContent)((Object)this.get_store().add_element_user(SIMPLECONTENT$0));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSimpleContent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SIMPLECONTENT$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ComplexContentDocument.ComplexContent getComplexContent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ComplexContentDocument.ComplexContent target = null;
            target = (ComplexContentDocument.ComplexContent)((Object)this.get_store().find_element_user(COMPLEXCONTENT$2, 0));
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
    public boolean isSetComplexContent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(COMPLEXCONTENT$2) != 0;
        }
    }

    @Override
    public void setComplexContent(ComplexContentDocument.ComplexContent complexContent) {
        this.generatedSetterHelperImpl(complexContent, COMPLEXCONTENT$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ComplexContentDocument.ComplexContent addNewComplexContent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ComplexContentDocument.ComplexContent target = null;
            target = (ComplexContentDocument.ComplexContent)((Object)this.get_store().add_element_user(COMPLEXCONTENT$2));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetComplexContent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(COMPLEXCONTENT$2, 0);
        }
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
            target = (GroupRef)((Object)this.get_store().find_element_user(GROUP$4, 0));
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
            return this.get_store().count_elements(GROUP$4) != 0;
        }
    }

    @Override
    public void setGroup(GroupRef group) {
        this.generatedSetterHelperImpl(group, GROUP$4, 0, (short)1);
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
            target = (GroupRef)((Object)this.get_store().add_element_user(GROUP$4));
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
            this.get_store().remove_element(GROUP$4, 0);
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
            target = (All)((Object)this.get_store().find_element_user(ALL$6, 0));
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
            return this.get_store().count_elements(ALL$6) != 0;
        }
    }

    @Override
    public void setAll(All all) {
        this.generatedSetterHelperImpl(all, ALL$6, 0, (short)1);
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
            target = (All)((Object)this.get_store().add_element_user(ALL$6));
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
            this.get_store().remove_element(ALL$6, 0);
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
            target = (ExplicitGroup)((Object)this.get_store().find_element_user(CHOICE$8, 0));
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
            return this.get_store().count_elements(CHOICE$8) != 0;
        }
    }

    @Override
    public void setChoice(ExplicitGroup choice) {
        this.generatedSetterHelperImpl(choice, CHOICE$8, 0, (short)1);
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
            target = (ExplicitGroup)((Object)this.get_store().add_element_user(CHOICE$8));
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
            this.get_store().remove_element(CHOICE$8, 0);
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
            target = (ExplicitGroup)((Object)this.get_store().find_element_user(SEQUENCE$10, 0));
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
            return this.get_store().count_elements(SEQUENCE$10) != 0;
        }
    }

    @Override
    public void setSequence(ExplicitGroup sequence) {
        this.generatedSetterHelperImpl(sequence, SEQUENCE$10, 0, (short)1);
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
            target = (ExplicitGroup)((Object)this.get_store().add_element_user(SEQUENCE$10));
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
            this.get_store().remove_element(SEQUENCE$10, 0);
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
            this.get_store().find_all_element_users(ATTRIBUTE$12, targetList);
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
            target = (Attribute)((Object)this.get_store().find_element_user(ATTRIBUTE$12, i));
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
            return this.get_store().count_elements(ATTRIBUTE$12);
        }
    }

    @Override
    public void setAttributeArray(Attribute[] attributeArray) {
        this.check_orphaned();
        this.arraySetterHelper(attributeArray, ATTRIBUTE$12);
    }

    @Override
    public void setAttributeArray(int i, Attribute attribute) {
        this.generatedSetterHelperImpl(attribute, ATTRIBUTE$12, i, (short)2);
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
            target = (Attribute)((Object)this.get_store().insert_element_user(ATTRIBUTE$12, i));
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
            target = (Attribute)((Object)this.get_store().add_element_user(ATTRIBUTE$12));
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
            this.get_store().remove_element(ATTRIBUTE$12, i);
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
            this.get_store().find_all_element_users(ATTRIBUTEGROUP$14, targetList);
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
            target = (AttributeGroupRef)((Object)this.get_store().find_element_user(ATTRIBUTEGROUP$14, i));
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
            return this.get_store().count_elements(ATTRIBUTEGROUP$14);
        }
    }

    @Override
    public void setAttributeGroupArray(AttributeGroupRef[] attributeGroupArray) {
        this.check_orphaned();
        this.arraySetterHelper(attributeGroupArray, ATTRIBUTEGROUP$14);
    }

    @Override
    public void setAttributeGroupArray(int i, AttributeGroupRef attributeGroup) {
        this.generatedSetterHelperImpl(attributeGroup, ATTRIBUTEGROUP$14, i, (short)2);
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
            target = (AttributeGroupRef)((Object)this.get_store().insert_element_user(ATTRIBUTEGROUP$14, i));
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
            target = (AttributeGroupRef)((Object)this.get_store().add_element_user(ATTRIBUTEGROUP$14));
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
            this.get_store().remove_element(ATTRIBUTEGROUP$14, i);
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
            target = (Wildcard)((Object)this.get_store().find_element_user(ANYATTRIBUTE$16, 0));
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
            return this.get_store().count_elements(ANYATTRIBUTE$16) != 0;
        }
    }

    @Override
    public void setAnyAttribute(Wildcard anyAttribute) {
        this.generatedSetterHelperImpl(anyAttribute, ANYATTRIBUTE$16, 0, (short)1);
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
            target = (Wildcard)((Object)this.get_store().add_element_user(ANYATTRIBUTE$16));
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
            this.get_store().remove_element(ANYATTRIBUTE$16, 0);
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
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(NAME$18));
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
            target = (XmlNCName)((Object)this.get_store().find_attribute_user(NAME$18));
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
            return this.get_store().find_attribute_user(NAME$18) != null;
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
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(NAME$18));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(NAME$18));
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
            target = (XmlNCName)((Object)this.get_store().find_attribute_user(NAME$18));
            if (target == null) {
                target = (XmlNCName)((Object)this.get_store().add_attribute_user(NAME$18));
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
            this.get_store().remove_attribute(NAME$18);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getMixed() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(MIXED$20));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_default_attribute_value(MIXED$20));
            }
            if (target == null) {
                return false;
            }
            return target.getBooleanValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlBoolean xgetMixed() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean target = null;
            target = (XmlBoolean)((Object)this.get_store().find_attribute_user(MIXED$20));
            if (target == null) {
                target = (XmlBoolean)this.get_default_attribute_value(MIXED$20);
            }
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetMixed() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(MIXED$20) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setMixed(boolean mixed) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(MIXED$20));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(MIXED$20));
            }
            target.setBooleanValue(mixed);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetMixed(XmlBoolean mixed) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean target = null;
            target = (XmlBoolean)((Object)this.get_store().find_attribute_user(MIXED$20));
            if (target == null) {
                target = (XmlBoolean)((Object)this.get_store().add_attribute_user(MIXED$20));
            }
            target.set(mixed);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetMixed() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(MIXED$20);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getAbstract() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(ABSTRACT$22));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_default_attribute_value(ABSTRACT$22));
            }
            if (target == null) {
                return false;
            }
            return target.getBooleanValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlBoolean xgetAbstract() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean target = null;
            target = (XmlBoolean)((Object)this.get_store().find_attribute_user(ABSTRACT$22));
            if (target == null) {
                target = (XmlBoolean)this.get_default_attribute_value(ABSTRACT$22);
            }
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetAbstract() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(ABSTRACT$22) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAbstract(boolean xabstract) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(ABSTRACT$22));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(ABSTRACT$22));
            }
            target.setBooleanValue(xabstract);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAbstract(XmlBoolean xabstract) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean target = null;
            target = (XmlBoolean)((Object)this.get_store().find_attribute_user(ABSTRACT$22));
            if (target == null) {
                target = (XmlBoolean)((Object)this.get_store().add_attribute_user(ABSTRACT$22));
            }
            target.set(xabstract);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAbstract() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(ABSTRACT$22);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Object getFinal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(FINAL$24));
            if (target == null) {
                return null;
            }
            return target.getObjectValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public DerivationSet xgetFinal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            DerivationSet target = null;
            target = (DerivationSet)((Object)this.get_store().find_attribute_user(FINAL$24));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFinal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(FINAL$24) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFinal(Object xfinal) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(FINAL$24));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(FINAL$24));
            }
            target.setObjectValue(xfinal);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFinal(DerivationSet xfinal) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            DerivationSet target = null;
            target = (DerivationSet)((Object)this.get_store().find_attribute_user(FINAL$24));
            if (target == null) {
                target = (DerivationSet)((Object)this.get_store().add_attribute_user(FINAL$24));
            }
            target.set(xfinal);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFinal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(FINAL$24);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Object getBlock() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(BLOCK$26));
            if (target == null) {
                return null;
            }
            return target.getObjectValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public DerivationSet xgetBlock() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            DerivationSet target = null;
            target = (DerivationSet)((Object)this.get_store().find_attribute_user(BLOCK$26));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBlock() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(BLOCK$26) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setBlock(Object block) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(BLOCK$26));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(BLOCK$26));
            }
            target.setObjectValue(block);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetBlock(DerivationSet block) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            DerivationSet target = null;
            target = (DerivationSet)((Object)this.get_store().find_attribute_user(BLOCK$26));
            if (target == null) {
                target = (DerivationSet)((Object)this.get_store().add_attribute_user(BLOCK$26));
            }
            target.set(block);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBlock() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(BLOCK$26);
        }
    }
}

