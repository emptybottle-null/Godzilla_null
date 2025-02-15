/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFootnotes;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.FootnotesDocument;

public class FootnotesDocumentImpl
extends XmlComplexContentImpl
implements FootnotesDocument {
    private static final long serialVersionUID = 1L;
    private static final QName FOOTNOTES$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "footnotes");

    public FootnotesDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFootnotes getFootnotes() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFootnotes cTFootnotes = null;
            cTFootnotes = (CTFootnotes)((Object)this.get_store().find_element_user(FOOTNOTES$0, 0));
            if (cTFootnotes == null) {
                return null;
            }
            return cTFootnotes;
        }
    }

    @Override
    public void setFootnotes(CTFootnotes cTFootnotes) {
        this.generatedSetterHelperImpl(cTFootnotes, FOOTNOTES$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFootnotes addNewFootnotes() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFootnotes cTFootnotes = null;
            cTFootnotes = (CTFootnotes)((Object)this.get_store().add_element_user(FOOTNOTES$0));
            return cTFootnotes;
        }
    }
}

