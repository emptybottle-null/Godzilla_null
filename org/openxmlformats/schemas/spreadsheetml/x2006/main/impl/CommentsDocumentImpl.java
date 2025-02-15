/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComments;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CommentsDocument;

public class CommentsDocumentImpl
extends XmlComplexContentImpl
implements CommentsDocument {
    private static final long serialVersionUID = 1L;
    private static final QName COMMENTS$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "comments");

    public CommentsDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTComments getComments() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTComments cTComments = null;
            cTComments = (CTComments)((Object)this.get_store().find_element_user(COMMENTS$0, 0));
            if (cTComments == null) {
                return null;
            }
            return cTComments;
        }
    }

    @Override
    public void setComments(CTComments cTComments) {
        this.generatedSetterHelperImpl(cTComments, COMMENTS$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTComments addNewComments() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTComments cTComments = null;
            cTComments = (CTComments)((Object)this.get_store().add_element_user(COMMENTS$0));
            return cTComments;
        }
    }
}

