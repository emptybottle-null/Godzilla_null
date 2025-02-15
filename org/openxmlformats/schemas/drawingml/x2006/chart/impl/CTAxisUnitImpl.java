/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.chart.STAxisUnit
 */
package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxisUnit;
import org.openxmlformats.schemas.drawingml.x2006.chart.STAxisUnit;

public class CTAxisUnitImpl
extends XmlComplexContentImpl
implements CTAxisUnit {
    private static final long serialVersionUID = 1L;
    private static final QName VAL$0 = new QName("", "val");

    public CTAxisUnitImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public double getVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(VAL$0));
            if (simpleValue == null) {
                return 0.0;
            }
            return simpleValue.getDoubleValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STAxisUnit xgetVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STAxisUnit sTAxisUnit = null;
            sTAxisUnit = (STAxisUnit)this.get_store().find_attribute_user(VAL$0);
            return sTAxisUnit;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setVal(double d) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(VAL$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(VAL$0));
            }
            simpleValue.setDoubleValue(d);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetVal(STAxisUnit sTAxisUnit) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STAxisUnit sTAxisUnit2 = null;
            sTAxisUnit2 = (STAxisUnit)this.get_store().find_attribute_user(VAL$0);
            if (sTAxisUnit2 == null) {
                sTAxisUnit2 = (STAxisUnit)this.get_store().add_attribute_user(VAL$0);
            }
            sTAxisUnit2.set((XmlObject)sTAxisUnit);
        }
    }
}

