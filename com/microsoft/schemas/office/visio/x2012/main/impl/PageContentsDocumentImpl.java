/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.office.visio.x2012.main.impl;

import com.microsoft.schemas.office.visio.x2012.main.PageContentsDocument;
import com.microsoft.schemas.office.visio.x2012.main.PageContentsType;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class PageContentsDocumentImpl
extends XmlComplexContentImpl
implements PageContentsDocument {
    private static final long serialVersionUID = 1L;
    private static final QName PAGECONTENTS$0 = new QName("http://schemas.microsoft.com/office/visio/2012/main", "PageContents");

    public PageContentsDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public PageContentsType getPageContents() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            PageContentsType pageContentsType = null;
            pageContentsType = (PageContentsType)((Object)this.get_store().find_element_user(PAGECONTENTS$0, 0));
            if (pageContentsType == null) {
                return null;
            }
            return pageContentsType;
        }
    }

    @Override
    public void setPageContents(PageContentsType pageContentsType) {
        this.generatedSetterHelperImpl(pageContentsType, PAGECONTENTS$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public PageContentsType addNewPageContents() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            PageContentsType pageContentsType = null;
            pageContentsType = (PageContentsType)((Object)this.get_store().add_element_user(PAGECONTENTS$0));
            return pageContentsType;
        }
    }
}

