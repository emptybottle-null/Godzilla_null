/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlNCName;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import org.apache.xmlbeans.impl.xb.xsdschema.FormChoice;
import org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.AnnotatedImpl;

public class AttributeImpl
extends AnnotatedImpl
implements Attribute {
    private static final long serialVersionUID = 1L;
    private static final QName SIMPLETYPE$0 = new QName("http://www.w3.org/2001/XMLSchema", "simpleType");
    private static final QName NAME$2 = new QName("", "name");
    private static final QName REF$4 = new QName("", "ref");
    private static final QName TYPE$6 = new QName("", "type");
    private static final QName USE$8 = new QName("", "use");
    private static final QName DEFAULT$10 = new QName("", "default");
    private static final QName FIXED$12 = new QName("", "fixed");
    private static final QName FORM$14 = new QName("", "form");

    public AttributeImpl(SchemaType sType) {
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
    public String getName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(NAME$2));
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
            target = (XmlNCName)((Object)this.get_store().find_attribute_user(NAME$2));
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
            return this.get_store().find_attribute_user(NAME$2) != null;
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
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(NAME$2));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(NAME$2));
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
            target = (XmlNCName)((Object)this.get_store().find_attribute_user(NAME$2));
            if (target == null) {
                target = (XmlNCName)((Object)this.get_store().add_attribute_user(NAME$2));
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
            this.get_store().remove_attribute(NAME$2);
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
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(REF$4));
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
            target = (XmlQName)((Object)this.get_store().find_attribute_user(REF$4));
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
            return this.get_store().find_attribute_user(REF$4) != null;
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
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(REF$4));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(REF$4));
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
            target = (XmlQName)((Object)this.get_store().find_attribute_user(REF$4));
            if (target == null) {
                target = (XmlQName)((Object)this.get_store().add_attribute_user(REF$4));
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
            this.get_store().remove_attribute(REF$4);
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
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(TYPE$6));
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
            target = (XmlQName)((Object)this.get_store().find_attribute_user(TYPE$6));
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
            return this.get_store().find_attribute_user(TYPE$6) != null;
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
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(TYPE$6));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(TYPE$6));
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
            target = (XmlQName)((Object)this.get_store().find_attribute_user(TYPE$6));
            if (target == null) {
                target = (XmlQName)((Object)this.get_store().add_attribute_user(TYPE$6));
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
            this.get_store().remove_attribute(TYPE$6);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Attribute.Use.Enum getUse() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(USE$8));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_default_attribute_value(USE$8));
            }
            if (target == null) {
                return null;
            }
            return (Attribute.Use.Enum)target.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Attribute.Use xgetUse() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Attribute.Use target = null;
            target = (Attribute.Use)((Object)this.get_store().find_attribute_user(USE$8));
            if (target == null) {
                target = (Attribute.Use)this.get_default_attribute_value(USE$8);
            }
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetUse() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(USE$8) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setUse(Attribute.Use.Enum use) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(USE$8));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(USE$8));
            }
            target.setEnumValue(use);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetUse(Attribute.Use use) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Attribute.Use target = null;
            target = (Attribute.Use)((Object)this.get_store().find_attribute_user(USE$8));
            if (target == null) {
                target = (Attribute.Use)((Object)this.get_store().add_attribute_user(USE$8));
            }
            target.set(use);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetUse() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(USE$8);
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
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(DEFAULT$10));
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
            target = (XmlString)((Object)this.get_store().find_attribute_user(DEFAULT$10));
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
            return this.get_store().find_attribute_user(DEFAULT$10) != null;
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
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(DEFAULT$10));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(DEFAULT$10));
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
            target = (XmlString)((Object)this.get_store().find_attribute_user(DEFAULT$10));
            if (target == null) {
                target = (XmlString)((Object)this.get_store().add_attribute_user(DEFAULT$10));
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
            this.get_store().remove_attribute(DEFAULT$10);
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
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(FIXED$12));
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
            target = (XmlString)((Object)this.get_store().find_attribute_user(FIXED$12));
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
            return this.get_store().find_attribute_user(FIXED$12) != null;
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
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(FIXED$12));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(FIXED$12));
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
            target = (XmlString)((Object)this.get_store().find_attribute_user(FIXED$12));
            if (target == null) {
                target = (XmlString)((Object)this.get_store().add_attribute_user(FIXED$12));
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
            this.get_store().remove_attribute(FIXED$12);
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
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(FORM$14));
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
            target = (FormChoice)((Object)this.get_store().find_attribute_user(FORM$14));
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
            return this.get_store().find_attribute_user(FORM$14) != null;
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
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(FORM$14));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(FORM$14));
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
            target = (FormChoice)((Object)this.get_store().find_attribute_user(FORM$14));
            if (target == null) {
                target = (FormChoice)((Object)this.get_store().add_attribute_user(FORM$14));
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
            this.get_store().remove_attribute(FORM$14);
        }
    }

    public static class UseImpl
    extends JavaStringEnumerationHolderEx
    implements Attribute.Use {
        private static final long serialVersionUID = 1L;

        public UseImpl(SchemaType sType) {
            super(sType, false);
        }

        protected UseImpl(SchemaType sType, boolean b) {
            super(sType, b);
        }
    }
}

