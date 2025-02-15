/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation;
import org.openxmlformats.schemas.presentationml.x2006.main.PresentationDocument;

public class PresentationDocumentImpl
extends XmlComplexContentImpl
implements PresentationDocument {
    private static final long serialVersionUID = 1L;
    private static final QName PRESENTATION$0 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "presentation");

    public PresentationDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPresentation getPresentation() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPresentation cTPresentation = null;
            cTPresentation = (CTPresentation)((Object)this.get_store().find_element_user(PRESENTATION$0, 0));
            if (cTPresentation == null) {
                return null;
            }
            return cTPresentation;
        }
    }

    @Override
    public void setPresentation(CTPresentation cTPresentation) {
        this.generatedSetterHelperImpl(cTPresentation, PRESENTATION$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPresentation addNewPresentation() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPresentation cTPresentation = null;
            cTPresentation = (CTPresentation)((Object)this.get_store().add_element_user(PRESENTATION$0));
            return cTPresentation;
        }
    }
}

