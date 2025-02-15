/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumbering;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.NumberingDocument;

public class NumberingDocumentImpl
extends XmlComplexContentImpl
implements NumberingDocument {
    private static final long serialVersionUID = 1L;
    private static final QName NUMBERING$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "numbering");

    public NumberingDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNumbering getNumbering() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNumbering cTNumbering = null;
            cTNumbering = (CTNumbering)((Object)this.get_store().find_element_user(NUMBERING$0, 0));
            if (cTNumbering == null) {
                return null;
            }
            return cTNumbering;
        }
    }

    @Override
    public void setNumbering(CTNumbering cTNumbering) {
        this.generatedSetterHelperImpl(cTNumbering, NUMBERING$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNumbering addNewNumbering() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNumbering cTNumbering = null;
            cTNumbering = (CTNumbering)((Object)this.get_store().add_element_user(NUMBERING$0));
            return cTNumbering;
        }
    }
}

