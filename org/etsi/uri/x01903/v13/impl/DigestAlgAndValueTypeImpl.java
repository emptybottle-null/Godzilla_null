/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.etsi.uri.x01903.v13.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.DigestAlgAndValueType;
import org.w3.x2000.x09.xmldsig.DigestMethodType;
import org.w3.x2000.x09.xmldsig.DigestValueType;

public class DigestAlgAndValueTypeImpl
extends XmlComplexContentImpl
implements DigestAlgAndValueType {
    private static final long serialVersionUID = 1L;
    private static final QName DIGESTMETHOD$0 = new QName("http://www.w3.org/2000/09/xmldsig#", "DigestMethod");
    private static final QName DIGESTVALUE$2 = new QName("http://www.w3.org/2000/09/xmldsig#", "DigestValue");

    public DigestAlgAndValueTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public DigestMethodType getDigestMethod() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            DigestMethodType digestMethodType = null;
            digestMethodType = (DigestMethodType)((Object)this.get_store().find_element_user(DIGESTMETHOD$0, 0));
            if (digestMethodType == null) {
                return null;
            }
            return digestMethodType;
        }
    }

    @Override
    public void setDigestMethod(DigestMethodType digestMethodType) {
        this.generatedSetterHelperImpl(digestMethodType, DIGESTMETHOD$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public DigestMethodType addNewDigestMethod() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            DigestMethodType digestMethodType = null;
            digestMethodType = (DigestMethodType)((Object)this.get_store().add_element_user(DIGESTMETHOD$0));
            return digestMethodType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte[] getDigestValue() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(DIGESTVALUE$2, 0));
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
    public DigestValueType xgetDigestValue() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            DigestValueType digestValueType = null;
            digestValueType = (DigestValueType)((Object)this.get_store().find_element_user(DIGESTVALUE$2, 0));
            return digestValueType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDigestValue(byte[] byArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(DIGESTVALUE$2, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(DIGESTVALUE$2));
            }
            simpleValue.setByteArrayValue(byArray);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDigestValue(DigestValueType digestValueType) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            DigestValueType digestValueType2 = null;
            digestValueType2 = (DigestValueType)((Object)this.get_store().find_element_user(DIGESTVALUE$2, 0));
            if (digestValueType2 == null) {
                digestValueType2 = (DigestValueType)((Object)this.get_store().add_element_user(DIGESTVALUE$2));
            }
            digestValueType2.set(digestValueType);
        }
    }
}

