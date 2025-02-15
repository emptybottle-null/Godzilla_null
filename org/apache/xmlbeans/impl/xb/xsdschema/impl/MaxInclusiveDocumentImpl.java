/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.Facet;
import org.apache.xmlbeans.impl.xb.xsdschema.MaxInclusiveDocument;

public class MaxInclusiveDocumentImpl
extends XmlComplexContentImpl
implements MaxInclusiveDocument {
    private static final long serialVersionUID = 1L;
    private static final QName MAXINCLUSIVE$0 = new QName("http://www.w3.org/2001/XMLSchema", "maxInclusive");

    public MaxInclusiveDocumentImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Facet getMaxInclusive() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Facet target = null;
            target = (Facet)((Object)this.get_store().find_element_user(MAXINCLUSIVE$0, 0));
            if (target == null) {
                return null;
            }
            return target;
        }
    }

    @Override
    public void setMaxInclusive(Facet maxInclusive) {
        this.generatedSetterHelperImpl(maxInclusive, MAXINCLUSIVE$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Facet addNewMaxInclusive() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Facet target = null;
            target = (Facet)((Object)this.get_store().add_element_user(MAXINCLUSIVE$0));
            return target;
        }
    }
}

