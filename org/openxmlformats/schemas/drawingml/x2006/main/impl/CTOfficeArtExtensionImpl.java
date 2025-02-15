/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtension;

public class CTOfficeArtExtensionImpl
extends XmlComplexContentImpl
implements CTOfficeArtExtension {
    private static final long serialVersionUID = 1L;
    private static final QName URI$0 = new QName("", "uri");

    public CTOfficeArtExtensionImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getUri() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(URI$0));
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
    public XmlToken xgetUri() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlToken xmlToken = null;
            xmlToken = (XmlToken)((Object)this.get_store().find_attribute_user(URI$0));
            return xmlToken;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetUri() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(URI$0) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setUri(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(URI$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(URI$0));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetUri(XmlToken xmlToken) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlToken xmlToken2 = null;
            xmlToken2 = (XmlToken)((Object)this.get_store().find_attribute_user(URI$0));
            if (xmlToken2 == null) {
                xmlToken2 = (XmlToken)((Object)this.get_store().add_attribute_user(URI$0));
            }
            xmlToken2.set(xmlToken);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetUri() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(URI$0);
        }
    }
}

