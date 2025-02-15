/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEndnotes;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.EndnotesDocument;

public class EndnotesDocumentImpl
extends XmlComplexContentImpl
implements EndnotesDocument {
    private static final long serialVersionUID = 1L;
    private static final QName ENDNOTES$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "endnotes");

    public EndnotesDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEndnotes getEndnotes() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEndnotes cTEndnotes = null;
            cTEndnotes = (CTEndnotes)((Object)this.get_store().find_element_user(ENDNOTES$0, 0));
            if (cTEndnotes == null) {
                return null;
            }
            return cTEndnotes;
        }
    }

    @Override
    public void setEndnotes(CTEndnotes cTEndnotes) {
        this.generatedSetterHelperImpl(cTEndnotes, ENDNOTES$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEndnotes addNewEndnotes() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEndnotes cTEndnotes = null;
            cTEndnotes = (CTEndnotes)((Object)this.get_store().add_element_user(ENDNOTES$0));
            return cTEndnotes;
        }
    }
}

