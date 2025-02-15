/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTUnderlineProperty;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STUnderlineValues;

public class CTUnderlinePropertyImpl
extends XmlComplexContentImpl
implements CTUnderlineProperty {
    private static final long serialVersionUID = 1L;
    private static final QName VAL$0 = new QName("", "val");

    public CTUnderlinePropertyImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STUnderlineValues.Enum getVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(VAL$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(VAL$0));
            }
            if (simpleValue == null) {
                return null;
            }
            return (STUnderlineValues.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STUnderlineValues xgetVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STUnderlineValues sTUnderlineValues = null;
            sTUnderlineValues = (STUnderlineValues)((Object)this.get_store().find_attribute_user(VAL$0));
            if (sTUnderlineValues == null) {
                sTUnderlineValues = (STUnderlineValues)this.get_default_attribute_value(VAL$0);
            }
            return sTUnderlineValues;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(VAL$0) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setVal(STUnderlineValues.Enum enum_) {
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
    public void xsetVal(STUnderlineValues sTUnderlineValues) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STUnderlineValues sTUnderlineValues2 = null;
            sTUnderlineValues2 = (STUnderlineValues)((Object)this.get_store().find_attribute_user(VAL$0));
            if (sTUnderlineValues2 == null) {
                sTUnderlineValues2 = (STUnderlineValues)((Object)this.get_store().add_attribute_user(VAL$0));
            }
            sTUnderlineValues2.set(sTUnderlineValues);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(VAL$0);
        }
    }
}

