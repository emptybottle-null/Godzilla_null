/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.StylesDocument;

public class StylesDocumentImpl
extends XmlComplexContentImpl
implements StylesDocument {
    private static final long serialVersionUID = 1L;
    private static final QName STYLES$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "styles");

    public StylesDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStyles getStyles() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStyles cTStyles = null;
            cTStyles = (CTStyles)((Object)this.get_store().find_element_user(STYLES$0, 0));
            if (cTStyles == null) {
                return null;
            }
            return cTStyles;
        }
    }

    @Override
    public void setStyles(CTStyles cTStyles) {
        this.generatedSetterHelperImpl(cTStyles, STYLES$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStyles addNewStyles() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStyles cTStyles = null;
            cTStyles = (CTStyles)((Object)this.get_store().add_element_user(STYLES$0));
            return cTStyles;
        }
    }
}

