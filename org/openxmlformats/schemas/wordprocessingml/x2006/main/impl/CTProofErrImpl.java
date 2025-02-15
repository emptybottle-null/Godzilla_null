/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.STProofErr
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTProofErr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STProofErr;

public class CTProofErrImpl
extends XmlComplexContentImpl
implements CTProofErr {
    private static final long serialVersionUID = 1L;
    private static final QName TYPE$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "type");

    public CTProofErrImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STProofErr.Enum getType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TYPE$0));
            if (simpleValue == null) {
                return null;
            }
            return (STProofErr.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STProofErr xgetType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STProofErr sTProofErr = null;
            sTProofErr = (STProofErr)this.get_store().find_attribute_user(TYPE$0);
            return sTProofErr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setType(STProofErr.Enum enum_) {
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
    public void xsetType(STProofErr sTProofErr) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STProofErr sTProofErr2 = null;
            sTProofErr2 = (STProofErr)this.get_store().find_attribute_user(TYPE$0);
            if (sTProofErr2 == null) {
                sTProofErr2 = (STProofErr)this.get_store().add_attribute_user(TYPE$0);
            }
            sTProofErr2.set((XmlObject)sTProofErr);
        }
    }
}

