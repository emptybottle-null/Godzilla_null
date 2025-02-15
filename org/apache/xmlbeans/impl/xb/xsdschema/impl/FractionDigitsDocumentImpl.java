/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.FractionDigitsDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.NumFacet;

public class FractionDigitsDocumentImpl
extends XmlComplexContentImpl
implements FractionDigitsDocument {
    private static final long serialVersionUID = 1L;
    private static final QName FRACTIONDIGITS$0 = new QName("http://www.w3.org/2001/XMLSchema", "fractionDigits");

    public FractionDigitsDocumentImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public NumFacet getFractionDigits() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            NumFacet target = null;
            target = (NumFacet)((Object)this.get_store().find_element_user(FRACTIONDIGITS$0, 0));
            if (target == null) {
                return null;
            }
            return target;
        }
    }

    @Override
    public void setFractionDigits(NumFacet fractionDigits) {
        this.generatedSetterHelperImpl(fractionDigits, FRACTIONDIGITS$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public NumFacet addNewFractionDigits() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            NumFacet target = null;
            target = (NumFacet)((Object)this.get_store().add_element_user(FRACTIONDIGITS$0));
            return target;
        }
    }
}

