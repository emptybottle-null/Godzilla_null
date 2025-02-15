/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties;
import org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.PropertiesDocument;

public class PropertiesDocumentImpl
extends XmlComplexContentImpl
implements PropertiesDocument {
    private static final long serialVersionUID = 1L;
    private static final QName PROPERTIES$0 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/extended-properties", "Properties");

    public PropertiesDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTProperties getProperties() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTProperties cTProperties = null;
            cTProperties = (CTProperties)((Object)this.get_store().find_element_user(PROPERTIES$0, 0));
            if (cTProperties == null) {
                return null;
            }
            return cTProperties;
        }
    }

    @Override
    public void setProperties(CTProperties cTProperties) {
        this.generatedSetterHelperImpl(cTProperties, PROPERTIES$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTProperties addNewProperties() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTProperties cTProperties = null;
            cTProperties = (CTProperties)((Object)this.get_store().add_element_user(PROPERTIES$0));
            return cTProperties;
        }
    }
}

