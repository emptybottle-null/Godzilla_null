/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlide;
import org.openxmlformats.schemas.presentationml.x2006.main.SldDocument;

public class SldDocumentImpl
extends XmlComplexContentImpl
implements SldDocument {
    private static final long serialVersionUID = 1L;
    private static final QName SLD$0 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "sld");

    public SldDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSlide getSld() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSlide cTSlide = null;
            cTSlide = (CTSlide)((Object)this.get_store().find_element_user(SLD$0, 0));
            if (cTSlide == null) {
                return null;
            }
            return cTSlide;
        }
    }

    @Override
    public void setSld(CTSlide cTSlide) {
        this.generatedSetterHelperImpl(cTSlide, SLD$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSlide addNewSld() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSlide cTSlide = null;
            cTSlide = (CTSlide)((Object)this.get_store().add_element_user(SLD$0));
            return cTSlide;
        }
    }
}

