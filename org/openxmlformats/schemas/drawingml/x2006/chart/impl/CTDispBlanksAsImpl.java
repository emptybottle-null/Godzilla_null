/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDispBlanksAs;
import org.openxmlformats.schemas.drawingml.x2006.chart.STDispBlanksAs;

public class CTDispBlanksAsImpl
extends XmlComplexContentImpl
implements CTDispBlanksAs {
    private static final long serialVersionUID = 1L;
    private static final QName VAL$0 = new QName("", "val");

    public CTDispBlanksAsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STDispBlanksAs.Enum getVal() {
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
            return (STDispBlanksAs.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STDispBlanksAs xgetVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STDispBlanksAs sTDispBlanksAs = null;
            sTDispBlanksAs = (STDispBlanksAs)((Object)this.get_store().find_attribute_user(VAL$0));
            if (sTDispBlanksAs == null) {
                sTDispBlanksAs = (STDispBlanksAs)this.get_default_attribute_value(VAL$0);
            }
            return sTDispBlanksAs;
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
    public void setVal(STDispBlanksAs.Enum enum_) {
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
    public void xsetVal(STDispBlanksAs sTDispBlanksAs) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STDispBlanksAs sTDispBlanksAs2 = null;
            sTDispBlanksAs2 = (STDispBlanksAs)((Object)this.get_store().find_attribute_user(VAL$0));
            if (sTDispBlanksAs2 == null) {
                sTDispBlanksAs2 = (STDispBlanksAs)((Object)this.get_store().add_attribute_user(VAL$0));
            }
            sTDispBlanksAs2.set(sTDispBlanksAs);
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

