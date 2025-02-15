/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector;
import org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTVectorVariant;

public class CTVectorVariantImpl
extends XmlComplexContentImpl
implements CTVectorVariant {
    private static final long serialVersionUID = 1L;
    private static final QName VECTOR$0 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "vector");

    public CTVectorVariantImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTVector getVector() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTVector cTVector = null;
            cTVector = (CTVector)((Object)this.get_store().find_element_user(VECTOR$0, 0));
            if (cTVector == null) {
                return null;
            }
            return cTVector;
        }
    }

    @Override
    public void setVector(CTVector cTVector) {
        this.generatedSetterHelperImpl(cTVector, VECTOR$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTVector addNewVector() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTVector cTVector = null;
            cTVector = (CTVector)((Object)this.get_store().add_element_user(VECTOR$0));
            return cTVector;
        }
    }
}

