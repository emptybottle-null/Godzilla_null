/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.w3.x2000.x09.xmldsig.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.w3.x2000.x09.xmldsig.CanonicalizationMethodType;

public class CanonicalizationMethodTypeImpl
extends XmlComplexContentImpl
implements CanonicalizationMethodType {
    private static final long serialVersionUID = 1L;
    private static final QName ALGORITHM$0 = new QName("", "Algorithm");

    public CanonicalizationMethodTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getAlgorithm() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ALGORITHM$0));
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
    public XmlAnyURI xgetAlgorithm() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlAnyURI xmlAnyURI = null;
            xmlAnyURI = (XmlAnyURI)((Object)this.get_store().find_attribute_user(ALGORITHM$0));
            return xmlAnyURI;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAlgorithm(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ALGORITHM$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ALGORITHM$0));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAlgorithm(XmlAnyURI xmlAnyURI) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlAnyURI xmlAnyURI2 = null;
            xmlAnyURI2 = (XmlAnyURI)((Object)this.get_store().find_attribute_user(ALGORITHM$0));
            if (xmlAnyURI2 == null) {
                xmlAnyURI2 = (XmlAnyURI)((Object)this.get_store().add_attribute_user(ALGORITHM$0));
            }
            xmlAnyURI2.set(xmlAnyURI);
        }
    }
}

