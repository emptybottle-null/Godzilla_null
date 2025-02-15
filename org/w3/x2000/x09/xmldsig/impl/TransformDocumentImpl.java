/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.w3.x2000.x09.xmldsig.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.w3.x2000.x09.xmldsig.TransformDocument;
import org.w3.x2000.x09.xmldsig.TransformType;

public class TransformDocumentImpl
extends XmlComplexContentImpl
implements TransformDocument {
    private static final long serialVersionUID = 1L;
    private static final QName TRANSFORM$0 = new QName("http://www.w3.org/2000/09/xmldsig#", "Transform");

    public TransformDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public TransformType getTransform() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            TransformType transformType = null;
            transformType = (TransformType)((Object)this.get_store().find_element_user(TRANSFORM$0, 0));
            if (transformType == null) {
                return null;
            }
            return transformType;
        }
    }

    @Override
    public void setTransform(TransformType transformType) {
        this.generatedSetterHelperImpl(transformType, TRANSFORM$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public TransformType addNewTransform() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            TransformType transformType = null;
            transformType = (TransformType)((Object)this.get_store().add_element_user(TRANSFORM$0));
            return transformType;
        }
    }
}

