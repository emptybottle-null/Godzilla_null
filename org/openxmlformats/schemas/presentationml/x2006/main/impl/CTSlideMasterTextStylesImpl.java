/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionList
 */
package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle;
import org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterTextStyles;

public class CTSlideMasterTextStylesImpl
extends XmlComplexContentImpl
implements CTSlideMasterTextStyles {
    private static final long serialVersionUID = 1L;
    private static final QName TITLESTYLE$0 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "titleStyle");
    private static final QName BODYSTYLE$2 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "bodyStyle");
    private static final QName OTHERSTYLE$4 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "otherStyle");
    private static final QName EXTLST$6 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "extLst");

    public CTSlideMasterTextStylesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextListStyle getTitleStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextListStyle cTTextListStyle = null;
            cTTextListStyle = (CTTextListStyle)((Object)this.get_store().find_element_user(TITLESTYLE$0, 0));
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
    public boolean isSetTitleStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TITLESTYLE$0) != 0;
        }
    }

    @Override
    public void setTitleStyle(CTTextListStyle cTTextListStyle) {
        this.generatedSetterHelperImpl(cTTextListStyle, TITLESTYLE$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextListStyle addNewTitleStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextListStyle cTTextListStyle = null;
            cTTextListStyle = (CTTextListStyle)((Object)this.get_store().add_element_user(TITLESTYLE$0));
            return cTTextListStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTitleStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TITLESTYLE$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextListStyle getBodyStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextListStyle cTTextListStyle = null;
            cTTextListStyle = (CTTextListStyle)((Object)this.get_store().find_element_user(BODYSTYLE$2, 0));
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
    public boolean isSetBodyStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BODYSTYLE$2) != 0;
        }
    }

    @Override
    public void setBodyStyle(CTTextListStyle cTTextListStyle) {
        this.generatedSetterHelperImpl(cTTextListStyle, BODYSTYLE$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextListStyle addNewBodyStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextListStyle cTTextListStyle = null;
            cTTextListStyle = (CTTextListStyle)((Object)this.get_store().add_element_user(BODYSTYLE$2));
            return cTTextListStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBodyStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BODYSTYLE$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextListStyle getOtherStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextListStyle cTTextListStyle = null;
            cTTextListStyle = (CTTextListStyle)((Object)this.get_store().find_element_user(OTHERSTYLE$4, 0));
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
    public boolean isSetOtherStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(OTHERSTYLE$4) != 0;
        }
    }

    @Override
    public void setOtherStyle(CTTextListStyle cTTextListStyle) {
        this.generatedSetterHelperImpl(cTTextListStyle, OTHERSTYLE$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextListStyle addNewOtherStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextListStyle cTTextListStyle = null;
            cTTextListStyle = (CTTextListStyle)((Object)this.get_store().add_element_user(OTHERSTYLE$4));
            return cTTextListStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetOtherStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(OTHERSTYLE$4, 0);
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

