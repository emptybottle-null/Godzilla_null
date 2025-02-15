/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRgbColor;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STUnsignedIntHex;

public class CTRgbColorImpl
extends XmlComplexContentImpl
implements CTRgbColor {
    private static final long serialVersionUID = 1L;
    private static final QName RGB$0 = new QName("", "rgb");

    public CTRgbColorImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte[] getRgb() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(RGB$0));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getByteArrayValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STUnsignedIntHex xgetRgb() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STUnsignedIntHex sTUnsignedIntHex = null;
            sTUnsignedIntHex = (STUnsignedIntHex)((Object)this.get_store().find_attribute_user(RGB$0));
            return sTUnsignedIntHex;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRgb() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(RGB$0) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setRgb(byte[] byArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(RGB$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(RGB$0));
            }
            simpleValue.setByteArrayValue(byArray);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetRgb(STUnsignedIntHex sTUnsignedIntHex) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STUnsignedIntHex sTUnsignedIntHex2 = null;
            sTUnsignedIntHex2 = (STUnsignedIntHex)((Object)this.get_store().find_attribute_user(RGB$0));
            if (sTUnsignedIntHex2 == null) {
                sTUnsignedIntHex2 = (STUnsignedIntHex)((Object)this.get_store().add_attribute_user(RGB$0));
            }
            sTUnsignedIntHex2.set(sTUnsignedIntHex);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRgb() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(RGB$0);
        }
    }
}

