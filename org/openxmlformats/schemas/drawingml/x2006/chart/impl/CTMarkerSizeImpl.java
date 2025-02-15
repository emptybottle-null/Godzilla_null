/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.chart.STMarkerSize
 */
package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTMarkerSize;
import org.openxmlformats.schemas.drawingml.x2006.chart.STMarkerSize;

public class CTMarkerSizeImpl
extends XmlComplexContentImpl
implements CTMarkerSize {
    private static final long serialVersionUID = 1L;
    private static final QName VAL$0 = new QName("", "val");

    public CTMarkerSizeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public short getVal() {
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
            return simpleValue.getShortValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STMarkerSize xgetVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STMarkerSize sTMarkerSize = null;
            sTMarkerSize = (STMarkerSize)this.get_store().find_attribute_user(VAL$0);
            if (sTMarkerSize == null) {
                sTMarkerSize = (STMarkerSize)this.get_default_attribute_value(VAL$0);
            }
            return sTMarkerSize;
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
    public void setVal(short s) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(VAL$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(VAL$0));
            }
            simpleValue.setShortValue(s);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetVal(STMarkerSize sTMarkerSize) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STMarkerSize sTMarkerSize2 = null;
            sTMarkerSize2 = (STMarkerSize)this.get_store().find_attribute_user(VAL$0);
            if (sTMarkerSize2 == null) {
                sTMarkerSize2 = (STMarkerSize)this.get_store().add_attribute_user(VAL$0);
            }
            sTMarkerSize2.set((XmlObject)sTMarkerSize);
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

