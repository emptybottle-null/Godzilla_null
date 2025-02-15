/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHdrFtrRef;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHdrFtr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRelImpl;

public class CTHdrFtrRefImpl
extends CTRelImpl
implements CTHdrFtrRef {
    private static final long serialVersionUID = 1L;
    private static final QName TYPE$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "type");

    public CTHdrFtrRefImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STHdrFtr.Enum getType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TYPE$0));
            if (simpleValue == null) {
                return null;
            }
            return (STHdrFtr.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STHdrFtr xgetType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STHdrFtr sTHdrFtr = null;
            sTHdrFtr = (STHdrFtr)((Object)this.get_store().find_attribute_user(TYPE$0));
            return sTHdrFtr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setType(STHdrFtr.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TYPE$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(TYPE$0));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetType(STHdrFtr sTHdrFtr) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STHdrFtr sTHdrFtr2 = null;
            sTHdrFtr2 = (STHdrFtr)((Object)this.get_store().find_attribute_user(TYPE$0));
            if (sTHdrFtr2 == null) {
                sTHdrFtr2 = (STHdrFtr)((Object)this.get_store().add_attribute_user(TYPE$0));
            }
            sTHdrFtr2.set(sTHdrFtr);
        }
    }
}

