/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetLineDashProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.STPresetLineDashVal;

public class CTPresetLineDashPropertiesImpl
extends XmlComplexContentImpl
implements CTPresetLineDashProperties {
    private static final long serialVersionUID = 1L;
    private static final QName VAL$0 = new QName("", "val");

    public CTPresetLineDashPropertiesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STPresetLineDashVal.Enum getVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(VAL$0));
            if (simpleValue == null) {
                return null;
            }
            return (STPresetLineDashVal.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STPresetLineDashVal xgetVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPresetLineDashVal sTPresetLineDashVal = null;
            sTPresetLineDashVal = (STPresetLineDashVal)((Object)this.get_store().find_attribute_user(VAL$0));
            return sTPresetLineDashVal;
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
    public void setVal(STPresetLineDashVal.Enum enum_) {
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
    public void xsetVal(STPresetLineDashVal sTPresetLineDashVal) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPresetLineDashVal sTPresetLineDashVal2 = null;
            sTPresetLineDashVal2 = (STPresetLineDashVal)((Object)this.get_store().find_attribute_user(VAL$0));
            if (sTPresetLineDashVal2 == null) {
                sTPresetLineDashVal2 = (STPresetLineDashVal)((Object)this.get_store().add_attribute_user(VAL$0));
            }
            sTPresetLineDashVal2.set(sTPresetLineDashVal);
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

