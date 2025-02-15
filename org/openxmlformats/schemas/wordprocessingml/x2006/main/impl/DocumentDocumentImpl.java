/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocument1;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.DocumentDocument;

public class DocumentDocumentImpl
extends XmlComplexContentImpl
implements DocumentDocument {
    private static final long serialVersionUID = 1L;
    private static final QName DOCUMENT$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "document");

    public DocumentDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDocument1 getDocument() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDocument1 cTDocument1 = null;
            cTDocument1 = (CTDocument1)((Object)this.get_store().find_element_user(DOCUMENT$0, 0));
            if (cTDocument1 == null) {
                return null;
            }
            return cTDocument1;
        }
    }

    @Override
    public void setDocument(CTDocument1 cTDocument1) {
        this.generatedSetterHelperImpl(cTDocument1, DOCUMENT$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDocument1 addNewDocument() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDocument1 cTDocument1 = null;
            cTDocument1 = (CTDocument1)((Object)this.get_store().add_element_user(DOCUMENT$0));
            return cTDocument1;
        }
    }
}

