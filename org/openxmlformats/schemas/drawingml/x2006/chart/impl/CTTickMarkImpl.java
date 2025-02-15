/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTickMark;
import org.openxmlformats.schemas.drawingml.x2006.chart.STTickMark;

public class CTTickMarkImpl
extends XmlComplexContentImpl
implements CTTickMark {
    private static final long serialVersionUID = 1L;
    private static final QName VAL$0 = new QName("", "val");

    public CTTickMarkImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTickMark.Enum getVal() {
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
            return (STTickMark.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTickMark xgetVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTickMark sTTickMark = null;
            sTTickMark = (STTickMark)((Object)this.get_store().find_attribute_user(VAL$0));
            if (sTTickMark == null) {
                sTTickMark = (STTickMark)this.get_default_attribute_value(VAL$0);
            }
            return sTTickMark;
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
    public void setVal(STTickMark.Enum enum_) {
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
    public void xsetVal(STTickMark sTTickMark) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTickMark sTTickMark2 = null;
            sTTickMark2 = (STTickMark)((Object)this.get_store().find_attribute_user(VAL$0));
            if (sTTickMark2 == null) {
                sTTickMark2 = (STTickMark)((Object)this.get_store().add_attribute_user(VAL$0));
            }
            sTTickMark2.set(sTTickMark);
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

