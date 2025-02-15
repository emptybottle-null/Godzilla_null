/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xmlschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlLanguage;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xmlschema.LangAttribute;

public class LangAttributeImpl
extends XmlComplexContentImpl
implements LangAttribute {
    private static final long serialVersionUID = 1L;
    private static final QName LANG$0 = new QName("http://www.w3.org/XML/1998/namespace", "lang");

    public LangAttributeImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getLang() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(LANG$0));
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
    public XmlLanguage xgetLang() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlLanguage target = null;
            target = (XmlLanguage)((Object)this.get_store().find_attribute_user(LANG$0));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLang() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(LANG$0) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setLang(String lang) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(LANG$0));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(LANG$0));
            }
            target.setStringValue(lang);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetLang(XmlLanguage lang) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlLanguage target = null;
            target = (XmlLanguage)((Object)this.get_store().find_attribute_user(LANG$0));
            if (target == null) {
                target = (XmlLanguage)((Object)this.get_store().add_attribute_user(LANG$0));
            }
            target.set(lang);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLang() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(LANG$0);
        }
    }
}

