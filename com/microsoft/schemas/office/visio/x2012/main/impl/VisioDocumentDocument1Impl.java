/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.office.visio.x2012.main.impl;

import com.microsoft.schemas.office.visio.x2012.main.VisioDocumentDocument1;
import com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class VisioDocumentDocument1Impl
extends XmlComplexContentImpl
implements VisioDocumentDocument1 {
    private static final long serialVersionUID = 1L;
    private static final QName VISIODOCUMENT$0 = new QName("http://schemas.microsoft.com/office/visio/2012/main", "VisioDocument");

    public VisioDocumentDocument1Impl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public VisioDocumentType getVisioDocument() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            VisioDocumentType visioDocumentType = null;
            visioDocumentType = (VisioDocumentType)((Object)this.get_store().find_element_user(VISIODOCUMENT$0, 0));
            if (visioDocumentType == null) {
                return null;
            }
            return visioDocumentType;
        }
    }

    @Override
    public void setVisioDocument(VisioDocumentType visioDocumentType) {
        this.generatedSetterHelperImpl(visioDocumentType, VISIODOCUMENT$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public VisioDocumentType addNewVisioDocument() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            VisioDocumentType visioDocumentType = null;
            visioDocumentType = (VisioDocumentType)((Object)this.get_store().add_element_user(VISIODOCUMENT$0));
            return visioDocumentType;
        }
    }
}

