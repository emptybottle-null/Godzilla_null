/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTCrosses;
import org.openxmlformats.schemas.drawingml.x2006.chart.STCrosses;

public class CTCrossesImpl
extends XmlComplexContentImpl
implements CTCrosses {
    private static final long serialVersionUID = 1L;
    private static final QName VAL$0 = new QName("", "val");

    public CTCrossesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STCrosses.Enum getVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(VAL$0));
            if (simpleValue == null) {
                return null;
            }
            return (STCrosses.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STCrosses xgetVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCrosses sTCrosses = null;
            sTCrosses = (STCrosses)((Object)this.get_store().find_attribute_user(VAL$0));
            return sTCrosses;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setVal(STCrosses.Enum enum_) {
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
    public void xsetVal(STCrosses sTCrosses) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCrosses sTCrosses2 = null;
            sTCrosses2 = (STCrosses)((Object)this.get_store().find_attribute_user(VAL$0));
            if (sTCrosses2 == null) {
                sTCrosses2 = (STCrosses)((Object)this.get_store().add_attribute_user(VAL$0));
            }
            sTCrosses2.set(sTCrosses);
        }
    }
}

