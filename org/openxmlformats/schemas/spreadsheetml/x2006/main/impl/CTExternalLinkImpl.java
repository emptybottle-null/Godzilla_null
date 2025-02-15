/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDdeLink
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleLink
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDdeLink;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalBook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalLink;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleLink;

public class CTExternalLinkImpl
extends XmlComplexContentImpl
implements CTExternalLink {
    private static final long serialVersionUID = 1L;
    private static final QName EXTERNALBOOK$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "externalBook");
    private static final QName DDELINK$2 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "ddeLink");
    private static final QName OLELINK$4 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "oleLink");
    private static final QName EXTLST$6 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "extLst");

    public CTExternalLinkImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExternalBook getExternalBook() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExternalBook cTExternalBook = null;
            cTExternalBook = (CTExternalBook)((Object)this.get_store().find_element_user(EXTERNALBOOK$0, 0));
            if (cTExternalBook == null) {
                return null;
            }
            return cTExternalBook;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetExternalBook() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(EXTERNALBOOK$0) != 0;
        }
    }

    @Override
    public void setExternalBook(CTExternalBook cTExternalBook) {
        this.generatedSetterHelperImpl(cTExternalBook, EXTERNALBOOK$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExternalBook addNewExternalBook() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExternalBook cTExternalBook = null;
            cTExternalBook = (CTExternalBook)((Object)this.get_store().add_element_user(EXTERNALBOOK$0));
            return cTExternalBook;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetExternalBook() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(EXTERNALBOOK$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDdeLink getDdeLink() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDdeLink cTDdeLink = null;
            cTDdeLink = (CTDdeLink)this.get_store().find_element_user(DDELINK$2, 0);
            if (cTDdeLink == null) {
                return null;
            }
            return cTDdeLink;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDdeLink() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DDELINK$2) != 0;
        }
    }

    @Override
    public void setDdeLink(CTDdeLink cTDdeLink) {
        this.generatedSetterHelperImpl((XmlObject)cTDdeLink, DDELINK$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDdeLink addNewDdeLink() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDdeLink cTDdeLink = null;
            cTDdeLink = (CTDdeLink)this.get_store().add_element_user(DDELINK$2);
            return cTDdeLink;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDdeLink() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DDELINK$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOleLink getOleLink() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOleLink cTOleLink = null;
            cTOleLink = (CTOleLink)this.get_store().find_element_user(OLELINK$4, 0);
            if (cTOleLink == null) {
                return null;
            }
            return cTOleLink;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetOleLink() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(OLELINK$4) != 0;
        }
    }

    @Override
    public void setOleLink(CTOleLink cTOleLink) {
        this.generatedSetterHelperImpl((XmlObject)cTOleLink, OLELINK$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOleLink addNewOleLink() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOleLink cTOleLink = null;
            cTOleLink = (CTOleLink)this.get_store().add_element_user(OLELINK$4);
            return cTOleLink;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetOleLink() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(OLELINK$4, 0);
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
            cTExtensionList = (CTExtensionList)this.get_store().find_element_user(EXTLST$6, 0);
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
            return this.get_store().count_elements(EXTLST$6) != 0;
        }
    }

    @Override
    public void setExtLst(CTExtensionList cTExtensionList) {
        this.generatedSetterHelperImpl((XmlObject)cTExtensionList, EXTLST$6, 0, (short)1);
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
            cTExtensionList = (CTExtensionList)this.get_store().add_element_user(EXTLST$6);
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
            this.get_store().remove_element(EXTLST$6, 0);
        }
    }
}

