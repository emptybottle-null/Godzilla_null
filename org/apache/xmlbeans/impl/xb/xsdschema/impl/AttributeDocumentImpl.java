/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.AttributeDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelAttribute;

public class AttributeDocumentImpl
extends XmlComplexContentImpl
implements AttributeDocument {
    private static final long serialVersionUID = 1L;
    private static final QName ATTRIBUTE$0 = new QName("http://www.w3.org/2001/XMLSchema", "attribute");

    public AttributeDocumentImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public TopLevelAttribute getAttribute() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            TopLevelAttribute target = null;
            target = (TopLevelAttribute)((Object)this.get_store().find_element_user(ATTRIBUTE$0, 0));
            if (target == null) {
                return null;
            }
            return target;
        }
    }

    @Override
    public void setAttribute(TopLevelAttribute attribute) {
        this.generatedSetterHelperImpl(attribute, ATTRIBUTE$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public TopLevelAttribute addNewAttribute() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            TopLevelAttribute target = null;
            target = (TopLevelAttribute)((Object)this.get_store().add_element_user(ATTRIBUTE$0));
            return target;
        }
    }
}

