/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlNCName;
import org.apache.xmlbeans.XmlNonNegativeInteger;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.apache.xmlbeans.impl.xb.xsdschema.AllNNI;
import org.apache.xmlbeans.impl.xb.xsdschema.AnyDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.Group;
import org.apache.xmlbeans.impl.xb.xsdschema.GroupRef;
import org.apache.xmlbeans.impl.xb.xsdschema.LocalElement;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.AnnotatedImpl;

public class GroupImpl
extends AnnotatedImpl
implements Group {
    private static final long serialVersionUID = 1L;
    private static final QName ELEMENT$0 = new QName("http://www.w3.org/2001/XMLSchema", "element");
    private static final QName GROUP$2 = new QName("http://www.w3.org/2001/XMLSchema", "group");
    private static final QName ALL$4 = new QName("http://www.w3.org/2001/XMLSchema", "all");
    private static final QName CHOICE$6 = new QName("http://www.w3.org/2001/XMLSchema", "choice");
    private static final QName SEQUENCE$8 = new QName("http://www.w3.org/2001/XMLSchema", "sequence");
    private static final QName ANY$10 = new QName("http://www.w3.org/2001/XMLSchema", "any");
    private static final QName NAME$12 = new QName("", "name");
    private static final QName REF$14 = new QName("", "ref");
    private static final QName MINOCCURS$16 = new QName("", "minOccurs");
    private static final QName MAXOCCURS$18 = new QName("", "maxOccurs");

    public GroupImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public LocalElement[] getElementArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList targetList = new ArrayList();
            this.get_store().find_all_element_users(ELEMENT$0, targetList);
            LocalElement[] result = new LocalElement[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public LocalElement getElementArray(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            LocalElement target = null;
            target = (LocalElement)((Object)this.get_store().find_element_user(ELEMENT$0, i));
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
    public int sizeOfElementArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ELEMENT$0);
        }
    }

    @Override
    public void setElementArray(LocalElement[] elementArray) {
        this.check_orphaned();
        this.arraySetterHelper(elementArray, ELEMENT$0);
    }

    @Override
    public void setElementArray(int i, LocalElement element) {
        this.generatedSetterHelperImpl(element, ELEMENT$0, i, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public LocalElement insertNewElement(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            LocalElement target = null;
            target = (LocalElement)((Object)this.get_store().insert_element_user(ELEMENT$0, i));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public LocalElement addNewElement() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            LocalElement target = null;
            target = (LocalElement)((Object)this.get_store().add_element_user(ELEMENT$0));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeElement(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ELEMENT$0, i);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public GroupRef[] getGroupArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList targetList = new ArrayList();
            this.get_store().find_all_element_users(GROUP$2, targetList);
            GroupRef[] result = new GroupRef[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public GroupRef getGroupArray(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            GroupRef target = null;
            target = (GroupRef)((Object)this.get_store().find_element_user(GROUP$2, i));
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
    public int sizeOfGroupArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(GROUP$2);
        }
    }

    @Override
    public void setGroupArray(GroupRef[] groupArray) {
        this.check_orphaned();
        this.arraySetterHelper(groupArray, GROUP$2);
    }

    @Override
    public void setGroupArray(int i, GroupRef group) {
        this.generatedSetterHelperImpl(group, GROUP$2, i, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public GroupRef insertNewGroup(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            GroupRef target = null;
            target = (GroupRef)((Object)this.get_store().insert_element_user(GROUP$2, i));
            return target;
        }
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
            target = (GroupRef)((Object)this.get_store().add_element_user(GROUP$2));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeGroup(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(GROUP$2, i);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public All[] getAllArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList targetList = new ArrayList();
            this.get_store().find_all_element_users(ALL$4, targetList);
            All[] result = new All[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public All getAllArray(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            All target = null;
            target = (All)((Object)this.get_store().find_element_user(ALL$4, i));
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
    public int sizeOfAllArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ALL$4);
        }
    }

    @Override
    public void setAllArray(All[] allArray) {
        this.check_orphaned();
        this.arraySetterHelper(allArray, ALL$4);
    }

    @Override
    public void setAllArray(int i, All all) {
        this.generatedSetterHelperImpl(all, ALL$4, i, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public All insertNewAll(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            All target = null;
            target = (All)((Object)this.get_store().insert_element_user(ALL$4, i));
            return target;
        }
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
            target = (All)((Object)this.get_store().add_element_user(ALL$4));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAll(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ALL$4, i);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ExplicitGroup[] getChoiceArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList targetList = new ArrayList();
            this.get_store().find_all_element_users(CHOICE$6, targetList);
            ExplicitGroup[] result = new ExplicitGroup[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ExplicitGroup getChoiceArray(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ExplicitGroup target = null;
            target = (ExplicitGroup)((Object)this.get_store().find_element_user(CHOICE$6, i));
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
    public int sizeOfChoiceArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CHOICE$6);
        }
    }

    @Override
    public void setChoiceArray(ExplicitGroup[] choiceArray) {
        this.check_orphaned();
        this.arraySetterHelper(choiceArray, CHOICE$6);
    }

    @Override
    public void setChoiceArray(int i, ExplicitGroup choice) {
        this.generatedSetterHelperImpl(choice, CHOICE$6, i, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ExplicitGroup insertNewChoice(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ExplicitGroup target = null;
            target = (ExplicitGroup)((Object)this.get_store().insert_element_user(CHOICE$6, i));
            return target;
        }
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
            target = (ExplicitGroup)((Object)this.get_store().add_element_user(CHOICE$6));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeChoice(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CHOICE$6, i);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ExplicitGroup[] getSequenceArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList targetList = new ArrayList();
            this.get_store().find_all_element_users(SEQUENCE$8, targetList);
            ExplicitGroup[] result = new ExplicitGroup[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ExplicitGroup getSequenceArray(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ExplicitGroup target = null;
            target = (ExplicitGroup)((Object)this.get_store().find_element_user(SEQUENCE$8, i));
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
    public int sizeOfSequenceArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SEQUENCE$8);
        }
    }

    @Override
    public void setSequenceArray(ExplicitGroup[] sequenceArray) {
        this.check_orphaned();
        this.arraySetterHelper(sequenceArray, SEQUENCE$8);
    }

    @Override
    public void setSequenceArray(int i, ExplicitGroup sequence) {
        this.generatedSetterHelperImpl(sequence, SEQUENCE$8, i, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ExplicitGroup insertNewSequence(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ExplicitGroup target = null;
            target = (ExplicitGroup)((Object)this.get_store().insert_element_user(SEQUENCE$8, i));
            return target;
        }
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
            target = (ExplicitGroup)((Object)this.get_store().add_element_user(SEQUENCE$8));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSequence(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SEQUENCE$8, i);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public AnyDocument.Any[] getAnyArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList targetList = new ArrayList();
            this.get_store().find_all_element_users(ANY$10, targetList);
            AnyDocument.Any[] result = new AnyDocument.Any[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public AnyDocument.Any getAnyArray(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            AnyDocument.Any target = null;
            target = (AnyDocument.Any)((Object)this.get_store().find_element_user(ANY$10, i));
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
    public int sizeOfAnyArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ANY$10);
        }
    }

    @Override
    public void setAnyArray(AnyDocument.Any[] anyArray) {
        this.check_orphaned();
        this.arraySetterHelper(anyArray, ANY$10);
    }

    @Override
    public void setAnyArray(int i, AnyDocument.Any any) {
        this.generatedSetterHelperImpl(any, ANY$10, i, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public AnyDocument.Any insertNewAny(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            AnyDocument.Any target = null;
            target = (AnyDocument.Any)((Object)this.get_store().insert_element_user(ANY$10, i));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public AnyDocument.Any addNewAny() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            AnyDocument.Any target = null;
            target = (AnyDocument.Any)((Object)this.get_store().add_element_user(ANY$10));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAny(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ANY$10, i);
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
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(NAME$12));
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
            target = (XmlNCName)((Object)this.get_store().find_attribute_user(NAME$12));
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
            return this.get_store().find_attribute_user(NAME$12) != null;
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
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(NAME$12));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(NAME$12));
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
            target = (XmlNCName)((Object)this.get_store().find_attribute_user(NAME$12));
            if (target == null) {
                target = (XmlNCName)((Object)this.get_store().add_attribute_user(NAME$12));
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
            this.get_store().remove_attribute(NAME$12);
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
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(REF$14));
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
            target = (XmlQName)((Object)this.get_store().find_attribute_user(REF$14));
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
            return this.get_store().find_attribute_user(REF$14) != null;
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
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(REF$14));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(REF$14));
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
            target = (XmlQName)((Object)this.get_store().find_attribute_user(REF$14));
            if (target == null) {
                target = (XmlQName)((Object)this.get_store().add_attribute_user(REF$14));
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
            this.get_store().remove_attribute(REF$14);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public BigInteger getMinOccurs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(MINOCCURS$16));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_default_attribute_value(MINOCCURS$16));
            }
            if (target == null) {
                return null;
            }
            return target.getBigIntegerValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlNonNegativeInteger xgetMinOccurs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlNonNegativeInteger target = null;
            target = (XmlNonNegativeInteger)((Object)this.get_store().find_attribute_user(MINOCCURS$16));
            if (target == null) {
                target = (XmlNonNegativeInteger)this.get_default_attribute_value(MINOCCURS$16);
            }
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetMinOccurs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(MINOCCURS$16) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setMinOccurs(BigInteger minOccurs) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(MINOCCURS$16));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(MINOCCURS$16));
            }
            target.setBigIntegerValue(minOccurs);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetMinOccurs(XmlNonNegativeInteger minOccurs) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlNonNegativeInteger target = null;
            target = (XmlNonNegativeInteger)((Object)this.get_store().find_attribute_user(MINOCCURS$16));
            if (target == null) {
                target = (XmlNonNegativeInteger)((Object)this.get_store().add_attribute_user(MINOCCURS$16));
            }
            target.set(minOccurs);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetMinOccurs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(MINOCCURS$16);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Object getMaxOccurs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(MAXOCCURS$18));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_default_attribute_value(MAXOCCURS$18));
            }
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
    public AllNNI xgetMaxOccurs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            AllNNI target = null;
            target = (AllNNI)((Object)this.get_store().find_attribute_user(MAXOCCURS$18));
            if (target == null) {
                target = (AllNNI)this.get_default_attribute_value(MAXOCCURS$18);
            }
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetMaxOccurs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(MAXOCCURS$18) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setMaxOccurs(Object maxOccurs) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(MAXOCCURS$18));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(MAXOCCURS$18));
            }
            target.setObjectValue(maxOccurs);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetMaxOccurs(AllNNI maxOccurs) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            AllNNI target = null;
            target = (AllNNI)((Object)this.get_store().find_attribute_user(MAXOCCURS$18));
            if (target == null) {
                target = (AllNNI)((Object)this.get_store().add_attribute_user(MAXOCCURS$18));
            }
            target.set(maxOccurs);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetMaxOccurs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(MAXOCCURS$18);
        }
    }
}

