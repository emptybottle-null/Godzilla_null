/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.StyleSheetDocument;

public class StyleSheetDocumentImpl
extends XmlComplexContentImpl
implements StyleSheetDocument {
    private static final long serialVersionUID = 1L;
    private static final QName STYLESHEET$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "styleSheet");

    public StyleSheetDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStylesheet getStyleSheet() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStylesheet cTStylesheet = null;
            cTStylesheet = (CTStylesheet)((Object)this.get_store().find_element_user(STYLESHEET$0, 0));
            if (cTStylesheet == null) {
                return null;
            }
            return cTStylesheet;
        }
    }

    @Override
    public void setStyleSheet(CTStylesheet cTStylesheet) {
        this.generatedSetterHelperImpl(cTStylesheet, STYLESHEET$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStylesheet addNewStyleSheet() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStylesheet cTStylesheet = null;
            cTStylesheet = (CTStylesheet)((Object)this.get_store().add_element_user(STYLESHEET$0));
            return cTStylesheet;
        }
    }
}

