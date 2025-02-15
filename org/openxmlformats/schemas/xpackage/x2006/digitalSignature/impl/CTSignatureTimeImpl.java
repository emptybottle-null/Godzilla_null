/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.xpackage.x2006.digitalSignature.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.xpackage.x2006.digitalSignature.CTSignatureTime;
import org.openxmlformats.schemas.xpackage.x2006.digitalSignature.STFormat;
import org.openxmlformats.schemas.xpackage.x2006.digitalSignature.STValue;

public class CTSignatureTimeImpl
extends XmlComplexContentImpl
implements CTSignatureTime {
    private static final long serialVersionUID = 1L;
    private static final QName FORMAT$0 = new QName("http://schemas.openxmlformats.org/package/2006/digital-signature", "Format");
    private static final QName VALUE$2 = new QName("http://schemas.openxmlformats.org/package/2006/digital-signature", "Value");

    public CTSignatureTimeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getFormat() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(FORMAT$0, 0));
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
    public STFormat xgetFormat() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STFormat sTFormat = null;
            sTFormat = (STFormat)((Object)this.get_store().find_element_user(FORMAT$0, 0));
            return sTFormat;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFormat(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(FORMAT$0, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(FORMAT$0));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFormat(STFormat sTFormat) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STFormat sTFormat2 = null;
            sTFormat2 = (STFormat)((Object)this.get_store().find_element_user(FORMAT$0, 0));
            if (sTFormat2 == null) {
                sTFormat2 = (STFormat)((Object)this.get_store().add_element_user(FORMAT$0));
            }
            sTFormat2.set(sTFormat);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getValue() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(VALUE$2, 0));
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
    public STValue xgetValue() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STValue sTValue = null;
            sTValue = (STValue)((Object)this.get_store().find_element_user(VALUE$2, 0));
            return sTValue;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setValue(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(VALUE$2, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(VALUE$2));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetValue(STValue sTValue) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STValue sTValue2 = null;
            sTValue2 = (STValue)((Object)this.get_store().find_element_user(VALUE$2, 0));
            if (sTValue2 == null) {
                sTValue2 = (STValue)((Object)this.get_store().add_element_user(VALUE$2));
            }
            sTValue2.set(sTValue);
        }
    }
}

