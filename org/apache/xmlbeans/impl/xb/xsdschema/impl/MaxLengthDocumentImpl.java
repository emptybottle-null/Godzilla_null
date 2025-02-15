/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.MaxLengthDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.NumFacet;

public class MaxLengthDocumentImpl
extends XmlComplexContentImpl
implements MaxLengthDocument {
    private static final long serialVersionUID = 1L;
    private static final QName MAXLENGTH$0 = new QName("http://www.w3.org/2001/XMLSchema", "maxLength");

    public MaxLengthDocumentImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public NumFacet getMaxLength() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            NumFacet target = null;
            target = (NumFacet)((Object)this.get_store().find_element_user(MAXLENGTH$0, 0));
            if (target == null) {
                return null;
            }
            return target;
        }
    }

    @Override
    public void setMaxLength(NumFacet maxLength) {
        this.generatedSetterHelperImpl(maxLength, MAXLENGTH$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public NumFacet addNewMaxLength() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            NumFacet target = null;
            target = (NumFacet)((Object)this.get_store().add_element_user(MAXLENGTH$0));
            return target;
        }
    }
}

