/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTComment;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommentList;

public class CTCommentListImpl
extends XmlComplexContentImpl
implements CTCommentList {
    private static final long serialVersionUID = 1L;
    private static final QName CM$0 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "cm");

    public CTCommentListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTComment> getCmList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CmList
            extends AbstractList<CTComment> {
                CmList() {
                }

                @Override
                public CTComment get(int n) {
                    return CTCommentListImpl.this.getCmArray(n);
                }

                @Override
                public CTComment set(int n, CTComment cTComment) {
                    CTComment cTComment2 = CTCommentListImpl.this.getCmArray(n);
                    CTCommentListImpl.this.setCmArray(n, cTComment);
                    return cTComment2;
                }

                @Override
                public void add(int n, CTComment cTComment) {
                    CTCommentListImpl.this.insertNewCm(n).set(cTComment);
                }

                @Override
                public CTComment remove(int n) {
                    CTComment cTComment = CTCommentListImpl.this.getCmArray(n);
                    CTCommentListImpl.this.removeCm(n);
                    return cTComment;
                }

                @Override
                public int size() {
                    return CTCommentListImpl.this.sizeOfCmArray();
                }
            }
            return new CmList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTComment[] getCmArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CM$0, arrayList);
            CTComment[] cTCommentArray = new CTComment[arrayList.size()];
            arrayList.toArray(cTCommentArray);
            return cTCommentArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTComment getCmArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTComment cTComment = null;
            cTComment = (CTComment)((Object)this.get_store().find_element_user(CM$0, n));
            if (cTComment == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTComment;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCmArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CM$0);
        }
    }

    @Override
    public void setCmArray(CTComment[] cTCommentArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTCommentArray, CM$0);
    }

    @Override
    public void setCmArray(int n, CTComment cTComment) {
        this.generatedSetterHelperImpl(cTComment, CM$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTComment insertNewCm(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTComment cTComment = null;
            cTComment = (CTComment)((Object)this.get_store().insert_element_user(CM$0, n));
            return cTComment;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTComment addNewCm() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTComment cTComment = null;
            cTComment = (CTComment)((Object)this.get_store().add_element_user(CM$0));
            return cTComment;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCm(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CM$0, n);
        }
    }
}

