/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.office.visio.x2012.main.impl;

import com.microsoft.schemas.office.visio.x2012.main.MasterContentsDocument;
import com.microsoft.schemas.office.visio.x2012.main.PageContentsType;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class MasterContentsDocumentImpl
extends XmlComplexContentImpl
implements MasterContentsDocument {
    private static final long serialVersionUID = 1L;
    private static final QName MASTERCONTENTS$0 = new QName("http://schemas.microsoft.com/office/visio/2012/main", "MasterContents");

    public MasterContentsDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public PageContentsType getMasterContents() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            PageContentsType pageContentsType = null;
            pageContentsType = (PageContentsType)((Object)this.get_store().find_element_user(MASTERCONTENTS$0, 0));
            if (pageContentsType == null) {
                return null;
            }
            return pageContentsType;
        }
    }

    @Override
    public void setMasterContents(PageContentsType pageContentsType) {
        this.generatedSetterHelperImpl(pageContentsType, MASTERCONTENTS$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public PageContentsType addNewMasterContents() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            PageContentsType pageContentsType = null;
            pageContentsType = (PageContentsType)((Object)this.get_store().add_element_user(MASTERCONTENTS$0));
            return pageContentsType;
        }
    }
}

