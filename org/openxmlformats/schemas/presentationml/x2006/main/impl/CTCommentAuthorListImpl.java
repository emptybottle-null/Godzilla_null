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
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommentAuthor;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommentAuthorList;

public class CTCommentAuthorListImpl
extends XmlComplexContentImpl
implements CTCommentAuthorList {
    private static final long serialVersionUID = 1L;
    private static final QName CMAUTHOR$0 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "cmAuthor");

    public CTCommentAuthorListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTCommentAuthor> getCmAuthorList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CmAuthorList
            extends AbstractList<CTCommentAuthor> {
                CmAuthorList() {
                }

                @Override
                public CTCommentAuthor get(int n) {
                    return CTCommentAuthorListImpl.this.getCmAuthorArray(n);
                }

                @Override
                public CTCommentAuthor set(int n, CTCommentAuthor cTCommentAuthor) {
                    CTCommentAuthor cTCommentAuthor2 = CTCommentAuthorListImpl.this.getCmAuthorArray(n);
                    CTCommentAuthorListImpl.this.setCmAuthorArray(n, cTCommentAuthor);
                    return cTCommentAuthor2;
                }

                @Override
                public void add(int n, CTCommentAuthor cTCommentAuthor) {
                    CTCommentAuthorListImpl.this.insertNewCmAuthor(n).set(cTCommentAuthor);
                }

                @Override
                public CTCommentAuthor remove(int n) {
                    CTCommentAuthor cTCommentAuthor = CTCommentAuthorListImpl.this.getCmAuthorArray(n);
                    CTCommentAuthorListImpl.this.removeCmAuthor(n);
                    return cTCommentAuthor;
                }

                @Override
                public int size() {
                    return CTCommentAuthorListImpl.this.sizeOfCmAuthorArray();
                }
            }
            return new CmAuthorList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTCommentAuthor[] getCmAuthorArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CMAUTHOR$0, arrayList);
            CTCommentAuthor[] cTCommentAuthorArray = new CTCommentAuthor[arrayList.size()];
            arrayList.toArray(cTCommentAuthorArray);
            return cTCommentAuthorArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCommentAuthor getCmAuthorArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCommentAuthor cTCommentAuthor = null;
            cTCommentAuthor = (CTCommentAuthor)((Object)this.get_store().find_element_user(CMAUTHOR$0, n));
            if (cTCommentAuthor == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTCommentAuthor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCmAuthorArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CMAUTHOR$0);
        }
    }

    @Override
    public void setCmAuthorArray(CTCommentAuthor[] cTCommentAuthorArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTCommentAuthorArray, CMAUTHOR$0);
    }

    @Override
    public void setCmAuthorArray(int n, CTCommentAuthor cTCommentAuthor) {
        this.generatedSetterHelperImpl(cTCommentAuthor, CMAUTHOR$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCommentAuthor insertNewCmAuthor(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCommentAuthor cTCommentAuthor = null;
            cTCommentAuthor = (CTCommentAuthor)((Object)this.get_store().insert_element_user(CMAUTHOR$0, n));
            return cTCommentAuthor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCommentAuthor addNewCmAuthor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCommentAuthor cTCommentAuthor = null;
            cTCommentAuthor = (CTCommentAuthor)((Object)this.get_store().add_element_user(CMAUTHOR$0));
            return cTCommentAuthor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCmAuthor(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CMAUTHOR$0, n);
        }
    }
}

