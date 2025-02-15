/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.SimpleTypeDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelSimpleType;

public class SimpleTypeDocumentImpl
extends XmlComplexContentImpl
implements SimpleTypeDocument {
    private static final long serialVersionUID = 1L;
    private static final QName SIMPLETYPE$0 = new QName("http://www.w3.org/2001/XMLSchema", "simpleType");

    public SimpleTypeDocumentImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public TopLevelSimpleType getSimpleType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            TopLevelSimpleType target = null;
            target = (TopLevelSimpleType)((Object)this.get_store().find_element_user(SIMPLETYPE$0, 0));
            if (target == null) {
                return null;
            }
            return target;
        }
    }

    @Override
    public void setSimpleType(TopLevelSimpleType simpleType) {
        this.generatedSetterHelperImpl(simpleType, SIMPLETYPE$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public TopLevelSimpleType addNewSimpleType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            TopLevelSimpleType target = null;
            target = (TopLevelSimpleType)((Object)this.get_store().add_element_user(SIMPLETYPE$0));
            return target;
        }
    }
}

