/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPercentage;
import org.openxmlformats.schemas.drawingml.x2006.main.STPercentage;

public class CTPercentageImpl
extends XmlComplexContentImpl
implements CTPercentage {
    private static final long serialVersionUID = 1L;
    private static final QName VAL$0 = new QName("", "val");

    public CTPercentageImpl(SchemaType schemaType) {
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
    public STPercentage xgetVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPercentage sTPercentage = null;
            sTPercentage = (STPercentage)((Object)this.get_store().find_attribute_user(VAL$0));
            return sTPercentage;
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
    public void xsetVal(STPercentage sTPercentage) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPercentage sTPercentage2 = null;
            sTPercentage2 = (STPercentage)((Object)this.get_store().find_attribute_user(VAL$0));
            if (sTPercentage2 == null) {
                sTPercentage2 = (STPercentage)((Object)this.get_store().add_attribute_user(VAL$0));
            }
            sTPercentage2.set(sTPercentage);
        }
    }
}

