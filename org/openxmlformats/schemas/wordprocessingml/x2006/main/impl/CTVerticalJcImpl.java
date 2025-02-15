/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVerticalJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalJc;

public class CTVerticalJcImpl
extends XmlComplexContentImpl
implements CTVerticalJc {
    private static final long serialVersionUID = 1L;
    private static final QName VAL$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "val");

    public CTVerticalJcImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STVerticalJc.Enum getVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(VAL$0));
            if (simpleValue == null) {
                return null;
            }
            return (STVerticalJc.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STVerticalJc xgetVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STVerticalJc sTVerticalJc = null;
            sTVerticalJc = (STVerticalJc)((Object)this.get_store().find_attribute_user(VAL$0));
            return sTVerticalJc;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setVal(STVerticalJc.Enum enum_) {
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
    public void xsetVal(STVerticalJc sTVerticalJc) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STVerticalJc sTVerticalJc2 = null;
            sTVerticalJc2 = (STVerticalJc)((Object)this.get_store().find_attribute_user(VAL$0));
            if (sTVerticalJc2 == null) {
                sTVerticalJc2 = (STVerticalJc)((Object)this.get_store().add_attribute_user(VAL$0));
            }
            sTVerticalJc2.set(sTVerticalJc);
        }
    }
}

