/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumFmt;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STNumberFormat;

public class CTNumFmtImpl
extends XmlComplexContentImpl
implements CTNumFmt {
    private static final long serialVersionUID = 1L;
    private static final QName VAL$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "val");

    public CTNumFmtImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STNumberFormat.Enum getVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(VAL$0));
            if (simpleValue == null) {
                return null;
            }
            return (STNumberFormat.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STNumberFormat xgetVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STNumberFormat sTNumberFormat = null;
            sTNumberFormat = (STNumberFormat)((Object)this.get_store().find_attribute_user(VAL$0));
            return sTNumberFormat;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setVal(STNumberFormat.Enum enum_) {
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
    public void xsetVal(STNumberFormat sTNumberFormat) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STNumberFormat sTNumberFormat2 = null;
            sTNumberFormat2 = (STNumberFormat)((Object)this.get_store().find_attribute_user(VAL$0));
            if (sTNumberFormat2 == null) {
                sTNumberFormat2 = (STNumberFormat)((Object)this.get_store().add_attribute_user(VAL$0));
            }
            sTNumberFormat2.set(sTNumberFormat);
        }
    }
}

