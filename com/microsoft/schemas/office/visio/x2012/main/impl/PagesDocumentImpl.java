/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.office.visio.x2012.main.impl;

import com.microsoft.schemas.office.visio.x2012.main.PagesDocument;
import com.microsoft.schemas.office.visio.x2012.main.PagesType;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class PagesDocumentImpl
extends XmlComplexContentImpl
implements PagesDocument {
    private static final long serialVersionUID = 1L;
    private static final QName PAGES$0 = new QName("http://schemas.microsoft.com/office/visio/2012/main", "Pages");

    public PagesDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public PagesType getPages() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            PagesType pagesType = null;
            pagesType = (PagesType)((Object)this.get_store().find_element_user(PAGES$0, 0));
            if (pagesType == null) {
                return null;
            }
            return pagesType;
        }
    }

    @Override
    public void setPages(PagesType pagesType) {
        this.generatedSetterHelperImpl(pagesType, PAGES$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public PagesType addNewPages() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            PagesType pagesType = null;
            pagesType = (PagesType)((Object)this.get_store().add_element_user(PAGES$0));
            return pagesType;
        }
    }
}

