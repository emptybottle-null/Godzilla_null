/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.STAxPos;

public class CTAxPosImpl
extends XmlComplexContentImpl
implements CTAxPos {
    private static final long serialVersionUID = 1L;
    private static final QName VAL$0 = new QName("", "val");

    public CTAxPosImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STAxPos.Enum getVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(VAL$0));
            if (simpleValue == null) {
                return null;
            }
            return (STAxPos.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STAxPos xgetVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STAxPos sTAxPos = null;
            sTAxPos = (STAxPos)((Object)this.get_store().find_attribute_user(VAL$0));
            return sTAxPos;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setVal(STAxPos.Enum enum_) {
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
    public void xsetVal(STAxPos sTAxPos) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STAxPos sTAxPos2 = null;
            sTAxPos2 = (STAxPos)((Object)this.get_store().find_attribute_user(VAL$0));
            if (sTAxPos2 == null) {
                sTAxPos2 = (STAxPos)((Object)this.get_store().add_attribute_user(VAL$0));
            }
            sTAxPos2.set(sTAxPos);
        }
    }
}

