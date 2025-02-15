/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLayoutTarget;
import org.openxmlformats.schemas.drawingml.x2006.chart.STLayoutTarget;

public class CTLayoutTargetImpl
extends XmlComplexContentImpl
implements CTLayoutTarget {
    private static final long serialVersionUID = 1L;
    private static final QName VAL$0 = new QName("", "val");

    public CTLayoutTargetImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STLayoutTarget.Enum getVal() {
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
            return (STLayoutTarget.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STLayoutTarget xgetVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STLayoutTarget sTLayoutTarget = null;
            sTLayoutTarget = (STLayoutTarget)((Object)this.get_store().find_attribute_user(VAL$0));
            if (sTLayoutTarget == null) {
                sTLayoutTarget = (STLayoutTarget)this.get_default_attribute_value(VAL$0);
            }
            return sTLayoutTarget;
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
    public void setVal(STLayoutTarget.Enum enum_) {
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
    public void xsetVal(STLayoutTarget sTLayoutTarget) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STLayoutTarget sTLayoutTarget2 = null;
            sTLayoutTarget2 = (STLayoutTarget)((Object)this.get_store().find_attribute_user(VAL$0));
            if (sTLayoutTarget2 == null) {
                sTLayoutTarget2 = (STLayoutTarget)((Object)this.get_store().add_attribute_user(VAL$0));
            }
            sTLayoutTarget2.set(sTLayoutTarget);
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

