/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.TotalDigitsDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.NumFacetImpl;

public class TotalDigitsDocumentImpl
extends XmlComplexContentImpl
implements TotalDigitsDocument {
    private static final long serialVersionUID = 1L;
    private static final QName TOTALDIGITS$0 = new QName("http://www.w3.org/2001/XMLSchema", "totalDigits");

    public TotalDigitsDocumentImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public TotalDigitsDocument.TotalDigits getTotalDigits() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            TotalDigitsDocument.TotalDigits target = null;
            target = (TotalDigitsDocument.TotalDigits)((Object)this.get_store().find_element_user(TOTALDIGITS$0, 0));
            if (target == null) {
                return null;
            }
            return target;
        }
    }

    @Override
    public void setTotalDigits(TotalDigitsDocument.TotalDigits totalDigits) {
        this.generatedSetterHelperImpl(totalDigits, TOTALDIGITS$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public TotalDigitsDocument.TotalDigits addNewTotalDigits() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            TotalDigitsDocument.TotalDigits target = null;
            target = (TotalDigitsDocument.TotalDigits)((Object)this.get_store().add_element_user(TOTALDIGITS$0));
            return target;
        }
    }

    public static class TotalDigitsImpl
    extends NumFacetImpl
    implements TotalDigitsDocument.TotalDigits {
        private static final long serialVersionUID = 1L;

        public TotalDigitsImpl(SchemaType sType) {
            super(sType);
        }
    }
}

