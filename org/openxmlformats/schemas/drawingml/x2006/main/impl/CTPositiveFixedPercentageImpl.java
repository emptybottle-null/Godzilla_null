/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveFixedPercentage;
import org.openxmlformats.schemas.drawingml.x2006.main.STPositiveFixedPercentage;

public class CTPositiveFixedPercentageImpl
extends XmlComplexContentImpl
implements CTPositiveFixedPercentage {
    private static final long serialVersionUID = 1L;
    private static final QName VAL$0 = new QName("", "val");

    public CTPositiveFixedPercentageImpl(SchemaType schemaType) {
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
                return 0;
            }
            return simpleValue.getIntValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STPositiveFixedPercentage xgetVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPositiveFixedPercentage sTPositiveFixedPercentage = null;
            sTPositiveFixedPercentage = (STPositiveFixedPercentage)((Object)this.get_store().find_attribute_user(VAL$0));
            return sTPositiveFixedPercentage;
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
    public void xsetVal(STPositiveFixedPercentage sTPositiveFixedPercentage) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPositiveFixedPercentage sTPositiveFixedPercentage2 = null;
            sTPositiveFixedPercentage2 = (STPositiveFixedPercentage)((Object)this.get_store().find_attribute_user(VAL$0));
            if (sTPositiveFixedPercentage2 == null) {
                sTPositiveFixedPercentage2 = (STPositiveFixedPercentage)((Object)this.get_store().add_attribute_user(VAL$0));
            }
            sTPositiveFixedPercentage2.set(sTPositiveFixedPercentage);
        }
    }
}

