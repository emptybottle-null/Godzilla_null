/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.office.x2006.encryption.impl;

import com.microsoft.schemas.office.x2006.encryption.CTDataIntegrity;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBase64Binary;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class CTDataIntegrityImpl
extends XmlComplexContentImpl
implements CTDataIntegrity {
    private static final long serialVersionUID = 1L;
    private static final QName ENCRYPTEDHMACKEY$0 = new QName("", "encryptedHmacKey");
    private static final QName ENCRYPTEDHMACVALUE$2 = new QName("", "encryptedHmacValue");

    public CTDataIntegrityImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte[] getEncryptedHmacKey() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ENCRYPTEDHMACKEY$0));
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
    public XmlBase64Binary xgetEncryptedHmacKey() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBase64Binary xmlBase64Binary = null;
            xmlBase64Binary = (XmlBase64Binary)((Object)this.get_store().find_attribute_user(ENCRYPTEDHMACKEY$0));
            return xmlBase64Binary;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setEncryptedHmacKey(byte[] byArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ENCRYPTEDHMACKEY$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ENCRYPTEDHMACKEY$0));
            }
            simpleValue.setByteArrayValue(byArray);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetEncryptedHmacKey(XmlBase64Binary xmlBase64Binary) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBase64Binary xmlBase64Binary2 = null;
            xmlBase64Binary2 = (XmlBase64Binary)((Object)this.get_store().find_attribute_user(ENCRYPTEDHMACKEY$0));
            if (xmlBase64Binary2 == null) {
                xmlBase64Binary2 = (XmlBase64Binary)((Object)this.get_store().add_attribute_user(ENCRYPTEDHMACKEY$0));
            }
            xmlBase64Binary2.set(xmlBase64Binary);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte[] getEncryptedHmacValue() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ENCRYPTEDHMACVALUE$2));
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
    public XmlBase64Binary xgetEncryptedHmacValue() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBase64Binary xmlBase64Binary = null;
            xmlBase64Binary = (XmlBase64Binary)((Object)this.get_store().find_attribute_user(ENCRYPTEDHMACVALUE$2));
            return xmlBase64Binary;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setEncryptedHmacValue(byte[] byArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ENCRYPTEDHMACVALUE$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ENCRYPTEDHMACVALUE$2));
            }
            simpleValue.setByteArrayValue(byArray);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetEncryptedHmacValue(XmlBase64Binary xmlBase64Binary) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBase64Binary xmlBase64Binary2 = null;
            xmlBase64Binary2 = (XmlBase64Binary)((Object)this.get_store().find_attribute_user(ENCRYPTEDHMACVALUE$2));
            if (xmlBase64Binary2 == null) {
                xmlBase64Binary2 = (XmlBase64Binary)((Object)this.get_store().add_attribute_user(ENCRYPTEDHMACVALUE$2));
            }
            xmlBase64Binary2.set(xmlBase64Binary);
        }
    }
}

