/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlNCName;
import org.apache.xmlbeans.XmlNonNegativeInteger;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.xb.xsdschema.AllNNI;
import org.apache.xmlbeans.impl.xb.xsdschema.BlockSet;
import org.apache.xmlbeans.impl.xb.xsdschema.DerivationSet;
import org.apache.xmlbeans.impl.xb.xsdschema.Element;
import org.apache.xmlbeans.impl.xb.xsdschema.FormChoice;
import org.apache.xmlbeans.impl.xb.xsdschema.Keybase;
import org.apache.xmlbeans.impl.xb.xsdschema.KeyrefDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.LocalComplexType;
import org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.AnnotatedImpl;

public class ElementImpl
extends AnnotatedImpl
implements Element {
    private static final long serialVersionUID = 1L;
    private static final QName SIMPLETYPE$0 = new QName("http://www.w3.org/2001/XMLSchema", "simpleType");
    private static final QName COMPLEXTYPE$2 = new QName("http://www.w3.org/2001/XMLSchema", "complexType");
    private static final QName UNIQUE$4 = new QName("http://www.w3.org/2001/XMLSchema", "unique");
    private static final QName KEY$6 = new QName("http://www.w3.org/2001/XMLSchema", "key");
    private static final QName KEYREF$8 = new QName("http://www.w3.org/2001/XMLSchema", "keyref");
    private static final QName NAME$10 = new QName("", "name");
    private static final QName REF$12 = new QName("", "ref");
    private static final QName TYPE$14 = new QName("", "type");
    private static final QName SUBSTITUTIONGROUP$16 = new QName("", "substitutionGroup");
    private static final QName MINOCCURS$18 = new QName("", "minOccurs");
    private static final QName MAXOCCURS$20 = new QName("", "maxOccurs");
    private static final QName DEFAULT$22 = new QName("", "default");
    private static final QName FIXED$24 = new QName("", "fixed");
    private static final QName NILLABLE$26 = new QName("", "nillable");
    private static final QName ABSTRACT$28 = new QName("", "abstract");
    private static final QName FINAL$30 = new QName("", "final");
    private static final QName BLOCK$32 = new QName("", "block");
    private static final QName FORM$34 = new QName("", "form");

    public ElementImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public LocalSimpleType getSimpleType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            LocalSimpleType target = null;
            target = (LocalSimpleType)((Object)this.get_store().find_element_user(SIMPLETYPE$0, 0));
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
    public boolean isSetSimpleType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SIMPLETYPE$0) != 0;
        }
    }

    @Override
    public void setSimpleType(LocalSimpleType simpleType) {
        this.generatedSetterHelperImpl(simpleType, SIMPLETYPE$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public LocalSimpleType addNewSimpleType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            LocalSimpleType target = null;
            target = (LocalSimpleType)((Object)this.get_store().add_element_user(SIMPLETYPE$0));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSimpleType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SIMPLETYPE$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public LocalComplexType getComplexType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            LocalComplexType target = null;
            target = (LocalComplexType)((Object)this.get_store().find_element_user(COMPLEXTYPE$2, 0));
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
    public boolean isSetComplexType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(COMPLEXTYPE$2) != 0;
        }
    }

    @Override
    public void setComplexType(LocalComplexType complexType) {
        this.generatedSetterHelperImpl(complexType, COMPLEXTYPE$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public LocalComplexType addNewComplexType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            LocalComplexType target = null;
            target = (LocalComplexType)((Object)this.get_store().add_element_user(COMPLEXTYPE$2));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetComplexType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(COMPLEXTYPE$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Keybase[] getUniqueArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList targetList = new ArrayList();
            this.get_store().find_all_element_users(UNIQUE$4, targetList);
            Keybase[] result = new Keybase[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Keybase getUniqueArray(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Keybase target = null;
            target = (Keybase)((Object)this.get_store().find_element_user(UNIQUE$4, i));
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
    public int sizeOfUniqueArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(UNIQUE$4);
        }
    }

    @Override
    public void setUniqueArray(Keybase[] uniqueArray) {
        this.check_orphaned();
        this.arraySetterHelper(uniqueArray, UNIQUE$4);
    }

    @Override
    public void setUniqueArray(int i, Keybase unique) {
        this.generatedSetterHelperImpl(unique, UNIQUE$4, i, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Keybase insertNewUnique(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Keybase target = null;
            target = (Keybase)((Object)this.get_store().insert_element_user(UNIQUE$4, i));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Keybase addNewUnique() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Keybase target = null;
            target = (Keybase)((Object)this.get_store().add_element_user(UNIQUE$4));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeUnique(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(UNIQUE$4, i);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Keybase[] getKeyArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList targetList = new ArrayList();
            this.get_store().find_all_element_users(KEY$6, targetList);
            Keybase[] result = new Keybase[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Keybase getKeyArray(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Keybase target = null;
            target = (Keybase)((Object)this.get_store().find_element_user(KEY$6, i));
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
    public int sizeOfKeyArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(KEY$6);
        }
    }

    @Override
    public void setKeyArray(Keybase[] keyArray) {
        this.check_orphaned();
        this.arraySetterHelper(keyArray, KEY$6);
    }

    @Override
    public void setKeyArray(int i, Keybase key) {
        this.generatedSetterHelperImpl(key, KEY$6, i, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Keybase insertNewKey(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Keybase target = null;
            target = (Keybase)((Object)this.get_store().insert_element_user(KEY$6, i));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Keybase addNewKey() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Keybase target = null;
            target = (Keybase)((Object)this.get_store().add_element_user(KEY$6));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeKey(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(KEY$6, i);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public KeyrefDocument.Keyref[] getKeyrefArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList targetList = new ArrayList();
            this.get_store().find_all_element_users(KEYREF$8, targetList);
            KeyrefDocument.Keyref[] result = new KeyrefDocument.Keyref[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public KeyrefDocument.Keyref getKeyrefArray(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            KeyrefDocument.Keyref target = null;
            target = (KeyrefDocument.Keyref)((Object)this.get_store().find_element_user(KEYREF$8, i));
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
    public int sizeOfKeyrefArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(KEYREF$8);
        }
    }

    @Override
    public void setKeyrefArray(KeyrefDocument.Keyref[] keyrefArray) {
        this.check_orphaned();
        this.arraySetterHelper(keyrefArray, KEYREF$8);
    }

    @Override
    public void setKeyrefArray(int i, KeyrefDocument.Keyref keyref) {
        this.generatedSetterHelperImpl(keyref, KEYREF$8, i, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public KeyrefDocument.Keyref insertNewKeyref(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            KeyrefDocument.Keyref target = null;
            target = (KeyrefDocument.Keyref)((Object)this.get_store().insert_element_user(KEYREF$8, i));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public KeyrefDocument.Keyref addNewKeyref() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            KeyrefDocument.Keyref target = null;
            target = (KeyrefDocument.Keyref)((Object)this.get_store().add_element_user(KEYREF$8));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeKeyref(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(KEYREF$8, i);
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
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(NAME$10));
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
            target = (XmlNCName)((Object)this.get_store().find_attribute_user(NAME$10));
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
            return this.get_store().find_attribute_user(NAME$10) != null;
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
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(NAME$10));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(NAME$10));
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
            target = (XmlNCName)((Object)this.get_store().find_attribute_user(NAME$10));
            if (target == null) {
                target = (XmlNCName)((Object)this.get_store().add_attribute_user(NAME$10));
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
            this.get_store().remove_attribute(NAME$10);
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
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(REF$12));
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
            target = (XmlQName)((Object)this.get_store().find_attribute_user(REF$12));
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
            return this.get_store().find_attribute_user(REF$12) != null;
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
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(REF$12));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(REF$12));
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
            target = (XmlQName)((Object)this.get_store().find_attribute_user(REF$12));
            if (target == null) {
                target = (XmlQName)((Object)this.get_store().add_attribute_user(REF$12));
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
            this.get_store().remove_attribute(REF$12);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public QName getType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(TYPE$14));
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
    public XmlQName xgetType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlQName target = null;
            target = (XmlQName)((Object)this.get_store().find_attribute_user(TYPE$14));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(TYPE$14) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setType(QName type) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(TYPE$14));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(TYPE$14));
            }
            target.setQNameValue(type);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetType(XmlQName type) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlQName target = null;
            target = (XmlQName)((Object)this.get_store().find_attribute_user(TYPE$14));
            if (target == null) {
                target = (XmlQName)((Object)this.get_store().add_attribute_user(TYPE$14));
            }
            target.set(type);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(TYPE$14);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public QName getSubstitutionGroup() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(SUBSTITUTIONGROUP$16));
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
    public XmlQName xgetSubstitutionGroup() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlQName target = null;
            target = (XmlQName)((Object)this.get_store().find_attribute_user(SUBSTITUTIONGROUP$16));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSubstitutionGroup() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(SUBSTITUTIONGROUP$16) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSubstitutionGroup(QName substitutionGroup) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(SUBSTITUTIONGROUP$16));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(SUBSTITUTIONGROUP$16));
            }
            target.setQNameValue(substitutionGroup);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSubstitutionGroup(XmlQName substitutionGroup) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlQName target = null;
            target = (XmlQName)((Object)this.get_store().find_attribute_user(SUBSTITUTIONGROUP$16));
            if (target == null) {
                target = (XmlQName)((Object)this.get_store().add_attribute_user(SUBSTITUTIONGROUP$16));
            }
            target.set(substitutionGroup);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSubstitutionGroup() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(SUBSTITUTIONGROUP$16);
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
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(MINOCCURS$18));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_default_attribute_value(MINOCCURS$18));
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
            target = (XmlNonNegativeInteger)((Object)this.get_store().find_attribute_user(MINOCCURS$18));
            if (target == null) {
                target = (XmlNonNegativeInteger)this.get_default_attribute_value(MINOCCURS$18);
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
            return this.get_store().find_attribute_user(MINOCCURS$18) != null;
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
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(MINOCCURS$18));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(MINOCCURS$18));
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
            target = (XmlNonNegativeInteger)((Object)this.get_store().find_attribute_user(MINOCCURS$18));
            if (target == null) {
                target = (XmlNonNegativeInteger)((Object)this.get_store().add_attribute_user(MINOCCURS$18));
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
            this.get_store().remove_attribute(MINOCCURS$18);
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
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(MAXOCCURS$20));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_default_attribute_value(MAXOCCURS$20));
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
            target = (AllNNI)((Object)this.get_store().find_attribute_user(MAXOCCURS$20));
            if (target == null) {
                target = (AllNNI)this.get_default_attribute_value(MAXOCCURS$20);
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
            return this.get_store().find_attribute_user(MAXOCCURS$20) != null;
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
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(MAXOCCURS$20));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(MAXOCCURS$20));
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
            target = (AllNNI)((Object)this.get_store().find_attribute_user(MAXOCCURS$20));
            if (target == null) {
                target = (AllNNI)((Object)this.get_store().add_attribute_user(MAXOCCURS$20));
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
            this.get_store().remove_attribute(MAXOCCURS$20);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getDefault() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(DEFAULT$22));
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
    public XmlString xgetDefault() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString target = null;
            target = (XmlString)((Object)this.get_store().find_attribute_user(DEFAULT$22));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDefault() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(DEFAULT$22) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDefault(String xdefault) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(DEFAULT$22));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(DEFAULT$22));
            }
            target.setStringValue(xdefault);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDefault(XmlString xdefault) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString target = null;
            target = (XmlString)((Object)this.get_store().find_attribute_user(DEFAULT$22));
            if (target == null) {
                target = (XmlString)((Object)this.get_store().add_attribute_user(DEFAULT$22));
            }
            target.set(xdefault);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDefault() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(DEFAULT$22);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getFixed() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(FIXED$24));
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
    public XmlString xgetFixed() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString target = null;
            target = (XmlString)((Object)this.get_store().find_attribute_user(FIXED$24));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFixed() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(FIXED$24) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFixed(String fixed) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(FIXED$24));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(FIXED$24));
            }
            target.setStringValue(fixed);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFixed(XmlString fixed) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString target = null;
            target = (XmlString)((Object)this.get_store().find_attribute_user(FIXED$24));
            if (target == null) {
                target = (XmlString)((Object)this.get_store().add_attribute_user(FIXED$24));
            }
            target.set(fixed);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFixed() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(FIXED$24);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getNillable() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(NILLABLE$26));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_default_attribute_value(NILLABLE$26));
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
    public XmlBoolean xgetNillable() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean target = null;
            target = (XmlBoolean)((Object)this.get_store().find_attribute_user(NILLABLE$26));
            if (target == null) {
                target = (XmlBoolean)this.get_default_attribute_value(NILLABLE$26);
            }
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetNillable() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(NILLABLE$26) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setNillable(boolean nillable) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(NILLABLE$26));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(NILLABLE$26));
            }
            target.setBooleanValue(nillable);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetNillable(XmlBoolean nillable) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean target = null;
            target = (XmlBoolean)((Object)this.get_store().find_attribute_user(NILLABLE$26));
            if (target == null) {
                target = (XmlBoolean)((Object)this.get_store().add_attribute_user(NILLABLE$26));
            }
            target.set(nillable);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetNillable() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(NILLABLE$26);
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
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(ABSTRACT$28));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_default_attribute_value(ABSTRACT$28));
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
            target = (XmlBoolean)((Object)this.get_store().find_attribute_user(ABSTRACT$28));
            if (target == null) {
                target = (XmlBoolean)this.get_default_attribute_value(ABSTRACT$28);
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
            return this.get_store().find_attribute_user(ABSTRACT$28) != null;
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
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(ABSTRACT$28));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(ABSTRACT$28));
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
            target = (XmlBoolean)((Object)this.get_store().find_attribute_user(ABSTRACT$28));
            if (target == null) {
                target = (XmlBoolean)((Object)this.get_store().add_attribute_user(ABSTRACT$28));
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
            this.get_store().remove_attribute(ABSTRACT$28);
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
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(FINAL$30));
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
            target = (DerivationSet)((Object)this.get_store().find_attribute_user(FINAL$30));
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
            return this.get_store().find_attribute_user(FINAL$30) != null;
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
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(FINAL$30));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(FINAL$30));
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
            target = (DerivationSet)((Object)this.get_store().find_attribute_user(FINAL$30));
            if (target == null) {
                target = (DerivationSet)((Object)this.get_store().add_attribute_user(FINAL$30));
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
            this.get_store().remove_attribute(FINAL$30);
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
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(BLOCK$32));
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
    public BlockSet xgetBlock() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            BlockSet target = null;
            target = (BlockSet)((Object)this.get_store().find_attribute_user(BLOCK$32));
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
            return this.get_store().find_attribute_user(BLOCK$32) != null;
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
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(BLOCK$32));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(BLOCK$32));
            }
            target.setObjectValue(block);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetBlock(BlockSet block) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            BlockSet target = null;
            target = (BlockSet)((Object)this.get_store().find_attribute_user(BLOCK$32));
            if (target == null) {
                target = (BlockSet)((Object)this.get_store().add_attribute_user(BLOCK$32));
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
            this.get_store().remove_attribute(BLOCK$32);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public FormChoice.Enum getForm() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(FORM$34));
            if (target == null) {
                return null;
            }
            return (FormChoice.Enum)target.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public FormChoice xgetForm() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            FormChoice target = null;
            target = (FormChoice)((Object)this.get_store().find_attribute_user(FORM$34));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetForm() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(FORM$34) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setForm(FormChoice.Enum form) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(FORM$34));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(FORM$34));
            }
            target.setEnumValue(form);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetForm(FormChoice form) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            FormChoice target = null;
            target = (FormChoice)((Object)this.get_store().find_attribute_user(FORM$34));
            if (target == null) {
                target = (FormChoice)((Object)this.get_store().add_attribute_user(FORM$34));
            }
            target.set(form);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetForm() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(FORM$34);
        }
    }
}

