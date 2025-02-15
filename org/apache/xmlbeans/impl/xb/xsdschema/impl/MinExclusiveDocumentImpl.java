/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.Facet;
import org.apache.xmlbeans.impl.xb.xsdschema.MinExclusiveDocument;

public class MinExclusiveDocumentImpl
extends XmlComplexContentImpl
implements MinExclusiveDocument {
    private static final long serialVersionUID = 1L;
    private static final QName MINEXCLUSIVE$0 = new QName("http://www.w3.org/2001/XMLSchema", "minExclusive");

    public MinExclusiveDocumentImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Facet getMinExclusive() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Facet target = null;
            target = (Facet)((Object)this.get_store().find_element_user(MINEXCLUSIVE$0, 0));
            if (target == null) {
                return null;
            }
            return target;
        }
    }

    @Override
    public void setMinExclusive(Facet minExclusive) {
        this.generatedSetterHelperImpl(minExclusive, MINEXCLUSIVE$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Facet addNewMinExclusive() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Facet target = null;
            target = (Facet)((Object)this.get_store().add_element_user(MINEXCLUSIVE$0));
            return target;
        }
    }
}

