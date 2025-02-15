/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.etsi.uri.x01903.v13.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.QualifyingPropertiesDocument;
import org.etsi.uri.x01903.v13.QualifyingPropertiesType;

public class QualifyingPropertiesDocumentImpl
extends XmlComplexContentImpl
implements QualifyingPropertiesDocument {
    private static final long serialVersionUID = 1L;
    private static final QName QUALIFYINGPROPERTIES$0 = new QName("http://uri.etsi.org/01903/v1.3.2#", "QualifyingProperties");

    public QualifyingPropertiesDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public QualifyingPropertiesType getQualifyingProperties() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            QualifyingPropertiesType qualifyingPropertiesType = null;
            qualifyingPropertiesType = (QualifyingPropertiesType)((Object)this.get_store().find_element_user(QUALIFYINGPROPERTIES$0, 0));
            if (qualifyingPropertiesType == null) {
                return null;
            }
            return qualifyingPropertiesType;
        }
    }

    @Override
    public void setQualifyingProperties(QualifyingPropertiesType qualifyingPropertiesType) {
        this.generatedSetterHelperImpl(qualifyingPropertiesType, QUALIFYINGPROPERTIES$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public QualifyingPropertiesType addNewQualifyingProperties() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            QualifyingPropertiesType qualifyingPropertiesType = null;
            qualifyingPropertiesType = (QualifyingPropertiesType)((Object)this.get_store().add_element_user(QUALIFYINGPROPERTIES$0));
            return qualifyingPropertiesType;
        }
    }
}

