/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTMarkerStyle;
import org.openxmlformats.schemas.drawingml.x2006.chart.STMarkerStyle;

public class CTMarkerStyleImpl
extends XmlComplexContentImpl
implements CTMarkerStyle {
    private static final long serialVersionUID = 1L;
    private static final QName VAL$0 = new QName("", "val");

    public CTMarkerStyleImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STMarkerStyle.Enum getVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(VAL$0));
            if (simpleValue == null) {
                return null;
            }
            return (STMarkerStyle.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STMarkerStyle xgetVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STMarkerStyle sTMarkerStyle = null;
            sTMarkerStyle = (STMarkerStyle)((Object)this.get_store().find_attribute_user(VAL$0));
            return sTMarkerStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setVal(STMarkerStyle.Enum enum_) {
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
    public void xsetVal(STMarkerStyle sTMarkerStyle) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STMarkerStyle sTMarkerStyle2 = null;
            sTMarkerStyle2 = (STMarkerStyle)((Object)this.get_store().find_attribute_user(VAL$0));
            if (sTMarkerStyle2 == null) {
                sTMarkerStyle2 = (STMarkerStyle)((Object)this.get_store().add_attribute_user(VAL$0));
            }
            sTMarkerStyle2.set(sTMarkerStyle);
        }
    }
}

