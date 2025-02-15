/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommentList;
import org.openxmlformats.schemas.presentationml.x2006.main.CmLstDocument;

public class CmLstDocumentImpl
extends XmlComplexContentImpl
implements CmLstDocument {
    private static final long serialVersionUID = 1L;
    private static final QName CMLST$0 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "cmLst");

    public CmLstDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCommentList getCmLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCommentList cTCommentList = null;
            cTCommentList = (CTCommentList)((Object)this.get_store().find_element_user(CMLST$0, 0));
            if (cTCommentList == null) {
                return null;
            }
            return cTCommentList;
        }
    }

    @Override
    public void setCmLst(CTCommentList cTCommentList) {
        this.generatedSetterHelperImpl(cTCommentList, CMLST$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCommentList addNewCmLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCommentList cTCommentList = null;
            cTCommentList = (CTCommentList)((Object)this.get_store().add_element_user(CMLST$0));
            return cTCommentList;
        }
    }
}

