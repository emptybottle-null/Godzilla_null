/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVerticalAlignRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalAlignRun;

public class CTVerticalAlignRunImpl
extends XmlComplexContentImpl
implements CTVerticalAlignRun {
    private static final long serialVersionUID = 1L;
    private static final QName VAL$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "val");

    public CTVerticalAlignRunImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STVerticalAlignRun.Enum getVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(VAL$0));
            if (simpleValue == null) {
                return null;
            }
            return (STVerticalAlignRun.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STVerticalAlignRun xgetVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STVerticalAlignRun sTVerticalAlignRun = null;
            sTVerticalAlignRun = (STVerticalAlignRun)((Object)this.get_store().find_attribute_user(VAL$0));
            return sTVerticalAlignRun;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setVal(STVerticalAlignRun.Enum enum_) {
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
    public void xsetVal(STVerticalAlignRun sTVerticalAlignRun) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STVerticalAlignRun sTVerticalAlignRun2 = null;
            sTVerticalAlignRun2 = (STVerticalAlignRun)((Object)this.get_store().find_attribute_user(VAL$0));
            if (sTVerticalAlignRun2 == null) {
                sTVerticalAlignRun2 = (STVerticalAlignRun)((Object)this.get_store().add_attribute_user(VAL$0));
            }
            sTVerticalAlignRun2.set(sTVerticalAlignRun);
        }
    }
}

