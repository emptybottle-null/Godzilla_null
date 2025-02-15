/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBarGrouping;
import org.openxmlformats.schemas.drawingml.x2006.chart.STBarGrouping;

public class CTBarGroupingImpl
extends XmlComplexContentImpl
implements CTBarGrouping {
    private static final long serialVersionUID = 1L;
    private static final QName VAL$0 = new QName("", "val");

    public CTBarGroupingImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STBarGrouping.Enum getVal() {
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
            return (STBarGrouping.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STBarGrouping xgetVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STBarGrouping sTBarGrouping = null;
            sTBarGrouping = (STBarGrouping)((Object)this.get_store().find_attribute_user(VAL$0));
            if (sTBarGrouping == null) {
                sTBarGrouping = (STBarGrouping)this.get_default_attribute_value(VAL$0);
            }
            return sTBarGrouping;
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
    public void setVal(STBarGrouping.Enum enum_) {
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
    public void xsetVal(STBarGrouping sTBarGrouping) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STBarGrouping sTBarGrouping2 = null;
            sTBarGrouping2 = (STBarGrouping)((Object)this.get_store().find_attribute_user(VAL$0));
            if (sTBarGrouping2 == null) {
                sTBarGrouping2 = (STBarGrouping)((Object)this.get_store().add_attribute_user(VAL$0));
            }
            sTBarGrouping2.set(sTBarGrouping);
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

