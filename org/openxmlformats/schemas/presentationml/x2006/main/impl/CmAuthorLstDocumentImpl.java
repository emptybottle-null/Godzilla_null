/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommentAuthorList;
import org.openxmlformats.schemas.presentationml.x2006.main.CmAuthorLstDocument;

public class CmAuthorLstDocumentImpl
extends XmlComplexContentImpl
implements CmAuthorLstDocument {
    private static final long serialVersionUID = 1L;
    private static final QName CMAUTHORLST$0 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "cmAuthorLst");

    public CmAuthorLstDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCommentAuthorList getCmAuthorLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCommentAuthorList cTCommentAuthorList = null;
            cTCommentAuthorList = (CTCommentAuthorList)((Object)this.get_store().find_element_user(CMAUTHORLST$0, 0));
            if (cTCommentAuthorList == null) {
                return null;
            }
            return cTCommentAuthorList;
        }
    }

    @Override
    public void setCmAuthorLst(CTCommentAuthorList cTCommentAuthorList) {
        this.generatedSetterHelperImpl(cTCommentAuthorList, CMAUTHORLST$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCommentAuthorList addNewCmAuthorLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCommentAuthorList cTCommentAuthorList = null;
            cTCommentAuthorList = (CTCommentAuthorList)((Object)this.get_store().add_element_user(CMAUTHORLST$0));
            return cTCommentAuthorList;
        }
    }
}

