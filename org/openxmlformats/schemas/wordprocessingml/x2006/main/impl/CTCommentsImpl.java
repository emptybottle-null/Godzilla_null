/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTComment;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTComments;

public class CTCommentsImpl
extends XmlComplexContentImpl
implements CTComments {
    private static final long serialVersionUID = 1L;
    private static final QName COMMENT$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "comment");

    public CTCommentsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTComment> getCommentList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CommentList
            extends AbstractList<CTComment> {
                CommentList() {
                }

                @Override
                public CTComment get(int n) {
                    return CTCommentsImpl.this.getCommentArray(n);
                }

                @Override
                public CTComment set(int n, CTComment cTComment) {
                    CTComment cTComment2 = CTCommentsImpl.this.getCommentArray(n);
                    CTCommentsImpl.this.setCommentArray(n, cTComment);
                    return cTComment2;
                }

                @Override
                public void add(int n, CTComment cTComment) {
                    CTCommentsImpl.this.insertNewComment(n).set(cTComment);
                }

                @Override
                public CTComment remove(int n) {
                    CTComment cTComment = CTCommentsImpl.this.getCommentArray(n);
                    CTCommentsImpl.this.removeComment(n);
                    return cTComment;
                }

                @Override
                public int size() {
                    return CTCommentsImpl.this.sizeOfCommentArray();
                }
            }
            return new CommentList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTComment[] getCommentArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(COMMENT$0, arrayList);
            CTComment[] cTCommentArray = new CTComment[arrayList.size()];
            arrayList.toArray(cTCommentArray);
            return cTCommentArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTComment getCommentArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTComment cTComment = null;
            cTComment = (CTComment)((Object)this.get_store().find_element_user(COMMENT$0, n));
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
    public int sizeOfCommentArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(COMMENT$0);
        }
    }

    @Override
    public void setCommentArray(CTComment[] cTCommentArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTCommentArray, COMMENT$0);
    }

    @Override
    public void setCommentArray(int n, CTComment cTComment) {
        this.generatedSetterHelperImpl(cTComment, COMMENT$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTComment insertNewComment(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTComment cTComment = null;
            cTComment = (CTComment)((Object)this.get_store().insert_element_user(COMMENT$0, n));
            return cTComment;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTComment addNewComment() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTComment cTComment = null;
            cTComment = (CTComment)((Object)this.get_store().add_element_user(COMMENT$0));
            return cTComment;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeComment(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(COMMENT$0, n);
        }
    }
}

