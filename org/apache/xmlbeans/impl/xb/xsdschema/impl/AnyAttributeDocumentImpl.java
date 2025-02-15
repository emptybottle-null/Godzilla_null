/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.AnyAttributeDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.Wildcard;

public class AnyAttributeDocumentImpl
extends XmlComplexContentImpl
implements AnyAttributeDocument {
    private static final long serialVersionUID = 1L;
    private static final QName ANYATTRIBUTE$0 = new QName("http://www.w3.org/2001/XMLSchema", "anyAttribute");

    public AnyAttributeDocumentImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Wildcard getAnyAttribute() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Wildcard target = null;
            target = (Wildcard)((Object)this.get_store().find_element_user(ANYATTRIBUTE$0, 0));
            if (target == null) {
                return null;
            }
            return target;
        }
    }

    @Override
    public void setAnyAttribute(Wildcard anyAttribute) {
        this.generatedSetterHelperImpl(anyAttribute, ANYATTRIBUTE$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Wildcard addNewAnyAttribute() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Wildcard target = null;
            target = (Wildcard)((Object)this.get_store().add_element_user(ANYATTRIBUTE$0));
            return target;
        }
    }
}

