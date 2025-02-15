/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLang;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLang;

public class CTLangImpl
extends XmlComplexContentImpl
implements CTLang {
    private static final long serialVersionUID = 1L;
    private static final QName VAL$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "val");

    public CTLangImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Object getVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(VAL$0));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getObjectValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STLang xgetVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STLang sTLang = null;
            sTLang = (STLang)((Object)this.get_store().find_attribute_user(VAL$0));
            return sTLang;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setVal(Object object) {
        Object object2 = this.monitor();
        synchronized (object2) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(VAL$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(VAL$0));
            }
            simpleValue.setObjectValue(object);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetVal(STLang sTLang) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STLang sTLang2 = null;
            sTLang2 = (STLang)((Object)this.get_store().find_attribute_user(VAL$0));
            if (sTLang2 == null) {
                sTLang2 = (STLang)((Object)this.get_store().add_attribute_user(VAL$0));
            }
            sTLang2.set(sTLang);
        }
    }
}

