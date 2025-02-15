/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTNotesMaster;
import org.openxmlformats.schemas.presentationml.x2006.main.NotesMasterDocument;

public class NotesMasterDocumentImpl
extends XmlComplexContentImpl
implements NotesMasterDocument {
    private static final long serialVersionUID = 1L;
    private static final QName NOTESMASTER$0 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "notesMaster");

    public NotesMasterDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNotesMaster getNotesMaster() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNotesMaster cTNotesMaster = null;
            cTNotesMaster = (CTNotesMaster)((Object)this.get_store().find_element_user(NOTESMASTER$0, 0));
            if (cTNotesMaster == null) {
                return null;
            }
            return cTNotesMaster;
        }
    }

    @Override
    public void setNotesMaster(CTNotesMaster cTNotesMaster) {
        this.generatedSetterHelperImpl(cTNotesMaster, NOTESMASTER$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNotesMaster addNewNotesMaster() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNotesMaster cTNotesMaster = null;
            cTNotesMaster = (CTNotesMaster)((Object)this.get_store().add_element_user(NOTESMASTER$0));
            return cTNotesMaster;
        }
    }
}

