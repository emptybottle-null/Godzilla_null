/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.Facet;
import org.apache.xmlbeans.impl.xb.xsdschema.MinInclusiveDocument;

public class MinInclusiveDocumentImpl
extends XmlComplexContentImpl
implements MinInclusiveDocument {
    private static final long serialVersionUID = 1L;
    private static final QName MININCLUSIVE$0 = new QName("http://www.w3.org/2001/XMLSchema", "minInclusive");

    public MinInclusiveDocumentImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Facet getMinInclusive() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Facet target = null;
            target = (Facet)((Object)this.get_store().find_element_user(MININCLUSIVE$0, 0));
            if (target == null) {
                return null;
            }
            return target;
        }
    }

    @Override
    public void setMinInclusive(Facet minInclusive) {
        this.generatedSetterHelperImpl(minInclusive, MININCLUSIVE$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Facet addNewMinInclusive() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Facet target = null;
            target = (Facet)((Object)this.get_store().add_element_user(MININCLUSIVE$0));
            return target;
        }
    }
}

