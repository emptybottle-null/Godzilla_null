/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLblAlgn;
import org.openxmlformats.schemas.drawingml.x2006.chart.STLblAlgn;

public class CTLblAlgnImpl
extends XmlComplexContentImpl
implements CTLblAlgn {
    private static final long serialVersionUID = 1L;
    private static final QName VAL$0 = new QName("", "val");

    public CTLblAlgnImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STLblAlgn.Enum getVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(VAL$0));
            if (simpleValue == null) {
                return null;
            }
            return (STLblAlgn.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STLblAlgn xgetVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STLblAlgn sTLblAlgn = null;
            sTLblAlgn = (STLblAlgn)((Object)this.get_store().find_attribute_user(VAL$0));
            return sTLblAlgn;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setVal(STLblAlgn.Enum enum_) {
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
    public void xsetVal(STLblAlgn sTLblAlgn) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STLblAlgn sTLblAlgn2 = null;
            sTLblAlgn2 = (STLblAlgn)((Object)this.get_store().find_attribute_user(VAL$0));
            if (sTLblAlgn2 == null) {
                sTLblAlgn2 = (STLblAlgn)((Object)this.get_store().add_attribute_user(VAL$0));
            }
            sTLblAlgn2.set(sTLblAlgn);
        }
    }
}

