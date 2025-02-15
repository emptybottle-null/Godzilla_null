/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetDimension;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STRef;

public class CTSheetDimensionImpl
extends XmlComplexContentImpl
implements CTSheetDimension {
    private static final long serialVersionUID = 1L;
    private static final QName REF$0 = new QName("", "ref");

    public CTSheetDimensionImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(REF$0));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STRef xgetRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STRef sTRef = null;
            sTRef = (STRef)((Object)this.get_store().find_attribute_user(REF$0));
            return sTRef;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setRef(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(REF$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(REF$0));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetRef(STRef sTRef) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STRef sTRef2 = null;
            sTRef2 = (STRef)((Object)this.get_store().find_attribute_user(REF$0));
            if (sTRef2 == null) {
                sTRef2 = (STRef)((Object)this.get_store().add_attribute_user(REF$0));
            }
            sTRef2.set(sTRef);
        }
    }
}

