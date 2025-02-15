/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeStyleSheet;
import org.openxmlformats.schemas.drawingml.x2006.main.ThemeDocument;

public class ThemeDocumentImpl
extends XmlComplexContentImpl
implements ThemeDocument {
    private static final long serialVersionUID = 1L;
    private static final QName THEME$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "theme");

    public ThemeDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOfficeStyleSheet getTheme() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOfficeStyleSheet cTOfficeStyleSheet = null;
            cTOfficeStyleSheet = (CTOfficeStyleSheet)((Object)this.get_store().find_element_user(THEME$0, 0));
            if (cTOfficeStyleSheet == null) {
                return null;
            }
            return cTOfficeStyleSheet;
        }
    }

    @Override
    public void setTheme(CTOfficeStyleSheet cTOfficeStyleSheet) {
        this.generatedSetterHelperImpl(cTOfficeStyleSheet, THEME$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOfficeStyleSheet addNewTheme() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOfficeStyleSheet cTOfficeStyleSheet = null;
            cTOfficeStyleSheet = (CTOfficeStyleSheet)((Object)this.get_store().add_element_user(THEME$0));
            return cTOfficeStyleSheet;
        }
    }
}

