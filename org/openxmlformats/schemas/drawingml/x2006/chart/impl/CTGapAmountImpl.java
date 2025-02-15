/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTGapAmount;
import org.openxmlformats.schemas.drawingml.x2006.chart.STGapAmount;

public class CTGapAmountImpl
extends XmlComplexContentImpl
implements CTGapAmount {
    private static final long serialVersionUID = 1L;
    private static final QName VAL$0 = new QName("", "val");

    public CTGapAmountImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(VAL$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(VAL$0));
            }
            if (simpleValue == null) {
                return 0;
            }
            return simpleValue.getIntValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STGapAmount xgetVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STGapAmount sTGapAmount = null;
            sTGapAmount = (STGapAmount)((Object)this.get_store().find_attribute_user(VAL$0));
            if (sTGapAmount == null) {
                sTGapAmount = (STGapAmount)this.get_default_attribute_value(VAL$0);
            }
            return sTGapAmount;
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
    public void setVal(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(VAL$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(VAL$0));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetVal(STGapAmount sTGapAmount) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STGapAmount sTGapAmount2 = null;
            sTGapAmount2 = (STGapAmount)((Object)this.get_store().find_attribute_user(VAL$0));
            if (sTGapAmount2 == null) {
                sTGapAmount2 = (STGapAmount)((Object)this.get_store().add_attribute_user(VAL$0));
            }
            sTGapAmount2.set(sTGapAmount);
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

