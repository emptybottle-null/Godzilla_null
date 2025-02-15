/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLogBase;
import org.openxmlformats.schemas.drawingml.x2006.chart.STLogBase;

public class CTLogBaseImpl
extends XmlComplexContentImpl
implements CTLogBase {
    private static final long serialVersionUID = 1L;
    private static final QName VAL$0 = new QName("", "val");

    public CTLogBaseImpl(SchemaType schemaType) {
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
    public STLogBase xgetVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STLogBase sTLogBase = null;
            sTLogBase = (STLogBase)((Object)this.get_store().find_attribute_user(VAL$0));
            return sTLogBase;
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
    public void xsetVal(STLogBase sTLogBase) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STLogBase sTLogBase2 = null;
            sTLogBase2 = (STLogBase)((Object)this.get_store().find_attribute_user(VAL$0));
            if (sTLogBase2 == null) {
                sTLogBase2 = (STLogBase)((Object)this.get_store().add_attribute_user(VAL$0));
            }
            sTLogBase2.set(sTLogBase);
        }
    }
}

