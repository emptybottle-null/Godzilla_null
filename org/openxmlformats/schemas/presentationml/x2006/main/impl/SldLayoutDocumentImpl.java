/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout;
import org.openxmlformats.schemas.presentationml.x2006.main.SldLayoutDocument;

public class SldLayoutDocumentImpl
extends XmlComplexContentImpl
implements SldLayoutDocument {
    private static final long serialVersionUID = 1L;
    private static final QName SLDLAYOUT$0 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "sldLayout");

    public SldLayoutDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSlideLayout getSldLayout() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSlideLayout cTSlideLayout = null;
            cTSlideLayout = (CTSlideLayout)((Object)this.get_store().find_element_user(SLDLAYOUT$0, 0));
            if (cTSlideLayout == null) {
                return null;
            }
            return cTSlideLayout;
        }
    }

    @Override
    public void setSldLayout(CTSlideLayout cTSlideLayout) {
        this.generatedSetterHelperImpl(cTSlideLayout, SLDLAYOUT$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSlideLayout addNewSldLayout() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSlideLayout cTSlideLayout = null;
            cTSlideLayout = (CTSlideLayout)((Object)this.get_store().add_element_user(SLDLAYOUT$0));
            return cTSlideLayout;
        }
    }
}

