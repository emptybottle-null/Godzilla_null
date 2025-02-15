/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLegendPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.STLegendPos;

public class CTLegendPosImpl
extends XmlComplexContentImpl
implements CTLegendPos {
    private static final long serialVersionUID = 1L;
    private static final QName VAL$0 = new QName("", "val");

    public CTLegendPosImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STLegendPos.Enum getVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(VAL$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(VAL$0));
            }
            if (simpleValue == null) {
                return null;
            }
            return (STLegendPos.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STLegendPos xgetVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STLegendPos sTLegendPos = null;
            sTLegendPos = (STLegendPos)((Object)this.get_store().find_attribute_user(VAL$0));
            if (sTLegendPos == null) {
                sTLegendPos = (STLegendPos)this.get_default_attribute_value(VAL$0);
            }
            return sTLegendPos;
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
    public void setVal(STLegendPos.Enum enum_) {
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
    public void xsetVal(STLegendPos sTLegendPos) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STLegendPos sTLegendPos2 = null;
            sTLegendPos2 = (STLegendPos)((Object)this.get_store().find_attribute_user(VAL$0));
            if (sTLegendPos2 == null) {
                sTLegendPos2 = (STLegendPos)((Object)this.get_store().add_attribute_user(VAL$0));
            }
            sTLegendPos2.set(sTLegendPos);
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

