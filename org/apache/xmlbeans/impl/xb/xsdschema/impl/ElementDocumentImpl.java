/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.ElementDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelElement;

public class ElementDocumentImpl
extends XmlComplexContentImpl
implements ElementDocument {
    private static final long serialVersionUID = 1L;
    private static final QName ELEMENT$0 = new QName("http://www.w3.org/2001/XMLSchema", "element");

    public ElementDocumentImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public TopLevelElement getElement() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            TopLevelElement target = null;
            target = (TopLevelElement)((Object)this.get_store().find_element_user(ELEMENT$0, 0));
            if (target == null) {
                return null;
            }
            return target;
        }
    }

    @Override
    public void setElement(TopLevelElement element) {
        this.generatedSetterHelperImpl(element, ELEMENT$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public TopLevelElement addNewElement() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            TopLevelElement target = null;
            target = (TopLevelElement)((Object)this.get_store().add_element_user(ELEMENT$0));
            return target;
        }
    }
}

