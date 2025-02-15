/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextAlignment;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTextAlignment;

public class CTTextAlignmentImpl
extends XmlComplexContentImpl
implements CTTextAlignment {
    private static final long serialVersionUID = 1L;
    private static final QName VAL$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "val");

    public CTTextAlignmentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTextAlignment.Enum getVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(VAL$0));
            if (simpleValue == null) {
                return null;
            }
            return (STTextAlignment.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTextAlignment xgetVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTextAlignment sTTextAlignment = null;
            sTTextAlignment = (STTextAlignment)((Object)this.get_store().find_attribute_user(VAL$0));
            return sTTextAlignment;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setVal(STTextAlignment.Enum enum_) {
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
    public void xsetVal(STTextAlignment sTTextAlignment) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTextAlignment sTTextAlignment2 = null;
            sTTextAlignment2 = (STTextAlignment)((Object)this.get_store().find_attribute_user(VAL$0));
            if (sTTextAlignment2 == null) {
                sTTextAlignment2 = (STTextAlignment)((Object)this.get_store().add_attribute_user(VAL$0));
            }
            sTTextAlignment2.set(sTTextAlignment);
        }
    }
}

