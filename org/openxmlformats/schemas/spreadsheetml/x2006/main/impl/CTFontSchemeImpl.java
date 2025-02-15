/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFontScheme;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STFontScheme;

public class CTFontSchemeImpl
extends XmlComplexContentImpl
implements CTFontScheme {
    private static final long serialVersionUID = 1L;
    private static final QName VAL$0 = new QName("", "val");

    public CTFontSchemeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STFontScheme.Enum getVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(VAL$0));
            if (simpleValue == null) {
                return null;
            }
            return (STFontScheme.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STFontScheme xgetVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STFontScheme sTFontScheme = null;
            sTFontScheme = (STFontScheme)((Object)this.get_store().find_attribute_user(VAL$0));
            return sTFontScheme;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setVal(STFontScheme.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(VAL$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(VAL$0));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetVal(STFontScheme sTFontScheme) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STFontScheme sTFontScheme2 = null;
            sTFontScheme2 = (STFontScheme)((Object)this.get_store().find_attribute_user(VAL$0));
            if (sTFontScheme2 == null) {
                sTFontScheme2 = (STFontScheme)((Object)this.get_store().add_attribute_user(VAL$0));
            }
            sTFontScheme2.set(sTFontScheme);
        }
    }
}

