/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTAuthors;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCommentList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComments;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;

public class CTCommentsImpl
extends XmlComplexContentImpl
implements CTComments {
    private static final long serialVersionUID = 1L;
    private static final QName AUTHORS$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "authors");
    private static final QName COMMENTLIST$2 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "commentList");
    private static final QName EXTLST$4 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "extLst");

    public CTCommentsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAuthors getAuthors() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAuthors cTAuthors = null;
            cTAuthors = (CTAuthors)((Object)this.get_store().find_element_user(AUTHORS$0, 0));
            if (cTAuthors == null) {
                return null;
            }
            return cTAuthors;
        }
    }

    @Override
    public void setAuthors(CTAuthors cTAuthors) {
        this.generatedSetterHelperImpl(cTAuthors, AUTHORS$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAuthors addNewAuthors() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAuthors cTAuthors = null;
            cTAuthors = (CTAuthors)((Object)this.get_store().add_element_user(AUTHORS$0));
            return cTAuthors;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCommentList getCommentList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCommentList cTCommentList = null;
            cTCommentList = (CTCommentList)((Object)this.get_store().find_element_user(COMMENTLIST$2, 0));
            if (cTCommentList == null) {
                return null;
            }
            return cTCommentList;
        }
    }

    @Override
    public void setCommentList(CTCommentList cTCommentList) {
        this.generatedSetterHelperImpl(cTCommentList, COMMENTLIST$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCommentList addNewCommentList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCommentList cTCommentList = null;
            cTCommentList = (CTCommentList)((Object)this.get_store().add_element_user(COMMENTLIST$2));
            return cTCommentList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExtensionList getExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExtensionList cTExtensionList = null;
            cTExtensionList = (CTExtensionList)this.get_store().find_element_user(EXTLST$4, 0);
            if (cTExtensionList == null) {
                return null;
            }
            return cTExtensionList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(EXTLST$4) != 0;
        }
    }

    @Override
    public void setExtLst(CTExtensionList cTExtensionList) {
        this.generatedSetterHelperImpl((XmlObject)cTExtensionList, EXTLST$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExtensionList addNewExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExtensionList cTExtensionList = null;
            cTExtensionList = (CTExtensionList)this.get_store().add_element_user(EXTLST$4);
            return cTExtensionList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(EXTLST$4, 0);
        }
    }
}

