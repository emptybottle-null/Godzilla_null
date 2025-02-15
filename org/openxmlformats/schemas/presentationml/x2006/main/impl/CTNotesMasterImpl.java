/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionListModify
 */
package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideData;
import org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionListModify;
import org.openxmlformats.schemas.presentationml.x2006.main.CTHeaderFooter;
import org.openxmlformats.schemas.presentationml.x2006.main.CTNotesMaster;

public class CTNotesMasterImpl
extends XmlComplexContentImpl
implements CTNotesMaster {
    private static final long serialVersionUID = 1L;
    private static final QName CSLD$0 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "cSld");
    private static final QName CLRMAP$2 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "clrMap");
    private static final QName HF$4 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "hf");
    private static final QName NOTESSTYLE$6 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "notesStyle");
    private static final QName EXTLST$8 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "extLst");

    public CTNotesMasterImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCommonSlideData getCSld() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCommonSlideData cTCommonSlideData = null;
            cTCommonSlideData = (CTCommonSlideData)((Object)this.get_store().find_element_user(CSLD$0, 0));
            if (cTCommonSlideData == null) {
                return null;
            }
            return cTCommonSlideData;
        }
    }

    @Override
    public void setCSld(CTCommonSlideData cTCommonSlideData) {
        this.generatedSetterHelperImpl(cTCommonSlideData, CSLD$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCommonSlideData addNewCSld() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCommonSlideData cTCommonSlideData = null;
            cTCommonSlideData = (CTCommonSlideData)((Object)this.get_store().add_element_user(CSLD$0));
            return cTCommonSlideData;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTColorMapping getClrMap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTColorMapping cTColorMapping = null;
            cTColorMapping = (CTColorMapping)((Object)this.get_store().find_element_user(CLRMAP$2, 0));
            if (cTColorMapping == null) {
                return null;
            }
            return cTColorMapping;
        }
    }

    @Override
    public void setClrMap(CTColorMapping cTColorMapping) {
        this.generatedSetterHelperImpl(cTColorMapping, CLRMAP$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTColorMapping addNewClrMap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTColorMapping cTColorMapping = null;
            cTColorMapping = (CTColorMapping)((Object)this.get_store().add_element_user(CLRMAP$2));
            return cTColorMapping;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHeaderFooter getHf() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHeaderFooter cTHeaderFooter = null;
            cTHeaderFooter = (CTHeaderFooter)((Object)this.get_store().find_element_user(HF$4, 0));
            if (cTHeaderFooter == null) {
                return null;
            }
            return cTHeaderFooter;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetHf() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(HF$4) != 0;
        }
    }

    @Override
    public void setHf(CTHeaderFooter cTHeaderFooter) {
        this.generatedSetterHelperImpl(cTHeaderFooter, HF$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHeaderFooter addNewHf() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHeaderFooter cTHeaderFooter = null;
            cTHeaderFooter = (CTHeaderFooter)((Object)this.get_store().add_element_user(HF$4));
            return cTHeaderFooter;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetHf() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(HF$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextListStyle getNotesStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextListStyle cTTextListStyle = null;
            cTTextListStyle = (CTTextListStyle)((Object)this.get_store().find_element_user(NOTESSTYLE$6, 0));
            if (cTTextListStyle == null) {
                return null;
            }
            return cTTextListStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetNotesStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(NOTESSTYLE$6) != 0;
        }
    }

    @Override
    public void setNotesStyle(CTTextListStyle cTTextListStyle) {
        this.generatedSetterHelperImpl(cTTextListStyle, NOTESSTYLE$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextListStyle addNewNotesStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextListStyle cTTextListStyle = null;
            cTTextListStyle = (CTTextListStyle)((Object)this.get_store().add_element_user(NOTESSTYLE$6));
            return cTTextListStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetNotesStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(NOTESSTYLE$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExtensionListModify getExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExtensionListModify cTExtensionListModify = null;
            cTExtensionListModify = (CTExtensionListModify)this.get_store().find_element_user(EXTLST$8, 0);
            if (cTExtensionListModify == null) {
                return null;
            }
            return cTExtensionListModify;
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
            return this.get_store().count_elements(EXTLST$8) != 0;
        }
    }

    @Override
    public void setExtLst(CTExtensionListModify cTExtensionListModify) {
        this.generatedSetterHelperImpl((XmlObject)cTExtensionListModify, EXTLST$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExtensionListModify addNewExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExtensionListModify cTExtensionListModify = null;
            cTExtensionListModify = (CTExtensionListModify)this.get_store().add_element_user(EXTLST$8);
            return cTExtensionListModify;
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
            this.get_store().remove_element(EXTLST$8, 0);
        }
    }
}

