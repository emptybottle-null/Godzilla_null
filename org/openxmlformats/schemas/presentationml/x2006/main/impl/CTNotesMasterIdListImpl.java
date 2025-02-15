/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTNotesMasterIdList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTNotesMasterIdListEntry;

public class CTNotesMasterIdListImpl
extends XmlComplexContentImpl
implements CTNotesMasterIdList {
    private static final long serialVersionUID = 1L;
    private static final QName NOTESMASTERID$0 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "notesMasterId");

    public CTNotesMasterIdListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNotesMasterIdListEntry getNotesMasterId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNotesMasterIdListEntry cTNotesMasterIdListEntry = null;
            cTNotesMasterIdListEntry = (CTNotesMasterIdListEntry)((Object)this.get_store().find_element_user(NOTESMASTERID$0, 0));
            if (cTNotesMasterIdListEntry == null) {
                return null;
            }
            return cTNotesMasterIdListEntry;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetNotesMasterId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(NOTESMASTERID$0) != 0;
        }
    }

    @Override
    public void setNotesMasterId(CTNotesMasterIdListEntry cTNotesMasterIdListEntry) {
        this.generatedSetterHelperImpl(cTNotesMasterIdListEntry, NOTESMASTERID$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNotesMasterIdListEntry addNewNotesMasterId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNotesMasterIdListEntry cTNotesMasterIdListEntry = null;
            cTNotesMasterIdListEntry = (CTNotesMasterIdListEntry)((Object)this.get_store().add_element_user(NOTESMASTERID$0));
            return cTNotesMasterIdListEntry;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetNotesMasterId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(NOTESMASTERID$0, 0);
        }
    }
}

