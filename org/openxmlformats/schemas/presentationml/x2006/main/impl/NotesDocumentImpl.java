/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTNotesSlide;
import org.openxmlformats.schemas.presentationml.x2006.main.NotesDocument;

public class NotesDocumentImpl
extends XmlComplexContentImpl
implements NotesDocument {
    private static final long serialVersionUID = 1L;
    private static final QName NOTES$0 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "notes");

    public NotesDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNotesSlide getNotes() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNotesSlide cTNotesSlide = null;
            cTNotesSlide = (CTNotesSlide)((Object)this.get_store().find_element_user(NOTES$0, 0));
            if (cTNotesSlide == null) {
                return null;
            }
            return cTNotesSlide;
        }
    }

    @Override
    public void setNotes(CTNotesSlide cTNotesSlide) {
        this.generatedSetterHelperImpl(cTNotesSlide, NOTES$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNotesSlide addNewNotes() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNotesSlide cTNotesSlide = null;
            cTNotesSlide = (CTNotesSlide)((Object)this.get_store().add_element_user(NOTES$0));
            return cTNotesSlide;
        }
    }
}

