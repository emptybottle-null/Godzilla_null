/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacingPercent;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextSpacingPercent;

public class CTTextSpacingPercentImpl
extends XmlComplexContentImpl
implements CTTextSpacingPercent {
    private static final long serialVersionUID = 1L;
    private static final QName VAL$0 = new QName("", "val");

    public CTTextSpacingPercentImpl(SchemaType schemaType) {
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
    public STTextSpacingPercent xgetVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTextSpacingPercent sTTextSpacingPercent = null;
            sTTextSpacingPercent = (STTextSpacingPercent)((Object)this.get_store().find_attribute_user(VAL$0));
            return sTTextSpacingPercent;
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
    public void xsetVal(STTextSpacingPercent sTTextSpacingPercent) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTextSpacingPercent sTTextSpacingPercent2 = null;
            sTTextSpacingPercent2 = (STTextSpacingPercent)((Object)this.get_store().find_attribute_user(VAL$0));
            if (sTTextSpacingPercent2 == null) {
                sTTextSpacingPercent2 = (STTextSpacingPercent)((Object)this.get_store().add_attribute_user(VAL$0));
            }
            sTTextSpacingPercent2.set(sTTextSpacingPercent);
        }
    }
}

