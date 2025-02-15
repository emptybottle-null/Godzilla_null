/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBase64Binary;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTDigSigBlob;

public class CTDigSigBlobImpl
extends XmlComplexContentImpl
implements CTDigSigBlob {
    private static final long serialVersionUID = 1L;
    private static final QName BLOB$0 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "blob");

    public CTDigSigBlobImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte[] getBlob() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(BLOB$0, 0));
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
    public XmlBase64Binary xgetBlob() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBase64Binary xmlBase64Binary = null;
            xmlBase64Binary = (XmlBase64Binary)((Object)this.get_store().find_element_user(BLOB$0, 0));
            return xmlBase64Binary;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setBlob(byte[] byArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(BLOB$0, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(BLOB$0));
            }
            simpleValue.setByteArrayValue(byArray);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetBlob(XmlBase64Binary xmlBase64Binary) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBase64Binary xmlBase64Binary2 = null;
            xmlBase64Binary2 = (XmlBase64Binary)((Object)this.get_store().find_element_user(BLOB$0, 0));
            if (xmlBase64Binary2 == null) {
                xmlBase64Binary2 = (XmlBase64Binary)((Object)this.get_store().add_element_user(BLOB$0));
            }
            xmlBase64Binary2.set(xmlBase64Binary);
        }
    }
}

