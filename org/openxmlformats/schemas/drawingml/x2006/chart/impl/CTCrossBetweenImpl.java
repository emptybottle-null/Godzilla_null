/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTCrossBetween;
import org.openxmlformats.schemas.drawingml.x2006.chart.STCrossBetween;

public class CTCrossBetweenImpl
extends XmlComplexContentImpl
implements CTCrossBetween {
    private static final long serialVersionUID = 1L;
    private static final QName VAL$0 = new QName("", "val");

    public CTCrossBetweenImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STCrossBetween.Enum getVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(VAL$0));
            if (simpleValue == null) {
                return null;
            }
            return (STCrossBetween.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STCrossBetween xgetVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCrossBetween sTCrossBetween = null;
            sTCrossBetween = (STCrossBetween)((Object)this.get_store().find_attribute_user(VAL$0));
            return sTCrossBetween;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setVal(STCrossBetween.Enum enum_) {
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
    public void xsetVal(STCrossBetween sTCrossBetween) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCrossBetween sTCrossBetween2 = null;
            sTCrossBetween2 = (STCrossBetween)((Object)this.get_store().find_attribute_user(VAL$0));
            if (sTCrossBetween2 == null) {
                sTCrossBetween2 = (STCrossBetween)((Object)this.get_store().add_attribute_user(VAL$0));
            }
            sTCrossBetween2.set(sTCrossBetween);
        }
    }
}

